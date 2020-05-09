package com.example.categorizer;

import com.example.categorizer.entity.Category;
import com.example.categorizer.model.CategoryModel;
import com.example.categorizer.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Initializer implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(Initializer.class);

    @Value("#{'${initialize.categories}'.split(',')}")
    private List<String> categories;

    @Autowired
    private CategoryService service;

    @Override
    public void run(String... args) throws Exception {
        categories.stream()
                .map(CategoryModel::new)
                .peek(service::save)
                .forEach(c -> logger.info("Category inserted:  " + c));
    }
}
