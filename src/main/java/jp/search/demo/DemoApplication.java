package jp.search.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
//@ServletComponentScan(basePackages = {"com.example.config"})
@SpringBootApplication
@ComponentScan(basePackages = {"jp.search.*"})
@MapperScan(basePackages = "jp.search.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
