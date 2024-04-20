package com.latif.vidiojavaspringboot.service.impl;

import com.latif.vidiojavaspringboot.domain.dto.req.ReqGenreDto;
import com.latif.vidiojavaspringboot.domain.dto.res.ResGenreDto;
import com.latif.vidiojavaspringboot.domain.dto.res.ResMessageDto;
import com.latif.vidiojavaspringboot.domain.entity.GenreEntity;
import com.latif.vidiojavaspringboot.exception.AccessDeniedException;
import com.latif.vidiojavaspringboot.repository.GenreRepository;
import com.latif.vidiojavaspringboot.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    public final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl (GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    @Override
    public ResMessageDto<String> create(ReqGenreDto req) {
        GenreEntity input = GenreEntity.builder()
                .name(req.getName())
                .build();
        try {
            GenreEntity savedGenre = genreRepository.save(input);

            if (savedGenre.getId() != null && savedGenre.getId() > 0) {
                return new ResMessageDto<>(200, "Genre Added Successfully", null);
            } else {
                return new ResMessageDto<>(500, "Failed to Add Genre", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AccessDeniedException("Server Error");
        }
    }

    @Override
    public ResMessageDto<String> update(int id, ReqGenreDto req) {
        return null;
    }

    @Override
    public ResMessageDto<List<ResGenreDto>> getAll(String token) {
        return null;
    }

    @Override
    public ResMessageDto<ResGenreDto> getById(int id, String token) {
        return null;
    }

    @Override
    public ResMessageDto<String> delete(int id) {
        return null;
    }

}
