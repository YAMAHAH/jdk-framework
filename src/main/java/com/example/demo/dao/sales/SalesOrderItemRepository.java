package com.example.demo.dao.sales;

import com.example.demo.models.SaleOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SalesOrderItemRepository extends
        JpaRepository<SaleOrderItem,Integer>,
        JpaSpecificationExecutor<SaleOrderItem> {

    <T> Collection<T> findBySalesOrder(String lastname, Class<T> type);
}
