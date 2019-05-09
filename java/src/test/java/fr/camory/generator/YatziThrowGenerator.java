package fr.camory.generator;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import fr.camory.Die;
import fr.camory.YatzyThrow;

public class YatziThrowGenerator extends Generator<YatzyThrow> {

    public YatziThrowGenerator() {
        super(YatzyThrow.class);
    }

    @Override
    public YatzyThrow generate(SourceOfRandomness random, GenerationStatus status) {
        return gen().constructor(YatzyThrow.class, Die.class, Die.class, Die.class, Die.class, Die.class)
                .generate(random, status);
    }
}
