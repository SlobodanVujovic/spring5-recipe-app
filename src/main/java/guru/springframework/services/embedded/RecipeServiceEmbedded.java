package guru.springframework.services.embedded;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.RecipeService;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceEmbedded implements RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeServiceEmbedded(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Recipe getGuacamole() {
        return recipeRepository.findById(1L).get();
    }
}
