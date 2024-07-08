package com.example.helloworld18318.Test.Repository;

import com.example.helloworld18318.Test.Entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customers, Integer> {
}
