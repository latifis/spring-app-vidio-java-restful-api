package com.latif.vidiojavaspringboot.service;

import com.latif.vidiojavaspringboot.domain.dto.req.ReqLoginDto;
import com.latif.vidiojavaspringboot.domain.dto.req.ReqUserDto;
import com.latif.vidiojavaspringboot.domain.dto.res.ResJwtValidationDto;
import com.latif.vidiojavaspringboot.domain.dto.res.ResLoginDto;
import com.latif.vidiojavaspringboot.domain.dto.res.ResUserDto;

public interface AuthService {

    ResLoginDto login(ReqLoginDto req);

    ResJwtValidationDto validateToken(String token);

    ResUserDto register(ReqUserDto req, String type);

}
