package com.example.demo.dao.organization;

import com.example.demo.models.Organization;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface OrganizationCustomRepository {

    List<Organization> findAllParent(Long id);

}
