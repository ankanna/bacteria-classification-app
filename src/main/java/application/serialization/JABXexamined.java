package application.serialization;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "examined")
@XmlAccessorType(XmlAccessType.FIELD)
public class JABXexamined {

        String genotype;
        String classId;

        public JABXexamined() {
        }

        public String getGenotype() {
            return genotype;
        }

        public void setGenotype(String genotype) {
            this.genotype = genotype;
        }

        public String getClassId() {
            return classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }
}
