package com.gothaxcity.catalogservice.controller;

import com.gothaxcity.catalogservice.entity.CatalogEntity;
import com.gothaxcity.catalogservice.service.CatalogService;
import com.gothaxcity.catalogservice.vo.ResponseCatalog;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.*;
import static org.modelmapper.convention.MatchingStrategies.STRICT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/catalog-service")
@RequiredArgsConstructor(access = PROTECTED)
public class CatalogController {

    private final Environment env;
    private final CatalogService catalogService;

    @GetMapping("/health_check")
    public String health_check() {
        return String.format("It's Working in Catalog Service on PORT %s",
                             env.getProperty("local.server.port"));
    }

    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getCatalogs() {
        Iterable<CatalogEntity> allCatalogs = catalogService.getAllCatalogs();

        List<ResponseCatalog> result = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(STRICT);

        allCatalogs.forEach(catalogEntity -> {
            result.add(mapper.map(catalogEntity, ResponseCatalog.class));
        });

        return ResponseEntity.status(OK).body(result);
    }

}
