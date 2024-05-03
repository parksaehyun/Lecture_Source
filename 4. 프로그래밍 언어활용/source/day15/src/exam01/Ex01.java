package exam01;

import java.util.Arrays;

public class Ex01 {
    public static void main(String[] args) {
        String[] fruits = {"Apple", "Orange","Apple", "Banana", "Melon", "Mango"};

        /**
         * 1. 중복 제거
         * 2. 문자열 길이 변경(String -> int : mapToInt : String -> int
         * 3. int[]배열로 변경
         * */

        int[] nums =  Arrays.stream(fruits)
                            .distinct() //중복제거
                            .mapToInt(String::length) //문자열길이만큼 정수로
                            .toArray(); //배열로 바꾸기 // 최종연산
    }
}
