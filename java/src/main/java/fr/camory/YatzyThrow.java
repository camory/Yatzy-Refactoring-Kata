package fr.camory;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class YatzyThrow {
    private final List<Die> dice;

    public YatzyThrow(Die d1, Die d2, Die d3, Die d4, Die d5) {
        dice = asList(d1, d2, d3, d4, d5);
    }

    Stream<Die> stream() {
        return dice.stream();
    }

    Stream<Map.Entry<Die, Long>> diceCount() {
        return dice.stream()
                .collect(groupingBy(identity(), counting()))
                .entrySet()
                .stream();

    }

    Optional<Long> count(Die die) {
        return diceCount()
                .filter(dieCount-> dieCount.getKey()==die)
                .map(Map.Entry::getValue)
                .findFirst();
    }

    boolean contains(Die die) {
        return dice.contains(die);
    }
}
