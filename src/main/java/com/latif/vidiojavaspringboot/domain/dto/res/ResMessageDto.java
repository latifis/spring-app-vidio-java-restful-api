package com.latif.vidiojavaspringboot.domain.dto.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResMessageDto<T> {

    private int status = 200;

    private String message = "Success";

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

}