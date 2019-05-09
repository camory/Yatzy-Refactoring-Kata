package fr.camory;

import org.junit.Test;

import static fr.camory.Die.*;
import static org.assertj.core.api.Assertions.assertThat;

public class YatzyScorerTest {

    @Test
    public void chance_should_scores_sum_of_all_dice() {
        // given
        final YatzyThrow throw1 = new YatzyThrow(TWO, THREE, FOUR, FIVE, ONE);
        final YatzyThrow throw2 = new YatzyThrow(THREE, THREE, FOUR, FIVE, ONE);

        // when
        final long chance1 = YatzyScorer.chance(throw1);
        final long chance2 = YatzyScorer.chance(throw2);

        // then
        assertThat(chance1).isEqualTo(15);
        assertThat(chance2).isEqualTo(16);
    }

    @Test
    public void yatzy_should_scores_50() {
        // given
        final YatzyThrow throw1 = new YatzyThrow(FOUR, FOUR, FOUR, FOUR, FOUR);
        final YatzyThrow throw2 = new YatzyThrow(SIX, SIX, SIX, SIX, SIX);
        final YatzyThrow throw3 = new YatzyThrow(SIX, SIX, SIX, SIX, THREE);

        // when
        final long yatzy1 = YatzyScorer.yatzy(throw1);
        final long yatzy2 = YatzyScorer.yatzy(throw2);
        final long yatzy3 = YatzyScorer.yatzy(throw3);

        // then
        assertThat(yatzy1).isEqualTo(50);
        assertThat(yatzy2).isEqualTo(50);
        assertThat(yatzy3).isEqualTo(0);
    }

    @Test
    public void ones_should_sum_1s() {
        // given
        final YatzyThrow throw1 = new YatzyThrow(ONE, TWO, THREE, FOUR, FIVE);
        final YatzyThrow throw2 = new YatzyThrow(ONE, TWO, ONE, FOUR, FIVE);
        final YatzyThrow throw3 = new YatzyThrow(SIX, TWO, TWO, FOUR, FIVE);
        final YatzyThrow throw4 = new YatzyThrow(ONE, TWO, ONE, ONE, ONE);

        // when
        final long ones1 = YatzyScorer.ones(throw1);
        final long ones2 = YatzyScorer.ones(throw2);
        final long ones3 = YatzyScorer.ones(throw3);
        final long ones4 = YatzyScorer.ones(throw4);

        // then
        assertThat(ones1).isEqualTo(1);
        assertThat(ones2).isEqualTo(2);
        assertThat(ones3).isEqualTo(0);
        assertThat(ones4).isEqualTo(4);
    }

    @Test
    public void twos_should_sum_2s() {
        // given
        final YatzyThrow throw1 = new YatzyThrow(ONE, TWO, THREE, TWO, SIX);
        final YatzyThrow throw2 = new YatzyThrow(TWO, TWO, TWO, TWO, TWO);

        // when
        final long twos1 = YatzyScorer.twos(throw1);
        final long twos2 = YatzyScorer.twos(throw2);

        // then
        assertThat(twos1).isEqualTo(4);
        assertThat(twos2).isEqualTo(10);
    }

    @Test
    public void threes_should_sum_3s() {
        // given
        final YatzyThrow throw1 = new YatzyThrow(ONE,TWO,THREE,TWO,THREE);
        final YatzyThrow throw2 = new YatzyThrow(TWO,THREE,THREE,THREE,THREE);

        // when
        final long threes1 = YatzyScorer.threes(throw1);
        final long threes2 = YatzyScorer.threes(throw2);

        // then
        assertThat(threes1).isEqualTo(6);
        assertThat(threes2).isEqualTo(12);
    }

    @Test
    public void fours_should_sum_4s() {
        // given
        final YatzyThrow throw1 = new YatzyThrow(FOUR,FOUR,FOUR,FIVE,FIVE);
        final YatzyThrow throw2 = new YatzyThrow(FOUR,FOUR,FIVE,FIVE,FIVE);
        final YatzyThrow throw3 = new YatzyThrow(FOUR,FIVE,FIVE,FIVE,FIVE);

        // when
        final long fours1 = YatzyScorer.fours(throw1);
        final long fours2 = YatzyScorer.fours(throw2);
        final long fours3 = YatzyScorer.fours(throw3);

        // then
        assertThat(fours1).isEqualTo(12);
        assertThat(fours2).isEqualTo(8);
        assertThat(fours3).isEqualTo(4);
    }

