function calcPercentage(actual, goal, multiplier) {
    return (actual / (goal * multiplier));
}

function calcWeightedPercentage(actual, goal, multiplier, dayOfMonth, totalDays) {
    var weightedGoal = (goal / totalDays) * dayOfMonth;

    return calcPercentage(actual, weightedGoal, multiplier);
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