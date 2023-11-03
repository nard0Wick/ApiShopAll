package com.ShopAll.apiShopAll.repository;

import com.ShopAll.apiShopAll.entity.Product;
import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    Boolean existsByName(String name);
    Optional<Product> findByName(String name);
    void deleteByName(String name);
    //List<Product> findAllByName(String name);
    /**
     * @Query(value = "insert into Logger (redirect,user_id) VALUES (:insertLink,:id)", nativeQuery = true)
     * @Transactional
     * void logURI(@Param("insertLink") String insertLink, @Param("id") Long id);
     */

    /*@Query(value = "insert into shop_all.stocktaking(user_id, product_id) values(:user_id,:product_id)", nativeQuery = true)
    @Transactional
    void insertInStock(Long user_id, Long product_id);*/

}
