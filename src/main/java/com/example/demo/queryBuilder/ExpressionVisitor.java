package com.example.demo.queryBuilder;

import com.example.demo.models.FilterModel;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@SuppressWarnings("unchecked")
public class ExpressionVisitor<T> {

    private Root<T> root;
    private CriteriaBuilder criteriaBuilder;

    public ExpressionVisitor(){

    }
    public ExpressionVisitor(Root<T> root, CriteriaBuilder criteriaBuilder) {
        this.root = root;
        this.criteriaBuilder = criteriaBuilder;
    }
    public ExpressionVisitor<T> SetRootAndCirteriaBuilder(Root<T> root, CriteriaBuilder criteriaBuilder) {
        this.root = root;
        this.criteriaBuilder = criteriaBuilder;
        return this;
    }

    private Predicate visitAndExpression(FilterModel exprNode) {
        Predicate restrictions = criteriaBuilder.conjunction();
        for (FilterModel filter : exprNode.getChildFilters()) {
            restrictions = criteriaBuilder.and(restrictions, this.visitExpression(filter));
        }
        if (exprNode.getNot()) {
            return this.visitNotExpression(restrictions);
        }
        return restrictions;
    }

    private Predicate visitOrExpression(FilterModel exprNode) {
        Predicate restrictions = criteriaBuilder.disjunction();
        for (FilterModel filter : exprNode.getChildFilters()) {
            restrictions = this.criteriaBuilder.or(restrictions, this.visitExpression(filter));
        }
        if (exprNode.getNot()) {
            return this.visitNotExpression(restrictions);
        }
        return restrictions;
    }

    private Predicate visitNotExpression(Predicate predicate) {
        return this.criteriaBuilder.not(predicate);
    }

    private Predicate visitNotExpression(FilterModel exprNode) {
        return this.criteriaBuilder.not(this.visitExpression(exprNode.getChildFilters().get(0)));
    }

    private Predicate visitUnaryExpression(FilterModel exprNode) {
        switch (exprNode.getOperator()) {
            case "and":
                return this.visitAndExpression(exprNode);
            case "or":
                return this.visitOrExpression(exprNode);
            case "not":
                return this.visitNotExpression(exprNode);
            default:
                return null;
        }
    }

    private Predicate visitBinaryExpression(FilterModel exprNode) {
        String property = exprNode.getProperty();
        String operator = exprNode.getOperator();
        Boolean not = exprNode.getNot();
        Object value = this.visitConstantExpression(exprNode);
        Boolean ignoreCase = exprNode.getIgnoreCase();
        Path<?> path = getPath(this.root, property);
        Predicate restrictions = null;
        switch (operator) {
            case "eq":
                if (value != null) {
                    if (BooleanUtils.isTrue(ignoreCase) && String.class.isAssignableFrom(path.getJavaType()) && value instanceof String) {
                        restrictions = criteriaBuilder.equal(criteriaBuilder.lower((Path<String>) path), ((String) value).toLowerCase());
                    } else {
                        restrictions = criteriaBuilder.equal(path, value);
                    }
                } else {
                    restrictions = path.isNull();
                }
                break;
            case "ne":
                if (value != null) {
                    if (BooleanUtils.isTrue(ignoreCase) && String.class.isAssignableFrom(path.getJavaType()) && value instanceof String) {
                        restrictions = criteriaBuilder.notEqual(criteriaBuilder.lower((Path<String>) path), ((String) value).toLowerCase());
                    } else {
                        restrictions = criteriaBuilder.notEqual(path, value);
                    }
                } else {
                    restrictions = path.isNotNull();
                }
                break;
            case "gt":
                if (Number.class.isAssignableFrom(path.getJavaType()) && value instanceof Number) {
                    restrictions = criteriaBuilder.gt((Path<Number>) path, (Number) value);
                }
                break;
            case "lt":
                if (Number.class.isAssignableFrom(path.getJavaType()) && value instanceof Number) {
                    restrictions = criteriaBuilder.lt((Path<Number>) path, (Number) value);
                }
                break;
            case "ge":
                if (Number.class.isAssignableFrom(path.getJavaType()) && value instanceof Number) {
                    restrictions = criteriaBuilder.ge((Path<Number>) path, (Number) value);
                }
                break;
            case "le":
                if (Number.class.isAssignableFrom(path.getJavaType()) && value instanceof Number) {
                    restrictions = criteriaBuilder.le((Path<Number>) path, (Number) value);
                }
                break;
            case "like":
                if (String.class.isAssignableFrom(path.getJavaType()) && value instanceof String) {
                    if (BooleanUtils.isTrue(ignoreCase)) {
                        restrictions = criteriaBuilder.like(criteriaBuilder.lower((Path<String>) path), ((String) value).toLowerCase());
                    } else {
                        restrictions = criteriaBuilder.like((Path<String>) path, "%" + (String) value + "%");
                    }
                }
                break;
            case "in":
                restrictions = path.in(value);
                break;
            case "isNull":
                restrictions = path.isNull();
                break;
            case "isNotNull":
                restrictions = path.isNotNull();
                break;
        }
        if (not) {
            return this.visitNotExpression(restrictions);
        }
        return restrictions;
    }

    private Predicate visitPropertyValueExpression(FilterModel expNode) {
        return null;
    }

    private Object visitConstantExpression(FilterModel exprNode) {
        return exprNode.getValue();
    }

    public Predicate visitExpression(FilterModel exprNode) {
        List<String> logics = Arrays.asList("and", "or", "not");
        if (logics.contains(exprNode.getOperator())) {
            return this.visitUnaryExpression(exprNode);
        } else {
            return this.visitBinaryExpression((exprNode));
        }
    }

    private <X> Path<X> getPath(Path<?> path, String propertyPath) {
        if (path == null || StringUtils.isEmpty(propertyPath)) {
            return (Path<X>) path;
        }
        String property = StringUtils.substringBefore(propertyPath, ".");
        return getPath(path.get(property), StringUtils.substringAfter(propertyPath, "."));
    }


}
