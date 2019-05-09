package fr.camory;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import java.util.stream.Stream;

import static fr.camory.Die.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

@RunWith(JUnitQuickcheck.class)
public class YatzyScorerTest {

    @Property
    public void chance_should_score_sum_of_all_dice(YatzyThrow yatzyThrow) {
        //given

        // when
        final long score = YatzyScorer.chance(yatzyThrow);

        // then
        assertThat(score).isEqualTo(yatzyThrow.stream().mapToLong(Die::value).sum());
    }

    @Property
    public void yatzy_should_score_50_with_five_equal_die(Die die) {
        // given
        final YatzyThrow yatzyThrow = new YatzyThrow(die, die, die, die, die);

        // when
        final long score = YatzyScorer.yatzy(yatzyThrow);

        // then
        assertThat(score).isEqualTo(50);
    }

    @Property
    public void yatzy_should_score_0_with_five_non_equal_die(Die d1, Die d2, Die d3, Die d4, Die d5) {
        // given
        assumeThat(Stream.of(d1, d2, d3, d4, d5).distinct().count()).isNotEqualTo(1);
        final YatzyThrow yatzyThrow = new YatzyThrow(d1, d2, d3, d4, d5);

        // when
        final long score = YatzyScorer.yatzy(yatzyThrow);

        // then
        assertThat(score).isEqualTo(0);
    }

    @Property
    public void ones_should_sum_1s(Die d1, Die d2, Die d3, Die d4, Die d5) {
        // given
        final YatzyThrow yatzyThrow = new YatzyThrow(d1, d2, d3, d4, d5);

        // when
        final long score = YatzyScorer.ones(yatzyThrow);

        // then
        assertThat(score).isEqualTo(Stream.of(d1, d2, d3, d4, d5).mapToLong(Die::value).filter(i -> i == 1).sum());
    }

    @Property
    public void twos_should_sum_2s(Die d1, Die d2, Die d3, Die d4, Die d5) {
        // given
        final YatzyThrow yatzyThrow = new YatzyThrow(d1, d2, d3, d4, d5);

        // when
        final long score = YatzyScorer.twos(yatzyThrow);

        // then
        assertThat(score).isEqualTo(Stream.of(d1, d2, d3, d4, d5).mapToLong(Die::value).filter(i -> i == 2).sum());
    }

    @Property
    public void threes_should_sum_3s(Die d1, Die d2, Die d3, Die d4, Die d5) {
        // given
        final YatzyThrow yatzyThrow = new YatzyThrow(d1, d2, d3, d4, d5);

        // when
        final long score = YatzyScorer.threes(yatzyThrow);

        // then
        assertThat(score).isEqualTo(Stream.of(d1, d2, d3, d4, d5).mapToLong(Die::value).filter(i -> i == 3).sum());
    }

    @Property
    public void fours_should_sum_4s(Die d1, Die d2, Die d3, Die d4, Die d5) {
        // given
        final YatzyThrow yatzyThrow = new YatzyThrow(d1, d2, d3, d4, d5);

        // when
        final long score = YatzyScorer.fours(yatzyThrow);

        // then
        assertThat(score).isEqualTo(Stream.of(d1, d2, d3, d4, d5).mapToLong(Die::value).filter(i -> i == 4).sum());
    }

    @Property
    public void fives_should_sum_5s(Die d1, Die d2, Die d3, Die d4, Die d5) {
        // given
        final YatzyThrow yatzyThrow = new YatzyThrow(d1, d2, d3, d4, d5);

        // when
        final long score = YatzyScorer.fives(yatzyThrow);

        // then
        assertThat(score).isEqualTo(Stream.of(d1, d2, d3, d4, d5).mapToLong(Die::value).filter(i -> i == 5).sum());
    }

    @Property
    public void sixes_should_sum_6s(Die d1, Die d2, Die d3, Die d4, Die d5) {
        // given
        final YatzyThrow yatzyThrow = new YatzyThrow(d1, d2, d3, d4, d5);

        // when
        final long score = YatzyScorer.sixes(yatzyThrow);

        // then
        assertThat(score).isEqualTo(Stream.of(d1, d2, d3, d4, d5).mapToLong(Die::value).filter(i -> i == 6).sum());
    }

    @Property
    public void one_pair_should_sum_dice_of_the_pair(Die pair, Die d3, Die d4, Die d5) {
        // given
        assumeThat(Stream.of(d3,d4,d5).distinct().count()).isEqualTo(3);
        final YatzyThrow yatzyThrow = new YatzyThrow(pair, pair, d3, d4, d5);
        // when
        final long score = YatzyScorer.onePair(yatzyThrow);

        // then
        assertThat(score).isEqualTo(pair.value() * 2);
    }

