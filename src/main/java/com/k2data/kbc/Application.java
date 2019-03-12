package com.k2data.kbc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Api("欢迎")
@EnableSwagger2
@RestController
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 为方便测试本应用是否启动成功
     * TODO: 将"/home"修改为"/"
     *
     * @return
     */
    @ApiOperation("欢迎页")
    @GetMapping("home")
    String home() {
        return "Hello, my KBC application.";
    }

}
