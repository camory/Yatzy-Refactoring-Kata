package fr.camory;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class YatzyTest {

    @Test
    public void yatzy_is_only_played_with_6_sided_dice() {
        assertThatExceptionOfType(IllegalStateException.class).as("die value must not be greater than 6").isThrownBy(() -> new Yatzy(1, 2, 3, 4, 8));
        assertThatExceptionOfType(IllegalStateException.class).as("die value must not be lesser than 1").isThrownBy(() -> new Yatzy(0, 2, 3, 4, 5));
    }

    @Test
    public void chance_should_scores_sum_of_all_dice() {
        // given
        final Yatzy player1 = new Yatzy(2, 3, 4, 5, 1);
        final Yatzy player2 = new Yatzy(3, 3, 4, 5, 1);

        // when
        final int chance1 = player1.chance();
        final int chance2 = player2.chance();

        // then
        assertThat(chance1).isEqualTo(15);
        assertThat(chance2).isEqualTo(16);
    }

    @Test
    public void yatzy_should_scores_50() {
        // given
        final Yatzy player1 = new Yatzy(4, 4, 4, 4, 4);
        final Yatzy player2 = new Yatzy(6, 6, 6, 6, 6);
        final Yatzy player3 = new Yatzy(6, 6, 6, 6, 3);

        // when
        final int yatzy1 = player1.yatzy();
        final int yatzy2 = player2.yatzy();
        final int yatzy3 = player3.yatzy();

        // then
        assertThat(yatzy1).isEqualTo(50);
        assertThat(yatzy2).isEqualTo(50);
        assertThat(yatzy3).isEqualTo(0);
    }

    @Test
    public void ones_should_sum_1s() {
        // given
        final Yatzy player1 = new Yatzy(1, 2, 3, 4, 5);
        final Yatzy player2 = new Yatzy(1, 2, 1, 4, 5);
        final Yatzy player3 = new Yatzy(6, 2, 2, 4, 5);
        final Yatzy player4 = new Yatzy(1, 2, 1, 1, 1);

        // when
        final int ones1 = player1.ones();
        final int ones2 = player2.ones();
        final int ones3 = player3.ones();
        final int ones4 = player4.ones();

        // then
        assertThat(ones1).isEqualTo(1);
        assertThat(ones2).isEqualTo(2);
        assertThat(ones3).isEqualTo(0);
        assertThat(ones4).isEqualTo(4);
    }

    @Test
    public void twos_should_sum_2s() {
        // given
        final Yatzy player1 = new Yatzy(1, 2, 3, 2, 6);
        final Yatzy player2 = new Yatzy(2, 2, 2, 2, 2);

        // when
        final int twos1 = player1.twos();
        final int twos2 = player2.twos();

        // then
        assertThat(twos1).isEqualTo(4);
        assertThat(twos2).isEqualTo(10);
    }

    @Test
    public void threes_should_sum_3s() {
        // given
        final Yatzy player1 = new Yatzy(1,2,3,2,3);
        final Yatzy player2 = new Yatzy(2,3,3,3,3);

        // when
        final int threes1 = player1.threes();
        final int threes2 = player2.threes();

        // then
        assertThat(threes1).isEqualTo(6);
        assertThat(threes2).isEqualTo(12);
    }

    @Test
    public void fours_should_sum_4s() {
        // given
        final Yatzy player1 = new Yatzy(4,4,4,5,5);
        final Yatzy player2 = new Yatzy(4,4,5,5,5);
        final Yatzy player3 = new Yatzy(4,5,5,5,5);

        // when
        final int fours1 = player1.fours();
        final int fours2 = player2.fours();
        final int fours3 = player3.fours();

        // then
        assertThat(fours1).isEqualTo(12);
        assertThat(fours2).isEqualTo(8);
        assertThat(fours3).isEqualTo(4);
    }

    @Test
    public void fives_should_sum_5s() {
        // given
        final Yatzy player1 = new  Yatzy(4,4,4,5,5);
        final Yatzy player2 = new Yatzy(4,4,5,5,5);
        final Yatzy player3 = new Yatzy(4,5,5,5,5);

        // when
        final int fives1 = player1.fives();
        final int fives2 = player2.fives();
        final int fives3 = player3.fives();

        // then
        assertThat(fives1).isEqualTo(10);
        assertThat(fives2).isEqualTo(15);
        assertThat(fives3).isEqualTo(20);
    }

    @Test
    public void sixes_should_sum_6s() {
        // given
        final Yatzy player1 = new  Yatzy(4,4,4,5,5);
        final Yatzy player2 = new Yatzy(4,4,6,5,5);
        final Yatzy player3 = new Yatzy(6,5,6,6,5);

        // when
        final int sixes1 = player1.sixes();
        final int sixes2 = player2.sixes();
        final int sixes3 = player3.sixes();

        // then
        assertThat(sixes1).isEqualTo(0);
        assertThat(sixes2).isEqualTo(6);
        assertThat(sixes3).isEqualTo(18);
    }

    @Test
    public void one_pair_should_sum_dice_of_the_pair() {
        // given
        final Yatzy player1 = new Yatzy(3, 4, 3, 5, 6);
        final Yatzy player2 = new Yatzy(5, 3, 3, 3, 5);
        final Yatzy player3 = new Yatzy(5, 3, 6, 6, 5);
        final Yatzy player4 = new Yatzy(1, 2, 3, 4, 5);

        // when
        final int pair1 = player1.onePair();
        final int pair2 = player2.onePair();
        final int pair3 = player3.onePair();
        final int pair4 = player4.onePair();

        // then
        assertThat(pair1).isEqualTo(6);
        assertThat(pair2).isEqualTo(10);
        assertThat(pair3).isEqualTo(12);
        assertThat(pair4).isEqualTo(0);
    }

    @Test
    public void two_pair_should_sum_dice_of_the_two_pair() {
        // given
        final Yatzy player1 = new Yatzy(3, 3, 5, 4, 5);
        final Yatzy player2 = new Yatzy(3, 3, 5, 5, 5);

        // when
        final int twoPair1 = player1.twoPair();
        final int twoPair2 = player2.twoPair();

        // then
        assertThat(twoPair1).isEqualTo(16);
        assertThat(twoPair2).isEqualTo(16);
    }

    @Test
    public void three_of_a_kind_should_sum_dice_of_the_three() {
        // given
        final Yatzy player1 = new Yatzy(3, 3, 3, 4, 5);
        final Yatzy player2 = new Yatzy(5, 3, 5, 4, 5);
        final Yatzy player3 = new Yatzy(3, 3, 3, 3, 5);

        // when
        final int threeOfAKind1 = player1.threeOfAKind();
        final int threeOfAKind2 = player2.threeOfAKind();
        final int threeOfAKind3 = player3.threeOfAKind();

        // then
        assertThat(threeOfAKind1).isEqualTo(9);
        assertThat(threeOfAKind2).isEqualTo(15);
        assertThat(threeOfAKind3).isEqualTo(9);
    }

    @Test
    public void four_of_a_kind_should_sum_dice_of_the_three() {
        // given
        final Yatzy player1 = new Yatzy(3,3,3,3,5);
        final Yatzy player2 = new Yatzy(5,5,5,4,5);
        final Yatzy player3 = new Yatzy(3,3,3,3,3);

        // when
        final int fourOfAKind1 = player1.fourOfAKind();
        final int fourOfAKind2 = player2.fourOfAKind();
        final int fourOfAKind3 = player3.fourOfAKind();

        // then
        assertThat(fourOfAKind1).isEqualTo(12);
        assertThat(fourOfAKind2).isEqualTo(20);
        assertThat(fourOfAKind3).isEqualTo(12);
    }

    @Test
    public void small_straight_should_score_15() {
        // given
        final Yatzy player1 = new Yatzy(1, 2, 3, 4, 5);
        final Yatzy player2 = new Yatzy(2, 3, 4, 5, 1);
        final Yatzy player3 = new Yatzy(1, 2, 2, 4, 5);

        // when
        final int smallStraight1 = player1.smallStraight();
        final int smallStraight2 = player2.smallStraight();
        final int smallStraight3 = player3.smallStraight();

        // then
        assertThat(smallStraight1).isEqualTo(15);
        assertThat(smallStraight2).isEqualTo(15);
        assertThat(smallStraight3).isEqualTo(0);
    }

    @Test
    public void large_straight_should_score_20() {
        // given
        final Yatzy player1 = new Yatzy(6,2,3,4,5);
        final Yatzy player2 = new Yatzy(2,3,4,5,6);
        final Yatzy player3 = new Yatzy(1,2,2,4,5);

        // when
        final int largeStraight1 = player1.largeStraight();
        final int largeStraight2 = player2.largeStraight();
        final int largeStraight3 = player3.largeStraight();

        // then
        assertThat(largeStraight1).isEqualTo(20);
        assertThat(largeStraight2).isEqualTo(20);
        assertThat(largeStraight3).isEqualTo(0);
    }

    @Test
    public void fullHouse_should_sum_the_dice() {
        // given
        final Yatzy player1 = new Yatzy(6, 2, 2, 2, 6);
        final Yatzy player2 = new Yatzy(2, 3, 4, 5, 6);

        // when
        final int fullHouse1 = player1.fullHouse();
        final int fullHouse2 = player2.fullHouse();

        // then
        assertThat(fullHouse1).isEqualTo(18);
        assertThat(fullHouse2).isEqualTo(0);
    }

    @Test
    public void yatzi_is_not_a_fullHouse() {
        // given
        final Yatzy player1 = new Yatzy(4, 4, 4, 4, 4);

        // when
        final int fullHouse1 = player1.fullHouse();

        // then
        assertThat(fullHouse1).isEqualTo(0);
    }
}
