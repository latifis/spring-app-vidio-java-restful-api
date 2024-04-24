package com.latif.vidiojavaspringboot.service.impl;

import com.latif.vidiojavaspringboot.domain.dto.req.ReqLoginDto;
import com.latif.vidiojavaspringboot.domain.dto.req.ReqUserDto;
import com.latif.vidiojavaspringboot.domain.dto.res.*;
import com.latif.vidiojavaspringboot.domain.entity.TypeUserEntity;
import com.latif.vidiojavaspringboot.domain.entity.UserEntity;
import com.latif.vidiojavaspringboot.domain.entity.VidioEntity;
import com.latif.vidiojavaspringboot.exception.AccessDeniedException;
import com.latif.vidiojavaspringboot.exception.DataExistException;
import com.latif.vidiojavaspringboot.exception.DataNotFoundException;
import com.latif.vidiojavaspringboot.repository.UserRepository;
import com.latif.vidiojavaspringboot.service.AuthService;
import com.latif.vidiojavaspringboot.util.GeneralFunction;
import com.latif.vidiojavaspringboot.util.JWTGenerator;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final GeneralFunction generalFunction;

    @Autowired
    public AuthServiceImpl (UserRepository userRepository, GeneralFunction generalFunction){
        this.userRepository = userRepository;
        this.generalFunction = generalFunction;
    }

    @Override
    public ResLoginDto login(ReqLoginDto req) {

        // Check if a user with the given email exists in the database
        UserEntity userExist = userRepository.findByEmail(req.getEmail());
        if (userExist == null) {
            // If user does not exist, throw a DataNotFoundException
            throw new DataNotFoundException("Email does not exist");
        }

        // Create an instance of Argon2 password hashing utility
        Argon2 argon2 = Argon2Factory.create();

        // Verify if the provided password matches the hashed password stored in the database
        boolean matchPassword = argon2.verify(userExist.getPassword(), req.getPassword().toCharArray());
        if (!matchPassword) {
            // If password does not match, throw an AccessDeniedException
            throw new AccessDeniedException("Invalid password");
        }

        // If user exists and password matches, prepare response data for the authenticated user
        ResUserDto userData = new ResUserDto(
                userExist.getId(),
                userExist.getUsername(),
                userExist.getEmail(),
                userExist.getTypeUserEntity().getTypeUser()
        );

        // Generate JWT (JSON Web Token) using user data
        String email = userExist.getEmail();
        String token = new JWTGenerator().createJwt(userData);

        // Return the response containing user's email and JWT token
        return new ResLoginDto(email, token);
    }

    @Override
    public ResJwtValidationDto validateToken(String token) {

        // Decode the JWT (JSON Web Token) using a JWTGenerator instance
        Claims claim = new JWTGenerator().decodeJwt(token);

        // Extract necessary claims (data) from the decoded JWT and prepare a response DTO
        return new ResJwtValidationDto(
                claim.getSubject(),                      // Get the subject (typically the email) from the JWT
                (Integer) claim.get("id"),               // Get the 'id' claim and cast it to Integer
                claim.get("username").toString(),        // Get the 'username' claim as a String
                claim.get("type").toString()             // Get the 'type' claim as a String
        );
    }

    @Override
    public ResUserDto register(ReqUserDto req, String type) {
        try {
            // Check if a user with the given email already exists in the database
            UserEntity userExist = userRepository.findByEmail(req.getEmail());
            if (userExist != null) {
                throw new DataExistException("Email already exists");
            }

            // Determine the user type based on the provided type parameter or default to "T001"
            TypeUserEntity userType = (type == null) ? generalFunction.typeAssign("Free") : generalFunction.typeAssign(type);

            // Create an instance of Argon2 password hashing utility
            Argon2 argon2 = Argon2Factory.create();

            // Hash the password
            String hashedPassword = argon2.hash(10, 65536, 1, req.getPassword().toCharArray());

            // Create a new UserEntity
            UserEntity newUser = new UserEntity();
            newUser.setUsername(req.getUsername());
            newUser.setEmail(req.getEmail());
            newUser.setPassword(hashedPassword);
            newUser.setTypeUserEntity(userType);

            // Save the new user entity
            UserEntity savedUser = userRepository.save(newUser);

            // Convert the savedUser to ResUserDto
            ResUserDto resUserDto = ResUserDto.builder()
                    .id(savedUser.getId())
                    .email(savedUser.getEmail())
                    .build();

            // Return the success response with ResUserDto
            return resUserDto;

        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            throw new AccessDeniedException("Server Error"); // Throw a generic server error
        }
    }

}
