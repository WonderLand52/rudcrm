package com.rudenkoinc.crm.app.controller;

import com.rudenkoinc.crm.app.model.Order;
import com.rudenkoinc.crm.app.service.OrderService;
import com.rudenkoinc.crm.app.shared.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static com.rudenkoinc.crm.app.shared.dto.OrderDTO.mapFromOrderEntity;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST)
    public OrderDTO saveOrder(Principal principal, @RequestBody OrderDTO orderDTO) {

        Order order = orderService.saveOrder(principal.getName(),
                orderDTO.getId(),
                orderDTO.getTypeOfPaper(),
                orderDTO.getSubject(),
                orderDTO.getStyle(),
                orderDTO.getTopic(),
                orderDTO.getOrderInstructions(),
                orderDTO.getSources());

        return mapFromOrderEntity(order);
    }
}
