const jsc = require("jsverify");
const YatzyThrow = require("../lib/yatzy_throw");
const Generators = require("./generators");

describe('YatzyThrow', () => {
    it('should not accept non die values', () => {
        jsc.checkForall(Generators.notADieArbitrary, Generators.notADieArbitrary, Generators.notADieArbitrary, Generators.notADieArbitrary, Generators.notADieArbitrary, (d1, d2, d3, d4, d5) => {
            try {
                new YatzyThrow(d1, d2, d3, d4, d5);
            } catch (e) {
                return true;
            }
            return false;
        });
    });

    it('should accept values between 1 and 6', () => {
        jsc.checkForall(Generators.dieArbitrary, Generators.dieArbitrary, Generators.dieArbitrary, Generators.dieArbitrary, Generators.dieArbitrary, (d1, d2, d3, d4, d5) => {
            try {
                new YatzyThrow(d1, d2, d3, d4, d5);
            } catch (e) {
                return false;
            }
            return true;
        });
    });
});
