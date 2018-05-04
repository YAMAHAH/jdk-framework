package com.example.demo.dao;

import com.example.demo.models.Person;
import com.example.demo.output.PersonDto;
import com.example.demo.output.person.PersonProjection;
import com.example.demo.output.person.PersonSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


    //    QuerydslPredicateExecutor<Person>,
//        QuerydslBinderCustomizer<QPerson>,

@Repository
public interface PersonRepository extends
        JpaRepository<Person,Integer>,
        JpaSpecificationExecutor<Person> {

    public List<Person> findByAge(Integer age);
    public Person findByName(String personName);

//    default void customize(QuerydslBindings bindings, QPerson person) {
//       // bindings.bind(person.name).first((path, value) -> path.contains(value).or(person.age.gt(value).contains(value)));
////        bindings.bind(person.firsttime).first((path, value) -> person.pushdate.after(value));
////        bindings.bind(person.secondtime).first((path, value) -> person.pushdate.before(value));
//    }

    //    @Query(value = "select * from person where id > :id",nativeQuery = true)
//    List<Person> findById(@Param("id") int id);
    @Query(value = "select name from person where id > :id", nativeQuery = true)
//    @Param("id")
    PersonDto findById(int id);

    Collection<PersonDto> findAllDtoBy();

//    @Query(value = "select new com.example.demo.output.PersonDto(p.name)  from Person p where p.id > ?1")
//    Collection<PersonDto> findDtoWithConstructorExpression(int id);


    Page<PersonProjection> findPagedProjectedBy(Pageable pageable);

    PersonProjection findProjectedById(int id);

    Collection<PersonProjection> findAllProjectedBy();

    @Query("select c.name as name, c.age as age,c.id from Person c inner join Address a")
    Collection<PersonProjection> findsByProjectedColumns();

    <T> T findProjectedById(int id, Class<T> projection);

    <T> Collection<T> findByName(String name, Class<T> projection);

    Collection<PersonSummary> findAllSummarizedBy();
}


