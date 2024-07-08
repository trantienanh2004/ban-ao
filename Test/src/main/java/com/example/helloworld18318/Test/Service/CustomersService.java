package com.example.helloworld18318.Test.Service;

import com.example.helloworld18318.Test.Entity.Customers;

import java.util.List;

public interface CustomersService {
    public List<Customers> getAllCustomers();

    public void addCustomer(Customers customers);

    public void updateCustomer(Customers customers);

    public void deleteCustomer(Integer id);

    public void getDetail(Integer id);
}
