package core.io.readers.structure;

public class WrongInputException extends Exception{
    public WrongInputException()
    {
        super("Неверный ввод");
    }

    public WrongInputException(String message) {
        super(message);
    }
}
