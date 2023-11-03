package com.ShopAll.apiShopAll.dto;

import com.ShopAll.apiShopAll.entity.Detail;
import jakarta.persistence.SecondaryTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class ProductDTO{
    private String name;
    private int quantityInStock;
    private double price;
}
