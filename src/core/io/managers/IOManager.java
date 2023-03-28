package core.io.managers;

import java.util.Scanner;

public abstract class IOManager {
    private final Scanner in;

    public IOManager(Scanner in) {
        this.in = in;
    }

    abstract public void print(String message);
    abstract public void printInv(String message);

    public Scanner getIn() {
        return in;
    }
}
