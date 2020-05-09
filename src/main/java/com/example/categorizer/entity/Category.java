package com.example.categorizer.entity;

import com.example.categorizer.model.CategoryModel;

import javax.persistence.*;

import java.util.Set;

import static com.google.common.base.MoreObjects.toStringHelper;

@Entity
@Table(indexes = {@Index(columnList = "name")})
public class Category {
    @Id
    @GeneratedValue
    private Short id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Subcategory> subcategories;

    protected Category() {
    }

    public static Category of(final CategoryModel model) {
        return new Category(model.getCategory());
    }

    public Category(final String name) {
        this.name = name;
    }

    public Short getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Subcategory> getSubcategories() {
        return subcategories;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .toString();
    }
}
