package org.openlogix;

import com.google.gson.JsonElement;

public class Book {
    Integer id;
    String name;
    String publisherName;
    String authorName;

    Book(String name2, String publisherName2, String authorName2) {
        this.name = name2;
        this.publisherName = publisherName2;
        this.authorName = authorName2;
    }

    public Book(String name2, String publisherName2, String authorName2) {
    }

    @Override
    public String toString() {
        return "Book Name - " + this.name + "\nPublisher Name - " + this.publisherName + "\nAuthor Name - " + this.authorName + "\n";
    }
}
