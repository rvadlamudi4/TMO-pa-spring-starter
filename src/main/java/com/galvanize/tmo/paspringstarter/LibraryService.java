package com.galvanize.tmo.paspringstarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LibraryService {

    @Autowired
    LibraryHelperUtil util;

    public Map<String, Object> addBook (Book book){
        book.setId(util.getListSize()+1);
        util.addBook(book);

        return book.returnMap();
    }

    public void deleteBooks(){
        util.deleteAllBooks();
    }

    public List<Map<String, Object>> getOrderedBooks() {
        return util.getSortedMapList();
    }
}
