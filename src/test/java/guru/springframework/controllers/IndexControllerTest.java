package guru.springframework.controllers;

import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {
    @Mock
    private RecipeService recipeService;
    @Mock
    Model model;

    private IndexController indexController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void getIndex() {
        String expected = "index";
        Recipe guacamole = Recipe.builder()
                .description("Description")
                .notes(new Notes())
                .ingredients(new HashSet<>())
                .difficulty(Difficulty.EASY)
                .categories(new HashSet<>())
                .build();
        when(recipeService.getGuacamole()).thenReturn(guacamole);

        assertEquals(expected, indexController.getIndex(model));
        verify(recipeService, times(1)).getGuacamole();
        verify(model, times(5)).addAttribute(any(), any());
    }

//    @Test
//    public void testMockMvc() throws Exception {
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("index"));
//    }
}