package application.serialization;

import application.model.Classifier;
import database_support.data_model.Examined;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JAXBserializer {

    public static void marshal() throws JAXBException
    {
        List<JABXexamined> jabxExaminedList = new ArrayList<>();
        for (Examined examined : Classifier.getDbManager().getAllExamined()
             ) {        JABXexamined jabXexamined = new JABXexamined();
                      jabXexamined.setGenotype(examined.getGenotype());
                      jabXexamined.setClassId(examined.getClassId());
                      jabxExaminedList.add(jabXexamined);
        }

        JABXexaminedBacterias jabXexaminedBacterias = new JABXexaminedBacterias();
        jabXexaminedBacterias.setExaminedList(jabxExaminedList);

        JAXBContext jaxbContext = JAXBContext.newInstance(JABXexaminedBacterias.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        //Marshal the examined list in console
        jaxbMarshaller.marshal(jabXexaminedBacterias, System.out);

        //Marshal the examined list in file
        jaxbMarshaller.marshal(jabXexaminedBacterias, new File("./examined.xml"));
    }
}
