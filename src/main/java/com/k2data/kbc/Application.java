package com.k2data.kbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 为方便测试本应用是否启动成功
     * TODO: 将/home修改为/
     * @return
     */
    @RequestMapping("home")
    String home() {
        return "Hello, my KBC application.";
    }

}
