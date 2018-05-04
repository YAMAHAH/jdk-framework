package com.example.demo.service;

import com.example.demo.dao.organization.OrganizationRepository;
import com.example.demo.models.FilterModel;
import com.example.demo.models.Organization;
import com.example.demo.queryBuilder.ExpressionVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private EntityManager entityManager;

    @Transactional()
    public void addParentOrg() {

        //父组织
        Organization chinaOrg = new Organization();
        chinaOrg.setName("中国");
        chinaOrg.setCode("CHINA");

        //子组织
        Organization gdOrg = new Organization();
        gdOrg.setName("广东");
        gdOrg.setCode("GD");

        Organization gzOrg = new Organization();
        gzOrg.setName("广州");
        gzOrg.setCode("GZ");

        Set<Organization> gdChildren = new HashSet<>();
        gdChildren.add(gzOrg);
        gdOrg.setChildren(gdChildren);

        //子组织
        Organization gxOrg = new Organization();
        gxOrg.setName("广西");
        gxOrg.setCode("GX");

        Set<Organization> children = new HashSet<>();
        children.add(gdOrg);
        children.add(gxOrg);

        //添加子组织
        chinaOrg.setChildren(children);

        //  entityManager.getTransaction().begin();
        //  entityManager.persist(chinaOrg);
        // entityManager.getTransaction().commit();
        organizationRepository.save(chinaOrg);
    }

    public void addChildOrg() {

        Organization gzOrg = new Organization();
        gzOrg.setName("广州");
        gzOrg.setCode("GZ");

        //找出广州所属的父组织广东
        Organization parent = organizationRepository.findById(3L).get();
        //设置广州的父组织
        gzOrg.setParent(parent);

        // entityManager.getTransaction().begin();
        //保存广州
        // entityManager.persist(gzOrg);
        // entityManager.getTransaction().commit();
    }

    /**
     * 根据子组织查询父组织
     */
    public void testQueryParentByChild() {
        //找出广州
        Organization gzOrg = organizationRepository.findById(4L).get();
        //找出父组织
        Organization parent = gzOrg.getParent();
    }

    /**
     * 根据父组织查询子组织
     */
    public void testQueryChildrenByParent() {
        //找出广东
        Organization gdOrg = organizationRepository.findById(3L).get();
        //找出子组织
        Set<Organization> children = gdOrg.getChildren();
//        Iterator<Organization> it = children.iterator();
//        while(it.hasNext()) {
//            Organization  child = it.next();
//        }
    }

    public Organization updateOrg(Organization org) {
        return this.organizationRepository.save(org);
    }

    public Organization getOrg(Long id) {
        return this.organizationRepository.findById(id).get();
    }

    public List<Organization> getOrg(Iterable<Long> ids) {
        return this.organizationRepository.findAllById(ids);
    }

    public List<Organization> getOrgs(FilterModel filter) {
        Specification<Organization> spec = new Specification<Organization>() {
            @Override
            public Predicate toPredicate(Root<Organization> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               Join<Organization, Organization> goodJoin = root.join("parent", JoinType.LEFT);
//                Join<SaleOrderItem, SalesOrder> orderHeaderJoin = root.join( "salesOrder", JoinType.INNER);
                return new ExpressionVisitor<Organization>(root, cb).visitExpression(filter);
            }
        };
        return organizationRepository.findAll(spec);
    }

    public  List<Organization> getOrgParents(Long id) {
        Integer i = organizationRepository.plus1inout(10);
        Integer i2 = organizationRepository.plus1BackedByOtherNamedStoredProcedure(15);
        System.out.println(i);
        System.out.println(i2);
        organizationRepository.plus1BackedByOtherNamedStoredProcedure2(id);
        return null;
    }

    @Transactional(readOnly = false)
    public void batchInsertAndUpdate(List list) {
//        int size =  list.size();
//        for (int i = 0; i < size; i++) {
//            BaseEntity dd = (BaseEntity) list.get(i);
//            if (dd.isNew()) {
//                 entityManager.persist(dd);
//            } else {
//                entityManager.persist(entityManager.merge(dd));
//            }
//            if (i % 1000 == 0 || i==(size-1)) { // 每1000条数据执行一次，或者最后不足1000条时执行
//                entityManager.flush();
//                entityManager.clear();
//            }
//        }
    }
}
