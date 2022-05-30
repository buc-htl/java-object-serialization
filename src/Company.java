import java.io.*;

public class Company implements Serializable {
    private String name;
    private String industry;

    public Company(String name, String industry) {
        this.name = name;
        this.industry = industry;
    }

    public String getName() {
        return name;
    }

    public String getIndustry() {
        return industry;
    }

    @Override
    public String toString() {
        return "Company{" +
            "name='" + name + '\'' +
            ", industry='" + industry + '\'' +
            '}';
    }
}
