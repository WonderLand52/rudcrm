package com.rudenkoinc.crm.app.dao;

import com.rudenkoinc.crm.app.model.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Illia Rudenko
 */

@Repository
public class OrderRepository {

    @PersistenceContext
    EntityManager em;

    public Order findOrderById(Long id) {
        return em.find(Order.class, id);
    }

    public Order save(Order order) {
        return em.merge(order);
    }
}
