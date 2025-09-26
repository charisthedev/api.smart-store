package com.charis.api.e_commerce.common.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PaginationSearchDto extends PaginationDto {
    private String search;
}
