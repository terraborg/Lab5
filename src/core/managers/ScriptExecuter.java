package core.managers;

import core.io.managers.FileInManager;
import core.io.managers.IOManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class ScriptExecuter{
    private final Set<String> history;
    private final ClientContext client;

    public ScriptExecuter(ClientContext client)
    {
        this.client = client;
        history = new HashSet<>();
    }

    public void execute(String path)
    {
        try(var in = new Scanner(new BufferedReader(new FileReader(path))))
        {
            File file = new File(path);
            IOManager io = new FileInManager(in);
            if(history.contains(file.getAbsolutePath())) {
                io.print("Обнаружено зацикливание скриптов, команда execute_script "+path+" будет пропущена\n");
                return;
            }
            history.add(file.getAbsolutePath());
            while(io.getIn().hasNext()) {
                client.execute(io.getIn().next(),io);
            }
            history.remove(file.getAbsolutePath());
        }catch (FileNotFoundException e)
        {
            System.out.println("Файл не найден");
        }
    }
}
