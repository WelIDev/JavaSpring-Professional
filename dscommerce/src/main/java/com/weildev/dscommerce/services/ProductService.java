package com.weildev.dscommerce.services;

import com.weildev.dscommerce.dto.ProductDTO;
import com.weildev.dscommerce.entities.Product;
import com.weildev.dscommerce.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product result = productRepository.findById(id).get();
        return new ProductDTO(result);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> result = productRepository.findAll(pageable);
        return result.map(ProductDTO::new);
    }

    @Transactional
    public ProductDTO insert(ProductDTO productDTO) {
        Product entity = new Product();
        BeanUtils.copyProperties(productDTO, entity);

        entity = productRepository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO) {
        Product entity = productRepository.getReferenceById(id);
        BeanUtils.copyProperties(productDTO, entity, "id");

        entity = productRepository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
