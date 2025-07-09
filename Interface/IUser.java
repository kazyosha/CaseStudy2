package Interface;

import src.Book;

public interface IUser {
    void borrowBook(Book book);
    void returnBook(Book book);
}
