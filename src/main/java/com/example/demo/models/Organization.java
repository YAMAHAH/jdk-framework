package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


@Entity

@NamedNativeQueries(value = { @NamedNativeQuery(name = "findByName", query = " select * from organization e where e.name=?1") })
@SqlResultSetMapping(
        name = "orgMapping",
        entities = @EntityResult(
                entityClass = Organization.class,
                fields = {
                        @FieldResult(name = "name", column = "name"),
                        @FieldResult(name = "code", column = "code"),
                        @FieldResult(name = "id", column = "id")})
)
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "Organization.plus2",
                resultClasses ={ Organization.class},
                procedureName = "plus3inout",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "pid", type = Long.class)
                }),
        @NamedStoredProcedureQuery(name = "Organization.plus1", procedureName = "plus1inout", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "args", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "res", type = Integer.class) })
})
public class Organization {

    public  Organization(){
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(length=64)
    private String name;

    @Column(length=64)
    private String code;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="parent_id")
    private Organization parent;

    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private Set<Organization> children = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**组织名称*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**组织编码*/
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**父组织*/
    public Organization getParent() {
        return parent;
    }

    public void setParent(Organization parent) {
        this.parent = parent;
    }

    /**子组织*/
    public Set<Organization> getChildren() {
        return children;
    }

    public void setChildren(Set<Organization> children) {
        this.children = children;
    }
}
