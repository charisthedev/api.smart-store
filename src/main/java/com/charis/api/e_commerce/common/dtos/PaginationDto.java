package com.charis.api.e_commerce.common.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper = true)
public class PaginationDto extends BasePaginationDto {
    private Integer page = 1;

    public Pageable getPageable(PaginationDto dto){
        Integer page = Objects.nonNull(dto.getPage())?dto.getPage():this.page;
        Integer limit = Objects.nonNull(dto.getLimit())?dto.getLimit():this.getLimit();
        Sort.Direction sort = Objects.nonNull(dto.getSort())?dto.getSort():this.getSort();
        String sortBy = Objects.nonNull(dto.getSortBy())?dto.getSortBy():this.getSortBy();

        return PageRequest.of(page,limit,sort,sortBy);
    }
}
