package com.asqint.webLib.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="book")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer year;
    private String isbn;
    private String genre;
    private String author;
    private String filename;
    @Lob
    @Column(name = "description", columnDefinition = "MEDIUMTEXT")
    private String description;

    public Book() {
    }

    public Book(String name, Integer year, String isbn, String genre, String author, String description) {
        this.name = name;
        this.year = year;
        this.isbn = isbn;
        this.genre = genre;
        this.author = author;
        this.description = description;
    }

}
