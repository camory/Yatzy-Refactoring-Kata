const _ = require("lodash");
const assert = require("assert");
const jsc = require('jsverify');
const YatzyScorer = require("../lib/yatzy_scorer");
const YatzyThrow = require("../lib/yatzy_throw");
const Generators = require("./generators");

describe('Chance', () => {
    it('should score sum of all dice', () => {
        jsc.checkForall(Generators.yatzyThrowGenerator, yatzyThrow => {
            // given

            // when
            let score = YatzyScorer.chance(yatzyThrow);

            // then
            return _.isEqual(score, yatzyThrow.dice[0] + yatzyThrow.dice[1] + yatzyThrow.dice[2] + yatzyThrow.dice[3] + yatzyThrow.dice[4]);
        })
    });
});

describe("YatzyScorer", () => {
    it("should scores 50", () => {
        jsc.checkForall(jsc.integer(1, 6), die => {
            // given
            let yatzyThrow = new YatzyThrow(die, die, die, die, die);

            // when
            let score = YatzyScorer.yatzy(yatzyThrow);

            // then
            return _.isEqual(score, 50);
        });
    });
    it('should score 0', () => {
        jsc.checkForall(Generators.notYatzyArbitrary, (yatzyThrow) => {
            // given

            // when
            let score = YatzyScorer.yatzy(yatzyThrow);

            // then
            return _.isEqual(score, 0);
        })
    });
});


describe("Ones", () => {
    it("should score the sum of 1s", () => {
        jsc.checkForall(Generators.yatzyThrowGenerator, (yatzyThrow) => {
            // given

            // when
            let score = YatzyScorer.ones(yatzyThrow);

            // then
            return _.isEqual(score, _.reduce(yatzyThrow.dice, (acc, die) => die === 1 ? acc + die : acc, 0));
        });
    });
});

describe("Twos", () => {
    it("should score the sum of 2s", () => {
        jsc.checkForall(Generators.yatzyThrowGenerator, (yatzyThrow) => {
            // given

            // when
            let score = YatzyScorer.twos(yatzyThrow);

            // then
            return _.isEqual(score, _.reduce(yatzyThrow.dice, (acc, die) => die === 2 ? acc + die : acc, 0));
        });
    });
});

describe("Threes", () => {
    it("should score the sum of 3s", () => {
        jsc.checkForall(Generators.yatzyThrowGenerator, (yatzyThrow) => {
            // given

            // when
            let score = YatzyScorer.threes(yatzyThrow);

            // then
            return _.isEqual(score, _.reduce(yatzyThrow.dice, (acc, die) => die === 3 ? acc + die : acc, 0));
        });
    });
});

describe("Fours", () => {
    it("score the sum of 4s", () => {
        jsc.checkForall(Generators.yatzyThrowGenerator, (yatzyThrow) => {
            // given

            // when
            let score = YatzyScorer.fours(yatzyThrow);

            // then
            return _.isEqual(score, _.reduce(yatzyThrow.dice, (acc, die) => die === 4 ? acc + die : acc, 0));
        });
    });
});

describe("Fives", () => {
    it("score the sum of fives", () => {
        jsc.checkForall(Generators.yatzyThrowGenerator, (yatzyThrow) => {
            // given

            // when
            let score = YatzyScorer.fives(yatzyThrow);

            // then
            return _.isEqual(score, _.reduce(yatzyThrow.dice, (acc, die) => die === 5 ? acc + die : acc, 0));
        });
    });
});

describe("Sixes", () => {
    it("score the sum of sixes", () => {
        jsc.checkForall(Generators.yatzyThrowGenerator, (yatzyThrow) => {
            // given

            // when
            let score = YatzyScorer.sixes(yatzyThrow);

            // then
            return _.isEqual(score, _.reduce(yatzyThrow.dice, (acc, die) => die === 6 ? acc + die : acc, 0));
        });
    });
});

describe("One pair", () => {
    it("should score the sum of the highest pair", () => {
        jsc.checkForall(Generators.onePairArbitrary, (yatzyThrow) => {
            // given

            // when
            let score = YatzyScorer.pair(yatzyThrow.yatzyThrow);

            // then
            return _.isEqual(score, yatzyThrow.pair * 2);
        });
    });

    it("should score 0 when there is no pair", () => {
        jsc.checkForall(Generators.notOnePairArbitrary, (yatzyThrow) => {
            // given

            // when
            let score = YatzyScorer.pair(yatzyThrow);

            // then
            return _.isEqual(score, 0);
        });
    });
});

