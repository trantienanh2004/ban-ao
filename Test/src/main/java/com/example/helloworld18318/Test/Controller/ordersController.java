package com.example.helloworld18318.Test.Controller;

import com.example.helloworld18318.Test.Entity.Customers;
import com.example.helloworld18318.Test.Entity.Orders;
import com.example.helloworld18318.Test.Service.CustomersService;
import com.example.helloworld18318.Test.Service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Order")
public class ordersController {
    @Autowired
    OrdersService ordersService;
    @Autowired
    CustomersService customersService;
    @GetMapping("/list")
    public String getAll(){
        return "/Test/SanPham";
    }

}
