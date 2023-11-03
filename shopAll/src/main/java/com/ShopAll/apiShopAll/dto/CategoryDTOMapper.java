package com.ShopAll.apiShopAll.dto;

import com.ShopAll.apiShopAll.entity.Category;

import java.util.function.Function;

public class CategoryDTOMapper implements Function<CategoryDTO, Category> {
    @Override
    public Category apply(CategoryDTO categoryDTO) {
        return null;/*new Category(categoryDTO.getCategoryName(),
                categoryDTO.getDescription());*/
    }
}
