/**
 * @author Oleh Baidiuk
 * Matrikelnummer: 01468936
 */

public class Lkw extends Fahrzeug {

	private static final long serialVersionUID = 1L;

	public Lkw(int id, String marke, String modell, int baujahr, double grundpreis) throws Exception {
		super(id, marke, modell, baujahr, grundpreis);
	}

	@Override
	public double getRabatt() {
		double rabbatProzent = getAlter() * 0.05;
		double rabbat = getGrundpreis() * rabbatProzent;
		double maxRabbat = getGrundpreis() * 0.2;
		return rabbat > maxRabbat ? maxRabbat : rabbat;

	}

	@Override
	public String toString() {
		return "Typ:         LKW\n" +
				"Id:          " + getId() + "\n"+
				"Marke:       " + getMarke() + '\n' +
				"Modell:      " + getModell() + '\n' +
				"Baujahr:     " + getBaujahr() +"\n"+
				"Grundpreis:  " + df.format(getGrundpreis()) + "\n"+
				"Preis:       " + getPreis() + "\n";
	}
}
