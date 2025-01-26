package com.emmang.product.client;

import com.emmang.product.constant.RestEndpoint;
import com.emmang.product.dto.OrderRequestDto;
import com.emmang.product.dto.OrderResponseAdminDto;
import com.emmang.product.dto.OrderResponseGuestDto;
import com.emmang.product.entity.UserDetailsImpl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderServiceClient {

    private final RestTemplate restTemplate;

    public OrderServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OrderResponseGuestDto fetchOrderGuest(OrderRequestDto orderRequestDto) {
        HttpEntity<OrderRequestDto> requestEntity = new HttpEntity<>(orderRequestDto, getHeaders());
        return restTemplate.exchange(
                RestEndpoint.ORDER_GUEST_ACCESS_ONLY_URL, HttpMethod.POST, requestEntity, OrderResponseGuestDto.class).getBody();
    }

    public OrderResponseAdminDto fetchOrderAdmin(OrderRequestDto orderRequestDto) {
        HttpEntity<OrderRequestDto> requestEntity = new HttpEntity<>(orderRequestDto, getHeaders());
        return restTemplate.exchange(
                RestEndpoint.ORDER_ADMIN_ACCESS_ONLY_URL, HttpMethod.POST, requestEntity, OrderResponseAdminDto.class).getBody();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = ((UserDetailsImpl) authentication.getPrincipal()).getAuthorities().get(0).getAuthority();
        String username = ((UserDetailsImpl) authentication.getPrincipal()).getUsername();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("username", username);
        httpHeaders.add("role", role);
        return httpHeaders;
    }
}
