package exam01;

import java.util.Arrays;

public class Ex03 {
    public static void main(String[] args) {
        int[] scores = {67, 80, 100, 98, 76, 56};
        //reduce(초기값, 매개변수 반환값 / 바이너리니까 2개)
        int total = Arrays.stream(scores).reduce(0, (n1, n2) -> n1 + n2);
        System.out.println(total);
    }
}
