package jfv.marah.springbootcursus.models;

public class Book {

    // attributes
    private String title;
    private String author;
    private String isbn;

    // geen constructor dus lege constructor


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
