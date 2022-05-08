package guru.springframework.controllers;

import guru.springframework.domain.Category;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeService recipeService;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository, RecipeService recipeService) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndex(Model model) {

        Optional<Category> fast_food = categoryRepository.findByName("Fast food");
        Optional<UnitOfMeasure> teaspoon = unitOfMeasureRepository.findByUom("Teaspoon");

        System.out.println("Fast food id: " + fast_food.get().getId());
        System.out.println("Teaspoon id: " + teaspoon.get().getId());

        Recipe guacamole = recipeService.getGuacamole();

        model.addAttribute("description", guacamole.getDescription());
        model.addAttribute("notes", guacamole.getNotes());
        model.addAttribute("ingredients", guacamole.getIngredients());
        model.addAttribute("difficulty", guacamole.getDifficulty());
        model.addAttribute("categories", guacamole.getCategories());

        return "index";
    }
}
