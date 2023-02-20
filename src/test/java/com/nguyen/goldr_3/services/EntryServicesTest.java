package com.nguyen.goldr_3.services;

/*
    testing method getEntriesByUserId in EntryServices
 */

import com.nguyen.goldr_3.model.Entry;
import com.nguyen.goldr_3.model.User;
import com.nguyen.goldr_3.repository.EntryRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class EntryServicesTest {

    private EntryServices entryServices;

    @Mock
    private EntryRepo entryRepo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        entryServices = new EntryServices();
        entryServices.setEntryRepo(entryRepo);
    }

    @Test
    public void testGetEntriesByUserId() {

//        given
        User testUser = new User();
        testUser.setId(8);

        Entry testEntry = new Entry();
        testEntry.setAmount(18.00);
        testEntry.setUser(testUser);

        List<Entry> expectedEntries = new ArrayList<>();
        expectedEntries.add(testEntry);

        when(entryRepo.findByUserId(testUser.getId())).thenReturn(expectedEntries);

//        when
        List<Entry> actualEntries = entryServices.getEntriesByUserId(testUser.getId());

//        then
        assertEquals(expectedEntries, actualEntries);

    }

}
