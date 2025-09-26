package com.charis.api.e_commerce.common.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Sort;

@Data
@EqualsAndHashCode(callSuper = true)
public class PaginationDto extends BasePaginationDto {
    private Integer page = 1;
}
