package com.example.helloworld18318.Test.Service;

import com.example.helloworld18318.Test.Entity.Orders;

import java.util.List;

public interface OrdersService {
    public List<Orders> getAllOrders();

    public void addOrders(Orders orders);

    public void deleteOrders(Integer id);

    public void updateOrders(Orders orders);

    public Orders getDetail(Integer id);
}
