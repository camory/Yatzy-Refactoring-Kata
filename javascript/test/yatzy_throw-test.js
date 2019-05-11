const assert = require("assert");
const YatzyThrow = require("../lib/yatzy_throw");

describe('YatzyThrow', function () {
    it('should not accept 0', function () {
        assert.throws(() => new YatzyThrow(0, 2, 3, 4, 5));
    });

    it('should not accept 7', function () {
        assert.throws(() => new YatzyThrow(1, 2, 3, 4, 7));
    });

    it('should accept values between 1 and 6', function () {
        // given
        assert.doesNotThrow(() => new YatzyThrow(1, 2, 3, 4, 5));
        assert.doesNotThrow(() => new YatzyThrow(2, 3, 4, 5, 6));

        console.log(new YatzyThrow(1, 1, 1, 3, 4).diceCount[1])
    });
});
