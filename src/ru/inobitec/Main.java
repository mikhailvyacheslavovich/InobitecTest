package ru.inobitec;

import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String fileName = args[0] + ".xml";
        String sortType = "";

        if (args.length < 2) {
            sortType = "";
        } else {
            sortType = args[1];
        }

        Path path = Paths.get(fileName);

        if (!Files.exists(path)) {
            System.out.println("Заданный файл не обнаружен");
        } else {
            try {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser parser = factory.newSAXParser();
                PatientHandler patientsHandler = new PatientHandler();

                parser.parse(fileName, patientsHandler);
                Patients result = patientsHandler.getAllPatients();

                System.out.println(result.toStringSorted(sortType));

            } catch (ParserConfigurationException | SAXException | IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}
