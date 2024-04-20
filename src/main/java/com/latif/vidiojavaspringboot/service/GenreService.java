package com.latif.vidiojavaspringboot.service;

import com.latif.vidiojavaspringboot.domain.dto.req.ReqGenreDto;
import com.latif.vidiojavaspringboot.domain.dto.res.ResMessageDto;
import com.latif.vidiojavaspringboot.domain.dto.res.ResGenreDto;

import java.util.List;

public interface GenreService {

    ResMessageDto<String> create(ReqGenreDto req);

    ResMessageDto<String> update(int id, ReqGenreDto req);

    ResMessageDto<List<ResGenreDto>> getAll(String token);

    ResMessageDto<ResGenreDto> getById(int id, String token);

    ResMessageDto<String> delete(int id);

}
