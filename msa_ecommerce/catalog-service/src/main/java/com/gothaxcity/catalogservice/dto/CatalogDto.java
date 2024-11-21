package com.gothaxcity.catalogservice.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CatalogDto implements Serializable {

    private String productId;
    private Integer unitPrice;
    private Integer quantity;
    private Integer totalPrice;

    private String orderId;
    private String userId;
}
