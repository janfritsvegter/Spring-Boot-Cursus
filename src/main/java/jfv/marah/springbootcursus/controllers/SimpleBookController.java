package jfv.marah.springbootcursus.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SimpleBookController {
    //attributes
    private List<String> simplebooks = new ArrayList<>();

    public SimpleBookController() {
        simplebooks.add("In de ban van de Ring");
        simplebooks.add("Harry Potter and the prisoner of Azkaban");
    }

    @GetMapping(value = "/simplebooks")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getBooksLes1(){
        return simplebooks;
    }

    @GetMapping(value = "/simplebooks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getName(@PathVariable int id) {
        return simplebooks.get(id);
    }

    @DeleteMapping(value = "/simplebooks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteName(@PathVariable int id) {
        simplebooks.remove(id);
        return "Deleted!";
    }

    @PostMapping(value = "/simplebooks")
    @ResponseStatus(HttpStatus.CREATED)
    public String addName(@RequestBody String name) {
        simplebooks.add(name);
        return "Added!";
    }
}
