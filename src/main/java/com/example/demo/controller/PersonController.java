package com.example.demo.controller;
import com.example.demo.dao.PersonRepository;
import com.example.demo.input.PersonForm;
import com.example.demo.input.PersonInput;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.models.Person;
//import com.example.demo.models.QPerson;
import com.example.demo.output.AjaxResponse;
import com.example.demo.output.PersonDto;
import com.example.demo.output.PersonOutput;
import com.example.demo.output.person.PersonProjection;
import com.example.demo.service.PersonService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.example.demo.output.AjaxResponse.ok;

@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    public PersonService personService;

//    @PersistenceContext
//    private EntityManager entityManager;

    //实体管理者
    @Autowired
    private EntityManager entityManager;

    //JPA查询工厂
    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void initFactory()
    {
        queryFactory = new JPAQueryFactory(entityManager);
        System.out.println("init JPAQueryFactory successfully");
    }

    public PersonController() {

    }

//    @GetMapping(value = "/person")
//    private Object personList(@QuerydslPredicate(root = Person.class) Predicate predicate,
//                              @RequestParam("of") int offset,
//                              @RequestParam("ps") int pageSize) {
////        QPerson person = QPerson.person;
////        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
////        List<Person> persons = queryFactory.selectFrom(person)
////                .where(person.id.gt(3))
////                .fetch();
//       // Predicate predicate = QUser.user.id.lt(10);,
//        //
//        Sort sort = new Sort(Sort.Direction.DESC, "id");
//        PageRequest pr = PageRequest.of(offset, pageSize, sort);
//        return  personRepository.findAll(predicate,pr);
//    }
//    @GetMapping("/persons")
//    public Object personList(@QuerydslPredicate(root = Person.class) Predicate predicate,
//                             @PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable) {
//        return personRepository.findAll(predicate, pageable);
//    }

    @ResponseBody
    @GetMapping("/person-query")
    public Person personList(@RequestParam("id") int id) {
//        QPerson person = QPerson.person;
//        return personRepository.findOne(person.id.eq(id)).get();
       // return personRepository.findById(id);
        return null;
    }

    @ResponseBody
    @GetMapping("/person-keyword")
    public List<Person> personList(String keyword) {
        return personService.getPerson(keyword);
    }

    @PostMapping("/person-form")
    public AjaxResponse personFormTest(@RequestBody @Validated PersonForm personForm){
        return ok("200","success",personForm);
    }

    /**
     * 添加一个人员
     *
     * @param name
     * @param age
     * @return
     */
    @PostMapping(value = "/person")
    public Person personAdd(@RequestParam("name") String name,
                            @RequestParam("age") Integer age) {
        Person person = new Person();
        person.setName(name);
        person.setAge(age);

        return personRepository.save(person);
    }
    @PostMapping(value = "/person-add")
    public PersonInput personAdd(@RequestBody PersonInput personInput) {
        //PersonMapper.personMapper
        Person person = personMapper2.PersonInputToPerson(personInput);
        personRepository.save(person);
        return personInput;
    }

    //注入Mapper实现类
    @Autowired
    public PersonMapper personMapper2;
    /**
     * 查询一个人员
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/person2/{id}")
    public PersonOutput personFindOne(@PathVariable("id") Integer id) {
        Optional<Person> person = personRepository.findById(id);
        if(person.get() != null){
            return PersonMapper.personMapper.from(person.get());
        }
        return null;
    }

    /**
     * 通过年龄来查询
     * @param age
     * @return
     */
    @GetMapping(value = "/person/age/{age}")
    public List<Person> personListByAge(@PathVariable("age") Integer age) {
        return personRepository.findByAge(age);
    }

    /**
     * 事务测试
     */

    @PostMapping("/person/two")
    public Integer personTwo(){
        personService.insertTwo();
        return 10;
    }
    /**
     * 删除一个人员
     *
     * @param id
     */
    @DeleteMapping(value = "/person/{id}")
    public void personDelete(@PathVariable("id") Integer id) {
        personRepository.deleteById(id);
    }

    /**
     * 更新一个人员
     *
     * @param id
     * @param name
     * @param age
     * @return
     */
    @PutMapping(value = "/person/{id}")
    public Person personUpdate(@PathVariable("id") Integer id,
                               @RequestParam("name") String name,
                               @RequestParam("age") Integer age) {
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        person.setAge(age);
        return personRepository.save(person);
    }

    @GetMapping(value="/getPart")
    public Collection<PersonProjection> getPersonToPersonDto(@RequestParam("id") int id) {

        Page<PersonProjection> page = personRepository
                .findPagedProjectedBy(PageRequest.of(0, 1, Sort.by(Sort.Direction.ASC, "name")));
        return personRepository.findAllProjectedBy();
    }
}
