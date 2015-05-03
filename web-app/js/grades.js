var kpiWeights = {
    'calls': 0.15,
    'revenue': 0.43,
    'demos': 0.14,
    'pipeline': 0.14,
    'closing': 0.14
};

var kpiKeys = {
    calls: 'calls',
    revenue: 'revenue',
    demos: 'demos',
    pipeline: 'pipeline',
    closing: 'closing'
};

function calcPercentage(actual, goal, multiplier) {
    return (actual / (goal * multiplier));
}

function calcWeightedPercentage(actual, goal, multiplier, dayOfMonth, totalDays) {
    var weightedGoal = (goal / totalDays) * dayOfMonth;

    return calcPercentage(actual, weightedGoal, multiplier);
}

function maxScore(score) {
    return Math.min(score, 1);
}

function rollupScores(scores) {
    var rollup = scores.reduce(function(prev, current) {
        return prev + (maxScore(current.score) * current.weight);
    }, 0);
    return rollup
}

function grabGrade(percentage) {
    var result = "F";

    if (percentage != null) {
        if (percentage >= 0.9) {
            result = "A"
        } else if (percentage >= 0.8) {
            result = "B"
        } else if (percentage >= 0.7) {
            result = "C"
        } else if (percentage >= 0.6) {
            result = "D"
        }
    }

    return result;
}