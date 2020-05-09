package com.example.categorizer.service;

import com.example.categorizer.entity.Category;
import com.example.categorizer.model.CategoryCount;
import com.example.categorizer.model.CategoryModel;
import com.example.categorizer.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
public class CategoryService extends AbstractService {
    private static final Logger log = LoggerFactory.getLogger(CategoryService.class);

    private CategoryRepository repository;

    @Autowired
    public CategoryService(final CategoryRepository repository) {
        this.repository = repository;
    }

    public Stream<CategoryCount> list() {
        return queryCount().stream();
    }

    public CategoryModel save(final CategoryModel model) {
        return find(model)
                .map(CategoryModel::of)
                .orElseGet(() -> CategoryModel.of(save(Category.of(model))));
    }

    public Stream<CategoryModel> save(final Stream<CategoryModel> models) {
        List<Category> validCategories = models
                .filter(model -> find(model).isEmpty())
                .map(Category::of)
                .collect(toList());

        return saveAll(validCategories).stream()
                .map(CategoryModel::of);
    }

    public void delete(final CategoryModel model) {
        find(model).ifPresent(category -> delete(category));
    }

    public void delete(final Stream<CategoryModel> models) {
        List<Category> categories = findAllByNameIn(models.map(CategoryModel::getCategory).collect(toList()));
        deleteAll(categories);
    }

    private List<Category> findAllByNameIn(final List<String> names) {
        return tryOrThrow(() -> repository.findAllByNameIn(names), "findAllByNameIn");
    }

    private List<CategoryCount> queryCount() {
        return tryOrThrow(repository::queryCategoryCount, "queryCount");
    }

    private Category save(final Category category) {
        return tryOrThrow(() -> repository.save(category), "save");
    }

    private List<Category> saveAll(final List<Category> validCategories) {
        return tryOrThrow(() -> repository.saveAll(validCategories), "saveAll");
    }

    private void delete(final Category category) {
        tryOrThrow(() -> {
            repository.delete(category);
            return null;
        }, "delete");
    }

    private void deleteAll(final List<Category> categories) {
        tryOrThrow(() -> {
            repository.deleteAll(categories);
            return null;
        }, "deleteAll");
    }

    protected Optional<Category> find(final CategoryModel category) {
        return tryOrThrow(() -> repository.findByName(category.getCategory()), "findByName");
    }

    @Override
    protected Logger log() {
        return log;
    }
}
