package com.example.api;

import com.example.application.command.product.CreateProductCommand;
import com.example.application.dto.product.ProductResultDto;
import com.example.application.service.product.CreateProductUseCase;
import com.example.application.service.product.FindProductByIdUseCase;
import com.example.mapper.ProductControllerMapper;
import com.example.request.CreateProductRequest;
import com.example.response.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {


    private final ProductControllerMapper productControllerMapper;
    private final CreateProductUseCase createProductUseCase;
    private final FindProductByIdUseCase findProductByIdUseCase;

    public ProductController(ProductControllerMapper productControllerMapper,
                             CreateProductUseCase createProductUseCase,
                             FindProductByIdUseCase findProductByIdUseCase) {
        this.productControllerMapper = productControllerMapper;
        this.createProductUseCase = createProductUseCase;
        this.findProductByIdUseCase = findProductByIdUseCase;
    }

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
