package com.weildev.componenteinjecaodependencia.services;

import com.weildev.componenteinjecaodependencia.entities.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final ShippingService shippingService;

    public OrderService(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    public double total(Order order) {
        double discount = (order.getBasic() * order.getDiscount()) / 100;
        return order.getBasic() - discount + shippingService.shipment(order);
    }
}
