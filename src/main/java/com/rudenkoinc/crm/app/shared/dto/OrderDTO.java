package com.rudenkoinc.crm.app.shared.dto;

import com.rudenkoinc.crm.app.model.Order;

import java.util.List;

public class OrderDTO {

    private Long id;
    private String typeOfPaper;
    private String subject;
    private String style;
    private String topic;
    private String orderInstructions;
    private List<String> sources;

    public OrderDTO(Long id,
                    String typeOfPaper,
                    String subject,
                    String style,
                    String topic,
                    String orderInstructions,
                    List<String> sources) {
        this.id = id;
        this.typeOfPaper = typeOfPaper;
        this.subject = subject;
        this.style = style;
        this.topic = topic;
        this.orderInstructions = orderInstructions;
        this.sources = sources;
    }

    public static OrderDTO mapFromOrderEntity(Order order) {
        return new OrderDTO(order.getId(),
                order.getType().toString(),
                order.getSubject().toString(),
                order.getStyle().toString(),
                order.getTopic(),
                order.getOrderInstructions(),
                order.getSources());
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeOfPaper() {
        return typeOfPaper;
    }

    public void setTypeOfPaper(String typeOfPaper) {
        this.typeOfPaper = typeOfPaper;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getOrderInstructions() {
        return orderInstructions;
    }

    public void setOrderInstructions(String orderInstructions) {
        this.orderInstructions = orderInstructions;
    }

    public List<String> getSources() {
        return sources;
    }

    public void setSources(List<String> sources) {
        this.sources = sources;
    }
}
