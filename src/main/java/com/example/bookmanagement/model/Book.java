package com.example.bookmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Books")
public class Book {
    @Id
    private String Id;

    @Indexed(unique = true)
    @Field
    private String ISBN;
    @Field
    private String BookTitle;
    @Field
    private String PublicationYear;
    @Field
    private String Language;
    @Field
    private int NumberOfCopies;
    @Field
    private int NumberOfCopiesAvailable;
}