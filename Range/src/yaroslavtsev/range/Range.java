package yaroslavtsev.range;

public class Range {
    private double from;
    private double to;
    private Range[] intervalsArray;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public void setIntervalsArray(Range[] intervalsArray) {
        this.intervalsArray = intervalsArray;
    }

    public Range[] getIntervalsArray() {
        return intervalsArray;
    }

    public boolean isInside(double number) {
        return from <= number && number <= to;
    }

    public Range getIntersectionInterval(Range firstRange, Range secondRange) {
        double intersectionIntervalFrom;
        double intersectionIntervalTo;
        Range intersectionInterval;

        if (firstRange.isInside(secondRange.getFrom()) && firstRange.isInside(secondRange.getTo())) {
            intersectionIntervalFrom = secondRange.getFrom();
            intersectionIntervalTo = secondRange.getTo();
            intersectionInterval = new Range(intersectionIntervalFrom, intersectionIntervalTo);
        } else if (secondRange.isInside(firstRange.getFrom()) && secondRange.isInside(firstRange.getTo())) {
            intersectionIntervalFrom = firstRange.getFrom();
            intersectionIntervalTo = firstRange.getTo();
            intersectionInterval = new Range(intersectionIntervalFrom, intersectionIntervalTo);
        } else intersectionInterval = null;

        return intersectionInterval;
    }

    public Range[] getIntervalsIntersection(Range firstRange, Range secondRange) {

        if (getIntersectionInterval(firstRange, secondRange) == null) {
            intervalsArray = new Range[2];
            intervalsArray[0] = firstRange;
            intervalsArray[1] = secondRange;
        } else {
            intervalsArray = new Range[1];
            Range intervalsIntersection;

            if (firstRange.getFrom() <= secondRange.getFrom()) {
                if (firstRange.getTo() >= secondRange.getTo()) {
                    intervalsIntersection = new Range(firstRange.getFrom(), firstRange.getTo());
                } else {
                    intervalsIntersection = new Range(firstRange.getFrom(), secondRange.getTo());
                }

            } else {
                if (firstRange.getTo() > secondRange.getTo()) {
                    intervalsIntersection = new Range(secondRange.getFrom(), firstRange.getTo());
                } else {
                    intervalsIntersection = new Range(secondRange.getFrom(), secondRange.getTo());
                }
            }

            intervalsArray[0] = intervalsIntersection;
        }

        return intervalsArray;
    }

    public Range[] getIntervalsDifference(Range firstRange, Range secondRange) {
        Range intervalsDifference;

        if ((firstRange.isInside(secondRange.getFrom()) && firstRange.isInside(secondRange.getTo())) && ((secondRange.getFrom() != firstRange.getFrom()) && (secondRange.getTo() != firstRange.getTo()))) {
            intervalsArray = new Range[2];
            intervalsArray[0] = new Range(firstRange.getFrom(), secondRange.getFrom());
            intervalsArray[1] = new Range(secondRange.getTo(), firstRange.getTo());
        } else if (firstRange.isInside(secondRange.getFrom())) {
            if (secondRange.getTo() == firstRange.getTo()) {
                intervalsArray = new Range[1];
                intervalsDifference = new Range(firstRange.getFrom(), secondRange.getFrom());
                intervalsArray[0] = intervalsDifference;
            } else {
                intervalsArray = new Range[1];
                intervalsDifference = new Range(secondRange.getTo(), firstRange.getTo());
                intervalsArray[0] = intervalsDifference;
            }

        } else if (firstRange.isInside(secondRange.getTo())) {
            if (secondRange.getFrom() == firstRange.getFrom()) {
                intervalsArray = new Range[1];
                intervalsDifference = new Range(secondRange.getTo(), firstRange.getTo());
                intervalsArray[0] = intervalsDifference;
            } else {
                intervalsArray = new Range[0];
                intervalsDifference = null;
                intervalsArray = null;
            }

        } else if ((firstRange.getFrom() == secondRange.getFrom()) && (firstRange.getTo() == secondRange.getTo())) {
            intervalsArray = new Range[0];
            intervalsDifference = null;
            intervalsArray = null;
        } else {
            intervalsArray = new Range[1];
            intervalsArray[0] = new Range(firstRange.getFrom(), secondRange.getTo());
        }

        return intervalsArray;
    }
}
