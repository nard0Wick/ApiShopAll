package com.ShopAll.apiShopAll.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Table(name = "details")
@Getter
@Setter
public class Detail {
    @JsonIgnore
    @Id()
    @Column(name = "detais_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "short_description")
    private String description;

    @Column(name = "explanation")
    private String explanation;
}
