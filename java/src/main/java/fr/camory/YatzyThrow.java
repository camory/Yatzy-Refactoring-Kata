package fr.camory;

import io.vavr.collection.List;
import io.vavr.collection.Map;

import static java.util.function.Function.identity;

public class YatzyThrow {
    private final List<Die> dice;

    public YatzyThrow(Die d1, Die d2, Die d3, Die d4, Die d5) {
        dice = List.of(d1, d2, d3, d4, d5);
    }

    List<Die> dice() {
        return dice;
    }

    Map<Die, Integer> diceCount() {
        return dice.groupBy(identity()).mapValues(List::size);
    }

    boolean contains(Die die) {
        return dice.contains(die);
    }
}
