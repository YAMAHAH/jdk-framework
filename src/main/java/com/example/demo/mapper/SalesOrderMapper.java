package com.example.demo.mapper;

import com.example.demo.models.SaleOrderItem;
import com.example.demo.models.SalesOrder;
import com.example.demo.output.SaleOrderDetailOutput;
import com.example.demo.output.SalesOrderItemOutput;
import com.example.demo.output.SalesOrderOutput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel="spring")
public interface SalesOrderMapper {

    public static SalesOrderMapper salesOrderMapper  = Mappers.getMapper(SalesOrderMapper.class);
    @Mappings({
            @Mapping(source = "id",target = "id"),
    })
    public SalesOrderOutput ModelToViewModel(SalesOrder salesOrder);

    public List<SalesOrderOutput> SalesOrderToViewModels(List<SalesOrder> list);

    @Mappings({
            @Mapping(source = "id",target = "id"),
    })
    public SalesOrderItemOutput ModelToViewModel(SaleOrderItem salesOrderItem);

    public List<SalesOrderItemOutput> SalesOrderItemToViewModels(List<SaleOrderItem> list);

    @Mappings({
            @Mapping(source = "saleOrderItem.salesOrder.id",target = "soid"),
            @Mapping(source = "saleOrderItem.salesOrder.saleOrderNo",target = "sono"),
            @Mapping(source = "saleOrderItem.itemProduct.id",target = "goid",defaultValue = "111"),
            @Mapping(source = "saleOrderItem.itemProduct.productNo",target = "gono"),
            @Mapping(source = "saleOrderItem.itemProduct.productName",target = "goName"),
            @Mapping(source = "saleOrderItem.itemProduct.productDescirption",target = "goDescription"),
            @Mapping(source = "saleOrderItem.itemProduct.unit",target = "unit"),
    })
    public SaleOrderDetailOutput DetailToViewModel(SaleOrderItem saleOrderItem);

    public List<SaleOrderDetailOutput> DetailToViewModels(List<SaleOrderItem> list);

}
