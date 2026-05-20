package com.example.api;

import com.example.application.command.CreateProductCommand;
import com.example.application.dto.ProductResultDto;
import com.example.application.usecase.CreateProductUseCase;
import com.example.application.usecase.FindProductByIdUseCase;
import com.example.mapper.ProductControllerMapper;
import com.example.request.CreateProductRequest;
import com.example.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductControllerMapper productControllerMapper;
    @Autowired
    private CreateProductUseCase createProductUseCase;
    @Autowired
    private FindProductByIdUseCase findProductByIdUseCase;

    @PostMapping("/create")
    public ProductResponse create(@RequestBody CreateProductRequest request) {
        CreateProductCommand command = productControllerMapper.toCreateCommand(request);
        ProductResultDto result = createProductUseCase.execute(command);
        return productControllerMapper.toResponse(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById (@PathVariable Long id) {
        ProductResultDto productResultDto = findProductByIdUseCase.execute(id);
        ProductResponse response = productControllerMapper.toResponse(productResultDto);
        return ResponseEntity.ok(response);
    }

}
