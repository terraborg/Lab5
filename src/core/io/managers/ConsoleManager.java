package core.io.managers;

import java.util.Scanner;

public class ConsoleManager extends IOManager{
    public ConsoleManager(Scanner in) {
        super(in);
    }

    @Override
    public void print(String message) {
        System.out.print(message);
    }

    @Override
    public void printInv(String message) {
        print(message);
    }
}
