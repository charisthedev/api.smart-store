package com.charis.api.e_commerce.common.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class KeySetPaginationDto extends BasePaginationDto {
    private UUID cursor;

    public Integer getLimitSafe(KeySetPaginationDto dto){
        return Objects.nonNull(dto.getLimit())?dto.getLimit():this.getLimit();
    }
}
