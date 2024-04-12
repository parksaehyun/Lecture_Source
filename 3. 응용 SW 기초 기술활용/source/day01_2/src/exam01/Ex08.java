package exam01;

public class Ex08 {
    public static void main(String[] args) {
        byte num1 = 100; // int -> byte

        int num2 = num1; // 자동형변환 / 묵시적 형변화
        // 작은 자료형에서 큰 자료형으로 바꿔도 문제가 없다. 왜? 공간이 충분하니까ㅏ
        long num3 = num2; // 자동형변환 / 묵시적 형변화
    }
}
