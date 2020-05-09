package com.example.categorizer.repository;

import com.example.categorizer.entity.Subcategory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Integer> {
    Optional<Subcategory> findByName(final String name);
    Optional<Subcategory> findByNameAndCategoryId(final String name, final Short categoryId);
    List<Subcategory> findAll(final Sort sort);
}
