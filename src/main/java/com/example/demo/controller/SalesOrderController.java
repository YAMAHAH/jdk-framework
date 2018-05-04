package com.example.demo.controller;

import com.example.demo.dao.sales.SalesOrderItemRepository;
import com.example.demo.dao.sales.SalesOrderRepository;
import com.example.demo.input.FilterInput;
import com.example.demo.mapper.FilterCycleMapper;
import com.example.demo.mapper.SalesOrderMapper;
import com.example.demo.mapper.utils.CycleMappingContext;
import com.example.demo.models.FilterModel;
import com.example.demo.models.SaleOrderItem;
import com.example.demo.models.SalesOrder;
import com.example.demo.output.AjaxResponse;
import com.example.demo.output.SaleOrderDetailOutput;
import com.example.demo.output.SalesOrderOutput;
import com.example.demo.queryBuilder.TreeNode;
import com.example.demo.service.SalesOrderService;
import com.example.demo.utils.OrderArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/salesOrder")
public class SalesOrderController {

    @Autowired
    SalesOrderRepository salesOrderRepository;

    @Autowired
    SalesOrderItemRepository salesOrderItemRepository;

    @Autowired
    SalesOrderService salesOrderService;
    @Autowired
    public SalesOrderMapper salesOrderMapper;

    @ResponseBody
    @GetMapping("/query")
    public AjaxResponse salesOrderQuery(@RequestParam String keyword) {
        List<SalesOrder> orders = salesOrderService.getSalesOrder(keyword);
        List<SalesOrderOutput>  orderOutputs =  salesOrderMapper.SalesOrderToViewModels(orders);
        return AjaxResponse.ok("salesOrderQuery", orderOutputs);
    }

    @ResponseBody
    @GetMapping("/item-query")
    public AjaxResponse saleOrderItemQuery(@RequestParam String keyword) {
        List<SaleOrderItem> orders = salesOrderService.getSalesOrderDetail(keyword);
        List<SaleOrderDetailOutput>  orderOutputs =  salesOrderMapper.DetailToViewModels(orders);
        return AjaxResponse.ok("salesOrderDetailQuery", orderOutputs);
    }

    @ResponseBody
    @GetMapping("/item-query-test")
    public AjaxResponse saleOrderItemQuery2() {
        List<SaleOrderItem> orders = salesOrderService.getSalesOrderDetails();
        List<SaleOrderDetailOutput>  orderOutputs =  salesOrderMapper.DetailToViewModels(orders);
        return AjaxResponse.ok("salesOrderDetailQuery", orderOutputs);
    }

    @Autowired
    private FilterCycleMapper filterCycleMapper;

    @ResponseBody
    @PostMapping("/query-filter")
    @RequestMapping(value = "/query-filter",method = RequestMethod.POST)
    public AjaxResponse queryFilter(@RequestBody List<FilterInput> filterInput) {

        Map<String, Object> propertyMap = new HashMap<String, Object>() {
            {
                put("soid", "salesOrder.id");
                put("sono", "salesOrder.SaleOrderNo");
                put("goid", "itemProduct.id");
                put("gono", "itemProduct.productNo");
                put("goName", "itemProduct.productName");
                put("goDescription", "itemProduct.productDescirption");
                put("unit", "itemProduct.unit");
            }
        };
        List<FilterModel> input = filterCycleMapper.toFilterModel(filterInput, new CycleMappingContext(propertyMap));
        List<SaleOrderItem> orders = salesOrderService.getSalesOrderDetails(input.get(0));
        List<SaleOrderDetailOutput> detailOutputs = salesOrderMapper.DetailToViewModels(orders);

        OrderArray oa = new OrderArray<Object>() {
            {
                put(1, "abc");
                put(2, "def");
                put(3, "ghi");
                put("abc", "kil");
            }
        };
        System.out.println(oa.getSize().toString() + ":" + oa.get(3).value + ":" + oa.get("abc"));
        return AjaxResponse.ok("queryFilter", detailOutputs);
    }
    @ResponseBody
    @PostMapping("/tree")
    public AjaxResponse calaTree(@RequestBody TreeNode root) {
        return AjaxResponse.ok("CALA Tree", salesOrderService.calaTree(root));
    }
}
