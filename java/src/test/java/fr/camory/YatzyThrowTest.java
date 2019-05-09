package fr.camory;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;


@RunWith(JUnitQuickcheck.class)
public class YatzyThrowTest {

    @Property
    public void grouping_should_contain_only_thrown_dice(Die d1, Die d2, Die d3, Die d4, Die d5) {
        // given
        final YatzyThrow yatzyThrow = new YatzyThrow(d1, d2, d3, d4, d5);

        // when
        final Stream<Map.Entry<Die, Long>> diceCount = yatzyThrow.diceCount();

        // then
        assertThat(
                diceCount.map(Map.Entry::getKey).collect(toList())
        ).containsOnly(d1, d2, d3, d4, d5);
    }

    @Property
    public void should_count_2_when_throw_has_2_same_dice(Die d1, Die d3, Die d4, Die d5) {
        // given
        assumeThat(Stream.of(d1, d3, d4, d5).distinct().count()).isEqualTo(4);
        final YatzyThrow yatzyThrow = new YatzyThrow(d1, d1, d3, d4, d5);

        // when
        final Stream<Map.Entry<Die, Long>> diceCount = yatzyThrow.diceCount();

        // then
        assertThat(
                diceCount.filter(dieCount -> dieCount.getKey() == d1).mapToLong(Map.Entry::getValue).findFirst().getAsLong()
        ).isEqualTo(2);
    }
}
