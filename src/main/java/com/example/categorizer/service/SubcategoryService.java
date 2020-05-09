package com.example.categorizer.service;

import com.example.categorizer.entity.Subcategory;
import com.example.categorizer.model.SubcategoryModel;
import com.example.categorizer.repository.SubcategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
public class SubcategoryService extends AbstractService {
    private static final Logger log = LoggerFactory.getLogger(SubcategoryService.class);

    private final SubcategoryRepository repository;
    private final CategoryService categoryService;

    @Autowired
    public SubcategoryService(final SubcategoryRepository repository, final CategoryService categoryService) {
        this.repository = repository;
        this.categoryService = categoryService;
    }

    public Stream<SubcategoryModel> list() {
        return findAll().stream().map(SubcategoryModel::of);
    }

    public SubcategoryModel save(final SubcategoryModel model) {
        return find(model)
                .map(SubcategoryModel::of)
                .orElseGet(() -> categoryService.find(model)
                        .map(category -> SubcategoryModel.of(save(Subcategory.of(model, category))))
                        .orElseThrow() //FIXME
                );
    }

    public Stream<SubcategoryModel> save(final Stream<SubcategoryModel> models) {
        List<Subcategory> validSubcategories = models
                .filter(model -> find(model).isEmpty())
                .map(model -> categoryService.find(model)
                        .map(category -> Subcategory.of(model, category)))
                .flatMap(Optional::stream)
                .collect(toList());

        return saveAll(validSubcategories).stream()
                .map(SubcategoryModel::of);
    }

    private List<Subcategory> findAll() {
        return tryOrThrow(() -> repository.findAll(Sort.by("name").ascending()), "findAll");
    }

    private Subcategory save(final Subcategory validSubcategory) {
        return tryOrThrow(() -> repository.save(validSubcategory), "save");
    }

    private List<Subcategory> saveAll(final List<Subcategory> validSubcategories) {
        return tryOrThrow(() -> repository.saveAll(validSubcategories), "saveAll");
    }

    private Optional<Subcategory> find(SubcategoryModel model) {
        return tryOrThrow(() -> categoryService.find(model)
                .flatMap(category -> repository.findByNameAndCategoryId(model.getSubcategory(), category.getId())),
                "findByNameAndCategoryId");
    }

    @Override
    protected Logger log() {
        return log;
    }
}
