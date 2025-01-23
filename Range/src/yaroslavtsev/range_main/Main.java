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

        System.out.printf("Введённое значение попало в диапазон. Попыток: %d", attemptsCount);
    }
}
