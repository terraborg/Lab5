package core;

import core.database.TreeSetHolder;
import core.file.FromObjectToXML;
import core.file.FromXMLToObject;
import core.io.managers.ConsoleManager;
import core.managers.ClientManager;
import core.managers.ServerManager;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var c = new ClientManager(new ServerManager(new TreeSetHolder(new FromXMLToObject(System.getenv("LAB5")),
                                                                      new FromObjectToXML(System.getenv("LAB5")))));
        var in = new ConsoleManager(new Scanner(System.in));
        //c.execute("help",null);
        c.execute("read",null);
        while(true)
        {
            try {
                c.execute(in.getIn().next(), in);
            }catch (NoSuchElementException e)
            {
                c.execute("exit",in);
            }
        }
    }
}