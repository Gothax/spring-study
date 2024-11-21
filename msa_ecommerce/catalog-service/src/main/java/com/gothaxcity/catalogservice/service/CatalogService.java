package com.gothaxcity.catalogservice.service;

import com.gothaxcity.catalogservice.entity.CatalogEntity;

public interface CatalogService {
    Iterable<CatalogEntity> getAllCatalogs();
    CatalogEntity getCatalogById(String productId);
}
