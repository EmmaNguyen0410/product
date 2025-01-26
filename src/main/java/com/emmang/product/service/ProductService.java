package com.emmang.product.service;

import com.emmang.product.client.OrderServiceClient;
import com.emmang.product.dto.*;
import com.emmang.product.exception.RestTemplateFailureException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

@Service
public class ProductService {
    private final OrderServiceClient orderServiceClient;

    public ProductService(OrderServiceClient orderServiceClient) {
        this.orderServiceClient = orderServiceClient;
    }

    public ProductResponseAdminDto getProductDetailsAdmin(ProductRequestDto productRequestDto) {
        ProductResponseAdminDto response = new ProductResponseAdminDto();
        response.setProductId(productRequestDto.getProductId());
        response.setName("Cup");
        response.setPrice(20);
        return response;
    }

    public ProductResponseGuestDto getProductDetailsGuest(ProductRequestDto productRequestDto) {
        ProductResponseGuestDto response = new ProductResponseGuestDto();
        response.setProductStar(20);
        return response;
    }

    public String getModifiedOrderName(OrderRequestDto orderRequestDto) {
        try {
            return orderServiceClient.fetchOrderAdmin(orderRequestDto).getName() + "1";
        } catch (HttpStatusCodeException e) {
            throw new RestTemplateFailureException(e.getMessage());
        }
    }

    public String getModifiedOrderQty(OrderRequestDto orderRequestDto) {
        try {
            return orderServiceClient.fetchOrderGuest(orderRequestDto).getQty() + 1 + "";
        } catch (HttpStatusCodeException e) {
            throw new RestTemplateFailureException(e.getMessage());
        }
    }
}
