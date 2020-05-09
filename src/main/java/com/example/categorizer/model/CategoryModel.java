package com.example.categorizer.model;

import com.example.categorizer.entity.Category;
import com.google.common.base.MoreObjects;

import javax.validation.constraints.NotEmpty;

public class CategoryModel {
    @NotEmpty
    private String category;

    public static CategoryModel of(Category entity) {
        return new CategoryModel(entity.getName());
    }

    protected CategoryModel() {
        //FIXME
        this.category = "";
    }
    public CategoryModel(@NotEmpty String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("category", category)
                .toString();
    }
}
