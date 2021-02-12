package com.ahmed.lab;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressBook {

    @Id
    @GeneratedValue(generator = "addressBook", strategy = GenerationType.IDENTITY)
    long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn
    private List<BuddyInfo> buddies;

    public AddressBook() {
        buddies = new ArrayList<>();
    }

    public void addBuddy(BuddyInfo buddy) {
        if (buddy != null) {
            buddies.add(buddy);
        } else {
            System.out.println("\n\n =====================BUDDY IS NULL\n\n ========================");
        }
    }

    public void removeBuddy(int index) {
        if (index >= 0 && index < buddies.size()) {
            buddies.remove(index);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<BuddyInfo> getBuddies() {
        return buddies;
    }

    public void setBuddies(List<BuddyInfo> buddies) {
        this.buddies = buddies;
    }

    @Override
    public String toString() {
        return "AddressBook{" +
                "id=" + id +
                ", buddies=" + buddies +
                '}';
    }
}
