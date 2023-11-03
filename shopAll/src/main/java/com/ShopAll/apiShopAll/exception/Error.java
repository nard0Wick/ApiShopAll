package com.ShopAll.apiShopAll.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class Error {
    private String message;
    private List<String> causes;
    /*private Throwable throwable;
    private HttpStatus httpStatus;
    private ZonedDateTime zonedDateTime;*/

}
