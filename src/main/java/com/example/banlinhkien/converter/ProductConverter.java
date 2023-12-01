package com.example.banlinhkien.converter;


import com.example.banlinhkien.entity.Product;
import com.example.banlinhkien.models.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    private ModelMapper modelMapper;

    public ProductConverter() {
        modelMapper = new ModelMapper();
    }

    public ProductDTO toDTO(Product product) {
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);

        return productDTO;
    }

    public Product toEntity(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);

        return product;

    }
}
