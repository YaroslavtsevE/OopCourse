package yaroslavtsev.range_main;

import yaroslavtsev.range.Range;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Random random = new Random();
        Range mysteryRange = new Range(random.nextDouble(10), random.nextDouble(11) + 10);

        int attemptsCount = 1;
        System.out.printf("Длина диапазона равна: %f%n", mysteryRange.getLength());

        while (true) {
            System.out.print("Введите число от 0.0 до 20.0: ");
            double enteredNumber = scanner.nextDouble();

            if (mysteryRange.isInside(enteredNumber)) {
                break;
            }

            System.out.println("Число не в диапазоне");
            ++attemptsCount;
        }

        System.out.printf("Введённое значение попало в диапазон. Попыток: %d%n", attemptsCount);

        System.out.print("Введите первое число для диапазона 1: ");
        double firstRangeFrom = scanner.nextDouble();

        System.out.print("Введите второе число для диапазона 1: ");
        double firstRangeTo = scanner.nextDouble();

        System.out.print("Введите первое число для диапазона 2: ");
        double secondRangeFrom = scanner.nextDouble();

        System.out.print("Введите второе число для диапазона 2: ");
        double secondRangeTo = scanner.nextDouble();

        Range firstRange = new Range(firstRangeFrom, firstRangeTo);
        Range secondRange = new Range(secondRangeFrom, secondRangeTo);
        Range intersectionRange = firstRange.getIntersectionInterval(firstRange, secondRange);
        Range[] rangesArray;

        if (intersectionRange != null) {
            System.out.printf("Результат получения интервала пересечения: %.2f : %.2f%n", intersectionRange.getFrom(), intersectionRange.getTo());

            rangesArray = firstRange.getIntervalsIntersection(firstRange, secondRange);
            System.out.printf("Результат объединения интервалов: (%.2f : %.2f)%n", rangesArray[0].getFrom(), rangesArray[0].getTo());

            rangesArray = firstRange.getIntervalsDifference(firstRange, secondRange);

            if (rangesArray != null) {
                if (rangesArray.length > 1) {
                    System.out.printf
                            ("Результат вычисления разности интервалов: (%.2f : %.2f), (%.2f : %.2f)%n", rangesArray[0].getFrom(), rangesArray[0].getTo(), rangesArray[1].getFrom(), rangesArray[1].getTo());
                } else {
                    System.out.printf("Результат вычисления разности интервалов: (%.2f : %.2f)%n", rangesArray[0].getFrom(), rangesArray[0].getTo());
                }
            } else {
                System.out.println("Нет пересечения интревалов для вычисления их разности");
            }

        } else {
            System.out.println("Нет пересечения интревалов для вычисления их пересечения");

            rangesArray = firstRange.getIntervalsIntersection(firstRange, secondRange);
            System.out.printf
                    ("Результат объединения интервалов: (%.2f : %.2f), (%.2f : %.2f)%n", rangesArray[0].getFrom(), rangesArray[0].getTo(), rangesArray[1].getFrom(), rangesArray[1].getTo());

            System.out.println("Нет пересечения интревалов для вычисления их разности");
        }
    }
}
