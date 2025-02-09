package com.weildev.dscommerce.services;

import com.weildev.dscommerce.dto.ProductDTO;
import com.weildev.dscommerce.entities.Product;
import com.weildev.dscommerce.repositories.ProductRepository;
import com.weildev.dscommerce.services.exceptions.DatabaseException;
import com.weildev.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product result = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource not found"));
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
        try{
            Product entity = productRepository.getReferenceById(id);
            BeanUtils.copyProperties(productDTO, entity, "id");

            entity = productRepository.save(entity);
            return new ProductDTO(entity);
        }catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Resource not found");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Resource not found");
        }
        try {
            productRepository.deleteById(id);
        }catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Referential integrity failure");
        }
    }
}
