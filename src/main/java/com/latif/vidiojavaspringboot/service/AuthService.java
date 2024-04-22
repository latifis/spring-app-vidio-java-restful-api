package com.latif.vidiojavaspringboot.service;

import com.latif.vidiojavaspringboot.domain.dto.req.ReqLoginDto;
import com.latif.vidiojavaspringboot.domain.dto.res.ResJwtValidationDto;
import com.latif.vidiojavaspringboot.domain.dto.res.ResLoginDto;

public interface AuthService {

    ResLoginDto login(ReqLoginDto req);

    ResJwtValidationDto validateToken(String token);

}
