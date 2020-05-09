package com.example.categorizer.controller;

import com.example.categorizer.model.CategoryCount;
import com.example.categorizer.model.SubcategoryCategoryWrapper;
import com.example.categorizer.model.SubcategoryModel;
import com.example.categorizer.service.SubcategoryService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SubcategoryController.class)
class SubcategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubcategoryService mockService;

    private final Gson gson = new Gson();
    private SubcategoryModel stubModel = new SubcategoryModel("TEST", "sample");
    private Collection<SubcategoryModel> stubModels = Arrays.asList(
            new SubcategoryModel("TEST1", "sample1"),
            new SubcategoryModel("TEST2", "sample2")
    );
    private final Collection<CategoryCount> stubCounts = Arrays.asList(
            new CategoryCount("TEST1", 5L),
            new CategoryCount("TEST2", 9L)
    );
    private SubcategoryCategoryWrapper stubWrapper = new SubcategoryCategoryWrapper(stubModels, stubCounts);

    @Test
    void whenGetRequested_thenRespondedWith200AndSubcategories() throws Exception {
        when(mockService.wrapper()).thenReturn(stubWrapper);

        this.mockMvc.perform(
                get("/subcategories")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.subcategories").isArray())
                .andExpect(jsonPath("$.subcategories[0].category", is("TEST1")))
                .andExpect(jsonPath("$.subcategories[0].subcategory", is("sample1")))
                .andExpect(jsonPath("$.subcategories[1].category", is("TEST2")))
                .andExpect(jsonPath("$.subcategories[1].subcategory", is("sample2")))
                .andExpect(jsonPath("$.categories").isArray())
                .andExpect(jsonPath("$.categories[0].category", is("TEST1")))
                .andExpect(jsonPath("$.categories[0].count", is(5)))
                .andExpect(jsonPath("$.categories[1].category", is("TEST2")))
                .andExpect(jsonPath("$.categories[1].count", is(9)))
                .andDo(log());

        verify(mockService, times(1)).wrapper();
    }

    @Test
    void whenPostSubcategory_thenRespondedWith200AndSubcategory() throws Exception {
        when(mockService.save(any(SubcategoryModel.class))).thenReturn(stubModel);
        when(mockService.wrapper()).thenReturn(stubWrapper);

        mockMvc.perform(
                post("/subcategory")
                        .contentType(APPLICATION_JSON)
                        .content(gson.toJson(stubModel))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.subcategories").isArray())
                .andExpect(jsonPath("$.subcategories[0].category", is("TEST1")))
                .andExpect(jsonPath("$.subcategories[0].subcategory", is("sample1")))
                .andExpect(jsonPath("$.subcategories[1].category", is("TEST2")))
                .andExpect(jsonPath("$.subcategories[1].subcategory", is("sample2")))
                .andExpect(jsonPath("$.categories").isArray())
                .andExpect(jsonPath("$.categories[0].category", is("TEST1")))
                .andExpect(jsonPath("$.categories[0].count", is(5)))
                .andExpect(jsonPath("$.categories[1].category", is("TEST2")))
                .andExpect(jsonPath("$.categories[1].count", is(9)))
                .andDo(log());

        verify(mockService, times(1)).save(any(SubcategoryModel.class));
        verify(mockService, times(1)).wrapper();
    }

    @Test
    void whenPostSubcategories_thenRespondedWith200AndSubcategories() throws Exception {
        when(mockService.save(any(Stream.class))).thenReturn(stubModels.stream());
        when(mockService.wrapper()).thenReturn(stubWrapper);

        this.mockMvc.perform(
                post("/subcategories")
                        .contentType(APPLICATION_JSON)
                        .content(gson.toJson(stubModels))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.subcategories").isArray())
                .andExpect(jsonPath("$.subcategories[0].category", is("TEST1")))
                .andExpect(jsonPath("$.subcategories[0].subcategory", is("sample1")))
                .andExpect(jsonPath("$.subcategories[1].category", is("TEST2")))
                .andExpect(jsonPath("$.subcategories[1].subcategory", is("sample2")))
                .andExpect(jsonPath("$.categories").isArray())
                .andExpect(jsonPath("$.categories[0].category", is("TEST1")))
                .andExpect(jsonPath("$.categories[0].count", is(5)))
                .andExpect(jsonPath("$.categories[1].category", is("TEST2")))
                .andExpect(jsonPath("$.categories[1].count", is(9)))
                .andDo(log());

        verify(mockService, times(1)).save(any(Stream.class));
        verify(mockService, times(1)).wrapper();
    }
}