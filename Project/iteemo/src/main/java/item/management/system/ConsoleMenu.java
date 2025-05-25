package item.management.system;

import java.io.IOException;

public class ConsoleMenu {
    public String[] options = {"Add Item", "Delete Item", "Update Item", "View Items", "Search Items", "Undo Last Deletion", "Exit"};
    public int optionsNum = options.length;
    public int selected = 0;
    // ANSI escape codes for colors and styling
    public final String RESET       = "\u001B[0m";
    public final String BRIGHT      = "\u001B[1m";
    public final String UNDERSCORE  = "\u001B[4m";

    public final String FG_CYAN     = "\u001B[36m";
    public final String FG_YELLOW   = "\u001B[33m";
    public final String FG_GREEN    = "\u001B[32m";
    public final String FG_RED      = "\u001B[31m";

    // Box-drawing characters (UTF-8)
    public final String HORIZONTAL  = "\u2500";
    public final String VERTICAL    = "\u2502";
    public final String TOP_LEFT    = "\u250C";
    public final String TOP_RIGHT   = "\u2510";
    public final String BOTTOM_LEFT = "\u2514";
    public final String BOTTOM_RIGHT= "\u2518";

    public void clearScreen() {
        // ANSI clear screen
        System.out.print("\033[2J\033[H");
        System.out.flush();
    }

    public void pause() throws IOException {
        System.out.print("\nPress ENTER to continue...");
        System.in.read();
    }

    public void printMenu() {
        clearScreen();
        String consoleTitle = "COOL CONSOLE MENU";
        System.out.print(BRIGHT + FG_CYAN);
        System.out.printf("%s%s%s\n", TOP_LEFT, HORIZONTAL.repeat(20), TOP_RIGHT);
        System.out.printf("%s  %s  %s\n", VERTICAL, consoleTitle, VERTICAL);
        System.out.printf("%s%s%s\n" + RESET, BOTTOM_LEFT, HORIZONTAL.repeat(20), BOTTOM_RIGHT);

        for (int i = 0; i < optionsNum; i++) {
            if (i == selected) {
                System.out.printf("  %s> %s%s%s\n" + RESET,
                        FG_YELLOW, UNDERSCORE, options[i], RESET);
            } else {
                System.out.printf("    %s\n", options[i]);
            }
        }
    }

    public void cleanBuffer() throws IOException {
        // consume newline or other leftover
        while (System.in.available() > 0) {
            System.in.read();
        }
    }

    public void start() {
        // Hide cursor
        System.out.print("\u001B[?25l");
        System.out.flush();
    }

    public void close() {
        // Show cursor
        System.out.print("\u001B[?25h");
        System.out.flush();
        System.exit(0);
    }

    public void moveUp() {
        selected = (selected - 1 + optionsNum) % optionsNum;
    }

    public void moveDown() {
        selected = (selected + 1) % optionsNum;
    }

    public static void main(String[] args) throws IOException {
        ConsoleMenu menu = new ConsoleMenu();
        menu.clearScreen();

        menu.start();

        while (true) {
            menu.printMenu();

            int input = System.in.read();
            menu.cleanBuffer();

            if (input == 'w' || input == 'W') {
                menu.moveUp();
            } else if (input == 's' || input == 'S') {
                menu.moveDown();
            } else if (input == '\n' || input == '\r') {
                switch (menu.selected) {
                    case 0:
                        System.out.println(menu.FG_GREEN + "Adding a new Item..." + menu.RESET);
                        menu.pause();
                        break;
                    case 1:
                        System.out.println(menu.FG_GREEN + "Deleting an Item..." + menu.RESET);
                        menu.pause();
                        break;
                    case 2:
                        System.out.println(menu.FG_GREEN + "Updating an Item..." + menu.RESET);
                        menu.pause();
                        break;
                    case 3:
                        System.out.println(menu.FG_GREEN + "Viewing Items..." + menu.RESET);
                        menu.pause();
                        break;
                    case 4:
                        System.out.println(menu.FG_GREEN + "Searching Items..." + menu.RESET);
                        menu.pause();
                        break;
                    case 5:
                        System.out.println(menu.FG_GREEN + "Undoing last Deletion..." + menu.RESET);
                        menu.pause();
                        break;
                    case 6:
                        System.out.println(menu.FG_RED + "Exiting..." + menu.RESET);
                        menu.close();
                        break;
                }
            }
        }
    }
}

