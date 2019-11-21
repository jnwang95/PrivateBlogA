package com.wjn;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * The type App run admin.
 *
 * @description: 启动类
 * @author: jnWang
 * @create: 2019 -11-15 16:41
 */
@SpringBootApplication
@MapperScan({"com.wjn.mapper"})
public class AppRunAdmin {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(AppRunAdmin.class);
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(AppRunAdmin.class,args);
        logger.info("<<<<<<<博客系统启动成功>>>>>>>");
    }

}
