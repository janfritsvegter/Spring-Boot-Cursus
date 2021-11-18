package jfv.marah.springbootcursus.controllers;

import jfv.marah.springbootcursus.models.Book;
import jfv.marah.springbootcursus.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ObjectBookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping(value = "/objectbooks")
    public ResponseEntity<Object> getObjectBooks() {
        return ResponseEntity.ok(bookRepository.findAll());   // Jackson is een paket dat de vertaling doet: object => json
    }

    @GetMapping(value = "/objectbooks/{id}")
    public ResponseEntity<Object> getObjectBook(@PathVariable int id) {
        return ResponseEntity.ok(bookRepository.findById(id));
    }

    @DeleteMapping(value = "/objectbooks/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable int id){
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/objectbooks")
    public ResponseEntity<Object> addBook(@RequestBody Book newBook){
        bookRepository.save(newBook);
        int newId = (int) bookRepository.count();
                URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newId).toUri();
        return ResponseEntity.created(location).build();
    }
    // created verwacht de locatie waar je het nieuwe boek kan ophalen

    @PutMapping(value = "/objectbooks/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable int id, @RequestBody Book newBook){
        if(bookRepository.existsById(id)) {
            Book existingBook = bookRepository.findById(id).get();
            existingBook.setTitle(newBook.getTitle());
            existingBook.setAuthor(newBook.getAuthor());
            existingBook.setIsbn(newBook.getIsbn());
            bookRepository.save(existingBook);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping(value = "/objectbooks/{id}")
    public ResponseEntity<Object> partialUpdateBook(@PathVariable int id, @RequestBody Book newBook){
        if(bookRepository.existsById(id)) {
        Book existingBook = bookRepository.findById(id).get();
        if(!newBook.getTitle().isEmpty()){
            existingBook.setTitle(newBook.getTitle());
        }
        if (!newBook.getAuthor().isEmpty()){
            existingBook.setAuthor(newBook.getAuthor());
        }
        if(!newBook.getIsbn().isEmpty()){
            existingBook.setIsbn(newBook.getIsbn());
        }
        bookRepository.save(existingBook);
        return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
