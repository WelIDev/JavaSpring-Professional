package com.weildev.componenteinjecaodependencia.entities;

public class Order {

    private final Integer code;
    private final Double basic;
    private final Double discount;

    public Order(Integer code, Double basic, Double discount) {
        this.code = code;
        this.basic = basic;
        this.discount = discount;
    }

    public Double getDiscount() {
        return discount;
    }

    public Double getBasic() {
        return basic;
    }

    public Integer getCode() {
        return code;
    }

}
