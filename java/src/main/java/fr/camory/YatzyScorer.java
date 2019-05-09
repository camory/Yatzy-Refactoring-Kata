package fr.camory;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import static fr.camory.Die.*;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

class YatzyScorer {

    static long chance(YatzyThrow yatzyThrow) {
        return yatzyThrow.stream()
                .mapToInt(Die::value)
                .sum();
    }

    static long yatzy(YatzyThrow yatzyThrow) {
        return yatzyThrow.diceCount().count() == 1 ? 50 : 0;
    }

    static long ones(YatzyThrow yatzyThrow) {
        return yatzyThrow.count(ONE).orElse(0L) * ONE.value();
    }

    static long twos(YatzyThrow yatzyThrow) {
        return yatzyThrow.count(TWO).orElse(0L) * TWO.value();
    }

    static long threes(YatzyThrow yatzyThrow) {
        return yatzyThrow.count(THREE).orElse(0L) * THREE.value();
    }

    static long fours(YatzyThrow yatzyThrow) {
        return yatzyThrow.count(FOUR).orElse(0L) * FOUR.value();
    }

    static long fives(YatzyThrow yatzyThrow) {
        return yatzyThrow.count(FIVE).orElse(0L) * FIVE.value();
    }

    static long sixes(YatzyThrow yatzyThrow) {
        return yatzyThrow.count(SIX).orElse(0L) * SIX.value();
    }

    static long onePair(YatzyThrow yatzyThrow) {
        return yatzyThrow.diceCount()
                .filter(byAtLeastCount(2))
                .max(comparingInt(dieCount -> dieCount.getKey().value()))
                .map(dieCount -> dieCount.getKey().value())
                .orElse(0) * 2;
    }

    static long twoPair(YatzyThrow yatzyThrow) {
        final List<Map.Entry<Die, Long>> pairs = yatzyThrow.diceCount().filter(byAtLeastCount(2)).collect(toList());

        if (pairs.size() == 2) {
            return (pairs.get(0).getKey().value() + pairs.get(1).getKey().value()) * 2;
        }

        if (pairs.size() == 1 && pairs.get(0).getValue() > 3) {
            return pairs.get(0).getKey().value() * 4;
        }

        return 0;
    }

    static long threeOfAKind(YatzyThrow yatzyThrow) {
        return yatzyThrow.diceCount()
                .filter(byAtLeastCount(3))
                .findFirst()
                .map(dieCount -> dieCount.getKey().value())
                .orElse(0) * 3;
    }

    static long fourOfAKind(YatzyThrow yatzyThrow) {
        return yatzyThrow.diceCount()
                .filter(byAtLeastCount(4))
                .findFirst()
                .map(dieCount -> dieCount.getKey().value())
                .orElse(0) * 4;
    }

    static long smallStraight(YatzyThrow yatzyThrow) {
        return yatzyThrow.diceCount().count() == 5 && yatzyThrow.contains(ONE) ? 15 : 0;
    }

    static long largeStraight(YatzyThrow yatzyThrow) {
        return yatzyThrow.diceCount().count() == 5 && yatzyThrow.contains(SIX) ? 20 : 0;
    }

    static long fullHouse(final YatzyThrow yatzyThrow) {
        final Optional<Map.Entry<Die, Long>> maybeThree = yatzyThrow.diceCount().filter(byCount(3)).findFirst();
        return maybeThree.flatMap(three -> yatzyThrow.diceCount()
                .filter(byCount(2))
                .findFirst()
                .map(two -> two.getKey().value() * 2 + three.getKey().value() * 3)
        ).orElse(0);
    }


    private static Predicate<Map.Entry<Die, Long>> byCount(long count) {
        return dieCount -> dieCount.getValue() == count;
    }

    private static Predicate<Map.Entry<Die, Long>> byAtLeastCount(long count) {
        return dieCount -> dieCount.getValue() >= count;
    }
}
