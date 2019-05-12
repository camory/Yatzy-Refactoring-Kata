package fr.camory;

import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.control.Option;

import java.util.function.Predicate;

import static fr.camory.Die.*;
import static io.vavr.API.*;
import static java.util.Comparator.comparingInt;

class YatzyScorer {

    static int chance(YatzyThrow yatzyThrow) {
        return yatzyThrow.dice().map(Die::value).sum().intValue();
    }

    static int yatzy(YatzyThrow yatzyThrow) {
        return yatzyThrow.diceCount().size() == 1 ? 50 : 0;
    }

    static int ones(YatzyThrow yatzyThrow) {
        return count(yatzyThrow, ONE);
    }

    static int twos(YatzyThrow yatzyThrow) {
        return count(yatzyThrow, TWO);
    }

    static int threes(YatzyThrow yatzyThrow) {
        return count(yatzyThrow, THREE);
    }

    static int fours(YatzyThrow yatzyThrow) {
        return count(yatzyThrow, FOUR);
    }

    static int fives(YatzyThrow yatzyThrow) {
        return count(yatzyThrow, FIVE);
    }

    static int sixes(YatzyThrow yatzyThrow) {
        return count(yatzyThrow, SIX);
    }

    private static int count(YatzyThrow yatzyThrow, Die die) {
        return yatzyThrow.diceCount().get(die).getOrElse(0) * die.value();
    }

    static int onePair(YatzyThrow yatzyThrow) {
        return yatzyThrow.diceCount()
                .filterValues(v -> v >= 2)
                .maxBy(comparingInt(dieCount1 -> dieCount1._1.value()))
                .map(dieCount -> dieCount._1.value())
                .getOrElse(0) * 2;
    }

    static int twoPair(YatzyThrow yatzyThrow) {
        final Map<Die, Integer> pairs = yatzyThrow.diceCount().filterValues(v -> v >= 2);
        final Predicate<Map<Die, Integer>> is2Pairs = p -> pairs.size() == 2;
        final Predicate<Map<Die, Integer>> isMore3OfAKind = p -> pairs.size() == 1 && pairs.get()._2 > 3;

        return Match(pairs).of(
                Case($(is2Pairs), pairs.map(dieCount -> dieCount._1.value()).sum().intValue() * 2),
                Case($(isMore3OfAKind), pairs.get()._1.value() * 4),
                Case($(), 0)
        );
    }

    static int threeOfAKind(YatzyThrow yatzyThrow) {
        return yatzyThrow.diceCount()
                .find(dieCount -> dieCount._2 >= 3)
                .map(dieCount -> dieCount._1.value())
                .getOrElse(0) * 3;
    }

    static int fourOfAKind(YatzyThrow yatzyThrow) {
        return yatzyThrow.diceCount()
                .find(dieCount -> dieCount._2 >= 4)
                .map(dieCount -> dieCount._1.value())
                .getOrElse(0) * 4;
    }

    static int smallStraight(YatzyThrow yatzyThrow) {
        return List.of(ONE, TWO, THREE, FOUR, FIVE).removeAll(yatzyThrow.dice()).size() == 0 ? 15 : 0;
    }

    static int largeStraight(YatzyThrow yatzyThrow) {
        return List.of(TWO, THREE, FOUR, FIVE, SIX).removeAll(yatzyThrow.dice()).size() == 0 ? 20 : 0;
    }

    static int fullHouse(final YatzyThrow yatzyThrow) {
        final Option<Tuple2<Die, Integer>> maybeThree = yatzyThrow.diceCount().find(dieCount -> dieCount._2 == 3);
        return maybeThree.flatMap(three ->
                yatzyThrow.diceCount()
                        .find(dieCount -> dieCount._2 == 2)
                        .map(pair -> three._1.value() * 3 + pair._1.value() * 2)
        ).getOrElse(0);
    }
}
