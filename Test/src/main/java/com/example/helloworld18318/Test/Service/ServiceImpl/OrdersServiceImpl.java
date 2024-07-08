package com.example.helloworld18318.Test.Service.ServiceImpl;

import com.example.helloworld18318.Test.Entity.Orders;
import com.example.helloworld18318.Test.Repository.OrdersRepo;
import com.example.helloworld18318.Test.Service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    OrdersRepo ordersRepo;

    @Override
    public List<Orders> getAllOrders() {
        return ordersRepo.findAll();
    }

    @Override
    public void addOrders(Orders orders) {
        ordersRepo.save(orders);
    }

    @Override
    public void deleteOrders(Integer id) {
        ordersRepo.deleteById(id);
    }

    @Override
    public void updateOrders(Orders orders) {
        ordersRepo.save(orders);
    }

    @Override
    public Orders getDetail(Integer id) {
        return ordersRepo.findById(id).get();
    }
}
