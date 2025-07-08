package src;

import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private ArrayList<Book> borrowedBooks = new ArrayList<>();

    public User(int id, String name, String email, String phoneNumber, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }
}
