package core.io.readers.structure;

import core.io.managers.IOManager;

abstract public class Input<T> {
    protected IOManager io;
    protected boolean repeat;
    protected String invMessage;
    protected boolean nullable;

    public Input(IOManager io, boolean repeat, boolean nullable, String invMessage) {
        this.io = io;
        this.repeat = repeat;
        this.invMessage = invMessage;
        this.nullable = nullable;
    }

    abstract public T read(Validator<T> validator) throws WrongInputException;
    abstract public T read() throws WrongInputException;
}
