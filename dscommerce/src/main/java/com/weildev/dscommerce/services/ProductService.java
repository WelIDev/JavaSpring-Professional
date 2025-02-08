package com.weildev.dscommerce.services;

import com.weildev.dscommerce.dto.ProductDTO;
import com.weildev.dscommerce.entities.Product;
import com.weildev.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product result = productRepository.findById(id).get();
        return new ProductDTO(result);
    }
}