    @Test
    public void fives_should_sum_5s() {
        // given
        final YatzyThrow throw1 = new YatzyThrow(FOUR,FOUR,FOUR,FIVE,FIVE);
        final YatzyThrow throw2 = new YatzyThrow(FOUR,FOUR,FIVE,FIVE,FIVE);
        final YatzyThrow throw3 = new YatzyThrow(FOUR,FIVE,FIVE,FIVE,FIVE);

        // when
        final long fives1 = YatzyScorer.fives(throw1);
        final long fives2 = YatzyScorer.fives(throw2);
        final long fives3 = YatzyScorer.fives(throw3);

        // then
        assertThat(fives1).isEqualTo(10);
        assertThat(fives2).isEqualTo(15);
        assertThat(fives3).isEqualTo(20);
    }

    @Test
    public void sixes_should_sum_6s() {
        // given
        final YatzyThrow throw1 = new YatzyThrow(FOUR,FOUR,FOUR,FIVE,FIVE);
        final YatzyThrow throw2 = new YatzyThrow(FOUR,FOUR,SIX,FIVE,FIVE);
        final YatzyThrow throw3 = new YatzyThrow(SIX,FIVE,SIX,SIX,FIVE);

        // when
        final long sixes1 = YatzyScorer.sixes(throw1);
        final long sixes2 = YatzyScorer.sixes(throw2);
        final long sixes3 = YatzyScorer.sixes(throw3);

        // then
        assertThat(sixes1).isEqualTo(0);
        assertThat(sixes2).isEqualTo(6);
        assertThat(sixes3).isEqualTo(18);
    }

    @Test
    public void one_pair_should_sum_dice_of_the_pair() {
        // given
        final YatzyThrow throw1 = new YatzyThrow(THREE, FOUR, THREE, FIVE, SIX);
        final YatzyThrow throw2 = new YatzyThrow(FIVE, THREE, THREE, THREE, FIVE);
        final YatzyThrow throw3 = new YatzyThrow(FIVE, THREE, SIX, SIX, FIVE);
        final YatzyThrow throw4 = new YatzyThrow(ONE, TWO, THREE, FOUR, FIVE);

        // when
        final long pair1 = YatzyScorer.onePair(throw1);
        final long pair2 = YatzyScorer.onePair(throw2);
        final long pair3 = YatzyScorer.onePair(throw3);
        final long pair4 = YatzyScorer.onePair(throw4);

        // then
        assertThat(pair1).isEqualTo(6);
        assertThat(pair2).isEqualTo(10);
        assertThat(pair3).isEqualTo(12);
        assertThat(pair4).isEqualTo(0);
    }

    @Test
    public void two_pair_should_sum_dice_of_the_two_pair() {
        // given
        final YatzyThrow throw1 = new YatzyThrow(THREE, THREE, FIVE, FOUR, FIVE);
        final YatzyThrow throw2 = new YatzyThrow(THREE, THREE, FIVE, FIVE, FIVE);
        final YatzyThrow throw3 = new YatzyThrow(THREE, THREE, FOUR, ONE, FIVE);
        final YatzyThrow throw4 = new YatzyThrow(THREE, THREE, THREE, THREE, THREE);

        // when
        final long twoPair1 = YatzyScorer.twoPair(throw1);
        final long twoPair2 = YatzyScorer.twoPair(throw2);
        final long twoPair3 = YatzyScorer.twoPair(throw3);
        final long twoPair4 = YatzyScorer.twoPair(throw4);

        // then
        assertThat(twoPair1).isEqualTo(16);
        assertThat(twoPair2).isEqualTo(16);
        assertThat(twoPair3).isEqualTo(0);
        assertThat(twoPair4).isEqualTo(12);
    }

