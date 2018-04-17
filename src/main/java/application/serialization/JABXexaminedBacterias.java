package application.serialization;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(namespace = "appliaction.serialization")
public class JABXexaminedBacterias {

    private List<JABXexamined> examinedList;

    @XmlElementWrapper(name = "examinedBacterias")
    @XmlElement(name = "examined")
    public List<JABXexamined> getExaminedList() {
        return examinedList;
    }

    public void setExaminedList(List<JABXexamined> examinedList) {
        this.examinedList = examinedList;
    }
}
