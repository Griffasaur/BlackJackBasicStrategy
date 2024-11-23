package Model;

public class Card {

    int value;
    String name;

    public Card(int value, String name) {
        this.value = value;
        this.name = name;
    }
    Card() {}

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Card createCard(int value, String name) {
        return new Card(value, name);
    }
}
