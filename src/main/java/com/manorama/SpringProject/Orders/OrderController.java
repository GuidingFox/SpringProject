package com.manorama.SpringProject.Orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Orders> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/user/{id}")
    public List<Orders> getOrdersByUser(@PathVariable Long id) {
        System.out.println(id);
        return orderService.getOrdersByUser(id);
    }

    @PostMapping
    public void createOrder(@RequestBody Orders orders) {
        orders.setDate(LocalDate.now());
        orderService.createOrder(orders);

    }
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
    }
}
