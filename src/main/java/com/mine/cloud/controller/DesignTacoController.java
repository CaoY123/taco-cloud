package com.mine.cloud.controller;

import com.mine.cloud.domain.Ingredient;
import com.mine.cloud.domain.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import com.mine.cloud.domain.Ingredient.Type;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CaoY
 * @date 2022-12-16 1:31
 * @description 处理配料表请求的控制器
 */
@Slf4j
@Controller
@RequestMapping("/design") // 指定基本的、通用的路径
public class DesignTacoController {

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );

        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(
                    type.toString().toLowerCase(),
                    filterByType(ingredients, type)
            );
        }
    }

//    @RequestMapping(method = RequestMethod.GET) // 另一种通用的写法
    @GetMapping
    public String showDesignForm(Model model) {
        model.addAttribute("design", new Taco());
        // 跳到design.html页面
        return "design";
    }

    @PostMapping
    public String processzDesign(@Valid @ModelAttribute("design") Taco design, Errors errors,Model model) {
        if(errors.hasErrors()) {
            return "design";
        }

        // TODO 保存taco design
        log.info("Processing design: " + design);

        // 重定向到：/order/current
        return "redirect:/orders/current";
    }

    // filter by type（用过类型过滤）
    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

}
