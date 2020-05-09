package com.example.categorizer.model;

import com.example.categorizer.entity.Subcategory;
import com.google.common.base.MoreObjects;

import javax.validation.constraints.NotEmpty;

public class SubcategoryModel extends CategoryModel{
    @NotEmpty
    private String subcategory;

    public SubcategoryModel() {
        super();
        this.subcategory = "";
    }

    public static SubcategoryModel of (final Subcategory entity) {
        return new SubcategoryModel(entity.getCategory().getName(), entity.getName());
    }

    public SubcategoryModel(@NotEmpty String category, @NotEmpty String subcategory) {
        super(category);
        this.subcategory = subcategory;
    }

    public String getSubcategory() {
        return subcategory;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("category", this.getCategory())
                .add("subcategory", subcategory)
                .toString();
    }
}
