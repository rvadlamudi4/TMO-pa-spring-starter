package com.galvanize.tmo.paspringstarter;

import java.util.Comparator;

public class BookComparator implements Comparator<Book> {

    @Override
    public int compare(Book prev, Book next) {
        return prev.getTitle().compareTo(next.getTitle());
    }
}
