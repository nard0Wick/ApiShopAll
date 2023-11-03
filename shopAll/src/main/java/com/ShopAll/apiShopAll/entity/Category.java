package com.ShopAll.apiShopAll.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {
    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @JsonIgnore
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String name;

    @Column(name = "description")
    private String description;

    /*@ManyToOne(cascade = CascadeType.ALL)
    //@JsonBackReference
    private Product product;*/
    //@JsonIgnore
    @ManyToMany(mappedBy = "categorySet", fetch = FetchType.LAZY)
    @JsonBackReference
    @Null
    private Set<Product> products;

}
