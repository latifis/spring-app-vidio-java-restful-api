package com.latif.vidiojavaspringboot.controller;

import com.latif.vidiojavaspringboot.domain.dto.req.ReqGenreDto;
import com.latif.vidiojavaspringboot.domain.dto.res.ResMessageDto;
import com.latif.vidiojavaspringboot.service.GenreService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/genre")
public class GenreController {

    private final GenreService genreService;

    public GenreController (GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping
    public ResponseEntity<ResMessageDto<String>> createGenre(
            @Valid
            @RequestBody ReqGenreDto req
    ) {
        return ResponseEntity.ok(genreService.create(req));
    }

}
