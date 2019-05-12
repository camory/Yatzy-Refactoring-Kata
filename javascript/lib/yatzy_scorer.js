const _ = require('lodash');

module.exports.chance = (yatzyThrow) => _.sum(yatzyThrow.dice);

module.exports.yatzy = (yatzyThrow) => _.keys(yatzyThrow.diceCount).length === 1 ? 50 : 0;

module.exports.ones = (yatzyThrow) => yatzyThrow.count(1);

module.exports.twos = (yatzyThrow) => yatzyThrow.count(2);

module.exports.threes = (yatzyThrow) => yatzyThrow.count(3);

module.exports.fours = (yatzyThrow) => yatzyThrow.count(4);

module.exports.fives = (yatzyThrow) => yatzyThrow.count(5);

module.exports.sixes = (yatzyThrow) => yatzyThrow.count(6);

module.exports.pair = (yatzyThrow) => {
    let pairs = _.pickBy(yatzyThrow.diceCount, value => value >= 2);
    return _.isEmpty(pairs) ? 0 : _.max(_.keys(pairs)) * 2;
};

module.exports.twoPair = (yatzyThrow) => {
    let pairs = _.pickBy(yatzyThrow.diceCount, value => value >= 2);
    let keys = _.keys(pairs);

    if (keys.length === 2) return keys[0] * 2 + keys[1] * 2;
    if (keys.length === 1 && pairs[keys[0]] > 3) return keys[0] * 4;

    return 0;
};

module.exports.threeOfAKind = (yatzyThrow) => {
    let three_of_a_kind = _.pickBy(yatzyThrow.diceCount, value => value >= 3);
    return _.isEmpty(three_of_a_kind) ? 0 : _.keys(three_of_a_kind) * 3;
};

module.exports.fourOfAKind = (yatzyThrow) => {
    let three_of_a_kind = _.pickBy(yatzyThrow.diceCount, value => value >= 4);
    return _.isEmpty(three_of_a_kind) ? 0 : _.keys(three_of_a_kind) * 4;
};

module.exports.smallStraight = (yatzyThrow) => {
    return _.difference([1, 2, 3, 4, 5], yatzyThrow.dice).length === 0 ? 15 : 0;
};

module.exports.largeStraight = (yatzyThrow) => {
    return _.difference([2, 3, 4, 5, 6], yatzyThrow.dice).length === 0 ? 20 : 0;
};

module.exports.fullHouse = (yatzyThrow) => {
    let three_of_kind = _.pickBy(yatzyThrow.diceCount, value => value >= 3);
    let two_of_a_kind = _.pickBy(yatzyThrow.diceCount, value => value === 2);

    if (!_.isEmpty(three_of_kind) && !_.isEmpty(two_of_a_kind)) {
        return _.keys(three_of_kind)[0] * 3 + _.keys(two_of_a_kind)[0] * 2;
    }
    return 0;
};

