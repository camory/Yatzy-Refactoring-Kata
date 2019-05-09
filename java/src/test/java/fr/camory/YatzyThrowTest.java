package fr.camory;

import org.junit.Test;

import java.util.Map;
import java.util.stream.Stream;

import static fr.camory.Die.*;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class YatzyThrowTest {

    @Test
    public void grouping_should_contain_only_thrown_dice() {
        // given
        final YatzyThrow yatzyThrow = new YatzyThrow(ONE, TWO, TWO, SIX, FIVE);

        // when
        final Stream<Map.Entry<Die, Long>> diceCount = yatzyThrow.diceCount();

        // then
        assertThat(
                diceCount.map(Map.Entry::getKey).collect(toList())
        ).containsOnly(ONE, TWO, FIVE, SIX);
    }

    @Test
    public void should_count_2_when_throw_has_2_same_dice() {
        // given
        final YatzyThrow yatzyThrow = new YatzyThrow(ONE, ONE, THREE, FOUR, SIX);

        // when
        final Stream<Map.Entry<Die, Long>> diceCount = yatzyThrow.diceCount();

        // then
        assertThat(
                diceCount.filter(dieCount -> dieCount.getKey() == ONE).mapToLong(Map.Entry::getValue).findFirst().getAsLong()
        ).isEqualTo(2);
    }

}
