package com.emmang.product.controller;

import com.emmang.product.dto.OrderRequestDto;
import com.emmang.product.dto.ProductRequestDto;
import com.emmang.product.dto.ProductResponseAdminDto;
import com.emmang.product.dto.ProductResponseGuestDto;
import com.emmang.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/product")
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/guest-access-only")
    public ResponseEntity<ProductResponseGuestDto> guestAccess(@RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductDetailsGuest(productRequestDto));
    }

    @PostMapping("/admin-access-only")
    public ResponseEntity<ProductResponseAdminDto> adminAccess(@RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductDetailsAdmin(productRequestDto));
    }

    @PostMapping("/admin-access-call-admin-access")
    public ResponseEntity<String> adminToAdminAccess(@RequestBody OrderRequestDto orderRequestDto) {
        String modifiedOrderName = productService.getModifiedOrderName(orderRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Admin access calls admin access with modified order name: " + modifiedOrderName);
    }

    @PostMapping("/admin-access-call-guest-access")
    public ResponseEntity<String> adminToGuestAccess(@RequestBody OrderRequestDto orderRequestDto) {
        String modifiedOrderQty = productService.getModifiedOrderQty(orderRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Admin access calls guest access with modified order qty: " + modifiedOrderQty);
    }
}