package model;

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
