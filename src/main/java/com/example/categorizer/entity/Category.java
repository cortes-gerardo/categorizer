package com.example.categorizer.entity;

import com.example.categorizer.model.CategoryModel;
import com.google.common.base.Objects;

import javax.persistence.*;

import java.util.Set;

import static com.google.common.base.MoreObjects.toStringHelper;

@Entity
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

    public Category(final Short id, final String name) {
        this.id = id;
        this.name = name;
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
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        final Category category = (Category) o;
        return Objects.equal(id, category.id) &&
                Objects.equal(name, category.name) &&
                Objects.equal(subcategories, category.subcategories);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, subcategories);
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .toString();
    }
}
