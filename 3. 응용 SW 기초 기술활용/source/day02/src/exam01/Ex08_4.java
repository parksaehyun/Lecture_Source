package exam01;

public class Ex08_4 {
    //구구단을 홀수 단만 출력하도록 프로그램을 만드시오. 구현 소스
    public static void main(String[] args) {
        for(int i = 1; i<= 9 ; i++){

            if(i % 2 == 0){
                continue;
            }

            for(int j=1 ; j <=9; j++) {
                int result = i * j;
                System.out.printf("%d * %d = %d%n",i, j, result );
            }
        }
    }
}
