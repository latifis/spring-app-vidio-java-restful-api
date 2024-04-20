package com.latif.vidiojavaspringboot.controller;

import com.latif.vidiojavaspringboot.domain.dto.req.ReqVidioDto;
import com.latif.vidiojavaspringboot.domain.dto.res.ResMessageDto;
import com.latif.vidiojavaspringboot.service.VidioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/vidio")
public class VidioController {

    private final VidioService vidioService;

    @Autowired
    public VidioController(VidioService vidioService){
        this.vidioService = vidioService;
    }

    @PostMapping
    public ResponseEntity<ResMessageDto<String>> createVidio(
            @Valid
            @RequestBody ReqVidioDto req
    ) {
        return ResponseEntity.ok(vidioService.create(req));
    }

}
