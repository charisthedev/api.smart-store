package com.charis.api.e_commerce.common.dtos;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class BasePaginationDto {
    private Integer limit = 10;
    private Sort.Direction sort = Sort.Direction.ASC;
    private String sortBy = "createdAt";
}
