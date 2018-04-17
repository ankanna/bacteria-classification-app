package database_support.data_model;

public class Examined {

    String genotype;
    String classId;

    public Examined(String genotype, String classId) {
        this.genotype = genotype;
        this.classId = classId;
    }

    private Examined(){
        this.genotype = null;
        this.classId = null;
    }

    private static Examined newInstance(){
    return new Examined();
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
