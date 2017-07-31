package com.fanxl.july;

import com.fanxl.july.util.MyMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.fanxl.july.dao", markerInterface = MyMapper.class)
public class JulyApplication {

	public static void main(String[] args) {
		SpringApplication.run(JulyApplication.class, args);
	}
}
