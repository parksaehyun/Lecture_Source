package exam01;

import java.util.ArrayList;

public class Ex03 {
    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1000, "책1", "저자1"));
        books.add(new Book(1000, "책2", "저자2"));
        books.add(new Book(1000, "책3", "저자3"));
        books.add(new Book(1000, "책4", "저자4"));
        books.add(new Book(1000, "책5", "저자5"));

        for(Book book : books){ //향상된 for문
            System.out.println(book);
        }
        System.out.println("-------코드3줄도 길다 -> 1줄로 줄이기-------");
        books.forEach(System.out::println);
    }
}
