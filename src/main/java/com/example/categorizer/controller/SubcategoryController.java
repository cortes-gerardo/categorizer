package com.example.categorizer.controller;

import com.example.categorizer.model.SubcategoryCategoryWrapper;
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
    ResponseEntity<SubcategoryCategoryWrapper> get() {
        return ResponseEntity.ok(service.wrapper());
    }

    @RequestMapping(value = "/subcategory", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<SubcategoryCategoryWrapper> post(final @RequestBody SubcategoryModel subcategoryModel) {
        service.save(subcategoryModel);
        return ResponseEntity.ok(service.wrapper());
    }

    @RequestMapping(value = "/subcategories", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<SubcategoryCategoryWrapper> post(final @RequestBody List<SubcategoryModel> subcategories) {
        service.save(subcategories.stream());
        return ResponseEntity.ok(service.wrapper());
    }
}
