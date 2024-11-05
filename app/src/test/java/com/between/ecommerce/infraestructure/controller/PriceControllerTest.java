package com.between.ecommerce.infraestructure.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getTest1() throws Exception { //Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
        String date = "2020-06-14T10:00:00.00Z";
        String productId = "35455";
        String brandId = "1";
        mvc.perform(MockMvcRequestBuilders.get("/price/").accept(MediaType.APPLICATION_JSON).param("date", date).param("product_id",productId).param("brand_id",brandId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.product_id").value(35455))
                .andExpect(jsonPath("$.brand_id").value(1))
                .andExpect(jsonPath("$.price_list").value(1))
                .andExpect(jsonPath("$.start_date").value("2020-06-14T00:00:00Z"))
                .andExpect(jsonPath("$.end_date").value("2020-12-31T23:59:59Z"))
                .andExpect(jsonPath("$.final_price").value(35.5d));
    }


    @Test
    public void getTest2() throws Exception { //Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
        String date = "2020-06-14T16:00:00.00Z";
        String productId = "35455";
        String brandId = "1";
        mvc.perform(MockMvcRequestBuilders.get("/price/").accept(MediaType.APPLICATION_JSON).param("date", date).param("product_id",productId).param("brand_id",brandId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.product_id").value(35455))
                .andExpect(jsonPath("$.brand_id").value(1))
                .andExpect(jsonPath("$.price_list").value(2))
                .andExpect(jsonPath("$.start_date").value("2020-06-14T15:00:00Z"))
                .andExpect(jsonPath("$.end_date").value("2020-06-14T18:30:00Z"))
                .andExpect(jsonPath("$.final_price").value(25.45d));
    }

    @Test
    public void getTest3() throws Exception { //Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
        String date = "2020-06-14T21:00:00.00Z";
        String productId = "35455";
        String brandId = "1";
        mvc.perform(MockMvcRequestBuilders.get("/price/").accept(MediaType.APPLICATION_JSON).param("date", date).param("product_id",productId).param("brand_id",brandId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.product_id").value(35455))
                .andExpect(jsonPath("$.brand_id").value(1))
                .andExpect(jsonPath("$.price_list").value(1))
                .andExpect(jsonPath("$.start_date").value("2020-06-14T00:00:00Z"))
                .andExpect(jsonPath("$.end_date").value("2020-12-31T23:59:59Z"))
                .andExpect(jsonPath("$.final_price").value(35.5d));
    }

    @Test
    public void getTest4() throws Exception { //Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
        String date = "2020-06-15T10:00:00.00Z";
        String productId = "35455";
        String brandId = "1";
        mvc.perform(MockMvcRequestBuilders.get("/price/").accept(MediaType.APPLICATION_JSON).param("date", date).param("product_id",productId).param("brand_id",brandId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.product_id").value(35455))
                .andExpect(jsonPath("$.brand_id").value(1))
                .andExpect(jsonPath("$.price_list").value(3))
                .andExpect(jsonPath("$.start_date").value("2020-06-15T00:00:00Z"))
                .andExpect(jsonPath("$.end_date").value("2020-06-15T11:00:00Z"))
                .andExpect(jsonPath("$.final_price").value(30.5d));
    }

    @Test
    public void getTest5() throws Exception { //Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
        String date = "2020-06-16T21:00:00.00Z";
        String productId = "35455";
        String brandId = "1";
        mvc.perform(MockMvcRequestBuilders.get("/price/").accept(MediaType.APPLICATION_JSON).param("date", date).param("product_id",productId).param("brand_id",brandId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.product_id").value(35455))
                .andExpect(jsonPath("$.brand_id").value(1))
                .andExpect(jsonPath("$.price_list").value(4))
                .andExpect(jsonPath("$.start_date").value("2020-06-15T16:00:00Z"))
                .andExpect(jsonPath("$.end_date").value("2020-12-31T23:59:59Z"))
                .andExpect(jsonPath("$.final_price").value(38.95d));
    }

    @Test
    public void notFound() throws Exception { //Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
        String date = "2020-06-16T21:00:00.00Z";
        String productId = "35451";
        String brandId = "1";
        mvc.perform(MockMvcRequestBuilders.get("/price/").accept(MediaType.APPLICATION_JSON).param("date", date).param("product_id",productId).param("brand_id",brandId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void badRequest() throws Exception { //Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
        String date = "2020-06-16T21:00:00.00Z";
        String productId = "35451";
        mvc.perform(MockMvcRequestBuilders.get("/price/").accept(MediaType.APPLICATION_JSON).param("date", date).param("product_id",productId))
                .andExpect(status().isBadRequest());
    }

}