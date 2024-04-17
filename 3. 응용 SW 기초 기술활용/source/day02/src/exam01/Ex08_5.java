package exam01;

public class Ex08_5 {
    // 구구단을 단보다 곱하는 수가 크거나 같은 경우만 출력하는 프로그램을 만들어 보세요.구현 소스
    public static void main(String[] args) {
        for(int i =1 ; i <= 9 ; i++){
            System.out.println(i + "단=========");

            for(int j=i; j <=9; j++){
                int result = i*j;
                System.out.printf("%d * %d = %d%n", i, j, result);
            }
        }
    }

}
