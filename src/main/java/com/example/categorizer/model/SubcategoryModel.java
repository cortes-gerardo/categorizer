package com.example.categorizer.model;

import com.example.categorizer.entity.Subcategory;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.validation.constraints.NotEmpty;

public class SubcategoryModel extends CategoryModel{
    @NotEmpty
    private String subcategory;

    protected SubcategoryModel() {
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
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof SubcategoryModel)) return false;
        final SubcategoryModel that = (SubcategoryModel) o;
        return Objects.equal(this.getCategory(), that.getCategory()) && Objects.equal(subcategory, that.subcategory);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getSubcategory(), subcategory);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("category", this.getCategory())
                .add("subcategory", subcategory)
                .toString();
    }
}
