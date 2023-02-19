package com.nguyen.goldr_3.repository;

import com.nguyen.goldr_3.model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRepo extends JpaRepository<Entry, Integer> {

    List<Entry> findByUserId(Integer userId);

}
