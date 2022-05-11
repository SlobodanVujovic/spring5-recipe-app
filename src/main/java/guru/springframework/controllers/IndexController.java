package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {
    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndex(Model model) {
        log.debug("Get index page.");

        Recipe guacamole = recipeService.getGuacamole();

        model.addAttribute("description", guacamole.getDescription());
        model.addAttribute("notes", guacamole.getNotes());
        model.addAttribute("ingredients", guacamole.getIngredients());
        model.addAttribute("difficulty", guacamole.getDifficulty());
        model.addAttribute("categories", guacamole.getCategories());

        return "index";
    }
}
