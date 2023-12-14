package com.manorama.SpringProject.Orders;

import com.manorama.SpringProject.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository)
    {
        this.orderRepository=orderRepository;
    }
    public List<Orders> getAllOrders(){
        return orderRepository.findAll();
    }
    public List<Orders> getOrdersByUser(Long id){
        return orderRepository.findAllById(List.of(id));
    }
    public Orders createOrder(Orders order){
        return orderRepository.save(order);
    }
    public void  deleteOrder(Long id){
        orderRepository.deleteById(id);
    }
}
