package com.example.demo.dao.organization;

import com.example.demo.models.Organization;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@SuppressWarnings("unchecked")
public class OrganizationCustomRepositoryImpl implements OrganizationCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Organization> findAllParent(Long id) {
       return this.entityManager.createNativeQuery("CALL getOrgParents(:pId);",Organization.class)
                .setParameter("pId", id)
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Organization> findAllViaProc(Long id) {
        StoredProcedureQuery storedProcedureQuery = this.entityManager.createNamedStoredProcedureQuery("Organization.plus2");
        storedProcedureQuery.setParameter("pid", id);
        storedProcedureQuery.execute();
        return storedProcedureQuery.getResultList();
    }
    @SuppressWarnings("unchecked")
    public List<Organization> findAllByViaQuery(String name) {
        List<Organization> orgs = this.entityManager
                .createNativeQuery("select id,name,code from organization where name like :name", "orgMapping")
                .setParameter("name", name)
                .setMaxResults(5)
                .getResultList();
        return orgs;
    }
}
