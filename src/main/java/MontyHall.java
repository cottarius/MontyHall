import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MontyHall {
    static Random random = new Random();
    static final int COUNT_OF_DOORS = 3;
    static final int ITERATION_COUNT = 1000;
    static HashMap<Integer, Boolean> countSelectedDoorArray;
    static HashMap<Integer, Boolean> countAnotherDoorArray;

    public static void main(String[] args) {
        countSelectedDoorArray = new HashMap<>();
        countAnotherDoorArray = new HashMap<>();
        long result1 = countWinResult(countSelectedDoorArray, true);
        long result2 = countWinResult(countAnotherDoorArray, false);
        System.out.printf("Если поменять дверь, то шанс выиграть приз - %s из 1000 (%.1f%%)\n", result1, (double) result1 / 1000 * 100);
        System.out.printf("Если НЕ менять дверь, то шанс выиграть приз - %s из 1000 (%.1f%%)", result2, (double) result2 / 1000 * 100);
    }

    /**
     * Подсчёт количества выигрышей
     * @param hashMap контейнер для сложения результатов
     * @param flag true - выбор другой двери, false - повторный выбор текущей двери
     * @return количество побед
     */
    private static long countWinResult(HashMap<Integer, Boolean> hashMap, boolean flag) {
        for (int i = 0; i < ITERATION_COUNT; i++) {
            ArrayList<Boolean> doors = initDoors();
            hashMap.put(i, flag ? chooseAnotherDoor(doors) : chooseSelectedDoor(doors));
        }
        return hashMap.values().stream().filter(o -> o).count();
    }

    static boolean chooseSelectedDoor(ArrayList<Boolean> doors) {
        return doors.get(random.nextInt(COUNT_OF_DOORS));
    }

    /**
     * Выбор другой двери после предложения ведущего сменить дверь
     * @param doors список дверей с одним true элементом
     * @return измененный другой, измененный на true, элемент списка
     */
    static boolean chooseAnotherDoor(ArrayList<Boolean> doors) {
        int choice = random.nextInt(COUNT_OF_DOORS);
        int suggestion = 0;

        for (int i = 0; i < COUNT_OF_DOORS; i++) {
            if (i == choice) {
                continue;
            }
            if (!doors.get(i)) {
                suggestion = i;
                break;
            }
        }
        int newChoice = COUNT_OF_DOORS - (choice + suggestion);
        return doors.get(newChoice);
    }

    /**
     * Иництализация списка с одним true элементом (остальные - false)
     * @return список элементов
     */
    static ArrayList<Boolean> initDoors() {
        ArrayList<Boolean> doors = new ArrayList<>();
        int index = random.nextInt(COUNT_OF_DOORS);
        for (int i = 0; i < COUNT_OF_DOORS; i++) {
            doors.add(i == index);
        }
        return doors;
    }
}
