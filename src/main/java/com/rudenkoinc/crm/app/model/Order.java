package com.rudenkoinc.crm.app.model;

import com.rudenkoinc.crm.app.model.orderextentions.Style;
import com.rudenkoinc.crm.app.model.orderextentions.Subject;
import com.rudenkoinc.crm.app.model.orderextentions.TypeOfPaper;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order extends AbstractEntity {

    @ManyToOne
    private User client;

    @Enumerated(EnumType.ORDINAL)
    private TypeOfPaper type;

    @Enumerated(EnumType.ORDINAL)
    private Subject subject;

    @Enumerated(EnumType.ORDINAL)
    private Style style;

    private String topic;
    private String orderInstructions;

    @ElementCollection
    @CollectionTable(name="sources", joinColumns=@JoinColumn(name="order_id"))
    @Column(name="source")
    private List<String> sources;

    public Order() {
        //for Hibernate
    }

    public Order(User client,
                 TypeOfPaper type,
                 Subject subject,
                 Style style,
                 String topic,
                 String orderInstructions,
                 List<String> sources) {

        this.client = client;
        this.type = type;
        this.subject = subject;
        this.style = style;
        this.topic = topic;
        this.orderInstructions = orderInstructions;
        this.sources = sources;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public TypeOfPaper getType() {
        return type;
    }

    public void setType(TypeOfPaper type) {
        this.type = type;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
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

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }
}
