package com.weildev.componenteinjecaodependencia.services;

import com.weildev.componenteinjecaodependencia.entities.Order;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    public double shipment(Order order){
        if (order.getBasic() < 100.0){
            return 20;
        } else if (order.getBasic() < 200.0) {
            return 12;
        }else {
            return 0;
        }
    }
}
