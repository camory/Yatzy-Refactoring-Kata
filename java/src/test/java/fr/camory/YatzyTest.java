package fr.camory;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;

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
    public void one_pair() {
        // given
        final Yatzy player1 = new Yatzy(3, 4, 3, 5, 6);
        final Yatzy player2 = new Yatzy(5, 3, 3, 3, 5);
        final Yatzy player3 = new Yatzy(5, 3, 6, 6, 5);
        final Yatzy player4 = new Yatzy(1, 2, 3, 4, 5);

        // when
        final int pair1 = player1.one_pair();
        final int pair2 = player2.one_pair();
        final int pair3 = player3.one_pair();
        final int pair4 = player4.one_pair();

        // then
        assertThat(pair1).isEqualTo(6);
        assertThat(pair2).isEqualTo(10);
        assertThat(pair3).isEqualTo(12);
        assertThat(pair4).isEqualTo(0);
    }

    @Test
    public void two_Pair() {
        assertEquals(16, Yatzy.two_pair(3,3,5,4,5));
        assertEquals(16, Yatzy.two_pair(3,3,5,5,5));
    }

    @Test
    public void three_of_a_kind() {
        assertEquals(9, Yatzy.three_of_a_kind(3,3,3,4,5));
        assertEquals(15, Yatzy.three_of_a_kind(5,3,5,4,5));
        assertEquals(9, Yatzy.three_of_a_kind(3,3,3,3,5));
    }

    @Test
    public void four_of_a_knd() {
        assertEquals(12, Yatzy.four_of_a_kind(3,3,3,3,5));
        assertEquals(20, Yatzy.four_of_a_kind(5,5,5,4,5));
        assertEquals(9, Yatzy.three_of_a_kind(3,3,3,3,3));
    }

    @Test
    public void smallStraight() {
        assertEquals(15, Yatzy.smallStraight(1,2,3,4,5));
        assertEquals(15, Yatzy.smallStraight(2,3,4,5,1));
        assertEquals(0, Yatzy.smallStraight(1,2,2,4,5));
    }

    @Test
    public void largeStraight() {
        assertEquals(20, Yatzy.largeStraight(6,2,3,4,5));
        assertEquals(20, Yatzy.largeStraight(2,3,4,5,6));
        assertEquals(0, Yatzy.largeStraight(1,2,2,4,5));
    }

    @Test
    public void fullHouse() {
        assertEquals(18, Yatzy.fullHouse(6,2,2,2,6));
        assertEquals(0, Yatzy.fullHouse(2,3,4,5,6));
    }
}

