package fr.camory;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Comparator.reverseOrder;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;
import static java.util.stream.IntStream.of;

class Yatzy {

    private static final HashSet<Integer> SMALL_STRAIGHT = new HashSet<>(asList(1, 2, 3, 4, 5));
    private static final HashSet<Integer> LARGE_STRAIGHT = new HashSet<>(asList(2, 3, 4, 5, 6));

    private final int[] dice;

    Yatzy(int d1, int d2, int d3, int d4, int d5) {
        if (!isSixSidedDice(d1, d2, d3, d4, d5)) {
            throw new IllegalStateException("Yatzy is only played with Six Sided Dice");
        }

        dice = new int[]{d1, d2, d3, d4, d5};
    }

    private boolean isSixSidedDice(int... dice) {
        return of(dice).allMatch(die -> die >= 1 && die <= 6);
    }

    int chance() {
        return of(dice).sum();
    }

    int yatzy() {
        return of(dice)
                .boxed()
                .collect(groupingBy(identity(), counting()))
                .size() == 1 ? 50 : 0;
    }

    int ones() {
        return count(1);
    }

    int twos() {
        return count(2);
    }

    int threes() {
        return count(3);
    }

    int fours() {
        return count(4);
    }

    int fives() {
        return count(5);
    }

    int sixes() {
        return count(6);
    }

    private int count(int die) {
        return of(dice).filter(x -> x == die).sum();
    }

    int onePair() {
        return reverseOrderedGroupsBy(2).findFirst().orElse(0) * 2;
    }

    int twoPair() {
        return reverseOrderedGroupsBy(2).limit(2).mapToInt(Integer::intValue).sum() * 2;
    }

    int fourOfAKind() {
        return reverseOrderedGroupsBy(4).findFirst().orElse(0) * 4;
    }

    int threeOfAKind() {
        return reverseOrderedGroupsBy(3).findFirst().orElse(0) * 3;
    }

    private Stream<Integer> reverseOrderedGroupsBy(int value) {
        return of(dice)
                .boxed()
                .collect(groupingBy(identity(), counting()))
                .entrySet()
                .stream()
                .filter(dieCount -> dieCount.getValue() >= value)
                .map(Map.Entry::getKey)
                .sorted(reverseOrder());
    }

    int smallStraight() {
        return SMALL_STRAIGHT.equals(of(dice).boxed().collect(toSet())) ? 15 : 0;
    }

    int largeStraight() {
        return LARGE_STRAIGHT.equals(of(dice).boxed().collect(toSet())) ? 20 : 0;
    }

    int fullHouse() {
        final Optional<Integer> maybePair = reverseOrderedGroupsBy(2).findFirst();
        final Optional<Integer> maybeThreeOfKind = reverseOrderedGroupsBy(3).findFirst();

        return maybePair.flatMap(pair ->
                maybeThreeOfKind.map(three -> three.equals(pair) ? 0 : three * 3 + pair * 2)
        ).orElse(0);
    }
}
