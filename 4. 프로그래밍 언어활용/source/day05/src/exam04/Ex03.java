package exam04;

public class Ex03 {
    public static void main(String[] args) {
        A ad = new D(); // D -> A


        A ac = new C(); // C -> B -> A

        // C c = (C) ad;  // 출처가 달라서 에러뜸

        if (ad instanceof  C) { // false
            C c = (C) ad;
            System.out.println(1);
        }

        if (ac instanceof C) { // true
            C c = (C) ac;
            System.out.println(2);

        }
    }
}