describe("Two pair", () => {
    it("should score the sum of the two pairs", () => {
        jsc.checkForall(Generators.twoPairGenerator, (yatzyThrow) => {
            // given

            // when
            let score = YatzyScorer.twoPair(yatzyThrow.yatzyThrow);

            // then
            return _.isEqual(score, (yatzyThrow.pair1 + yatzyThrow.pair2) * 2);
        });

    });
    it("should score 0 if no two pair", () => {
        jsc.checkForall(Generators.notTwoPairArbitrary, (yatzyThrow) => {
            // given

            // when
            let score = YatzyScorer.twoPair(yatzyThrow);

            // then
            return _.isEqual(score, 0);
        });

    });
});

describe("Three of a kind", () => {
    it("should score the sum of the three of the kind", () => {
        jsc.checkForall(Generators.atLeastThreeOfAKindGenerator, (yatzyThrow) => {
            // given

            // when
            let score = YatzyScorer.threeOfAKind(yatzyThrow.yatzyThrow);

            // then
            return _.isEqual(score, yatzyThrow.threeOfAKind * 3);
        });
    });
    it("should score 0 when there is no the three of the kind", () => {
        jsc.checkForall(Generators.notThreeOfAKindArbitrary, (yatzyThrow) => {
            // given

            // when
            let score = YatzyScorer.threeOfAKind(yatzyThrow);

            // then
            return _.isEqual(score, 0);
        });
    });
});

describe("Four of a kind", () => {
    it("should score the sum of the four of the kind", () => {
        jsc.checkForall(Generators.atLeastFourOfAKindGenerator, (yatzyThrow) => {
            // given

            // when
            let score = YatzyScorer.fourOfAKind(yatzyThrow.yatzyThrow);

            // then
            return _.isEqual(score, yatzyThrow.fourOfAKind * 4);
        });
    });
    it("should score 0 when there is no the four of the kind", () => {
        jsc.checkForall(Generators.notFourOfAKindArbitrary, (yatzyThrow) => {
            // given

            // when
            let score = YatzyScorer.fourOfAKind(yatzyThrow);

            // then
            return _.isEqual(score, 0);
        });
    });
});

describe("Small straight", () => {
    it("should score 15", () => {
        // given
        let throw1 = new YatzyThrow(1, 2, 3, 4, 5);

        // when
        let score1 = YatzyScorer.smallStraight(throw1);

        // then
        assert.strictEqual(score1, 15);
    });
    it("should score 0 when no small straight", () => {
        jsc.checkForall(Generators.notSmallStraightArbitrary, (yatzyThrow) => {
            // given

            // when
            let score = YatzyScorer.smallStraight(yatzyThrow);

            // then
            return _.isEqual(score, 0);
        });
    });
});

describe("Large straight", () => {
    it("should score 20", () => {
        // given
        let throw1 = new YatzyThrow(2, 3, 4, 5, 6);

        // when
        let score1 = YatzyScorer.largeStraight(throw1);

        // then
        assert.strictEqual(score1, 20);
    });
    it("should score 0 when no large straight", () => {
        jsc.checkForall(Generators.notLargeStraightArbitrary, (yatzyThrow) => {
            // given

            // when
            let score = YatzyScorer.largeStraight(yatzyThrow);

            // then
            return _.isEqual(score, 0);
        });
    });
});

describe("Full house", () => {
    it("should score the sum of the full house", () => {
        jsc.checkForall(Generators.fullHouseArbitrary, (yatzyThrow) => {
            // given

            // when
            let score = YatzyScorer.fullHouse(yatzyThrow.yatzyThrow);

            // then
            return _.isEqual(score, yatzyThrow.pair * 2 + yatzyThrow.threeOfAKind * 3);
        });
    });
    it("should score O if no the full house", () => {
        jsc.checkForall(Generators.notFullHouseArbitrary, (yatzyThrow) => {
            // given

            // when
            let score = YatzyScorer.fullHouse(yatzyThrow);

            // then
            return _.isEqual(score, 0);
        });
    });
    it("should score 0 if yatzee", () => {
        jsc.checkForall(jsc.integer(1, 6), die => {
            // given
            let yatzyThrow = new YatzyThrow(die, die, die, die, die);

            // when
            let score = YatzyScorer.fullHouse(yatzyThrow);

            // then
            return _.isEqual(score, 0);
        });
    });
});
