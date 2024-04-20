package com.latif.vidiojavaspringboot.service.impl;

import com.latif.vidiojavaspringboot.domain.dto.req.ReqVidioDto;
import com.latif.vidiojavaspringboot.domain.dto.res.ResMessageDto;
import com.latif.vidiojavaspringboot.domain.dto.res.ResVidioDto;
import com.latif.vidiojavaspringboot.domain.entity.VidioEntity;
import com.latif.vidiojavaspringboot.exception.AccessDeniedException;
import com.latif.vidiojavaspringboot.repository.TypeUserRepository;
import com.latif.vidiojavaspringboot.repository.VidioRepository;
import com.latif.vidiojavaspringboot.service.VidioService;
import com.latif.vidiojavaspringboot.util.GeneralFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VidioServiceImpl implements VidioService {

    private final GeneralFunction generalFunction;

    private final VidioRepository vidioRepository;

    private final TypeUserRepository typeUserRepository;

    @Autowired
    public VidioServiceImpl(VidioRepository vidioRepository, TypeUserRepository typeUserRepository, GeneralFunction generalFunction) {
        this.vidioRepository = vidioRepository;
        this.typeUserRepository = typeUserRepository;
        this.generalFunction = generalFunction;
    }

    @Override
    public ResMessageDto<String> create(ReqVidioDto req) {
        VidioEntity input = VidioEntity.builder()
                .type(generalFunction.typeAssign(req.getType()))
                .name(req.getName())
                .genreEntities(generalFunction.genreSearch(req.getGenre()))
                .build();
        try{
            Integer id = 0;
            VidioEntity data = vidioRepository.save(input);
            id = data.getId();

            String message = id == 0 ? "Genre Added Failed" : "Genre Added Success";
            return new ResMessageDto<>(
                    200, message,null
            );
        }catch (Exception e){
            e.printStackTrace();
            throw new AccessDeniedException("Server Error");
        }
    }

    @Override
    public ResMessageDto<String> update(int id, ReqVidioDto req) {
        return null;
    }

    @Override
    public ResMessageDto<List<ResVidioDto>> getAll(String token) {
        return null;
    }

    @Override
    public ResMessageDto<ResVidioDto> getById(int id, String token) {
        return null;
    }

    @Override
    public ResMessageDto<String> delete(int id) {
        return null;
    }
}
