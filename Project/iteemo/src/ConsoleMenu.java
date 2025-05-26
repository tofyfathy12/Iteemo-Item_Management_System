

import java.io.IOException;
import java.util.Scanner;

/**
 * Handles the console-based user interface for the item management system.
 * It displays an interactive menu, processes user input for navigation and option selection,
 * and uses ANSI escape codes for styling the output.
 */
public class ConsoleMenu {
    /**
     * Array of menu options to be displayed to the user.
     */
    public String[] options = {"Add Item", "Delete Item", "Update Item", "View Items", "Search Items", "Undo Last Deletion", "Exit"};
    /**
     * The total number of menu options.
     */
    public int optionsNum = options.length;
    /**
     * The currently selected menu option index.
     */
    public int selected = 0;
    // ANSI escape codes for colors and styling
    /**
     * ANSI escape code to reset all text formatting to default.
     */
    public final String RESET       = "\u001B[0m";
    /**
     * ANSI escape code for bright/bold text.
     */
    public final String BRIGHT      = "\u001B[1m";
    /**
     * ANSI escape code for underlined text.
     */
    public final String UNDERSCORE  = "\u001B[4m";

    /**
     * ANSI escape code for cyan foreground color.
     */
    public final String FG_CYAN     = "\u001B[36m";
    /**
     * ANSI escape code for yellow foreground color.
     */
    public final String FG_YELLOW   = "\u001B[33m";
    /**
     * ANSI escape code for green foreground color.
     */
    public final String FG_GREEN    = "\u001B[32m";
    /**
     * ANSI escape code for red foreground color.
     */
    public final String FG_RED      = "\u001B[31m";

    // Box-drawing characters (UTF-8)
    /**
     * UTF-8 box-drawing character for a horizontal line.
     */
    public final String HORIZONTAL  = "\u2500";
    /**
     * UTF-8 box-drawing character for a vertical line.
     */
    public final String VERTICAL    = "\u2502";
    /**
     * UTF-8 box-drawing character for a top-left corner.
     */
    public final String TOP_LEFT    = "\u250C";
    /**
     * UTF-8 box-drawing character for a top-right corner.
     */
    public final String TOP_RIGHT   = "\u2510";
    /**
     * UTF-8 box-drawing character for a bottom-left corner.
     */
    public final String BOTTOM_LEFT = "\u2514";
    /**
     * UTF-8 box-drawing character for a bottom-right corner.
     */
    public final String BOTTOM_RIGHT= "\u2518";

    /**
     * Clears the console screen using ANSI escape codes.
     * It moves the cursor to the home position (top-left) and clears the screen.
     */
    public void clearScreen() {
        // ANSI clear screen
        System.out.print("\033[2J\033[H");
        System.out.flush();
    }
    Scanner scanner = new Scanner(System.in);
    /**
     * Pauses the execution and waits for the user to press ENTER to continue.
     * After the user presses ENTER, the screen is cleared.
     * @throws IOException if an I/O error occurs.
     */
    public void pause() throws IOException {
        System.out.print("\nPress any key and ENTER to continue...");
        while (!scanner.nextLine().isEmpty()) {
            // Wait for user input
        }
        clearScreen();
    }

    /**
     * Prints the main menu to the console.
     * It displays a title and lists all available options, highlighting the currently selected one.
     */
    public void printMenu() {
        clearScreen();
        String consoleTitle = "ITEEMO - Item Management System";
        System.out.print(BRIGHT + FG_CYAN);
        System.out.printf("%s%s%s\n", TOP_LEFT, HORIZONTAL.repeat(35), TOP_RIGHT);
        System.out.printf("%s  %s  %s\n", VERTICAL, consoleTitle, VERTICAL);
        System.out.printf("%s%s%s\n" + RESET, BOTTOM_LEFT, HORIZONTAL.repeat(35), BOTTOM_RIGHT);

        for (int i = 0; i < optionsNum; i++) {
            if (i == selected) {
                System.out.printf("  %s> %s%s%s\n" + RESET,
                        FG_YELLOW, UNDERSCORE, options[i], RESET);
            } else {
                System.out.printf("    %s\n", options[i]);
            }
        }
    }

    /**
     * Clears any leftover characters from the input buffer (System.in).
     * This is typically used after reading specific input to prevent interference with subsequent reads.
     * @throws IOException if an I/O error occurs while reading from the input stream.
     */
    public void cleanBuffer() throws IOException {
        // consume newline or other leftover
        while (System.in.available() > 0) {
            System.in.read();
        }
    }

    /**
     * Initializes the console menu display by hiding the cursor.
     */
    public void start() {
        // Hide cursor
        System.out.print("\u001B[?25l");
        System.out.flush();
    }

    /**
     * Performs cleanup operations before exiting the application.
     * This includes showing the cursor, closing the scanner, and terminating the program.
     */
    public void close() {
        // Show cursor
        System.out.print("\u001B[?25h");
        System.out.flush();
        scanner.close();
        System.exit(0);
    }

    /**
     * Moves the selection in the menu up by one item.
     * If the selection is at the first item, it wraps around to the last item.
     */
    public void moveUp() {
        selected = (selected - 1 + optionsNum) % optionsNum;
    }

    /**
     * Moves the selection in the menu down by one item.
     * If the selection is at the last item, it wraps around to the first item.
     */
    public void moveDown() {
        selected = (selected + 1) % optionsNum;
    }
}