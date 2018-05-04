package com.example.demo.service;

import com.example.demo.dao.sales.SalesOrderItemRepository;
import com.example.demo.dao.sales.SalesOrderRepository;
import com.example.demo.models.FilterModel;
import com.example.demo.models.Good;
import com.example.demo.models.SaleOrderItem;
import com.example.demo.models.SalesOrder;
import com.example.demo.queryBuilder.ExpressionVisitor;
import com.example.demo.queryBuilder.Filter;
import com.example.demo.queryBuilder.QueryParams;
import com.example.demo.queryBuilder.TreeNode;
import com.example.demo.utils.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalesOrderService {

    @Autowired
    SalesOrderRepository salesOrderRepository;

    @Autowired
    SalesOrderItemRepository salesOrderItemRepository;

    /** 属性分隔符 */
    private static final String PROPERTY_SEPARATOR = ".";
    @SuppressWarnings("unchecked")
    private <X> Path<X> getPath(Path<?> path, String propertyPath) {
        if (path == null || StringUtils.isEmpty(propertyPath)) {
            return (Path<X>) path;
        }
        String property = StringUtils.substringBefore(propertyPath, PROPERTY_SEPARATOR);
        return getPath(path.get(property), StringUtils.substringAfter(propertyPath, PROPERTY_SEPARATOR));
    }

    public List<SalesOrder> getSalesOrder(String keyword) {
        Specification<SalesOrder> spec = new Specification<SalesOrder>() {
            @Override
            public Predicate toPredicate(Root<SalesOrder> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicatesList = new ArrayList<>();
                if (!StringUtils.isEmpty(keyword)) {
                    predicatesList.add(cb.or(
                            cb.like(root.get("SaleOrderNo").as(String.class), "%" + keyword + "%"),
                            cb.like(root.get("SaleDate").as(String.class), "%" + keyword + "%"))
                    );
                }
                //  query.orderBy(cb.asc(root.get("age")), cb.asc(root.get("name")));
                //--------------------------------------------
                //最终将查询条件拼好然后return
                Predicate[] predicates = new Predicate[predicatesList.size()];
                return cb.and(predicatesList.toArray(predicates));
            }
        };
        return salesOrderRepository.findAll(spec);
    }
    public List<SaleOrderItem> getSalesOrderDetail(String keyword) {
        Specification<SaleOrderItem> spec = new Specification<SaleOrderItem>() {
            @Override
            public Predicate toPredicate(Root<SaleOrderItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicatesList = new ArrayList<>();
                if (!StringUtils.isEmpty(keyword)) {
                    predicatesList.add(cb.or(
                            cb.like(root.get("total").as(String.class), "%" + keyword + "%"),
                            cb.like(root.get("totalRemail").as(String.class), "%" + keyword + "%"))
                    );
                }
                //  query.orderBy(cb.asc(root.get("age")), cb.asc(root.get("name")));
                //--------------------------------------------
                //最终将查询条件拼好然后return
                Predicate[] predicates = new Predicate[predicatesList.size()];
                return cb.and(predicatesList.toArray(predicates));
            }
        };
        return salesOrderItemRepository.findAll(spec);
    }

    public List<SaleOrderItem> getSalesOrderDetails() {
        QueryParams<SaleOrderItem> queryParams = new QueryParams<>();
        queryParams
                .and(
                        Filter.gt("total", 100),
                        Filter.like("itemProduct.productNo", "%P0101%"))
                .or(Filter.like("salesOrder.SaleOrderNo", "%SO%"))
                .orderDESC("Id");
        Specification<SaleOrderItem> spec = new Specification<SaleOrderItem>() {
            @Override
            public Predicate toPredicate(Root<SaleOrderItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Join<SaleOrderItem, Good> goodJoin = root.join("itemProduct", JoinType.INNER);
                Join<SaleOrderItem, SalesOrder> orderHeaderJoin = root.join("salesOrder", JoinType.INNER);
                return queryParams.toPredicate(root, query, cb);
            }
        };
        return salesOrderItemRepository.findAll(spec);
    }
    @Autowired
    private ExpressionVisitor<SaleOrderItem> expressionVisitor;

    public List<SaleOrderItem> getSalesOrderDetails(FilterModel filter) {

        Specification<SaleOrderItem> spec = new Specification<SaleOrderItem>() {
            @Override
            public Predicate toPredicate(Root<SaleOrderItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Join<SaleOrderItem, Good> goodJoin = root.join("itemProduct", JoinType.INNER);
                Join<SaleOrderItem, SalesOrder> orderHeaderJoin = root.join( "salesOrder", JoinType.INNER);
                expressionVisitor.SetRootAndCirteriaBuilder(root,cb).visitExpression(filter);
                return new ExpressionVisitor<SaleOrderItem>(root,cb).visitExpression(filter);
            }
        };
        return salesOrderItemRepository.findAll(spec);
    }

    public TreeNode calaTree(TreeNode treeNode){
      return  new TreeUtil().visitTree(treeNode,false);
    }
}
