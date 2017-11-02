import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Oleh Baidiuk
 * Matrikelnummer: 01468936
 */

public class SerializedFahrzeugDAO implements FahrzeugDAO {
    private String filePath;
    private ArrayList<Fahrzeug> fahrzeugList;

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
                fahrzeugList = (ArrayList<Fahrzeug>) os.readObject();
                os.close();
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        else {
            fahrzeugList = new ArrayList<>();
        }
    }

    private void saveData() {
        try {


            File outFile = new File(filePath);
            if (outFile.getParentFile() != null) outFile.getParentFile().mkdirs();

            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream os = new ObjectOutputStream(fileOutputStream);
            os.writeObject(fahrzeugList);
            os.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public List<Fahrzeug> getFahrzeugList() {
        return fahrzeugList;
    }

    @Override
    public Fahrzeug getFahrzeugbyId(int id) {
        for (Fahrzeug fahrzeug : fahrzeugList)
            if (fahrzeug.getId() == id)
                return fahrzeug;
        return null;
    }

    @Override
    public void speichereFahrzeug(Fahrzeug fahrzeug) {

        for (Fahrzeug f : fahrzeugList) {
            if (f.getId() == fahrzeug.getId()){
                throw new IllegalArgumentException("Error: Fahrzeug schon existiert. (id=<" + fahrzeug.getId() + ">)");
            }
        }

        fahrzeugList.add(fahrzeug);
        saveData();
    }

    @Override
    public void loescheFahrzeug(int id) {
        Fahrzeug fahrzeug = null;

        for (Fahrzeug f : fahrzeugList) {
            if (f.getId() == id)
                fahrzeug = f;
        }
        if (fahrzeug == null){
            throw new IllegalArgumentException("Error: Fahrzeug nicht vorhanden. (id=<" + id + ">)");
        }

        fahrzeugList.remove(fahrzeug);
        saveData();
    }
}