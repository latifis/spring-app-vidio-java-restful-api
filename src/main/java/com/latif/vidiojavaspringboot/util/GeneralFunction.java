package com.latif.vidiojavaspringboot.util;

import com.latif.vidiojavaspringboot.domain.entity.GenreEntity;
import com.latif.vidiojavaspringboot.domain.entity.TypeUserEntity;
import com.latif.vidiojavaspringboot.domain.entity.VidioEntity;
import com.latif.vidiojavaspringboot.exception.AccessDeniedException;
import com.latif.vidiojavaspringboot.exception.DataNotFoundException;
import com.latif.vidiojavaspringboot.repository.GenreRepository;
import com.latif.vidiojavaspringboot.repository.TypeUserRepository;
import com.latif.vidiojavaspringboot.repository.VidioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class GeneralFunction{

    @Autowired
    private TypeUserRepository typeUserRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private VidioRepository vidioRepository;

    public TypeUserEntity typeAssign(String type) {
        TypeUserEntity typeFound = typeUserRepository.findByTypeUser(nullType(type));
        if (typeFound == null) {
            throw new DataNotFoundException("Type not found");
        }
        return typeFound; // Assuming 'getTypeId' returns a String. Make sure this is non-null or handled if it can be.
    }

    public String nullType(String type) {
        if (type.isEmpty()) {
            return "Free";
        } else {
            return type;
        }
    }

    public Set<GenreEntity> genreSearch(Set<Integer> genreIds) {
        Set<GenreEntity> genreEntities = new HashSet<>();
        for (Integer genreId : genreIds) {
            Optional<GenreEntity> optionalEntity = genreRepository.findById(genreId);
            if (optionalEntity.isPresent()) {
                GenreEntity entity = optionalEntity.get();
                genreEntities.add(entity);
            } else {
                throw new DataNotFoundException("Genre with ID " + genreId + " not found");
            }
        }
        return genreEntities;
    }

    public List<String> encodeGenre(List<GenreEntity> genres) {
        List<String> genreNames = new ArrayList<>();
        for (GenreEntity genre : genres) {
            Optional<GenreEntity> optionalEntity = genreRepository.findById(genre.getId());
            if (optionalEntity.isPresent()) {
                GenreEntity entity = optionalEntity.get();
                genreNames.add(entity.getName());
            } else {
                throw new DataNotFoundException("Genre with ID " + genre.getId() + " not found");
            }
        }
        return genreNames;
    }

    public List<VidioEntity> gameSearch(List<Integer> gameIds, String type) {
        List<VidioEntity> gameEntities = new ArrayList<>();
        for (Integer gameId : gameIds) {
            Optional<VidioEntity> optionalEntity = vidioRepository.findById(gameId);
            if (optionalEntity.isPresent()) {
                VidioEntity entity = optionalEntity.get();
                if ("T002".equals(type) || entity.getType().getId().equals(type)) {
                    gameEntities.add(entity);
                } else {
                    throw new AccessDeniedException("No Access To This Game");
                }
            } else {
                throw new DataNotFoundException("Game with ID " + gameId + " not found");
            }
        }
        return gameEntities;
    }

    public List<String> encodeGame(List<VidioEntity> games) {
        List<String> gameNames = new ArrayList<>();
        for (VidioEntity game : games) {
            Optional<VidioEntity> optionalEntity = vidioRepository.findById(game.getId());
            if (optionalEntity.isPresent()) {
                VidioEntity entity = optionalEntity.get();
                gameNames.add(entity.getName());
            } else {
                throw new DataNotFoundException("Vidio with ID " + game.getId() + " not found");
            }
        }
        return gameNames;
    }

}