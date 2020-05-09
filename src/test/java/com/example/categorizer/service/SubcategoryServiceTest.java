package com.example.categorizer.service;

import com.example.categorizer.entity.Category;
import com.example.categorizer.entity.Subcategory;
import com.example.categorizer.model.CategoryModel;
import com.example.categorizer.model.SubcategoryModel;
import com.example.categorizer.repository.SubcategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class SubcategoryServiceTest {

    @Mock
    private SubcategoryRepository mockRepository;
    @Mock
    private CategoryService mockCategoryService;

    @InjectMocks
    private SubcategoryService service;

    @Test
    void whenList_thenReturnStream() {
        Sort sort = Sort.by("name").ascending();
        when(mockRepository.findAll(sort))
                .thenReturn(singletonList(new Subcategory("sample", new Category("TEST"))));

        Stream<SubcategoryModel> list = service.list();
        List<SubcategoryModel> actual = list.collect(toList());
        assertThat(actual.isEmpty()).isFalse();
        assertThat(actual).contains(new SubcategoryModel("TEST","sample"));

        verify(mockRepository, times(1)).findAll(sort);
    }

    @Test
    void whenSaveCategory_thenReturnSubcategoryModel() {
        when(mockCategoryService.find(any(CategoryModel.class)))
                .thenReturn(Optional.of(new Category("TEST")));
        when(mockRepository.save(any(Subcategory.class)))
                .thenReturn(new Subcategory("sample", new Category("TEST")));

        SubcategoryModel actual = service.save(new SubcategoryModel("TEST", "sample"));
        assertThat(actual).isNotNull();
        assertThat(actual.getSubcategory()).isEqualTo("sample");

        verify(mockCategoryService, times(2)).find(any(CategoryModel.class));
        verify(mockRepository, times(1)).save(any(Subcategory.class));
    }

    @Test
    void whenSaveStream_thenReturnStream() {
        when(mockCategoryService.find(any(CategoryModel.class)))
                .thenReturn(Optional.of(new Category("TEST")));
        when(mockRepository.saveAll(anyList()))
                .thenReturn(singletonList(new Subcategory("sample", new Category("TEST"))));

        Stream<SubcategoryModel> stream = service.save(Stream.of(new SubcategoryModel("TEST", "sample")));
        List<SubcategoryModel> actual = stream.collect(toList());
        assertThat(actual.isEmpty()).isFalse();
        assertThat(actual).contains(new SubcategoryModel("TEST","sample"));

        verify(mockCategoryService, times(2)).find(any(CategoryModel.class));
        verify(mockRepository, times(1)).saveAll(anyList());
    }
}