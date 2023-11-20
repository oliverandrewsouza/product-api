package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.ProductDTO;
import org.acme.entity.ProductEntity;
import org.acme.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductService {
    @Inject
    ProductRepository productRepository;

    public List<ProductDTO> getAllProducts(){
        List<ProductDTO> products = new ArrayList<>();
        productRepository.findAll().stream().forEach(item->{
            products.add(mapProductEntityToDTO(item));
        });

        return products;
    }

    public void createNewProduct(ProductDTO product){
        productRepository.persist(mapProductDTOToEntity(product));

    }

    public void changeProduct(Long id, ProductDTO product){
        ProductEntity productEntity = productRepository.findById(id);

        product.setName(product.getName());
        product.setDescription(product.getDescription());
        product.setCategory(product.getCategory());
        product.setModel(product.getModel());
        product.setPrice(product.getPrice());

        productRepository.persist(productEntity);

    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
}

    private ProductDTO mapProductEntityToDTO(ProductEntity productEntity){

        ProductDTO product = new ProductDTO();

        product.setName(productEntity.getName());
        product.setDescription(productEntity.getDescription());
        product.setCategory(productEntity.getCategory());
        product.setModel(productEntity.getModel());
        product.setPrice(productEntity.getPrice());

        return product;


    }

    private ProductEntity mapProductDTOToEntity(ProductDTO productDto){

            ProductEntity product = new ProductEntity();

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setCategory(productDto.getCategory());
        product.setModel(productDto.getModel());
        product.setPrice(productDto.getPrice());

        return product;


    }
}

