package core.file;

import core.HumanBeing;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.util.ArrayList;

/**
 * @author Volovich Alexey
 * Этот класс считывает объекты класса HumanBeing из XML файла.
 * при помощи внутреннего класса InnerXMLReader достигается получение доступа к приватным полям.
 * @see HumanBeing
 */
public class FromXMLToObject implements FileIn{

    /**
     * В переменной path хранится путь к считываемому файлу
     */
    private final String path;

    public FromXMLToObject(String path) {
        this.path = path;
    }

    /**
     * Основная команда класса, считывает объекты из файла
     * Выбрасывает FileNotFoundException в случае отсутствия файла по пути path
     * Выбрасывает XMLStreamException в случае несоответствия формата файла XML формату или повреждению данных
     * @return Humanbase[]
     * @throws FileNotFoundException
     * @throws XMLStreamException
     */
    @Override
    public HumanBeing[] readFile() throws FileNotFoundException, XMLStreamException {
        XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(path, new BufferedReader(new FileReader(path)));
        ArrayList<HumanBeing> res = new ArrayList<>();
        while (reader.hasNext())
        {
            reader.next();
            if(reader.isStartElement() && reader.hasName() && reader.getLocalName().equals("HumanBeing")) {
                res.add(new HumanBeing(reader));
            }
        }
        reader.close();
        return res.toArray(new HumanBeing[0]);
    }
}
