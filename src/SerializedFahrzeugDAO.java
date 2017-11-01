import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Oleh Baidiuk
 * Matrikelnummer: 01468936
 */

public class SerializedFahrzeugDAO implements FahrzeugDAO {
    private String filePath;
    private Set<Fahrzeug> fahrzeugSet;

    public SerializedFahrzeugDAO(String filePath) {
        this.filePath = filePath;
        restoreData();
    }

    private void restoreData() {
        File file = new File(filePath);
        if (file.exists())
            try {
                FileInputStream fileInputStream = new FileInputStream(filePath);
                ObjectInputStream os = new ObjectInputStream(fileInputStream);
                fahrzeugSet = (HashSet<Fahrzeug>) os.readObject();
                os.close();
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        else {
            System.out.println("file<" + filePath + "> was not founded, will be create new one");
            fahrzeugSet = new HashSet<>();
        }
    }

    private void saveData() {
        try {
            File outFile = new File(filePath);
            if (outFile.getParentFile() != null)
                outFile.getParentFile().mkdirs();

            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream os = new ObjectOutputStream(fileOutputStream);
            os.writeObject(fahrzeugSet);
            os.close();
            fileOutputStream.close();
            System.out.println("Serialisierung erfolgreich");
        } catch (Exception e) {
            System.err.println("Fehler bei Serialisierung:");
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public List<Fahrzeug> getFahrzeugList() {
        return fahrzeugSet;
    }

    @Override
    public Fahrzeug getFahrzeugbyId(int id) {
        for (Fahrzeug fahrzeug : fahrzeugSet)
            if (fahrzeug.getId() == id)
                return fahrzeug;
        return null;
    }

    @Override
    public void speichereFahrzeug(Fahrzeug fahrzeug) throws Exception {
        if (fahrzeugSet.contains(fahrzeug))
            throw new Exception("Error: Fahrzeug bereits vorhanden. (id=<" + fahrzeug.getId() + ">)");
        fahrzeugSet.add(fahrzeug);
        saveData();
    }

    @Override
    public void loescheFahrzeug(int id) throws Exception {
        Fahrzeug fahrzeug = null;
        for (Fahrzeug f : fahrzeugSet)
            if (f.getId() == id)
                fahrzeug = f;
        if (fahrzeug == null)
            throw new Exception("Error: Fahrzeug nicht vorhanden. (id=<" + id + ">)");
        fahrzeugSet.remove(fahrzeug);
        saveData();
    }
}