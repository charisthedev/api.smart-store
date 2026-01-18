package com.charis.api.e_commerce.order.mappers;

import com.charis.api.e_commerce.order.domain.OrderItem;
import com.charis.api.e_commerce.order.domain.Orders;
import com.charis.api.e_commerce.order.dtos.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "unitPrice", target = "price")
    OrderResponse.OrderItemResponse toOrderItemResponse(OrderItem item);

    OrderResponse toOrderResponse(Orders order);
}
