package com.example.categorizer.model;

import com.example.categorizer.entity.Category;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.validation.constraints.NotEmpty;

public class CategoryModel {
    @NotEmpty
    private String category;

    protected CategoryModel() {
    }

    public static CategoryModel of(final Category entity) {
        return new CategoryModel(entity.getName());
    }

    public CategoryModel(@NotEmpty String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryModel)) return false;
        final CategoryModel that = (CategoryModel) o;
        return Objects.equal(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(category);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("category", category)
                .toString();
    }
}
