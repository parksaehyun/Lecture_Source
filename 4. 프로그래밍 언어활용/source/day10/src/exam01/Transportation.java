package exam01;

public enum Transportation {
    BUS(1400), //Transportation의 객체, 정적 상수 형태
    SUBWAY(4500),
    TAXI(1300);

    private final int fare; //final 안써도 되지만 관례적으로 상수로 해줌

    Transportation(int fare) {
        this.fare = fare;
    }

    public int getFare(){
        return fare;
    }

    //public abstract int getTotal(int person);
}
