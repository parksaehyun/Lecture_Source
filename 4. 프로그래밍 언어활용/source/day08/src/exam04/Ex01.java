package exam04;

import static java.lang.Math.*;  // 앞에 static 붙이고 import하면 식에 일일이 Math 안붙여도 ㅇㅋ

public class Ex01 {
    public static void main(String[] args) {
        System.out.println(abs(-10)); // 절대값  10
        System.out.println(ceil(10.2)); // 올림  11
        System.out.println(floor(10.2)); // 버림  10
        System.out.println(round(10.5)); // 반올림  11
        System.out.println(pow(2,4)); // 제곱(2의 4승)  16
        System.out.println(sqrt(9)); // root값  3
        System.out.println(random()); // 무작위 난수
    }
}
