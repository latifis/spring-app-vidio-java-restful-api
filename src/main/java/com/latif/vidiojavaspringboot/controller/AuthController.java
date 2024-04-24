package com.latif.vidiojavaspringboot.controller;

import com.latif.vidiojavaspringboot.domain.dto.req.ReqLoginDto;
import com.latif.vidiojavaspringboot.domain.dto.req.ReqUserDto;
import com.latif.vidiojavaspringboot.domain.dto.res.ResLoginDto;
import com.latif.vidiojavaspringboot.domain.dto.res.ResMessageDto;
import com.latif.vidiojavaspringboot.domain.dto.res.ResUserDto;
import com.latif.vidiojavaspringboot.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResMessageDto<ResLoginDto>> login(
            @RequestBody
            @Valid
            ReqLoginDto req
    ) {
        ResLoginDto res = authService.login(req);
        return ResponseEntity.ok(new ResMessageDto<>(200, "Success Login", res));
    }

    @PostMapping("/register")
    public ResponseEntity<ResMessageDto<ResUserDto>> register(
            @RequestBody
            @Valid
            ReqUserDto req,
            @RequestParam
            String type
    ) {
        ResUserDto res = authService.register(req, type);
        return ResponseEntity.ok(new ResMessageDto<>(200, "Success Login", res));
    }

//    @GetMapping("/validate")
//    public ResponseEntity<ResMessageDto<ResJwtValidationDto>> validate(@RequestHeader String token) {
//        ResJwtValidationDto res = validateTokenService.validateToken(token);
//        return ResponseEntity.ok(new ResMessageDto<>(200, "Success Decode Jwt", res));
//    }

}
