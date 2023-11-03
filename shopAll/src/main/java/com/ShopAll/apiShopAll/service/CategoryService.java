package com.ShopAll.apiShopAll.service;

import com.ShopAll.apiShopAll.entity.Category;
import com.ShopAll.apiShopAll.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category postCategory(Category category){return categoryRepository.save(category);}
    /*public Category getCategory(String name){return categoryRepository.findByName(name);}*/

    public Category putCategory(Long id, Category newCategory){
        Category category = categoryRepository.findById(id).orElse(null);
        if(category!= null){
            category.setName(newCategory.getName());
            category.setDescription(newCategory.getDescription());
        }
        return category;
    }

    public void deleteCategory(Long id){categoryRepository.deleteById(id);}

}
