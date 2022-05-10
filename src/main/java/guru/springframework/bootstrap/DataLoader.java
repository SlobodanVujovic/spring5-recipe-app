package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final CategoryRepository categoryRepository;

    private final RecipeRepository recipeRepository;

    public DataLoader(UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Recipe guacamole = new Recipe();
        guacamole.setDescription("The best guacamole");
        guacamole.setPrepTime(2);
        guacamole.setCookTime(1);
        guacamole.setServings(3);
        guacamole.setSource("My mom");
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setDirections("Hop pa cup");

        Notes notes = new Notes();
        notes.setDescription("Kuvaj, mesaj, sipaj, jedi.");
        guacamole.setNotes(notes);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setAmount(new BigDecimal(4));
        ingredient1.setDescription("Luk");
        ingredient1.setRecipe(guacamole);

        Optional<UnitOfMeasure> teaspoon = unitOfMeasureRepository.findByUom("Teaspoon");

        ingredient1.setUnitOfMeasure(teaspoon.get());

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setAmount(new BigDecimal(1));
        ingredient2.setDescription("Sargarepa");
        ingredient2.setRecipe(guacamole);

        Optional<UnitOfMeasure> ripe = unitOfMeasureRepository.findByUom("Ripe");

        ingredient2.setUnitOfMeasure(ripe.get());

        guacamole.getIngredients().add(ingredient1);
        guacamole.getIngredients().add(ingredient2);

        guacamole.setDifficulty(Difficulty.MODERATE);

        Optional<Category> mexican = categoryRepository.findByName("Mexican");

        guacamole.getCategories().add(mexican.get());

        recipeRepository.save(guacamole);

//        mexican.get().getRecipes().add(guacamole);


        log.debug("************************ Done");
    }
}
