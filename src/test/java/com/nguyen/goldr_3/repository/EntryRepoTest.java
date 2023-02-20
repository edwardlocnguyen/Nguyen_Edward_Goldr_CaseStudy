package com.nguyen.goldr_3.repository;

import com.nguyen.goldr_3.model.Entry;
import com.nguyen.goldr_3.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/*
    testing custom query findByUserId in EntryRepo
 */

@ExtendWith(MockitoExtension.class)
public class EntryRepoTest {

    @Mock
    private EntryRepo entryRepo;

    @Test
    public void testFindByUserId() {
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
        List<Entry> actualEntries = entryRepo.findByUserId(testUser.getId());

//        then
        assertThat(actualEntries).isNotNull();
        assertThat(actualEntries.size()).isEqualTo(expectedEntries.size());
        assertThat(actualEntries).containsAll(expectedEntries);

    }

}
