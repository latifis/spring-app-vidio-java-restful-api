package com.latif.vidiojavaspringboot.domain.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResVidioDto {

    private Integer id;

    private String name;

    private String type;

    private String creator;

    private List<String> genres;

    private LocalDate added;

    private LocalDate update;

}