    @Property
    public void two_pair_should_sum_dice_of_the_two_pair(Die pair1, Die pair2, Die d5) {
        // given
        final YatzyThrow yatzyThrow = new YatzyThrow(pair1, pair1, pair2, pair2, d5);

        // when
        final long score = YatzyScorer.twoPair(yatzyThrow);

        // then
        assertThat(score).isEqualTo((pair1.value() + pair2.value()) * 2);
    }

    @Property
    public void one_pair_should_not_score_two_pair(Die pair, Die d3, Die d4, Die d5) {
        // given
        assumeThat(Stream.of(pair, d3,d4,d5).distinct().count()).isEqualTo(4);
        final YatzyThrow yatzyThrow = new YatzyThrow(pair, pair, d3, d4, d5);

        // when
        final long score = YatzyScorer.twoPair(yatzyThrow);

        // then
        assertThat(score).isEqualTo(0);
    }

    @Property
    public void three_of_a_kind_should_sum_dice_of_the_three(Die threeOfAKind, Die d4, Die d5) {
        // given
        final YatzyThrow yatzyThrow = new YatzyThrow(threeOfAKind, threeOfAKind, threeOfAKind, d4, d5);

        // when
        final long score = YatzyScorer.threeOfAKind(yatzyThrow);

        // then
        assertThat(score).isEqualTo(threeOfAKind.value() * 3);
    }

    @Property
    public void four_of_a_kind_should_sum_dice_of_the_four(Die fourOfAKind, Die d5) {
        // given
        final YatzyThrow yatzyThrow = new YatzyThrow(fourOfAKind, fourOfAKind, fourOfAKind, fourOfAKind, d5);

        // when
        final long score = YatzyScorer.fourOfAKind(yatzyThrow);

        // then
        assertThat(score).isEqualTo(fourOfAKind.value() * 4);
    }

    @Property
    public void small_straight_should_score_15() {
        // given
        final YatzyThrow yatzyThrow = new YatzyThrow(ONE, TWO, THREE, FOUR, FIVE);

        // when
        final long score = YatzyScorer.smallStraight(yatzyThrow);

        // then
        assertThat(score).isEqualTo(15);
    }

    @Property
    public void small_straight_should_score_0_when_it_is_not_small_straight(YatzyThrow yatzyThrow) {
        // given
        assumeThat(
                (yatzyThrow.stream().distinct().count() == 5 && !yatzyThrow.contains(ONE))
                        || yatzyThrow.stream().distinct().count() != 5)
                .isTrue();

        // when
        final long score = YatzyScorer.smallStraight(yatzyThrow);

        // then
        assertThat(score).isEqualTo(0);
    }

    @Property
    public void large_straight_should_score_20() {
        // given
        final YatzyThrow yatzyThrow = new YatzyThrow(TWO, THREE, FOUR, FIVE, SIX);

        // when
        final long score = YatzyScorer.largeStraight(yatzyThrow);

        // then
        assertThat(score).isEqualTo(20);
    }

    @Property
    public void large_straight_should_score_0_when_it_is_not_large_straight(YatzyThrow yatzyThrow) {
        // given
        assumeThat(
                (yatzyThrow.stream().distinct().count() == 5 && !yatzyThrow.contains(SIX))
                        || yatzyThrow.stream().distinct().count() != 5)
                .isTrue();

        // when
        final long score = YatzyScorer.largeStraight(yatzyThrow);

        // then
        assertThat(score).isEqualTo(0);
    }

    @Property
    public void fullHouse_should_sum_the_dice(Die pair, Die three) {
        // given
        assumeThat(pair.value()).isNotEqualTo(three.value());
        final YatzyThrow yatzyThrow = new YatzyThrow(pair, pair, three, three, three);

        // when
        final long score = YatzyScorer.fullHouse(yatzyThrow);

        // then
        assertThat(score).isEqualTo(pair.value() * 2 + three.value() * 3);
    }

    @Property
    public void yatzi_is_not_a_fullHouse(Die d1) {
        // given
        final YatzyThrow yatzyThrow = new YatzyThrow(d1, d1, d1, d1, d1);

        // when
        final long score = YatzyScorer.fullHouse(yatzyThrow);

        // then
        assertThat(score).isEqualTo(0);
    }
}

