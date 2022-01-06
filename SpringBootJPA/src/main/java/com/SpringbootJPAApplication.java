package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

/**
 * @author 大数据老哥
 * @version V1.0
 * @Package PACKAGE_NAME
 * @File ：com.SpringbootJPAApplication.java
 * @date 2022/1/5 11:36
 */
@SpringBootApplication
@EntityScan
public class SpringbootJPAApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootJPAApplication.class, args);
    }
//    // 延迟session 会话时间
//    @Bean
//    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
//        return new OpenEntityManagerInViewFilter();
//    }
}
