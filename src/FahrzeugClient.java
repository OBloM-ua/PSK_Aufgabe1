/**
 * @author Oleh Baidiuk
 * Matrikelnummer: 01468936
 */

public class FahrzeugClient {
	public static void main(String[] args) {
		FahrzeugManagement fm = new FahrzeugManagement(args[0]);

		try {
			switch (args[1]) {
				case "show":
					if (args.length > 3) throw new IllegalArgumentException("Arguments ist Falsh!");
					else if (args.length == 2) {
						fm.printAll();
					} else if (args.length == 3)
						fm.print(Integer.parseInt(args[2]));
					break;

				case "add":
					if (args.length != 9 && args[2].equals("pkw"))
						throw new Exception("Error: Parameter ungueltig.");

					if (args.length != 8 && args[2].equals("lkw"))
						throw new Exception("Error: Parameter ungueltig.");

					if (args[2].equals("pkw")) {
						Pkw pkw = new Pkw(Integer.parseInt(args[3]), args[4], args[5], Integer.parseInt(args[6]), Double.parseDouble(args[7]), Integer.parseInt(args[8]));
						fm.add(pkw);
					} else if (args[2].equals("lkw")) {
						Lkw lkw = new Lkw(Integer.parseInt(args[3]), args[4], args[5], Integer.parseInt(args[6]), Double.parseDouble(args[7]));
						fm.add(lkw);
					}
					break;
				case "del":
					if (args.length != 3) throw new IllegalArgumentException("Arguments ist Falsh!");
					fm.delete(Integer.parseInt(args[2]));
					break;
				case "count":
					if (args.length == 2) System.out.println(fm.size());
					else if (args[2].equals("pkw")) System.out.println(fm.sizeOfPkw());
					else if (args[2].equals("lkw")) System.out.println(fm.sizeOfLkw());
					break;

				case "meanprice"://Durchschnittspreis aller Fahrzeuge berechnen
					if (args.length != 2)
						throw new IllegalArgumentException("Arguments ist Falsh!");
					System.out.println(fm.priceAvg());
					break;

				case "oldest": //Ältest(e) Fahrzeug(e) suchen
					if (args.length != 2)
						throw new IllegalArgumentException("Arguments ist Falsh!");
					System.out.println(fm.getOldestFahrzeugId());
					break;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}