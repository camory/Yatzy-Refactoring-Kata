package fr.camory;

public enum Die {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6);

    private final int value;

    Die(int value) {
        this.value = value;
    }

    int value() {
        return value;
    }
}
