package guru.springframework.services.embedded;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {
    @Mock
    private RecipeRepository recipeRepository;
    private RecipeServiceImpl recipeServiceImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        recipeServiceImpl = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getGuacamole() {
        Recipe seedData = new Recipe();
        seedData.setDescription("Guacamole");
        when(recipeRepository.findById(any()))
                .thenReturn(Optional.of(seedData));

        Recipe resultData = recipeServiceImpl.getGuacamole();

        assertEquals(seedData.getDescription(), resultData.getDescription());
        verify(recipeRepository, times(1)).findById(any());
    }
}
