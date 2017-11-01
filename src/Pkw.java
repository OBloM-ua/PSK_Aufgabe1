/**
 * @author <your-name-here>
 * Matrikelnummer:
 */

public class Pkw extends Fahrzeug {

	private static final long serialVersionUID = 1L;
	private int servicejahr;
	public Pkw(int id, String marke, String modell, int baujahr, double grundpreis, int servicejahr) throws Exception {
		super(id, marke, modell, baujahr, grundpreis);
		setServicejahr(servicejahr);
	}

	public int getServicejahr() {
		return servicejahr;
	}

	public void setServicejahr(int servicejahr) throws Exception {
		if (servicejahr < getBaujahr() || servicejahr > currentYear)
			throw new Exception("Error: ServiceJahr ungueltig.");
		this.servicejahr = servicejahr;
	}

	@Override
	public double getRabatt() {
		double rabbatProzent = getAlter() * 0.05 + (currentYear - servicejahr) * 0.02;
		double rabatt = getGrundpreis() * rabbatProzent;
		double maxRabatt = getGrundpreis() * 0.15;
		return rabatt > maxRabatt ? maxRabatt : rabatt;
	}

	@Override
	public String toString() {
		return "Typ: PKW \n" +
				"Id: " + getId() + "\n"+
				"Marke: " + getMarke() + '\n' +
				"Modell: " + getModell() + '\n' +
				"Baujahr: " + getBaujahr() +"\n"+
				"Grundpreis: " + df.format(getGrundpreis()) + "\n"+
				"Servicejahr: " + getServicejahr() + "\n"+
				"Price: " + getPreis() + "\n";
	}
}
