package com.sut.backend;

import java.sql.Types;
import java.text.ParseException;
import java.util.stream.Stream;

import com.sut.backend.entity.*;
import com.sut.backend.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	ApplicationRunner init(ProductRepository productRepository, TypeRepository typeRepository) {
		return args -> {

			Stream.of("ยอดนิยม", "ทั่วไป").forEach(type -> {
				Type t = new Type();
				t.setTypes(type);
				typeRepository.save(t);
			});
  
		Type type = typeRepository.findById(1);
		Product product = new Product();
		product.setImage("https://images-na.ssl-images-amazon.com/images/I/71JJB8w5erL._SX569_.jpg");
		product.setCode("P12345");
		product.setName("SUMSUNG NOTE10");
		product.setDetail("จอแสดงผล Dynamic AMOLED 24-bit กล้องหน้าฝังบนจอ ");
		product.setType(type);
		productRepository.save(product);
			
		};
	}
	
	
}
