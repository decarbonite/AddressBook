package com.ahmed.lab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AddressBookController {

    @Autowired
    AddressBookDao addressBookDao;

    @GetMapping(path = "/view-books")
    public String greeting(Model model) {
        model.addAttribute("addressBook", addressBookDao.getAllAddressBooks());
        return "addressbook";
    }


    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public AddressBook createAddressBook(@RequestBody AddressBook addressBook) {
        return addressBookDao.createAddressBook(addressBook);
    }

    @PostMapping(path = "/add-buddy", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public AddressBook addBuddyToAddressBook(@RequestParam("bookId") long bookId, @RequestBody BuddyInfo buddyInfo) {
        AddressBook book = addressBookDao.getAddressBookById(bookId);
        return addressBookDao.addBuddyToBook(buddyInfo, book);
    }

    @DeleteMapping(path = "/remove-book")
    @ResponseBody
    public void removeAddressBook(@RequestParam("bookId") long bookId) {
        addressBookDao.removeAddressBook(bookId);
    }

    @DeleteMapping(path = "/remove-buddy")
    @ResponseBody
    public void removeBuddyInfo(@RequestParam("buddyId") long buddyId) {
        addressBookDao.removeBuddyInfo(buddyId);
    }
}
