package yaroslavtsev.range;

public class Range {
    private double from;
    private double to;

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
        } else if (firstRange.isInside(secondRange.getFrom()) && firstRange.isInside(secondRange.getTo())) {
            intersectionIntervalFrom = firstRange.getFrom();
            intersectionIntervalTo = firstRange.getTo();
            intersectionInterval = new Range(intersectionIntervalFrom, intersectionIntervalTo);
        } else intersectionInterval = null;

        return intersectionInterval;
    }

    public Range[] getIntervalsIntersection(Range firstRange, Range secondRange) {
        Range[] intervalsArray;

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
                intervalsArray[0] = intervalsIntersection;
            } else if (firstRange.getTo() >= secondRange.getTo()) {
                intervalsIntersection = new Range(secondRange.getFrom(), firstRange.getTo());
                intervalsArray[0] = intervalsIntersection;
            } else {
                intervalsIntersection = new Range(secondRange.getFrom(), secondRange.getTo());
                intervalsArray[0] = intervalsIntersection;
            }
        }
        return intervalsArray;
    }
}
