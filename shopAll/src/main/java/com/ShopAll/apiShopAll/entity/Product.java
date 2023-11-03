package com.ShopAll.apiShopAll.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    public Product(String name, int quantityInStock, double price) {
        this.name = name;
        this.quantityInStock = quantityInStock;
        this.price = price;
        //this.detail = detail;
        //this.categorySet = categorySet;
    }

    @JsonIgnore
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private  String name;

    @Column(name = "quantity_in_stock")
    private int quantityInStock;

    @Column(name = "unit_price")
    private double price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id")
    private Detail detail;

    /*@OneToMany(cascade = CascadeType.ALL)
    //@JsonManagedReference
    private List<Opinion> opinions;*/
    //@JsonIgnore

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "products_categories",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    )
    //@JsonManagedReference
    private Set<Category> categorySet;

    //@JsonIgnore

    @ManyToMany(mappedBy = "productSet", fetch = FetchType.LAZY)
    @JsonBackReference
    @Null
    private Set<User> users;
}
