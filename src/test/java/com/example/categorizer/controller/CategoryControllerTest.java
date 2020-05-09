package com.example.categorizer.controller;

import com.example.categorizer.model.CategoryCount;
import com.example.categorizer.model.CategoryModel;
import com.example.categorizer.service.CategoryService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService mockService;

    private final Gson gson = new Gson();
    private final CategoryModel stubModel = new CategoryModel("TEST");
    private final Collection<CategoryModel> stubModels = Arrays.asList(
            new CategoryModel("TEST1"),
            new CategoryModel("TEST2")
    );
    private final Collection<CategoryCount> stubCounts = Arrays.asList(
            new CategoryCount("TEST1", 5L),
            new CategoryCount("TEST2", 9L)
    );

    @Test
    void whenGetRequested_thenRespondedWith200AndCategories() throws Exception {
        when(mockService.list()).thenReturn(stubCounts.stream());

        this.mockMvc.perform(
                get("/categories")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].category", is("TEST1")))
                .andExpect(jsonPath("$[0].count", is(5)))
                .andExpect(jsonPath("$[1].category", is("TEST2")))
                .andExpect(jsonPath("$[1].count", is(9)))
                .andDo(log());

        verify(mockService, times(1)).list();
    }

    @Test
    void whenPostCategory_thenRespondedWith200AndCategory() throws Exception {
        when(mockService.save(any(CategoryModel.class))).thenReturn(stubModel);

        mockMvc.perform(
                post("/category")
                        .contentType(APPLICATION_JSON)
                        .content(gson.toJson(stubModel))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.category", is("TEST")))
                .andDo(log());

        verify(mockService, times(1)).save(any(CategoryModel.class));
    }

    @Test
    void whenPostCategories_thenRespondedWith200AndCategories() throws Exception {
        when(mockService.save(any(Stream.class))).thenReturn(stubModels.stream());

        mockMvc.perform(
                post("/categories")
                        .contentType(APPLICATION_JSON)
                        .content(gson.toJson(stubModels))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].category", is("TEST1")))
                .andExpect(jsonPath("$[1].category", is("TEST2")))
                .andDo(log());

        verify(mockService, times(1)).save(any(Stream.class));
    }

    @Test
    void whenDeleteCategory_thenRespondedWith200() throws Exception {
        mockMvc.perform(
                delete("/category")
                        .contentType(APPLICATION_JSON)
                        .content(gson.toJson(stubModel))
        )
                .andExpect(status().isOk())
                .andDo(log());

        verify(mockService, times(1)).delete(any(CategoryModel.class));
    }

    @Test
    void whenDeleteCategories_thenRespondedWith200() throws Exception {
        mockMvc.perform(
                delete("/categories")
                        .contentType(APPLICATION_JSON)
                        .content(gson.toJson(stubModels))
        )
                .andExpect(status().isOk())
                .andDo(log());

        verify(mockService, times(1)).delete(any(Stream.class));    }
}