package com.example.categorizer.repository;

import com.example.categorizer.entity.Category;
import com.example.categorizer.model.CategoryCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Short> {
    Optional<Category> findByName(final String name);
    List<Category> findAllByNameIn(final List<String> names);

    @Query("SELECT new com.example.categorizer.model.CategoryCount(c.name, count(s.id)) FROM Category AS c LEFT OUTER JOIN Subcategory AS s on c.id = s.category.id GROUP BY c.name")
    List<CategoryCount> queryCategoryCount();
}
