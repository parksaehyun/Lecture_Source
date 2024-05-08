package exam02;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class Ex05 {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate lastYear = today.with(ChronoField.YEAR, 2023);
        System.out.println("기존꺼, 변경x : " + today);
        System.out.println("새로운 객체, 변경ㅇ : " + lastYear);
    }
}
