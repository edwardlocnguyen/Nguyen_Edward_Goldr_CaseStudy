package com.nguyen.goldr_3;

import com.nguyen.goldr_3.model.User;
import com.nguyen.goldr_3.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;

@SpringBootApplication
public class Goldr3Application {

	@Autowired
	private UserRepo userRepo;

	public static void main(String[] args) {
		SpringApplication.run(Goldr3Application.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void insertData() {

//		create user
		User user1 = new User();
		user1.setEmail("edwardnguyen@gmail.com");
		user1.setPassword("password");
		user1.setFirstName("Edward");
		user1.setLastName("Nguyen");
		user1.setOccupation("Software Developer");
		user1.setDob(LocalDate.of(1994, 11, 6));
		user1.setCreditScore(750);
		userRepo.save(user1);

	}

}
