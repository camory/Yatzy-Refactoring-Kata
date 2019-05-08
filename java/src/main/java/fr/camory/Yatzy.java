package fr.camory;

import java.util.Map;
import java.util.stream.Stream;

import static java.util.Comparator.*;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.IntStream.of;

public class Yatzy {

    private final int[] dice;

    public Yatzy(int d1, int d2, int d3, int d4, int d5) {
        if (!isSixSidedDice(d1, d2, d3, d4, d5)) {
            throw new IllegalStateException("Yatzy is only played with Six Sided Dice");
        }

        dice = new int[] {d1, d2, d3, d4, d5};
    }

    private boolean isSixSidedDice(int... dice) {
        return of(dice).allMatch(die -> die >= 1 && die <= 6);
    }

    public int chance() {
        return of(dice).sum();
    }

    public int yatzy() {
        return of(dice)
                .boxed()
                .collect(groupingBy(identity(), counting()))
                .size() == 1 ? 50 : 0;
    }

    public int ones() {
        return count(1);
    }

    public int twos() {
        return count(2);
    }

    public int threes() {
        return count(3);
    }

    public int fours() {
        return count(4);
    }

    public int fives() {
        return count(5);
    }

    public int sixes() {
        return count(6);
    }

    private int count(int die) {
        return of(dice).filter(x -> x == die).sum();
    }

    public int onePair() {
        return reverseOrderedPairs().limit(1).findFirst().orElse(0) * 2;
    }

    public int twoPair() {
        return reverseOrderedPairs().limit(2).mapToInt(Integer::intValue).sum() * 2;
    }

    private Stream<Integer> reverseOrderedPairs() {
        return of(dice)
                .boxed()
                .collect(groupingBy(identity(), counting()))
                .entrySet()
                .stream()
                .filter(dieCount -> dieCount.getValue() >= 2)
                .map(Map.Entry::getKey)
                .sorted(reverseOrder());
    }

    public static int four_of_a_kind(int _1, int _2, int d3, int d4, int d5)
    {
        int[] tallies;
        tallies = new int[6];
        tallies[_1-1]++;
        tallies[_2-1]++;
        tallies[d3-1]++;
        tallies[d4-1]++;
        tallies[d5-1]++;
        for (int i = 0; i < 6; i++)
            if (tallies[i] >= 4)
                return (i+1) * 4;
        return 0;
    }

    public static int three_of_a_kind(int d1, int d2, int d3, int d4, int d5)
    {
        int[] t;
        t = new int[6];
        t[d1-1]++;
        t[d2-1]++;
        t[d3-1]++;
        t[d4-1]++;
        t[d5-1]++;
        for (int i = 0; i < 6; i++)
            if (t[i] >= 3)
                return (i+1) * 3;
        return 0;
    }

    public static int smallStraight(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies;
        tallies = new int[6];
        tallies[d1-1] += 1;
        tallies[d2-1] += 1;
        tallies[d3-1] += 1;
        tallies[d4-1] += 1;
        tallies[d5-1] += 1;
        if (tallies[0] == 1 &&
                tallies[1] == 1 &&
                tallies[2] == 1 &&
                tallies[3] == 1 &&
                tallies[4] == 1)
            return 15;
        return 0;
    }

    public static int largeStraight(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies;
        tallies = new int[6];
        tallies[d1-1] += 1;
        tallies[d2-1] += 1;
        tallies[d3-1] += 1;
        tallies[d4-1] += 1;
        tallies[d5-1] += 1;
        if (tallies[1] == 1 &&
                tallies[2] == 1 &&
                tallies[3] == 1 &&
                tallies[4] == 1
                && tallies[5] == 1)
            return 20;
        return 0;
    }

    public static int fullHouse(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies;
        boolean _2 = false;
        int i;
        int _2_at = 0;
        boolean _3 = false;
        int _3_at = 0;




        tallies = new int[6];
        tallies[d1-1] += 1;
        tallies[d2-1] += 1;
        tallies[d3-1] += 1;
        tallies[d4-1] += 1;
        tallies[d5-1] += 1;

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 2) {
                _2 = true;
                _2_at = i+1;
            }

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 3) {
                _3 = true;
                _3_at = i+1;
            }

        if (_2 && _3)
            return _2_at * 2 + _3_at * 3;
        else
            return 0;
    }
}
