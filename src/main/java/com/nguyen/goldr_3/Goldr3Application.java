package com.nguyen.goldr_3;

import com.nguyen.goldr_3.model.Account;
import com.nguyen.goldr_3.model.Category;
import com.nguyen.goldr_3.model.Entry;
import com.nguyen.goldr_3.model.User;
import com.nguyen.goldr_3.repository.AccountRepo;
import com.nguyen.goldr_3.repository.CategoryRepo;
import com.nguyen.goldr_3.repository.EntryRepo;
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
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private EntryRepo entryRepo;

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

//		create category
		Category category1 = new Category();
		category1.setName("Cash");
		category1.setUser(user1);
		categoryRepo.save(category1);

//		create account
		Account account1 = new Account();
		account1.setName("BOA Checking");
		account1.setUser(user1);
		account1.setCategory(category1);
		accountRepo.save(account1);

//		create entries
		Entry entry1 = new Entry();
		entry1.setAmount(1000.01);
		entry1.setDate(LocalDate.now());
		entry1.setAccount(account1);
		entry1.setUser(user1);
		entryRepo.save(entry1);

		Entry entry2 = new Entry();
		entry2.setAmount(2000.02);
		entry2.setDate(LocalDate.now());
		entry2.setAccount(account1);
		entry2.setUser(user1);
		entryRepo.save(entry2);

	}

}
