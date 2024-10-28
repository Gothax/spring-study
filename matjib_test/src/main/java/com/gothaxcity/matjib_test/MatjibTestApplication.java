package com.gothaxcity.matjib_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class MatjibTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MatjibTestApplication.class, args);
        NaverApiSearch naverApiSearch = new NaverApiSearch();

        naverApiSearch.search("홍대맛집");
    }

}
