package com.example.categorizer.controller;

import com.example.categorizer.model.CategoryCount;
import com.example.categorizer.model.CategoryModel;
import com.example.categorizer.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
public class CategoryController {
    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

    private CategoryService service;

    @Autowired
    public CategoryController(final CategoryService service) {
        this.service = service;
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<Iterable<CategoryCount>> get() {
        return ResponseEntity.ok(service.list().collect(toList()));
    }

    @RequestMapping(value = "/category", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody
    ResponseEntity<CategoryModel> post(final @RequestBody CategoryModel category) {
        return ResponseEntity.ok(service.save(category));
    }

    @RequestMapping(value = "/categories", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody
    ResponseEntity<List<CategoryModel>> post(final @RequestBody List<CategoryModel> categories) {
        return ResponseEntity.ok(service.save(categories.stream()).collect(toList()));
    }

    @RequestMapping(value = "/category", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
    public void delete(final @RequestBody CategoryModel category) {
        service.delete(category);
    }

    @RequestMapping(value = "/categories", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
    public void delete(final @RequestBody List<CategoryModel> categories) {
        service.delete(categories.stream());
    }
}
