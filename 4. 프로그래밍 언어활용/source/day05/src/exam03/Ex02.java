package exam03;

public class Ex02 {
    public static void main(String[] args) {
        Animal[] animals = {new Human(), new Bird(), new Tiger()}; //선언과 동시에 초기화
        // Animal[] animals = new Animal[3];
        // animals[0] = new Human();
        // animals[1] = new Bird();
        // animals[2] = new Tiger();

        for (Animal animal : animals) { //향상된 for문
            animal.move();

            if (animal instanceof Human) {
                Human human = (Human)animal; //형변환
                human.reading(); //범위가 늘어나서 reading호출가능
            } else if (animal instanceof Tiger) {
                Tiger tiger = (Tiger) animal;
                tiger.hunting();
            }
        }
    }
}
