import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Unter Objekt Serialisierung (engl. Object serialization) versteht man die Umwandlung von Objekten in Bytes, die man dann in einer Datei speichern kann. Dadurch wird es möglich
 * Objekte über die Laufzeit des Programms hinaus zu speichern. Wenn man das Objekt von der Datei wieder lädt, muss die passende Klasse im Programm vorhanden sein.
 * Es wird nur der Zustand und nicht die Klasse gespeichert.
 * <p>
 * Objekt Serialisierung wird z.B. häufig verwendet um Objekte über eine Netzwerkverbindung zwischen Java Programmen zu transportieren (Stichwort RMI (Remote Method Invocation).
 * Zum langfristigen Speichern von Zuständen eignet sie sich nur bedingt, da z.B. das Format nur von Java Programmen gelesen werden kann. Zum Speichern von Daten und Zuständen sind
 * Datenbanken meist die bessere Wahl!
 */

public class ExampleSerialization {


    public static void main(String[] args) {

        try {
            Company sonnentor = new Company("Sonnentor", "Lebensmittelhandel");

            DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            Date birthday = formatter.parse("01.04.1992");

            Person p = new Person("Susanne", "Sonnenschein", birthday);
            p.setCompany((sonnentor));
            System.out.println(p);

            //Person serialisieren ("speichern")
            serializePerson(p, "resources/person1");

            //Person deserialisieren ("laden")
            Person p2 = deserializePerson("resources/person1");
            System.out.println(p2);  //Geburtstag nicht geladen, da das Attribut als transient gekennzeichnet ist

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    /**
     * Serialisiert eine Person in ein File.
     *
     * @param p        die Person
     * @param destFile das File, in das die Person gespeichert wird
     * @throws IOException es konnte nicht in destFile geschrieben werden
     */
    public static void serializePerson(Person p, String destFile) throws IOException {

        try (
            OutputStream fos = Files.newOutputStream(Paths.get(destFile));
            ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(p);
        }
    }

    /**
     * Derserialisiert eine Person von file
     *
     * @param file die Datei, die geladen wird
     * @return die in file gespeicherte Person
     * @throws IOException            die Datei kann nicht geladen werden
     * @throws ClassNotFoundException die Klasse Person kann nicht gefunden werden
     */
    public static Person deserializePerson(String file) throws IOException, ClassNotFoundException {

        try (
            InputStream fis = Files.newInputStream(Paths.get(file));
            ObjectInputStream ois = new ObjectInputStream(fis)
        ) {

            Person p = (Person) ois.readObject();
            return p;
        }
    }
}
