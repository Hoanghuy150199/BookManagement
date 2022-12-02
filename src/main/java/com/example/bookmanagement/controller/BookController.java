package com.example.bookmanagement.controller;

import com.example.bookmanagement.model.Book;
import com.example.bookmanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @PostMapping("/book")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        try{
            Book _book = bookRepository.save(new Book(
                    book.getId(),
                    book.getISBN(),
                    book.getBookTitle(),
                    book.getPublicationYear(),
                    book.getLanguage(),
                    book.getNumberOfCopies(),
                    book.getNumberOfCopiesAvailable()
            ));
            return new ResponseEntity<>(_book, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") String id, @RequestBody Book book){
        Optional<Book> bookData = bookRepository.findById(id);
        if(bookData.isPresent()){
            Book _book = bookData.get();
            _book.setISBN(book.getISBN());
            _book.setBookTitle(book.getBookTitle());
            _book.setPublicationYear(book.getPublicationYear());
            _book.setLanguage(book.getLanguage());
            _book.setNumberOfCopies(book.getNumberOfCopies());
            _book.setNumberOfCopiesAvailable(book.getNumberOfCopiesAvailable());
            return new ResponseEntity<>(bookRepository.save(_book), HttpStatus.OK);
        }
        else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable("id") String id){
        try{
            bookRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/bookall")
    public ResponseEntity<HttpStatus> deleteAllBooks(){
        try {
            bookRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bookall")
    public ResponseEntity<List<Book>> getAllBooks(){
        try{
            List<Book> bookList = new ArrayList<>();
            bookRepository.findAll().forEach(bookList::add);
            if(bookList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(bookList, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
