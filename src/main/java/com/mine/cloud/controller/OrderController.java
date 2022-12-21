package com.mine.cloud.controller;

import com.mine.cloud.dao.OrderRepository;
import com.mine.cloud.dao.UserRepository;
import com.mine.cloud.domain.Order;
import com.mine.cloud.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import javax.websocket.Session;
import java.security.Principal;

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

    @Autowired
    private UserRepository userRepository;

    private User user;

    @GetMapping("/current")
    public String orderForm(Model model,
                            Principal principal,
                            SessionStatus sessionStatus) {
//        model.addAttribute("order", new Order());
        user = userRepository.findByUsername(principal.getName());
        Order order = (Order) model.getAttribute("order");
        order.setUser(user);
        order.setStreet(user.getStreet());
        order.setCity(user.getCity());
        order.setState(user.getState());
        order.setZip(user.getZip());
        sessionStatus.setComplete();// 清除缓存域

        model.addAttribute("order", order);
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
        order.setUser(user);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
