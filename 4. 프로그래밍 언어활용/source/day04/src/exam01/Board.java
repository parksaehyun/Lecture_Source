package exam01;

public class Board {
    //private static Board instance = new Board(); //클래스 로드 시점부터 객체 생성

    private static Board instance; //필요할때만 쓰게 선언만 하고 값 넣지x
    static {
        System.out.println("처음부터 실행, 객체생성과 관련 없이");
    }

    private Board () {} //생성자 함수를 막는다? , private 때문에 외부에서 객체 못만듬

    public static Board getInstance(){
        if ( instance == null) { // 필요한 시점 최소 1번만 생성
            instance = new Board();
        }

        return instance;
    }
}
