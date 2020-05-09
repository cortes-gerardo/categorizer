package com.example.categorizer.service;

import com.example.categorizer.entity.Category;
import com.example.categorizer.model.CategoryCount;
import com.example.categorizer.model.CategoryModel;
import com.example.categorizer.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository mockRepository;

    @InjectMocks
    private CategoryService service;

    @Test
    void whenList_thenReturnStream() {
        CategoryCount stubCount = new CategoryCount("TEST", 1L);
        when(mockRepository.queryCategoryCount())
                .thenReturn(singletonList(stubCount));

        Stream<CategoryCount> list = service.list();
        List<CategoryCount> actual = list.collect(toList());
        assertThat(actual.isEmpty()).isFalse();
        assertThat(actual).contains(stubCount);

        verify(mockRepository, times(1)).queryCategoryCount();
    }

    @Test
    void whenSaveCategory_thenReturnCategoryModel() {
        when(mockRepository.save(any(Category.class)))
                .thenReturn(new Category("TEST"));

        CategoryModel actual = service.save(new CategoryModel("TEST"));
        assertThat(actual).isNotNull();
        assertThat(actual.getCategory()).isEqualTo("TEST");

        verify(mockRepository, times(1)).save(any(Category.class));
    }

    @Test
    void whenSaveStream_thenReturnStream() {
        when(mockRepository.saveAll(anyList()))
                .thenReturn(asList(new Category("TEST1"), new Category("TEST2")));
        when(mockRepository.findByName(anyString()))
                .thenReturn(Optional.of(new Category("TEST")));

        Stream<CategoryModel> modelStream = Stream.of(new CategoryModel("TEST1"), new CategoryModel("TEST2"));
        List<CategoryModel> actual = service.save(modelStream).collect(toList());
        assertThat(actual.isEmpty()).isFalse();
        assertThat(actual).contains(new CategoryModel("TEST1"), new CategoryModel("TEST2"));

        verify(mockRepository, times(2)).findByName(anyString());
        verify(mockRepository, times(1)).saveAll(anyList());
    }

    @Test
    void whenDeleteCategory_thenDelete() {
        when(mockRepository.findByName(anyString()))
                .thenReturn(Optional.of(new Category("TEST")));

        service.delete(new CategoryModel("TEST"));

        verify(mockRepository, times(1)).findByName(anyString());
        verify(mockRepository, times(1)).delete(any(Category.class));
    }

    @Test
    void whenDeleteStream_thenDeleteAll() {
        when(mockRepository.findAllByNameIn(anyList()))
                .thenReturn(singletonList(new Category("TEST")));

        service.delete(Stream.of(new CategoryModel("TEST")));

        verify(mockRepository, times(1)).findAllByNameIn(anyList());
        verify(mockRepository, times(1)).deleteAll(anyList());
    }
}