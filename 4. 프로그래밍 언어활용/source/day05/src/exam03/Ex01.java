package exam03;

public class Ex01 {
    public static void main(String[] args) {
        Human human = new Human(); // Human, Animal (다형성)
        human.move();

        Tiger tiger = new Tiger(); // Tiger, Animal (다형성)
        tiger.move();

        Bird bird = new Bird(); // Bird, Animal (다형성)
        bird.move();

        //Human [] animals = new Human[3];
        //animals[0] = human;
        // animals[1] = tiger; // 자료형이 달라서 에러


    }
}
