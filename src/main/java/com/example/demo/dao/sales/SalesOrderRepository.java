package com.example.demo.dao.sales;

import com.example.demo.models.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesOrderRepository extends
        JpaRepository<SalesOrder,Integer>,
        JpaSpecificationExecutor<SalesOrder> {

}

