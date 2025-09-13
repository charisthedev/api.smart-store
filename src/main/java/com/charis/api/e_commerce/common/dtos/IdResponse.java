package com.charis.api.e_commerce.common.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class IdResponse {
    private UUID id;
}
