package core.commands.structure;

/**
 * Класс в котором хранится обратная связь передаваемоя от команды клиенту
 */

public class CallbackUnit {
    private final boolean hasMessage;
    private final boolean isSuccess;
    private String message;

    public CallbackUnit(boolean isSuccess) {
        this.isSuccess = isSuccess;
        hasMessage = false;
    }

    public CallbackUnit(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
        hasMessage = true;
    }

    public boolean isSuccess()
    {
        return isSuccess;
    }
    public boolean hasMessage()
    {
        return hasMessage;
    }

    public String getMessage() {
        return message;
    }
}
