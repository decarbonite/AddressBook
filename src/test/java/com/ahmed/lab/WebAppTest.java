package com.ahmed.lab;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.Before;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class WebAppTest {

    @Autowired
    private MockMvc mvc;


    @Test
    public void testGetAllBooks() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/view-books")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateAddressBook() throws Exception {
        AddressBook book = new AddressBook();
        BuddyInfo bud = new BuddyInfo("Ahmed", "12345");
        book.addBuddy(bud);

        mvc.perform(MockMvcRequestBuilders
                .post("/create")
                .content(asJsonString(book))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.buddies").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.buddies[0].name").value("Ahmed"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.buddies[0].phoneNumber").value("12345"));
    }
//
//    @Test
//    public void testAddBuddyToAddressBook() throws Exception {
//        BuddyInfo bud = new BuddyInfo("Blah", "22");
//
//        mvc.perform(MockMvcRequestBuilders
//                .post("/add-buddy")
//                .param("bookId", String.valueOf(book.getId()))
//                .content(asJsonString(bud))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Blah"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("22"));
//    }
//
//    @Test
//    public void testRemoveAddressBook() throws Exception {
//        mvc.perform(MockMvcRequestBuilders
//                .delete("/remove-book")
//                .param("bookId", String.valueOf(book.getId()))
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isAccepted());
//    }
//
//    @Test
//    public void testRemoveBuddyInfo() throws Exception {
////        AddressBook book = new AddressBook();
////        BuddyInfo bud = new BuddyInfo("Jack", "55555");
////        book.addBuddy(bud);
//
////        addressBookController.createAddressBook(book);
//
//        mvc.perform(MockMvcRequestBuilders
//                .delete("/remove-buddy")
//                .param("buddyId", String.valueOf(bud.getId()))
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isAccepted());
//    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
