package com.example.demo;

import com.example.demo.dao.CompanyRepository;
import com.example.demo.dao.PersonRepository;
import com.example.demo.models.Company;
import com.example.demo.models.Person;
import com.example.demo.service.ActivitiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ServletComponentScan
@EnableAspectJAutoProxy
//@ComponentScan
//@EnableAutoConfiguration
@SpringBootApplication() //exclude = SecurityAutoConfiguration.class
public class DemoApplication {

	@Autowired
	private CompanyRepository compRepository;
	@Autowired
	private PersonRepository personRepository;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	//初始化模拟数据
	@Bean
	public CommandLineRunner init(final ActivitiService myService) {
		return new CommandLineRunner() {
			public void run(String... strings) throws Exception {
				if (personRepository.findAll().size() == 0) {
//					personRepository.save(new Person("wtr"));
//					personRepository.save(new Person("wyf"));
//					personRepository.save(new Person("admin"));
				}
				if (compRepository.findAll().size() == 0) {
					Company group = new Company("great company");
					compRepository.save(group);
					Person admin = personRepository.findByName("chen");
					Person wtr = personRepository.findByName("wang");
					admin.setCompany(group);
					wtr.setCompany(group);
					personRepository.save(admin);
					personRepository.save(wtr);
				}
			}
		};
	}
}
