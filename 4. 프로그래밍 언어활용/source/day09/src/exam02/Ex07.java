package exam02;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex07 {
    public static void main(String[] args) {
        String str = "java one\njava two\njava three"; //줄개행 했지만 하나의 문장으로 봄
        Pattern p1 = Pattern.compile("^\\w+\\s");
        //Pattern p1 = Pattern.compile("^\\w+\\s", Pattern.MULTILINE); // 여러줄에 걸쳐 패턴 체크
        Matcher m1 = p1.matcher(str);
        if(m1.find()){
            System.out.println(m1.group());
        }

        if(m1.find()){
            System.out.println(m1.group());
        }

        if(m1.find()){
            System.out.println(m1.group());
        }
        //System.out.println(str);
    }
}
