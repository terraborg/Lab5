package core.io.managers;

import java.util.Scanner;

public class FileInManager extends IOManager{
    public FileInManager(Scanner in) {
        super(in);
    }

    @Override
    public void print(String message) {
        System.out.print(message);
    }

    @Override
    public void printInv(String message) {

    }
}
