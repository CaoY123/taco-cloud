package com.mine.cloud.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.mine.cloud.dao.OrderRepository;
import com.mine.cloud.dao.UserRepository;
import com.mine.cloud.domain.Ingredient;
import com.mine.cloud.domain.Order;
import com.mine.cloud.domain.Taco;
import com.mine.cloud.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.Session;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
    public String orderForm(HttpSession session,
                            Principal principal,
                            SessionStatus sessionStatus) {
//        model.addAttribute("order", new Order());
        user = userRepository.findByUsername(principal.getName());
        Order order = (Order) session.getAttribute("order");
        order.setUser(user);
        order.setStreet(user.getStreet());
        order.setCity(user.getCity());
        order.setState(user.getState());
        order.setZip(user.getZip());
        sessionStatus.setComplete();// 清除缓存域

        session.setAttribute("order", order);
        return "orderForm";
//        return "design";

    }

    @PostMapping
    public String processOrder(@Valid Order order,
                               Errors errors,
                               Model model,
                               SessionStatus sessionStatus) {
        if(errors.hasErrors()) {
            return "orderForm";
        }
//        log.info("Order submitted: " + order);
        if (order.getTacos() == null || order.getTacos().size() == 0) {
            Ingredient.Type[] types = Ingredient.Type.values();

            String jsonTacos = order.getJsonTacos();
//            System.out.println("Json串转换后：");
            System.out.println(jsonTacos);
            JSONArray jsonArray = new JSONArray(jsonTacos);
//        System.out.println(jsonArray.toString());
            List<JSONObject> list = JSONUtil.toList(jsonArray, JSONObject.class);
            List<Taco> tacoList = new ArrayList<>();
            for (JSONObject oList : list) {
                Long id = oList.getLong("id");
                Date createdAt = oList.getDate("createdAt");
                String name =  oList.getStr("name");
                JSONArray ingredientsArray = oList.getJSONArray("ingredients");
                List<Ingredient> ingredients = new ArrayList<>();
                for (Object ingredient : ingredientsArray) {
                    Collection<Object> values = ((JSONObject) ingredient).values();
                    Object[] objects = values.toArray();
                    String ingredientId = (String) objects[0];
                    String ingredientName = (String) objects[1];
                    String typeStr = (String) objects[2];
                    Ingredient.Type type = null;
                    for (Ingredient.Type iType : types) {
                        if (iType.toString().equals(typeStr)) {
                            type = iType;
                        }
                    }
                    ingredients.add(new Ingredient(ingredientId, ingredientName, type));
                }
                tacoList.add(new Taco(id, createdAt, name, ingredients));
            }
//            tacoList.forEach(System.out::println);
            order.setTacos(tacoList);
        }
        orderRepository.save(order);
        order.setUser(user);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
