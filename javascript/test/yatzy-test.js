const assert = require("assert");
const YatzyScorer = require("../lib/yatzy_scorer");
const YatzyThrow = require("../lib/yatzy_throw");


describe('Chance', () => {
    it('should score sum of all dice', () => {
        // given
        let throw1 = new YatzyThrow(2, 3, 4, 5, 1);
        let throw2 = new YatzyThrow(3, 3, 4, 5, 1);

        // when
        let score1 = YatzyScorer.chance(throw1);
        let score2 = YatzyScorer.chance(throw2);

        // then
        assert.strictEqual(score1, 15);
        assert.strictEqual(score2, 16);
    });
});

describe("YatzyScorer", () => {
    it("should scores 50", () => {
        // given
        let throw1 = new YatzyThrow(4, 4, 4, 4, 4);
        let throw2 = new YatzyThrow(6, 6, 6, 6, 6);

        // when
        let score1 = YatzyScorer.yatzy(throw1);
        let score2 = YatzyScorer.yatzy(throw2);

        // then
        assert.strictEqual(score1, 50);
        assert.strictEqual(score2, 50);
    });
    it("should scores 0", () => {
        // given
        let throw1 = new YatzyThrow(6, 6, 6, 6, 2);

        // when
        let score1 = YatzyScorer.yatzy(throw1);

        // then
        assert.strictEqual(score1, 0);
    });
});

describe("Ones", () => {
    it("should score the sum of 1s", () => {
        // given
        let throw1 = new YatzyThrow(1, 2, 3, 4, 5);
        let throw2 = new YatzyThrow(1, 2, 1, 4, 5);
        let throw3 = new YatzyThrow(6, 2, 2, 4, 5);
        let throw4 = new YatzyThrow(1, 2, 1, 1, 1);

        // when
        let score1 = YatzyScorer.ones(throw1);
        let score2 = YatzyScorer.ones(throw2);
        let score3 = YatzyScorer.ones(throw3);
        let score4 = YatzyScorer.ones(throw4);

        // then
        assert.strictEqual(score1, 1);
        assert.strictEqual(score2, 2);
        assert.strictEqual(score3, 0);
        assert.strictEqual(score4, 4);
    });
});

describe("Twos", () => {
    it("should score the sum of 2s", () => {
        // given
        let throw1 = new YatzyThrow(1, 2, 3, 2, 6);
        let throw2 = new YatzyThrow(2, 2, 2, 2, 2);

        // when
        let score1 = YatzyScorer.twos(throw1);
        let score2 = YatzyScorer.twos(throw2);

        // then
        assert.strictEqual(score1, 4);
        assert.strictEqual(score2, 10);
    });
});

describe("Threes", () => {
    it("should score the sum of 3s", () => {
        //given
        let throw1 = new YatzyThrow(1, 2, 3, 2, 3);
        let throw2 = new YatzyThrow(2, 3, 3, 3, 3);

        // when
        let score1 = YatzyScorer.threes(throw1);
        let score2 = YatzyScorer.threes(throw2);

        // then
        assert.strictEqual(score1, 6);
        assert.strictEqual(score2, 12);
    });
});

describe("Fours", () => {
    it("score the sum of 4s", () => {
        // given
        let throw1 = new YatzyThrow(4,4,4,5,5);
        let throw2 = new YatzyThrow(4,4,5,5,5);
        let throw3 = new YatzyThrow(4,5,5,5,5);

        // when
        let score1 = YatzyScorer.fours(throw1);
        let score2 = YatzyScorer.fours(throw2);
        let score3 = YatzyScorer.fours(throw3);

        // then
        assert.strictEqual(score1, 12);
        assert.strictEqual(score2, 8);
        assert.strictEqual(score3, 4);
    });
});

describe("Fives", () => {
    it("score the sum of fives", () => {
        // given
        let throw1 = new YatzyThrow(4,4,4,5,5);
        let throw2 = new YatzyThrow(4,4,5,5,5);
        let throw3 = new YatzyThrow(4,5,5,5,5);

        // when
        let score1 = YatzyScorer.fives(throw1);
        let score2 = YatzyScorer.fives(throw2);
        let score3 = YatzyScorer.fives(throw3);

        // then
        assert.strictEqual(score1, 10);
        assert.strictEqual(score2, 15);
        assert.strictEqual(score3, 20);
    });
});

describe("Sixes", () => {
    it("score the sum of sixes", () => {
        // given
        let throw1 = new YatzyThrow(4,4,4,5,5);
        let throw2 = new YatzyThrow(4,4,6,5,5);
        let throw3 = new YatzyThrow(6,5,6,6,5);

        // when
        let score1 = YatzyScorer.sixes(throw1);
        let score2 = YatzyScorer.sixes(throw2);
        let score3 = YatzyScorer.sixes(throw3);

        // then
        assert.strictEqual(score1, 0);
        assert.strictEqual(score2, 6);
        assert.strictEqual(score3, 18);
    });
});

describe("One pair", () => {
    it("should score the sum of the highest pair", () => {
        // given
        let throw1 = new YatzyThrow(3, 4, 3, 5, 6);
        let throw2 = new YatzyThrow(5, 3, 3, 3, 5);
        let throw3 = new YatzyThrow(5, 3, 6, 6, 5);
        let throw4 = new YatzyThrow(1, 2, 3, 4, 5);

        // when
        let score1 = YatzyScorer.pair(throw1);
        let score2 = YatzyScorer.pair(throw2);
        let score3 = YatzyScorer.pair(throw3);
        let score4 = YatzyScorer.pair(throw4);

        // then
        assert.strictEqual(score1, 6);
        assert.strictEqual(score2, 10);
        assert.strictEqual(score3, 12);
        assert.strictEqual(score4, 0);
    });

    it("should score 0 when there is no pair", () => {
        // given
        let throw1 = new YatzyThrow(1, 2, 3, 4, 5);

        // when
        let score1 = YatzyScorer.pair(throw1);

        // then
        assert.strictEqual(score1, 0);
    });
});

