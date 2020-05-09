package com.example.categorizer.model;

public class SubcategoryCategoryWrapper {
    private Iterable<SubcategoryModel> subcategories;
    private Iterable<CategoryCount> categories;

    protected SubcategoryCategoryWrapper() {
    }

    public SubcategoryCategoryWrapper(final Iterable<SubcategoryModel> subcategories, final Iterable<CategoryCount> categories) {
        this.subcategories = subcategories;
        this.categories = categories;
    }

    public Iterable<SubcategoryModel> getSubcategories() {
        return subcategories;
    }

    public Iterable<CategoryCount> getCategories() {
        return categories;
    }
}
