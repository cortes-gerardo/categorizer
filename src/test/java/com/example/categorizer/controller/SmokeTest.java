package com.example.categorizer.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private CategoryController categoryController;
    @Autowired
    private SubcategoryController subcategoryController;

    @Test
    public void contexLoads() throws Exception {
        assertThat(categoryController).isNotNull();
        assertThat(subcategoryController).isNotNull();
    }
}
