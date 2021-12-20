package com.galvanize.tmo.paspringstarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
public class LibraryController {

    @Autowired
    LibraryService service;

    @GetMapping("/health")
    public void health() {

    }

    @PostMapping("/api/books")
    public ResponseEntity<Map<String, Object>> addBook(@RequestBody Map<String, Object> requestBody) {

        if (!requestBody.containsKey("author") || !requestBody.containsKey("title")
                || !requestBody.containsKey("yearPublished")
                || !requestBody.containsKey("author")
                || !(requestBody.get("title") instanceof String)
                || !(requestBody.get("author") instanceof String)
                || !(requestBody.get("yearPublished") instanceof Integer)) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing Required Parameters");
        }

        Book newBook = new Book();
        newBook.setAuthor((String) requestBody.get("author"));
        newBook.setTitle((String) requestBody.get("title"));
        newBook.setYearPublished((int) requestBody.get("yearPublished"));

        Map<String, Object> response = service.addBook(newBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/api/books")
    public ResponseEntity<List<Map<String, Object>>> getBookList(){
        List<Map<String, Object>> bookList= service.getOrderedBooks();
        return ResponseEntity.status(HttpStatus.OK).body(bookList);
    }

    @DeleteMapping("/api/books")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooks() {
        service.deleteBooks();
    }
}
