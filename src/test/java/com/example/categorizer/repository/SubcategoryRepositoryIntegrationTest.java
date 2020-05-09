package com.example.categorizer.repository;

import com.example.categorizer.entity.Category;
import com.example.categorizer.entity.Subcategory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class SubcategoryRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SubcategoryRepository repository;

    @Test
    void findByNameAndCategoryId() {
        final Category other = entityManager.persist(new Category("FOOD"));
        entityManager.persist(new Subcategory("Steak", other));
        entityManager.flush();

        Optional<Subcategory> actual = repository.findByNameAndCategoryId("Steak", other.getId());

        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get().getName()).isEqualTo("Steak");
        assertThat(actual.get().getCategory().getName()).isEqualTo("FOOD");
    }
}