import java.io.*;
import java.util.Date;

//jede Klasse, welches das Interface "Serializable" implementiert, kann serialisiert werden. Dabei handelt es sich um ein sogenanntes "Marker-Interface".
//Das Interface beinhaltet keine Methoden, die implementiert werden müssen, sondern "markiert" lediglich die Klasse.

public class Person implements Serializable {

    //definiert die "Version" der Klasse. Nur wenn die Klasse mit der gleichen Version serialisiert und deserialisiert wird, funktioinert es.
    static final long serialVersionUID = 1;

    //statische Attribute werden ebenfalls nicht serialisiert, da sie eine Eigenschaft der Klasse und nicht des Objekts sind.
    private static final String FEMALE_TITLE = "Mrs";
    private final String firstName;
    private final String lastName;

    //mit dem Schlüsselwort "transient" werden alle Attribute gekennzeichnet, die nicht serialisiert werden sollen.
    private transient final Date birthday;

    //Die Klassen der Attribute müssen ebenfalls serialisierbar sein, d.h. Klassen sein, die das Interface "Serializable" implementieren. Für die meisten Klassen
    //der Java API ist dies der Fall. Bei eigenen Klassen muss man dies natürlich immer selbst angeben.
    private Company company;

    public Person(String firstName, String lastName, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Person{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", birthday=" + birthday +
            ", company=" + company +
            '}';
    }
}
