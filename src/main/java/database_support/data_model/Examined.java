package database_support.data_model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "examined")
@XmlAccessorType(XmlAccessType.FIELD)
public class Examined {

    int genotype;
    String classId;

    public Examined(int genotype, String classId) {
        this.genotype = genotype;
        this.classId = classId;
    }

    public int getGenotype() {
        return genotype;
    }

    public void setGenotype(int genotype) {
        this.genotype = genotype;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
}
