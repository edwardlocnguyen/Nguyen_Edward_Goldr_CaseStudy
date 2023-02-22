package com.nguyen.goldr_3;

import com.nguyen.goldr_3.model.Account;
import com.nguyen.goldr_3.model.Category;
import com.nguyen.goldr_3.model.Entry;
import com.nguyen.goldr_3.model.User;
import com.nguyen.goldr_3.repository.AccountRepo;
import com.nguyen.goldr_3.repository.CategoryRepo;
import com.nguyen.goldr_3.repository.EntryRepo;
import com.nguyen.goldr_3.repository.UserRepo;
import com.nguyen.goldr_3.services.UserServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;
import java.util.Optional;

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

	@Autowired
	UserServices userServices;


	public static void main(String[] args) {
		SpringApplication.run(Goldr3Application.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void insertData() {

//		create user
		User user1 = new User();
		user1.setEmail("edwardnguyen@email.com");
		user1.setPassword("password");
		user1.setFirstName("Edward");
		user1.setLastName("Nguyen");
		user1.setOccupation("Software Developer");
		user1.setDob(LocalDate.of(1994, 11, 6));
		user1.setCreditScore(750);
		userServices.addUser(user1);

//		set categories
		Optional<Category> cash = categoryRepo.findById(1);
		Category _cash = cash.get();
		Optional<Category> loans = categoryRepo.findById(3);
		Category _loans = loans.get();
		Optional<Category> investments = categoryRepo.findById(4);
		Category _investments = investments.get();

//		create accounts
//		Account account2 = new Account();
//		account2.setName("PNC Savings");
//		account2.setUser(user1);
//		account2.setCategory(_cash);
//		accountRepo.save(account2);

		Account account3 = new Account();
		account3.setName("Sallie Mae");
		account3.setUser(user1);
		account3.setCategory(_loans);
		accountRepo.save(account3);

		Account account4 = new Account();
		account4.setName("Schwab 401(k)");
		account4.setUser(user1);
		account4.setCategory(_investments);
		accountRepo.save(account4);

		Account account1 = new Account();
		account1.setName("BOA Checking");
		account1.setUser(user1);
		account1.setCategory(_cash);
		accountRepo.save(account1);

//		create entries
		Entry entry1 = new Entry();
		entry1.setAmount(1000.00);
		entry1.setDate(LocalDate.now().minusDays(30));
		entry1.setAccount(account1);
		entry1.setUser(user1);
		entryRepo.save(entry1);

//		Entry entry2 = new Entry();
//		entry2.setAmount(2000.00);
//		entry2.setDate(LocalDate.now().minusDays(30));
//		entry2.setAccount(account2);
//		entry2.setUser(user1);
//		entryRepo.save(entry2);

		Entry entry3 = new Entry();
		entry3.setAmount(-100.00);
		entry3.setDate(LocalDate.now().minusDays(30));
		entry3.setAccount(account3);
		entry3.setUser(user1);
		entryRepo.save(entry3);

		Entry entry4 = new Entry();
		entry4.setAmount(10000.00);
		entry4.setDate(LocalDate.now().minusDays(30));
		entry4.setAccount(account4);
		entry4.setUser(user1);
		entryRepo.save(entry4);

		Entry entry5 = new Entry();
		entry5.setAmount(1234.56);
		entry5.setDate(LocalDate.now());
		entry5.setAccount(account1);
		entry5.setUser(user1);
		entryRepo.save(entry5);

//		Entry entry6 = new Entry();
//		entry6.setAmount(2345.67);
//		entry6.setDate(LocalDate.now());
//		entry6.setAccount(account2);
//		entry6.setUser(user1);
//		entryRepo.save(entry6);

		Entry entry7 = new Entry();
		entry7.setAmount(-50.00);
		entry7.setDate(LocalDate.now());
		entry7.setAccount(account3);
		entry7.setUser(user1);
		entryRepo.save(entry7);

		Entry entry8 = new Entry();
		entry8.setAmount(11000.00);
		entry8.setDate(LocalDate.now());
		entry8.setAccount(account4);
		entry8.setUser(user1);
		entryRepo.save(entry8);

	}

}
