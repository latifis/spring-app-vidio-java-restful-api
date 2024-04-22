package com.latif.vidiojavaspringboot.domain.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResUserDto {

    private int id;

    private String username;

    private String email;

    private String type;

}
