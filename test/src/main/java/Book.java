public class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private String publishedDate;

    public Book(int id, String title, String author, String genre, String publishedDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publishedDate = publishedDate;
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

    public String getGenre() {
        return genre;
    }

    public String getPublishedDate() {
        return publishedDate;
    }
}