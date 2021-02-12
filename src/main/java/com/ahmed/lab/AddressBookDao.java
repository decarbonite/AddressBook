package com.ahmed.lab;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class AddressBookDao {

    @Autowired
    AddressBookRepository addressBookRepository;

    @Autowired
    BuddyInfoRepository buddyInfoRepository;

    public List<AddressBook> getAllAddressBooks() {
        return addressBookRepository.findAll();
    }

    public AddressBook getAddressBookById(long id) {
        return addressBookRepository.findById(id);
    }

    public AddressBook createAddressBook(AddressBook addressBook) {
        return addressBookRepository.save(addressBook);
    }

    public AddressBook addBuddyToBook(BuddyInfo buddy, AddressBook book) {
        book.addBuddy(buddy);
        return addressBookRepository.save(book);
    }

    public void removeAddressBook(long bookId) {
        addressBookRepository.deleteById(bookId);
    }

    public void removeBuddyInfo(long buddyId) {
        buddyInfoRepository.deleteById(buddyId);
    }
}
