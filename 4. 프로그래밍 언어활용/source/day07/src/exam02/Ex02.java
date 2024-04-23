package exam02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

// 런타임x

public class Ex02 {
    public static void main(String[] args) {
        // Throw new FileNotFoundException(...)
        try {
            FileInputStream fis = new FileInputStream("b.txt");
            System.out.println("파일 처리...");
        } catch (FileNotFoundException e) {
            System.out.println("예외발생!");
            //String message = e.getMessage(); // e : 예외객체
            //System.out.println(message);
            e.printStackTrace();
        }
        System.out.println("중요한 코드 실행!");
    }
}
