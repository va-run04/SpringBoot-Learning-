package com.vk.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")  // NEW instance every time getBean() is called
public class OrderRequest {

    private String orderId;

    public OrderRequest() {
        System.out.println("[Prototype] OrderRequest constructor called — new instance!");
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}