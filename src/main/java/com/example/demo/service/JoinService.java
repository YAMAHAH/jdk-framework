package com.example.demo.service;

import com.example.demo.dao.CompanyRepository;
import com.example.demo.dao.PersonRepository;
import com.example.demo.models.Company;
import com.example.demo.models.Person;
import org.flowable.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class JoinService {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    private CompanyRepository compRepository;

    //加入公司操作，可从DelegateExecution获取流程中的变量
    public void joinGroup(DelegateExecution execution) {
        System.out.println("加入组织失败:execution");
        Boolean bool = (Boolean) execution.getVariable("joinApproved");
        if (bool) {
            Integer personId = ((Long)execution.getVariable("personId")).intValue();
            Long compId = (Long) execution.getVariable("compId");
           Company comp = compRepository.findById(compId).get();
            Person person = personRepository.findById(personId).get();
            person.setCompany(comp);
            personRepository.save(person);
            System.out.println("加入组织成功");
        } else {
            System.out.println("加入组织失败");
        }
    }

    //获取符合条件的审批人，演示这里写死，使用应用使用实际代码
    public List<String> findUsers(DelegateExecution execution) {
        return Arrays.asList("admin", "wtr","li");
    }
}
