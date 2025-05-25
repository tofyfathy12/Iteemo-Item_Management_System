package item.management.system;

import java.io.IOException;

public class ConsoleMenu {
    // ANSI escape codes for colors and styling
    public static final String RESET       = "\u001B[0m";
    public static final String BRIGHT      = "\u001B[1m";
    public static final String UNDERSCORE  = "\u001B[4m";

    public static final String FG_CYAN     = "\u001B[36m";
    public static final String FG_YELLOW   = "\u001B[33m";
    public static final String FG_GREEN    = "\u001B[32m";
    public static final String FG_RED      = "\u001B[31m";

    // Box-drawing characters (UTF-8)
    public static final String HORIZONTAL  = "\u2500";
    public static final String VERTICAL    = "\u2502";
    public static final String TOP_LEFT    = "\u250C";
    public static final String TOP_RIGHT   = "\u2510";
    public static final String BOTTOM_LEFT = "\u2514";
    public static final String BOTTOM_RIGHT= "\u2518";

    private static void clearScreen() {
        // ANSI clear screen
        System.out.print("\033[2J\033[H");
        System.out.flush();
    }

    private static void pause() throws IOException {
        System.out.print("\nPress ENTER to continue...");
        System.in.read();
    }

    private static void printMenu(int selected) {
        String[] options = {"Start New Game", "Load Game", "Settings", "Exit"};
        int n = options.length;

        clearScreen();
        System.out.print(BRIGHT + FG_CYAN);
        System.out.printf("%s%s%s\n", TOP_LEFT, HORIZONTAL.repeat(20), TOP_RIGHT);
        System.out.printf("%s  COOL CONSOLE MENU  %s\n", VERTICAL, VERTICAL);
        System.out.printf("%s%s%s\n" + RESET, BOTTOM_LEFT, HORIZONTAL.repeat(20), BOTTOM_RIGHT);

        for (int i = 0; i < n; i++) {
            if (i == selected) {
                System.out.printf("  %s> %s%s%s\n" + RESET,
                        FG_YELLOW, UNDERSCORE, options[i], RESET);
            } else {
                System.out.printf("    %s\n", options[i]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int selected = 0;
        clearScreen();

        // Hide cursor
        System.out.print("\u001B[?25l");
        System.out.flush();

        while (true) {
            printMenu(selected);

            int input = System.in.read();
            // consume newline or other leftover
            while (System.in.available() > 0) {
                System.in.read();
            }

            if (input == 'w' || input == 'W') {
                selected = (selected - 1 + 4) % 4;
            } else if (input == 's' || input == 'S') {
                selected = (selected + 1) % 4;
            } else if (input == '\n' || input == '\r') {
                switch (selected) {
                    case 0:
                        System.out.println(FG_GREEN + "Starting a new game..." + RESET);
                        pause();
                        break;
                    case 1:
                        System.out.println(FG_GREEN + "Loading game..." + RESET);
                        pause();
                        break;
                    case 2:
                        System.out.println(FG_GREEN + "Opening settings..." + RESET);
                        pause();
                        break;
                    case 3:
                        System.out.println(FG_RED + "Exiting..." + RESET);
                        // Show cursor
                        System.out.print("\u001B[?25h");
                        System.out.flush();
                        System.exit(0);
                        break;
                }
            }
        }
    }
}

