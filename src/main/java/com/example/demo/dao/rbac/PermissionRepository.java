package com.example.demo.dao.rbac;

import com.example.demo.models.rbac.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {

}
