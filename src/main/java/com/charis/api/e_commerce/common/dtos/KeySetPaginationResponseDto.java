package com.charis.api.e_commerce.common.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class KeySetPaginationResponseDto<T> {
    private List<T> data;
    private UUID nextCursor;
    private Boolean hasNext;
}
