import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * @author <your-name-here>
 * Matrikelnummer:
 */

public class FahrzeugManagement {
    private FahrzeugDAO dao;
    public FahrzeugManagement(String filePath) {
        dao = new SerializedFahrzeugDAO(filePath);
    }

    public void printAll() {
        List<Fahrzeug> fahrzeugSet = dao.getFahrzeugList();
        for (Fahrzeug f : fahrzeugSet) System.out.println(f);

    }

    /**
     * Alle Daten eines Fahrzeugs bereitstellen
     */
    public void print(int i) {
        System.out.println(dao.getFahrzeugbyId(i));
    }

    /**
     * Neues Fahrzeug hinzufügen
     */
    public void add(Fahrzeug f) {
        try {
            dao.speichereFahrzeug(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Bestehendes Fahrzeug löschen
     */
    public void delete(int i) {
        try {
            dao.loescheFahrzeug(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gesamtzahl aller Fahrzeuge ermitteln
     */
    public int size() {
        return dao.getFahrzeugList().size();
    }

    /**
     * Gesamtzahl aller PKWs ermitteln
     */
    public int sizeOfPkw() {
        int counter = 0;
        for (Fahrzeug f : dao.getFahrzeugList())
            if (f instanceof Pkw) counter++;
        return counter;
    }

    /**
     * Gesamtzahl aller LKWs ermitteln
     */
    public int sizeOfLkw() {
        int counter = 0;
        for (Fahrzeug f : dao.getFahrzeugList())
            if (f instanceof Lkw) counter++;
        return counter;
    }

    /**
     * Durchschnittspreis aller Fahrzeuge ermitteln
     */
    public double priceAvg() {
        double meanprice;
        double counter = 0;
        for (Fahrzeug f : dao.getFahrzeugList())
            counter += f.getGrundpreis();
        meanprice = counter / dao.getFahrzeugList().size();
        return meanprice;
    }

    /**
     * Id(s) des(r) ältesten Fahrzeugs(e) ermitteln
     */
    public List<Integer> getOldestFahrzeugId() {
        if (dao.getFahrzeugList().isEmpty()) {
            System.err.println("You have no Fahrzeug!");
            System.exit(1);
        }
        List<Integer> retList = new ArrayList<>();
        int ageOfOldest = dao.getFahrzeugList().stream().sorted(Comparator.comparingInt(Fahrzeug::getAlter).reversed()).findFirst().get().getAlter();

        for (Fahrzeug f : dao.getFahrzeugList()){
            if (f.getAlter() == ageOfOldest)
                retList.add(f.getId());
        }
        return retList;
    }


    @Deprecated
    public List<Fahrzeug> getAll() {// only for JunitTest
        return dao.getFahrzeugList();
    }

}
