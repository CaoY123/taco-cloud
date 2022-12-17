package com.mine.cloud.controller;

import com.mine.cloud.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @author CaoY
 * @date 2022-12-17 17:28
 * @description 处理订单的控制器
 */
@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());
        return "orderForm";
//        return "design";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors) {
        if(errors.hasErrors()) {
            return "orderForm";
        }

        log.info("Order submitted: " + order);
        return "redirect:/";
    }
}
