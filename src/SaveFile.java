package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveFile  implements IFile{
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

    @Override
    public void SaveFileBooks() {
        try {
            FileWriter writer = new FileWriter("books.txt", false);
            for (Book book : books) {
                writer.write(book.getId() + "," +
                        book.getName() + "," +
                        book.getAuthor() + "," +
                        book.getCategory() + "," +
                        book.isAvailable() + "\n");
            }
            writer.close();
            System.out.println("Books saved successfully.");
        } catch (IOException e) {
            System.out.println("❌ Lỗi khi lưu sách: " + e.getMessage());
        }
    }

    @Override
    public void SaveFileUser() {

    }

    @Override
    public void loadFileBook() {
        try (BufferedReader reader = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    String id = data[0];
                    String name = data[1];
                    String author = data[2];
                    String category = data[3];
                    boolean available = Boolean.parseBoolean(data[4]);

                    books.add(new Book(id, name, author, category, available));
                }
            }
            System.out.println("✅ Books loaded from file.");
        } catch (IOException e) {
            System.out.println("⚠️ Failed to load books: " + e.getMessage());
        }
    }

    @Override
    public void loadFileUser() {

    }
}
