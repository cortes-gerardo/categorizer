package com.example.categorizer.model;

import com.google.common.base.MoreObjects;

import javax.validation.constraints.NotEmpty;

public class CategoryCount extends CategoryModel {
    private Long count;

    protected CategoryCount() {}

    public CategoryCount(@NotEmpty final String category, final Long count) {
        super(category);
        this.count = count;
    }

    public Long getCount() {
        return count;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("category", this.getCategory())
                .add("count", count)
                .toString();
    }
}
