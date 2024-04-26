package exam03;

//@MyAnno(min=10, max=100)
//@MyAnno(names = {"이름1", "이름2"})
//@MyAnno(names = "이름1") // 1개만 있을 때에는 {} 생략가능
//@MyAnno(value = "이름1")
//@MyAnno("이름") // value="이름" 설정이 1개면 value생략 가능
@MyAnno(value="이름", min=20)
public class Ex02 {

}
