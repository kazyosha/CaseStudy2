package Interface;

import src.Book;
import src.User;

import java.util.ArrayList;

public interface IFile {
    void saveToFile();

    void saveUserFile();

    void ReadBookToFile();

    void ReadUserFile();
    ArrayList<User> loadUsersFromFile();
    ArrayList<Book> loadBooksFromFile();

}
