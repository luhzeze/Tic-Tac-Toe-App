import java.awt.desktop.SystemEventListener;
import java.util.Map;
import java.util.Scanner;

public class TicTacToeApp {
    static Scanner scanner;
    static char[][] map;
    static final int MAP_SIZE = 3;
    static final char EMPTY_FIELD = '*';
    static final char X_FIELD = 'X';
    static final char O_FIELD = 'O';

    public static void main(String[] args) {
        init();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(X_FIELD)) {
                System.out.println("Игра завершена. Победил игрок!");
                break;
            }
            if (chekDraft()) {
                System.out.println("Игра завершена. Ничья");
                break;
            }

            aiTurn();
            printMap();
            if (checkWin(O_FIELD)) {
                System.out.println("Игра завершена. Победил компьютер!");
                break;
            }
            if (chekDraft()) {
                System.out.println("Игра завершена. Ничья");
                break;
            }
        }
    }

    public static boolean checkWin(char playerField) {
        //   0 1 2
        // 0 * * *
        // 1 * * *
        // 2 * * *

        if (map[0][0] == playerField && map[0][1] == playerField && map[0][2] == playerField) return true;
        if (map[1][0] == playerField && map[1][1] == playerField && map[1][2] == playerField) return true;
        if (map[2][0] == playerField && map[2][1] == playerField && map[2][2] == playerField) return true;

        if (map[0][0] == playerField && map[1][0] == playerField && map[2][0] == playerField) return true;
        if (map[0][1] == playerField && map[1][1] == playerField && map[2][1] == playerField) return true;
        if (map[0][2] == playerField && map[1][2] == playerField && map[2][2] == playerField) return true;

        if (map[0][0] == playerField && map[1][1] == playerField && map[2][2] == playerField) return true;
        if (map[0][2] == playerField && map[1][1] == playerField && map[2][0] == playerField) return true;

        return false;
    }

    public static boolean chekDraft() {
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map[i][j] == EMPTY_FIELD) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= MAP_SIZE || y >= MAP_SIZE) {
            return false;
        }
        if (map[y][x] != EMPTY_FIELD) {
            return false;
        }
        return true;
    }

    public static void aiTurn() {
        int x, y;
        System.out.println("ход компьютера");
        do {
            x = (int)(Math.random() * MAP_SIZE);
            y = (int)(Math.random() * MAP_SIZE);
        } while (!isCellValid(x, y));
        map[y][x] = O_FIELD;
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Ход игрока. Введите координаты вашего хода X Y:");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = X_FIELD;
    }

    public static void printMap() {
        // 0 1 2 3
        // 1 * * *
        // 2 * * *
        // 3 * * *
        for (int i = 0; i <= MAP_SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < MAP_SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < MAP_SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void init() {
        map = new char[MAP_SIZE][MAP_SIZE];
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
            map[i][j] = EMPTY_FIELD;
            }
        }
        scanner = new Scanner(System.in);
    }
}
