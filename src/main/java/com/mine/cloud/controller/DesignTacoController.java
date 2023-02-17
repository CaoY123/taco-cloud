package com.mine.cloud.controller;

import cn.hutool.json.JSONUtil;
import com.mine.cloud.dao.IngredientRepository;
import com.mine.cloud.dao.TacoRepository;
import com.mine.cloud.domain.Ingredient;
import com.mine.cloud.domain.Order;
import com.mine.cloud.domain.Taco;
import com.mine.cloud.domain.TacoForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import com.mine.cloud.domain.Ingredient.Type;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author CaoY
 * @date 2022-12-16 1:31
 * @description 处理配料表请求的控制器
 */
@Slf4j
@Controller
@RequestMapping("/design") // 指定基本的、通用的路径
@SessionAttributes({"order", "ingredients"})
public class DesignTacoController {
    // 配料表持久化
    private final IngredientRepository ingredientRepository;

    // Taco表持久化(以及伴随Taco持久化的Taco_ingredients表持久化)
    private TacoRepository tacoRepository;

    @Autowired
    private HttpSession session;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository,
                                TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

//    @RequestMapping(method = RequestMethod.GET) // 另一种通用的写法
    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        model.addAttribute("ingredients", ingredients);

        Taco taco = new Taco();
        List<String> ingredientNames = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            ingredientNames.add(ingredient.getName());
        }
//        taco.setIngredients(ingredients);
        model.addAttribute("design", taco);
        // 跳到design.html页面
        return "design";
    }

    @PostMapping
    public String processDesign(
            @Valid TacoForm designForm,
            Errors errors,
            @ModelAttribute Order order,
            Model model,
            SessionStatus sessionStatus) {
        if(errors.hasErrors()) {
            return "design";
        }

        List<Ingredient> ingredients = (List<Ingredient>) model.getAttribute("ingredients");
        List<Ingredient> tacoIngredients = new ArrayList<>();
        // 将配料表单类（TacoForm类）进行转化为Taco类
        List<String> ingredientNames = designForm.getIngredients();
        for (String ingredientName : ingredientNames) {
            for (Ingredient ingredient : ingredients) {
                if (ingredient.getId().equals(ingredientName)) {
                    tacoIngredients.add(ingredient);
                }
            }
        }

//        Taco design = new Taco(
//                designForm.getId(),
//                new Date(),
//                designForm.getName()
////                tacoIngredients
//        );
        Taco design = null;

        Taco saved = tacoRepository.save(design);
        order.addDesign(saved);

//        sessionStatus.setComplete();
        session.setAttribute("order", order);
        model.addAttribute("order", order);

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
