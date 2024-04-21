package com.latif.vidiojavaspringboot.domain.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ReqVidioDto {

    @NotBlank(message = "Name cannot be blank")
    @NotNull(message = "Name cannot be null")
    private String name;

    @NotBlank(message = "Type cannot be blank")
    @NotNull(message = "Type cannot be null")
    private String type;

    @NotBlank(message = "Creator cannot be blank")
    @NotNull(message = "Creator cannot be null")
    private String creator;

    @NotEmpty(message = "Genre list cannot be empty")
    @NotNull(message = "Genre list cannot be null")
    private List<Integer> genre;

}
