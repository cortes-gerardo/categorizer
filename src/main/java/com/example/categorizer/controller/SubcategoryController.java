package com.example.categorizer.controller;

import com.example.categorizer.model.SubcategoryModel;
import com.example.categorizer.service.SubcategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class SubcategoryController {
    private static final Logger log = LoggerFactory.getLogger(SubcategoryController.class);

    private final SubcategoryService service;

    @Autowired
    public SubcategoryController(final SubcategoryService service) {
        this.service = service;
    }

    @RequestMapping(value = "/subcategories", method = GET, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Iterable<SubcategoryModel>> get() {
        return ResponseEntity.ok(service.list().collect(toList()));
    }

    @RequestMapping(value = "/subcategory", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<SubcategoryModel> post(final @RequestBody SubcategoryModel subcategoryModel) {
        return ResponseEntity.ok(service.save(subcategoryModel));
    }

    @RequestMapping(value = "/subcategories", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<SubcategoryModel>> post(final @RequestBody List<SubcategoryModel> subcategories) {
        return ResponseEntity.ok(service.save(subcategories.stream()).collect(toList()));
    }
}
