package com.rudenkoinc.crm.app.service;


import com.rudenkoinc.crm.app.dao.OrderRepository;
import com.rudenkoinc.crm.app.dao.UserRepository;
import com.rudenkoinc.crm.app.model.Order;
import com.rudenkoinc.crm.app.model.User;
import com.rudenkoinc.crm.app.model.orderextentions.Style;
import com.rudenkoinc.crm.app.model.orderextentions.Subject;
import com.rudenkoinc.crm.app.model.orderextentions.TypeOfPaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.rudenkoinc.crm.app.service.ValidationUtils.assertNotBlank;
import static com.rudenkoinc.crm.app.service.ValidationUtils.getEnumFromString;
import static org.springframework.util.Assert.notNull;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    public Order saveOrder(String email,
                          Long id,
                          String typeOfPaper,
                          String subjectStr,
                          String styleStr,
                          String topic,
                          String orderInstructions,
                          List<String> sources) {

        TypeOfPaper type = getEnumFromString(TypeOfPaper.class, typeOfPaper);
        Subject subject = getEnumFromString(Subject.class, subjectStr);
        Style style = getEnumFromString(Style.class, styleStr);

        assertNotBlank(email, "email cannot be blank");
        notNull(type, "typeOfPaper is mandatory");
        notNull(subject, "subject is mandatory");
        notNull(style, "style is mandatory");
        notNull(topic, "topic is mandatory");
        notNull(orderInstructions, "orderInstructions are mandatory");

        Order order = null;

        if(id != null) {
            order = orderRepository.findOrderById(id);

            order.setType(type);
            order.setSubject(subject);
            order.setStyle(style);
            order.setOrderInstructions(orderInstructions);
            order.setSources(sources);
            orderRepository.save(order);
        } else {
            User client =  userRepository.findUserByEmail(email);

            if(client != null) {
                Order newOrder = new Order(client, type, subject, style, topic, orderInstructions, sources);
                order = orderRepository.save(newOrder);
            }
        }

        return order;
    }
}
