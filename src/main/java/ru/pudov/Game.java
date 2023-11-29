package ru.pudov;

import java.util.*;

public class Game {
    private static final Scanner in = new Scanner(System.in);
    private static int changeWon = 0;
    private static int changeLoose = 0;
    private static int stayWon = 0;
    private static int stayLoose = 0;
    private static String firstChoice;
    private static final Set<String> choice = Set.of("0", "1", "2", "3");
    private static DoorList doorList;

    public static void startGame() {

        doorList = new DoorList();

        System.out.println("\nПеред Вами три двери. За одной из них автомобиль, за двумя другими козы. Выберите любую " +
                "дверь цифрами от 1 до 3 или введите 0 для окончания игры: ");

        step1();
    }

    public static void step1() {

        firstChoice = in.nextLine();

        while (!choice.contains(firstChoice)) {
            System.out.println("Неправильно указан номер двери! Укажите номер от 1 до 3! Или нажмите 0 для завершения:");
            step1();
        }
        if (Objects.equals(firstChoice, "0")) endTheGame();
        else step2(firstChoice);
    }

    public static void step2(String input) {
        ArrayList<String> nums = new ArrayList<>(Arrays.asList("1", "2", "3"));
        int adminChoice = doorList.removeOne(Integer.parseInt(input));
        String removed = nums.get(adminChoice);
        nums.remove(adminChoice);

        System.out.println("Вы выбрали дверь под номером " + input + ". Мы пока не знаем, что за ней скрывается." +
                "\nНо зато мы точно знаем, что за дверью № " + removed + " была коза, и мы убрали эту дверь из игры!" +
                " Таким образом в игре осталось две двери: " + nums.get(0) + " и " + nums.get(1) +
                "\nКакую дверь теперь Вы выбираете?");

        step3(nums);
    }

    public static void step3(ArrayList<String> nums) {
        String input = in.nextLine();
        while (!nums.contains(input)) {
            System.out.println("Неправильно указан номер двери! Укажите номер " + nums.get(0) + " или номер " + nums.get(1) + ":");
            step3(nums);
        }
        if (doorList.getList().get(Integer.parseInt(input) - 1).car) {
            if (input.equals(firstChoice)) {
                System.out.println("Поздравляем! Вы не меняли дверь и при этом выиграли автомобиль!");
                stayWon++;
            }
            else {
                System.out.println("Поздравляем! Вы поменяли дверь и выиграли автомобиль!");
                changeWon++;
            }
        } else {
            if (input.equals(firstChoice)) {
                System.out.println("Увы... Вы не меняли дверь и за дверью оказалась коза...");
                stayLoose++;
            }
            else {
                System.out.println("Увы... Вы поменяли дверь и за дверью оказалась коза...");
                changeLoose++;
            }
        }
        System.out.println("Введите любой символ для продолжения или введите 0 для завершения игры: ");
        if (Objects.equals(in.nextLine(), "0")) endTheGame();
        else startGame();
    }

    public static void endTheGame() {
        CalcStatistic calcStatistic = new CalcStatistic(changeWon, changeLoose, stayWon, stayLoose);
        System.out.println("\nСтатистика Вашей игры: " +
                "\nВыигрышей - " + calcStatistic.calcWin() + " %, в т.ч.: \n-после смены двери - " + calcStatistic.calcWinAfterChange() +
                " %\n-без смены двери - " + calcStatistic.calcWinNoChange() + " %\n\nПроигрышей - " +
                calcStatistic.calcLoose() + " %, в т.ч.: \n-после смены двери - " + calcStatistic.calcLooseAfterChange() +
                " %\n-без смены двери - " + calcStatistic.calcLooseNoChange() + " %");

        System.exit(0);
    }
}
