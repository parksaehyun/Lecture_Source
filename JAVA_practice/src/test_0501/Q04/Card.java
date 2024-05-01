package test_0501.Q04;

public class Card {
    public int cardNumber;

    public static int nextCardNumber = 1;

    public Card() {
        this.cardNumber = nextCardNumber++;
    }

    public int getCardNumber() {
        return cardNumber;
    }
}

