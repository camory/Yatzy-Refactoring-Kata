const _ = require('lodash');

class YatzyThrow {
    constructor(d1, d2, d3, d4, d5) {
        [d1, d2, d3, d4, d5].forEach(validate);
        this._dice = [d1, d2, d3, d4, d5];
    }

    get dice() {
        return this._dice;
    }

    get diceCount() {
        return _.mapValues(_.groupBy(this.dice), a => a.length);
    }

    count(die) {
        validate(die);
        return this._dice.indexOf(die) === -1 ? 0 : this.diceCount[die] * die
    }
}

let validate = (die) => {
    if (!_.isInteger(die) || !_.inRange(die, 1, 7)) throw die + " is not a valid value";
};

module.exports = YatzyThrow;
