package com.example.categorizer.repository;

import com.example.categorizer.entity.Category;
import com.example.categorizer.entity.Subcategory;
import com.example.categorizer.model.CategoryCount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CategoryRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategoryRepository repository;

    @Test
    void whenFindByName_thenReturnCategory() {
        final Category stubPerson = new Category("PERSON");
        entityManager.persist(stubPerson);
        entityManager.flush();

        Optional<Category> actual = repository.findByName("PERSON");

        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get()).isEqualTo(stubPerson);
        assertThat(actual.get().getName()).isEqualTo("PERSON");
    }

    @Test
    void whenFindAllByNameIn_thenReturnListOfCategories() {
        final Category stubAnimal = new Category("ANIMAL");
        final Category stubPlace = new Category("PLACE");
        entityManager.persist(stubAnimal);
        entityManager.persist(stubPlace);
        entityManager.flush();
        List<String> names = Arrays.asList("ANIMAL", "PLACE");

        List<Category> actual = repository.findAllByNameIn(names);

        assertThat(actual.isEmpty()).isFalse();
        assertThat(actual).contains(
                new Category((short) 2, "ANIMAL"),
                new Category((short) 3, "PLACE"));
    }

    @Test
    void queryCategoryCount_thenReturnZeroCounts() {
        final Category other = entityManager.persist(new Category("OTHER"));
        entityManager.persist(new Subcategory("Tree", other));
        entityManager.flush();

        List<CategoryCount> actual = repository.queryCategoryCount();

        assertThat(actual.isEmpty()).isFalse();
        assertThat(actual).contains(new CategoryCount("OTHER", 1L));
    }
}