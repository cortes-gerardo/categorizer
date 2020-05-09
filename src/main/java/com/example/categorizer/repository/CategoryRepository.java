package com.example.categorizer.repository;

import com.example.categorizer.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Short> {
    Optional<Category> findByName(final String name);
    List<Category> findAllByNameIn(final List<String> names);
}
