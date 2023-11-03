package com.ShopAll.apiShopAll.dto;

import com.ShopAll.apiShopAll.entity.Detail;
import com.ShopAll.apiShopAll.entity.Product;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.function.Function;

public class ProductDTOMapper implements Function<ProductDTO, Product> {
    @Override
    public Product apply(ProductDTO productDTO) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return null;
        /*Product(
                productDTO.getName(),
                productDTO.getQuantityInStock(),
                productDTO.getPrice(),
                productDTO.getDetailDTO(),
                mapper.convertValue(productDTO.getDetailDTO(), Detail.class),
                productDTO.getCategoryDTOList().
                        stream().map(CategoryDTO::
                        )).collect(Collectors.toSet());*/
    }
}
