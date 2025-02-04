package com.weildev.componenteinjecaodependencia;

import com.weildev.componenteinjecaodependencia.entities.Order;
import com.weildev.componenteinjecaodependencia.services.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ComponenteInjecaoDependenciaApplication implements CommandLineRunner {

    private final OrderService orderService;

    public ComponenteInjecaoDependenciaApplication(OrderService orderService) {
        this.orderService = orderService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ComponenteInjecaoDependenciaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Order order1 = new Order(1034, 150.00, 20.0);
        Order order2 = new Order(2282, 800.00, 10.0);
        Order order3 = new Order(1309, 95.90, 0.0);

        System.out.println("Pedido código: " + order1.getCode() + "\n" + "Valor total: R$ " + orderService.total(order1));
        System.out.println("Pedido código: " + order2.getCode() + "\n" + "Valor total: R$ " + orderService.total(order2));
        System.out.println("Pedido código: " + order3.getCode() + "\n" + "Valor total: R$ " + orderService.total(order3));
    }
}
