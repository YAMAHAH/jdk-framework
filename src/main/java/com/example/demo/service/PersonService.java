package com.example.demo.service;

import com.example.demo.dao.PersonRepository;
import com.example.demo.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    /**
     * 事务管理测试
     * 两条数据同时成功，或者同时不成功
     * 保证数据库数据的完整性和一致性
     */

    @Transactional
    public void insertTwo(){
        Person personA = new Person();
        personA.setId(3);
        personA.setName("秋雅");
        personA.setAge(19);
        personRepository.save(personA);

        System.out.print(1/10);

        Person personB = new Person();
        personB.setId(4);
        personB.setName("梦特娇");
        personB.setAge(25);
        personRepository.save(personB);
    }
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
//    @Override
    public List<Person> getPerson(String keyword) {
        Specification<Person> specification = new Specification<Person>() {
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
               EntityType<Person> entityType =  root.getModel();
                //--------------------------------------------
                //查询条件示例
                //equal示例
//                if (!StringUtils.isEmpty(name)) {
//                    Predicate namePredicate = cb.equal(root.get("name"), name);
//                    predicatesList.add(namePredicate);
//                }
                if(!StringUtils.isEmpty(keyword)) {
                    predicatesList.add(cb.or(
                            cb.like(root.get("name").as(String.class), "%" + keyword + "%"),
                            cb.like(root.get("age").as(String.class), "%" + keyword + "%"))
                    );
                }

                //like示例
//                if (!StringUtils.isEmpty(nickName)) {
//                    Predicate nickNamePredicate = cb.like(root.get("nickName"), '%' + nickName + '%');
//                    predicatesList.add(nickNamePredicate);
//                }
                //between示例
//                if (birthday != null) {
//                    Predicate birthdayPredicate = cb.between(root.get("birthday"), birthday, new Date());
//                    predicatesList.add(birthdayPredicate);
//                }

                //关联表查询示例
//                if (!StringUtils.isEmpty(courseName)) {
//                    Join<Student, Teacher> joinTeacher = root.join("teachers", JoinType.LEFT);
//                    Predicate coursePredicate = cb.equal(joinTeacher.get("courseName"), courseName);
//                    predicatesList.add(coursePredicate);
//                }

                //复杂条件组合示例
//                if (chineseScore != 0 && mathScore != 0 && englishScore != 0 && performancePoints != 0) {
//                    Join<Student, Examination> joinExam = root.join("exams", JoinType.LEFT);
//                    Predicate predicateExamChinese = cb.ge(joinExam.get("chineseScore"), chineseScore);
//                    Predicate predicateExamMath = cb.ge(joinExam.get("mathScore"), mathScore);
//                    Predicate predicateExamEnglish = cb.ge(joinExam.get("englishScore"), englishScore);
//                    Predicate predicateExamPerformance = cb.ge(joinExam.get("performancePoints"), performancePoints);
//                    //组合
//                    Predicate predicateExam = cb.or(predicateExamChinese, predicateExamEnglish, predicateExamMath);
//                    Predicate predicateExamAll = cb.and(predicateExamPerformance, predicateExam);
//                    predicatesList.add(predicateExamAll);
//                }
                //--------------------------------------------
                //排序示例(先根据学号排序，后根据姓名排序)
                query.orderBy(cb.asc(root.get("age")), cb.asc(root.get("name")));
                //--------------------------------------------
                //最终将查询条件拼好然后return
                Predicate[] predicates = new Predicate[predicatesList.size()];
                return cb.and(predicatesList.toArray(predicates));
            }
        };
        return personRepository.findAll(specification);
    }

}
