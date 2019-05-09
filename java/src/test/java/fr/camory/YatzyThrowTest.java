package fr.camory;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.vavr.Tuple2;
import io.vavr.collection.HashSet;
import io.vavr.collection.Map;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;


@RunWith(JUnitQuickcheck.class)
public class YatzyThrowTest {

    @Property
    public void grouping_should_contain_only_thrown_dice(Die d1, Die d2, Die d3, Die d4, Die d5) {
        // given
        final YatzyThrow yatzyThrow = new YatzyThrow(d1, d2, d3, d4, d5);

        // when
        final Map<Die, Integer> diceCount = yatzyThrow.diceCount();

        // then
        assertThat(diceCount.keySet()).containsOnly(d1, d2, d3, d4, d5);
    }

    @Property
    public void should_count_2_when_throw_has_2_same_dice(Die d1, Die d3, Die d4, Die d5) {
        // given
        assumeThat(HashSet.of(d1, d3, d4, d5).size()).isEqualTo(4);
        final YatzyThrow yatzyThrow = new YatzyThrow(d1, d1, d3, d4, d5);

        // when
        final Map<Die, Integer> diceCount = yatzyThrow.diceCount();

        // then
        assertThat(diceCount.filterKeys(die -> die == d1).map(Tuple2::_2).get()).isEqualTo(2);
    }
}
