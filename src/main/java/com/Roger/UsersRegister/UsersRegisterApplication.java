package com.Roger.UsersRegister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsersRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersRegisterApplication.class, args);
	}

	Usuario usuario1 = new Usuario("Roger", "rogermoga@hotmail.com" ,"Getronics");
}
