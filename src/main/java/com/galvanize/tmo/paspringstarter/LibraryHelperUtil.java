package com.galvanize.tmo.paspringstarter;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LibraryHelperUtil {
    private static ArrayList<Book> bookList= new ArrayList<Book>();

    public void deleteAllBooks(){
        bookList.clear();
    }

    public void addBook(Book newBook){
        bookList.add(newBook);
    }

    public int getListSize (){
        return bookList.size();
    }

    public Map<String,Object> getSortedMapList(){
        LibraryHelperUtil.bookList.sort(new BookComparator());

        List<Map<String,Object>> bookMapList=
                bookList.stream().map(Book::returnMap).collect(Collectors.toList());
        Map<String,Object> bookMap=
                new HashMap<String, Object>();
        bookMap.put("books",bookMapList);
        return bookMap;
    }
}
