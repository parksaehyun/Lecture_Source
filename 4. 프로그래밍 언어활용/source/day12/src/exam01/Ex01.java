package exam01;

import java.util.ArrayList;
import java.util.Iterator;

public class Ex01 {
    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1000, "책1", "저자1"));
        books.add(new Book(1000, "책2", "저자2"));
        books.add(new Book(1000, "책3", "저자3"));
        books.add(new Book(1000, "책4", "저자4"));
        books.add(new Book(1000, "책5", "저자5"));
        Iterator<Book> iter = books.iterator();

        while(iter.hasNext()) {
            Book book = iter.next();
            System.out.println(book);
        }


        while(iter.hasNext()) {
            Book book = iter.next();
            System.out.println(book);
        }
    }
}
