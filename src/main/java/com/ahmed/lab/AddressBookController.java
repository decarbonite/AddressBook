package com.ahmed.lab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AddressBookController {

    @Autowired
    AddressBookRepository addressBookRepository;

    @Autowired
    BuddyInfoRepository buddyInfoRepository;

    @GetMapping(path = "/view-books")
    public String greeting(Model model) {
        model.addAttribute("buddyInfo", new BuddyInfo());
        return "addressbook";
    }

    @PostMapping(path = "/submit-data")
    public String viewAllBooks(@ModelAttribute BuddyInfo buddyInfo, Model model) {
        addBuddyToBook(buddyInfo.getAddressBookId(), buddyInfo);
        model.addAttribute("addressBook", addressBookRepository.findAll());
        return "newpage";
    }

    @GetMapping(path = "/ajax-too-good", produces = "application/json")
    @ResponseBody
    public List<AddressBook> viewAllBooksAJAX() {
        return addressBookRepository.findAll();
    }


    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<AddressBook> createAddressBook(@RequestBody AddressBook addressBook) {
        addressBookRepository.save(addressBook);
        return new ResponseEntity<>(addressBook, HttpStatus.CREATED);
    }

    @PostMapping(path = "/add-buddy", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<BuddyInfo> addBuddyToAddressBook(@RequestParam("bookId") long bookId, @RequestBody BuddyInfo buddyInfo) {
        AddressBook book = addressBookRepository.findById(bookId);
        if (book == null) book = new AddressBook();
        book.addBuddy(buddyInfo);
        addressBookRepository.save(book);
        return new ResponseEntity<>(buddyInfo, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/remove-book")
    @ResponseBody
    public ResponseEntity<HttpStatus> removeAddressBook(@RequestParam("bookId") long bookId) {
        addressBookRepository.deleteById(bookId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/remove-buddy")
    @ResponseBody
    public ResponseEntity<HttpStatus> removeBuddyInfo(@RequestParam("buddyId") long buddyId) {
        buddyInfoRepository.deleteById(buddyId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/remove-all")
    @ResponseBody
    public ResponseEntity<HttpStatus> removeAll() {
        addressBookRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    public AddressBook addBuddyToBook(long bookId, BuddyInfo buddyInfo) {
        AddressBook book = addressBookRepository.findById(bookId);
        if (book == null) book = new AddressBook();
        book.addBuddy(buddyInfo);
        addressBookRepository.save(book);
        return book;
    }

}
