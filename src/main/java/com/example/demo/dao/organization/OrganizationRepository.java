package com.example.demo.dao.organization;

import com.example.demo.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationRepository extends
        JpaRepository<Organization,Long>,
        JpaSpecificationExecutor<Organization>,OrganizationCustomRepository {

    Optional<Organization> findById(Long id);

    @Query(value = "call getOrgParents(?1) ", nativeQuery = true)
    List<Organization> explicitlyProc(Integer id);


    // procedureName = "Organization.getOrgParents"
    //@Procedure(name = "organization.getOrgParents")
//    @Query(value = "call getOrgParents(:pId) ", nativeQuery = true)
    //@Procedure(name = "Organization.plus2")
   // Object[] plus1BackedByOtherNamedStoredProcedure2(@Param("pid") Long pid);

    @Procedure(name = "Organization.plus2")
    void plus1BackedByOtherNamedStoredProcedure2(@Param("pid") Long pid);

//    @Procedure
//    Long plus3inout(Long pid);

    @Procedure(name = "Organization.plus1")
    Integer plus1BackedByOtherNamedStoredProcedure(@Param("args") Integer args);
    /**
     * Directly map the method to the stored procedure in the database (to avoid the annotation madness on your domain
     * classes).
     */
    @Procedure
    Integer plus1inout(Integer args);
}
