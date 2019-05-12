const _= require("lodash");
const jsc = require('jsverify');
const YatzyThrow = require("../lib/yatzy_throw");


module.exports.yatzyThrowGenerator = jsc.bless({
    generator: () => new YatzyThrow(jsc.random(1, 6), jsc.random(1, 6), jsc.random(1, 6), jsc.random(1, 6), jsc.random(1, 6))
});

module.exports.twoPairGenerator = jsc.bless({
    generator: () => {
        let pair1 = jsc.random(1, 6);
        let pair2 = jsc.random(1, 6);
        return {
            yatzyThrow: new YatzyThrow(pair1, pair2, pair1, pair2, jsc.random(1, 6)),
            pair1: pair1,
            pair2: pair2
        }
    }
});

module.exports.atLeastThreeOfAKindGenerator = jsc.bless({
    generator: () => {
        let three = jsc.random(1, 6);
        return {
            yatzyThrow: new YatzyThrow(three, three, three, jsc.random(1, 6), jsc.random(1, 6)),
            threeOfAKind: three
        }
    }
});


module.exports.atLeastFourOfAKindGenerator = jsc.bless({
    generator: () => {
        let four = jsc.random(1, 6);
        return {
            yatzyThrow: new YatzyThrow(four, four, four, four, jsc.random(1, 6)),
            fourOfAKind: four
        }
    }
});

module.exports.onePairArbitrary = jsc.suchthat(jsc.bless({
    generator: () => {
        let pair = jsc.random(1, 6);
        return {
            yatzyThrow: new YatzyThrow(pair, pair, jsc.random(1, 6), jsc.random(1, 6), jsc.random(1, 6)),
            pair: pair
        }
    }
}), yatzyThrow => _.uniq(yatzyThrow.yatzyThrow.dice).length === 4);

module.exports.fullHouseArbitrary = jsc.suchthat(jsc.bless({
    generator: () => {
        let pair = jsc.random(1, 6);
        let three = jsc.random(1, 6);
        return {
            yatzyThrow: new YatzyThrow(pair, pair, three, three, three),
            pair: pair,
            threeOfAKind: three
        }
    }
}), yatzyThrow => yatzyThrow.threeOfAKind !== yatzyThrow.pair);

module.exports.smallStraightArbitrary = jsc.suchthat(this.yatzyThrowGenerator, yatzyThrow => _.uniq(yatzyThrow.dice).length === 5 && _.sum(yatzyThrow.dice) === 15);
module.exports.largeStraightArbitrary = jsc.suchthat(this.yatzyThrowGenerator, yatzyThrow => _.uniq(yatzyThrow.dice).length === 5 && _.sum(yatzyThrow.dice) === 20);


module.exports.notYatzyArbitrary = jsc.suchthat(this.yatzyThrowGenerator, yatzyThrow => _.uniq(yatzyThrow.dice).length !== 1);
module.exports.notOnePairArbitrary = jsc.suchthat(this.yatzyThrowGenerator, yatzyThrow => _.uniq(yatzyThrow.dice).length === 5);
module.exports.notTwoPairArbitrary = jsc.suchthat(this.yatzyThrowGenerator, yatzyThrow => _.uniq(yatzyThrow.dice).length > 3);
module.exports.notThreeOfAKindArbitrary = jsc.suchthat(this.yatzyThrowGenerator, yatzyThrow => _.max([1, 2, 3, 4, 5, 6].map(die => yatzyThrow.count(die) / die)) < 3);
module.exports.notFourOfAKindArbitrary = jsc.suchthat(this.yatzyThrowGenerator, yatzyThrow => _.max([1, 2, 3, 4, 5, 6].map(die => yatzyThrow.count(die) / die)) < 4);
module.exports.notSmallStraightArbitrary = jsc.suchthat(this.yatzyThrowGenerator, yatzyThrow => !(_.uniq(yatzyThrow.dice).length === 5 && _.sum(yatzyThrow.dice) === 15));
module.exports.notLargeStraightArbitrary = jsc.suchthat(this.yatzyThrowGenerator, yatzyThrow => !(_.uniq(yatzyThrow.dice).length === 5 && _.sum(yatzyThrow.dice) === 20));
module.exports.notFullHouseArbitrary = jsc.suchthat(this.yatzyThrowGenerator, yatzyThrow => _.keys(yatzyThrow.diceCount).length !== 2 || _.values(yatzyThrow.diceCount).indexOf(3) === -1);


module.exports.dieArbitrary = jsc.suchthat(jsc.nat, n => n > 0 && n < 7);
module.exports.notADieArbitrary = jsc.suchthat(jsc.nat, n => n < 1 || n > 6);
