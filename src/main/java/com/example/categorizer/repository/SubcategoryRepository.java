package com.example.categorizer.repository;

import com.example.categorizer.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Integer> {
    Optional<Subcategory> findByNameAndCategoryId(final String name, final Short categoryId);
}
