package com.latif.vidiojavaspringboot.service;

import com.latif.vidiojavaspringboot.domain.dto.req.ReqVidioDto;
import com.latif.vidiojavaspringboot.domain.dto.res.ResMessageDto;
import com.latif.vidiojavaspringboot.domain.dto.res.ResVidioDto;

import java.util.List;

public interface VidioService {

    ResMessageDto<String> create(ReqVidioDto req);

    ResMessageDto<String> update(int id, ReqVidioDto req);

    ResMessageDto<List<ResVidioDto>> getAll(String token);

    ResMessageDto<ResVidioDto> getById(int id, String token);

    ResMessageDto<String> delete(int id);

}
