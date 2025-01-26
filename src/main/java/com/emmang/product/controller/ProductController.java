package com.emmang.product.controller;

import com.emmang.product.dto.*;
import com.emmang.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/product")
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/guest-access-only")
    public ResponseEntity<ProductResponseGuestDto> guestAccess(@RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.ok(productService.getProductDetailsGuest(productRequestDto));
    }

    @PostMapping("/admin-access-only")
    public ResponseEntity<ProductResponseAdminDto> adminAccess(@RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.ok(productService.getProductDetailsAdmin(productRequestDto));
    }

    @PostMapping("/admin-access-call-guest-access")
    public ResponseEntity<String> adminToGuestAccess(@RequestBody OrderRequestDto orderRequestDto) {
        String res = productService.getModifiedOrderQty(orderRequestDto);
        return ResponseEntity.ok("Modified qty: " + res);
    }

    @PostMapping("/admin-access-call-admin-access")
    public ResponseEntity<String> adminToAdminAccess(@RequestBody OrderRequestDto orderRequestDto) {
        String res = productService.getModifiedOrderName(orderRequestDto);
        return ResponseEntity.ok("Modified name: " + res);
    }
}