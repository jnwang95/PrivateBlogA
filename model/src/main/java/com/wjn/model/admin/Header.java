package com.wjn.model.admin;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @auther WJN
 * @date 2019/9/17 15:55
 * @describetion 主题
 */
@Data
public class Header implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Long id;
    //名称
    private String header;
    //创建时间
    private Long entryTime;
    //是否启用(只能启用一个 ，1.启用，2.不启用)
    private Integer state;

}
