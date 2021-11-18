package jfv.marah.springbootcursus.controllers;

import jfv.marah.springbootcursus.models.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ObjectBookController {

    // attributes
    private List<Book> objectBooks = new ArrayList<>();

    // constructor
    public ObjectBookController(){
        Book book1 = new Book();
        book1.setTitle("Harry Potter and the .....");
        book1.setAuthor("J.K. Rowlings");
        book1.setIsbn("1234587");

        Book book2 = new Book();
        book2.setTitle("The Lord of the Rings");
        book2.setAuthor("J.P.R. Tolkien");
        book2.setIsbn("35489795");

        objectBooks.add(book1);
        objectBooks.add(book2);
    }

    @GetMapping(value = "/objectbooks")
    public ResponseEntity<Object> getObjectBooks() {
        return ResponseEntity.ok(objectBooks);   // Jackson is een paket dat de vertaling doet: object => json
    }

    @GetMapping(value = "/objectbooks/{id}")
    public ResponseEntity<Object> getObjectBook(@PathVariable int id) {
        return ResponseEntity.ok(objectBooks.get(id));
    }

    @DeleteMapping(value = "/objectbooks/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable int id){
        objectBooks.remove(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/objectbooks")
    public ResponseEntity<Object> addBook(@RequestBody Book newBook){
        objectBooks.add(newBook);
        int newId = objectBooks.size()-1;
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newId).toUri();
        return ResponseEntity.created(location).build();
    }
    // created verwacht de locatie waar je het nieuwe boek kan ophalen

    @PutMapping(value = "/objectbooks/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable int id, @RequestBody Book newBook){
        objectBooks.set(id, newBook);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/objectbooks/{id}")
    public ResponseEntity<Object> patchBook(@PathVariable int id, @RequestBody Book newBook){
        Book existingBook = objectBooks.get(id);
        if(!newBook.getTitle().isEmpty()){
            existingBook.setTitle(newBook.getTitle());
        }
        if (!newBook.getAuthor().isEmpty()){
            existingBook.setAuthor(newBook.getAuthor());
        }
        if(!newBook.getIsbn().isEmpty()){
            existingBook.setIsbn(newBook.getIsbn());
        }
        objectBooks.set(id, existingBook);
        return ResponseEntity.noContent().build();
    }
/*
zou na de / tussen het endpoint en {id} moeten werken dat deed het met de repository wel
De put en de patch mapping werken niet vanwege een foutmelding
2021-11-18 13:09:32.230  WARN 9718 --- [nio-8080-exec-3] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.web.HttpRequestMethodNotSupportedException: Request method 'PATCH' not supported]
2021-11-18 13:13:19.101  WARN 9718 --- [nio-8080-exec-6] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.web.HttpRequestMethodNotSupportedException: Request method 'PUT' not supported]
 */

}
