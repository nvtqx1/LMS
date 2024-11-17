public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn; // Thêm thuộc tính isbn

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = "ISBN-" + id; // Tạo mã ISBN giả
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn; // Getter cho ISBN
    }
}
