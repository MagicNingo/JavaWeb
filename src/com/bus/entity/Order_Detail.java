package com.bus.entity;

public class Order_Detail {
    private Integer id;
    private Order  order;
    private Product p;
    private Integer quantity;
    private Double discount;

    public Order_Detail() {
    }

    public Order_Detail(Integer id, Order order, Product p, Integer quantity, Double discount) {
        this.id = id;
        this.order = order;
        this.p = p;
        this.quantity = quantity;
        this.discount = discount;
    }

    public Order_Detail(Order order, Product p, Integer quantity, Double discount) {
        this.order = order;
        this.p = p;
        this.quantity = quantity;
        this.discount = discount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Order_Detail{" +
                "id=" + id +
                ", order=" + order +
                ", productID=" + p.getProductID() +
                ", quantity=" + quantity +
                ", discount=" + discount +
                '}';
    }
}
