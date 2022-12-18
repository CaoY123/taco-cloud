package com.mine.cloud.controller;

import com.mine.cloud.dao.OrderRepository;
import com.mine.cloud.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

/**
 * @author CaoY
 * @date 2022-12-17 17:28
 * @description 处理订单的控制器
 */
@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes({"order"})
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/current")
    public String orderForm() {
//        model.addAttribute("order", new Order());
        return "orderForm";
//        return "design";
    }

    @PostMapping
    public String processOrder(@Valid Order order,
                               Errors errors,
                               SessionStatus sessionStatus) {
        if(errors.hasErrors()) {
            return "orderForm";
        }

//        log.info("Order submitted: " + order);
        orderRepository.save(order);

        sessionStatus.setComplete();

        return "redirect:/";
    }
}