    @Test
    public void three_of_a_kind_should_sum_dice_of_the_three() {
        // given
        final YatzyThrow throw1 = new YatzyThrow(THREE, THREE, THREE, FOUR, FIVE);
        final YatzyThrow throw2 = new YatzyThrow(FIVE, THREE, FIVE, FOUR, FIVE);
        final YatzyThrow throw3 = new YatzyThrow(THREE, THREE, THREE, THREE, FIVE);

        // when
        final long threeOfAKind1 = YatzyScorer.threeOfAKind(throw1);
        final long threeOfAKind2 = YatzyScorer.threeOfAKind(throw2);
        final long threeOfAKind3 = YatzyScorer.threeOfAKind(throw3);

        // then
        assertThat(threeOfAKind1).isEqualTo(9);
        assertThat(threeOfAKind2).isEqualTo(15);
        assertThat(threeOfAKind3).isEqualTo(9);
    }

    @Test
    public void four_of_a_kind_should_sum_dice_of_the_three() {
        // given
        final YatzyThrow throw1 = new YatzyThrow(THREE,THREE,THREE,THREE,FIVE);
        final YatzyThrow throw2 = new YatzyThrow(FIVE,FIVE,FIVE,FOUR,FIVE);
        final YatzyThrow throw3 = new YatzyThrow(THREE,THREE,THREE,THREE,THREE);

        // when
        final long fourOfAKind1 = YatzyScorer.fourOfAKind(throw1);
        final long fourOfAKind2 = YatzyScorer.fourOfAKind(throw2);
        final long fourOfAKind3 = YatzyScorer.fourOfAKind(throw3);

        // then
        assertThat(fourOfAKind1).isEqualTo(12);
        assertThat(fourOfAKind2).isEqualTo(20);
        assertThat(fourOfAKind3).isEqualTo(12);
    }

    @Test
    public void small_straight_should_score_15() {
        // given
        final YatzyThrow throw1 = new YatzyThrow(ONE, TWO, THREE, FOUR, FIVE);
        final YatzyThrow throw2 = new YatzyThrow(TWO, THREE, FOUR, FIVE, ONE);
        final YatzyThrow throw3 = new YatzyThrow(ONE, TWO, TWO, FOUR, FIVE);

        // when
        final long smallStraight1 = YatzyScorer.smallStraight(throw1);
        final long smallStraight2 = YatzyScorer.smallStraight(throw2);
        final long smallStraight3 = YatzyScorer.smallStraight(throw3);

        // then
        assertThat(smallStraight1).isEqualTo(15);
        assertThat(smallStraight2).isEqualTo(15);
        assertThat(smallStraight3).isEqualTo(0);
    }

    @Test
    public void large_straight_should_score_20() {
        // given
        final YatzyThrow throw1 = new YatzyThrow(SIX,TWO,THREE,FOUR,FIVE);
        final YatzyThrow throw2 = new YatzyThrow(TWO,THREE,FOUR,FIVE,SIX);
        final YatzyThrow throw3 = new YatzyThrow(ONE,TWO,TWO,FOUR,FIVE);

        // when
        final long largeStraight1 = YatzyScorer.largeStraight(throw1);
        final long largeStraight2 = YatzyScorer.largeStraight(throw2);
        final long largeStraight3 = YatzyScorer.largeStraight(throw3);

        // then
        assertThat(largeStraight1).isEqualTo(20);
        assertThat(largeStraight2).isEqualTo(20);
        assertThat(largeStraight3).isEqualTo(0);
    }

    @Test
    public void fullHouse_should_sum_the_dice() {
        // given
        final YatzyThrow throw1 = new YatzyThrow(SIX, TWO, TWO, TWO, SIX);
        final YatzyThrow throw2 = new YatzyThrow(TWO, THREE, FOUR, FIVE, SIX);

        // when
        final long fullHouse1 = YatzyScorer.fullHouse(throw1);
        final long fullHouse2 = YatzyScorer.fullHouse(throw2);

        // then
        assertThat(fullHouse1).isEqualTo(18);
        assertThat(fullHouse2).isEqualTo(0);
    }

    @Test
    public void yatzi_is_not_a_fullHouse() {
        // given
        final YatzyThrow throw1 = new YatzyThrow(FOUR, FOUR, FOUR, FOUR, FOUR);

        // when
        final long fullHouse1 = YatzyScorer.fullHouse(throw1);

        // then
        assertThat(fullHouse1).isEqualTo(0);
    }
}
