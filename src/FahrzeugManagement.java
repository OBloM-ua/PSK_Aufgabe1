import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Oleh Baidiuk
 * Matrikelnummer: 01468936
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

    public void print(int i) {
        System.out.println(dao.getFahrzeugbyId(i));
    }


    public void add(Fahrzeug f) {
        try {
            dao.speichereFahrzeug(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int i) {
        try {
            dao.loescheFahrzeug(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int size() {
        return dao.getFahrzeugList().size();
    }


    public int sizeOfPkw() {
        int counter = 0;
        for (Fahrzeug f : dao.getFahrzeugList())
            if (f instanceof Pkw) counter++;
        return counter;
    }

    public int sizeOfLkw() {
        int counter = 0;
        for (Fahrzeug f : dao.getFahrzeugList())
            if (f instanceof Lkw) counter++;
        return counter;
    }

    public double priceAvg() {
        double meanprice;
        double counter = 0;
        for (Fahrzeug f : dao.getFahrzeugList())
            counter += f.getPreis();
        meanprice = counter / dao.getFahrzeugList().size();
        return BigDecimal.valueOf(meanprice).setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue();
    }

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
}
