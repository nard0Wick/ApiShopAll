package com.ShopAll.apiShopAll.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Exception {
    private String message;
   // private Throwable cause;
    private String cause;
}