describe("Two pair", () => {
    it("should score the sum of the two pairs", () => {
        // given
        let throw1 = new YatzyThrow(3, 3, 5, 4, 5);
        let throw2 = new YatzyThrow(3, 3, 5, 5, 5);

        // when
        let score1 = YatzyScorer.two_pair(throw1);
        let score2 = YatzyScorer.two_pair(throw2);

        // then
        assert.strictEqual(score1, 16);
        assert.strictEqual(score2, 16);

    });
    it("should score 0 if one pair", () => {
        // given
        let throw1 = new YatzyThrow(3, 3, 1, 4, 5);

        // when
        let score1 = YatzyScorer.two_pair(throw1);

        // then
        assert.strictEqual(score1, 0);

    });
    it("should score 0 if no pair", () => {
        // given
        let throw1 = new YatzyThrow(3, 1, 1, 4, 5);

        // when
        let score1 = YatzyScorer.two_pair(throw1);

        // then
        assert.strictEqual(score1, 0);

    });
});

describe("Three of a kind", () => {
    it("should score the sum of the three of the kind", () => {
        // given
        let throw1 = new YatzyThrow(3, 3, 3, 4, 5);
        let throw2 = new YatzyThrow(5, 3, 5, 4, 5);
        let throw3 = new YatzyThrow(3, 3, 3, 3, 5);

        // when
        let score1 = YatzyScorer.three_of_a_kind(throw1);
        let score2 = YatzyScorer.three_of_a_kind(throw2);
        let score3 = YatzyScorer.three_of_a_kind(throw3);

        // then
        assert.strictEqual(score1, 9);
        assert.strictEqual(score2, 15);
        assert.strictEqual(score3, 9);
    });
    it("should score 0 when there is no the three of the kind", () => {
        // given
        let throw1 = new YatzyThrow(3, 3, 1, 4, 5);

        // when
        let score1 = YatzyScorer.three_of_a_kind(throw1);

        // then
        assert.strictEqual(score1, 0);
    });
});

describe("Four of a kind", () => {
    it("should score the sum of the four of the kind", () => {
        // given
        let throw1 = new YatzyThrow(3, 3, 3, 3, 5);
        let throw2 = new YatzyThrow(5, 5, 5, 4, 5);
        let throw3 = new YatzyThrow(3, 3, 3, 3, 3);

        // when
        let score1 = YatzyScorer.four_of_a_kind(throw1);
        let score2 = YatzyScorer.four_of_a_kind(throw2);
        let score3 = YatzyScorer.four_of_a_kind(throw3);

        // then
        assert.strictEqual(score1, 12);
        assert.strictEqual(score2, 20);
        assert.strictEqual(score3, 12);
    });
    it("should score 0 when there is no the four of the kind", () => {
        // given
        let throw1 = new YatzyThrow(3, 3, 1, 4, 5);

        // when
        let score1 = YatzyScorer.four_of_a_kind(throw1);

        // then
        assert.strictEqual(score1, 0);
    });
});

describe("Small straight", () => {
    it("should score 15", () => {
        // given
        let throw1 = new YatzyThrow(1, 2, 3, 4, 5);
        let throw2 = new YatzyThrow(2, 3, 4, 5, 1);

        // when
        let score1 = YatzyScorer.small_straight(throw1);
        let score2 = YatzyScorer.small_straight(throw2);

        // then
        assert.strictEqual(score1, 15);
        assert.strictEqual(score2, 15);
    });
    it("should score 0 when no small straight", () => {
        // given
        let throw1 = new YatzyThrow(1, 2, 2, 4, 5);

        // when
        let score1 = YatzyScorer.small_straight(throw1);

        // then
        assert.strictEqual(score1, 0);
    });
    it("should score 0 when large straight", () => {
        // given
        let throw1 = new YatzyThrow(2, 3, 4, 5, 6);

        // when
        let score1 = YatzyScorer.small_straight(throw1);

        // then
        assert.strictEqual(score1, 0);
    });
});

describe("Large straight", () => {
    it("should score 20", () => {
        // given
        let throw1 = new YatzyThrow(6,2,3,4,5);
        let throw2 = new YatzyThrow(2,3,4,5,6);

        // when
        let score1 = YatzyScorer.large_straight(throw1);
        let score2 = YatzyScorer.large_straight(throw2);

        // then
        assert.strictEqual(score1, 20);
        assert.strictEqual(score2, 20);
    });
    it("should score 0 when no large straight", () => {
        // given
        let throw1 = new YatzyThrow(1, 2, 2, 4, 5);

        // when
        let score1 = YatzyScorer.small_straight(throw1);

        // then
        assert.strictEqual(score1, 0);
    });
    it("should score 0 when small straight", () => {
        // given
        let throw1 = new YatzyThrow(1,2, 3, 4, 5);

        // when
        let score1 = YatzyScorer.large_straight(throw1);

        // then
        assert.strictEqual(score1, 0);
    });
});

describe("Full house", () => {
    it("should score the sum of the full house", () => {
        // given
        let throw1 = new YatzyThrow(6,2,2,2,6);

        // when
        let score1 = YatzyScorer.full_house(throw1);

        // then
        assert.strictEqual(score1, 18);
    });
    it("should score 0 if no the full house", () => {
        // given
        let throw1 = new YatzyThrow(2,3,4,5,6);

        // when
        let score1 = YatzyScorer.full_house(throw1);

        // then
        assert.strictEqual(score1, 0);
    });
});
