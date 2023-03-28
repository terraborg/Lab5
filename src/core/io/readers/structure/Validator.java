package core.io.readers.structure;

import java.util.function.Predicate;

public interface Validator<T> extends Predicate<T> {
    String getWrongMessage();
}
