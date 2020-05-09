package com.example.categorizer.entity;

import com.example.categorizer.model.SubcategoryModel;
import com.google.common.base.MoreObjects;

import javax.persistence.*;

@Entity
@Table(indexes = {@Index(columnList = "name")})
public class Subcategory {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    protected Subcategory() {

    }

    public static Subcategory of(final SubcategoryModel model, final Category category) {
        return new Subcategory(model.getSubcategory(), category);
    }

    public Subcategory(final String name, final Category category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("category", category)
                .toString();
    }
}
