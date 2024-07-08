package com.example.helloworld18318.Test.Service.ServiceImpl;

import com.example.helloworld18318.Test.Entity.Customers;
import com.example.helloworld18318.Test.Repository.CustomerRepo;
import com.example.helloworld18318.Test.Service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CusomersServiceImpl implements CustomersService {
    @Autowired
    CustomerRepo customerRepo;

    @Override
    public List<Customers> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public void addCustomer(Customers customers) {
        customerRepo.save(customers);
    }

    @Override
    public void updateCustomer(Customers customers) {
        customerRepo.save(customers);
    }

    @Override
    public void deleteCustomer(Integer id) {
        customerRepo.deleteById(id);
    }

    @Override
    public void getDetail(Integer id) {
        customerRepo.findById(id).get();
    }
}
