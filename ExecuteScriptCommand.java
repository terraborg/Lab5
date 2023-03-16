package core.commands;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteScriptCommand{
    public void execute() {
        String inputFileName = "file.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
