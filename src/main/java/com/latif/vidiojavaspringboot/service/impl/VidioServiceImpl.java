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

import java.time.LocalDate;
import java.util.ArrayList;
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
                .creator(req.getCreator())
                .genreEntities(generalFunction.genreSearch(req.getGenre()))
                .added(LocalDate.now())
                .updated(LocalDate.now())
                .build();
        try{
            Integer id = 0;
            VidioEntity data = vidioRepository.save(input);
            id = data.getId();

            String message = id == 0 ? "Vidio Added Failed" : "Vidio Added Success";
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
        // Decode JWT token to get type information (assuming JwtGenerator is used for decoding)
        // Claims claim = new JwtGenerator().decodeJwt(token);

        // Retrieve typeEntity based on type from JWT claim
        // String typeId = claim.get("type").toString();
        // TypeEntity typeEntity = typeRepo.findById(typeId).orElse(null);

        // Check if typeEntity is found
        // if (typeEntity == null) {
        //    throw new SomethingWrongException("Type not found in JWT claim");
        // }

        // Fetch list of games based on typeEntity
        // if (typeEntity.getName().equals("Free")) {
        //    listData = repo.findByType(typeEntity);
        // } else {
        //    listData = repo.findAll();
        // }

        // Get Data
        List<VidioEntity> listData = vidioRepository.findAll();
        List<ResVidioDto> listDto = new ArrayList<>();

        // Convert VidioEntity list to ResVidioDto list
        for (VidioEntity data : listData) {
            ResVidioDto dto = ResVidioDto.builder()
                    .id(data.getId())
                    .name(data.getName())
                    .creator(data.getCreator())
                    .type(data.getType().getTypeUser())
                    .genres(generalFunction.encodeGenre(data.getGenreEntities()))
                    .added(data.getAdded())
                    .update(data.getUpdated())
                    .build();
            listDto.add(dto);
        }

        // Return success response with list of ResGameDto
        return new ResMessageDto<>(200, "Successfully retrieved all games", listDto);
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
