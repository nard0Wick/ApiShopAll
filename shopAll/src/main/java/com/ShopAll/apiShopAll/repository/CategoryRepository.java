package com.ShopAll.apiShopAll.repository;

import com.ShopAll.apiShopAll.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    /*Category findByName(String name);*/
}
