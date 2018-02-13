
/**
 * Kulutus-luokka laskee henkilön päivittäisen kalorikulutuksen annettujen tietojen perusteella
 *
 * @author Roope
 * @version 1.00 2017/4/25
 */
public class Kulutus {

	  /** Metodi palauttaa henkilön kulutuksen.
	   *
	   * @param sukupuoli	henkilön sukupuoli
	   * @param ika			henkilön ikä
	   * @param pituus		henkilön pituus
	   * @param paino		henkilön aamupaino
	   * @param aktiivisuus
	   * @param aktiivisuus	henkilön päivän aktiivisuus
	 * @param kevyt
	 * @param raskas
	 * @param kuormittava
	 * @param kohtalainen
	   * @param k
	   * @param j
	   * @param i
	   * @return 			henkilön päivän kulutus
	   * The Harris-Benedict equation revised by Mifflin and St Jeor
	   * Men = ((10 × weight in kg) + (6.25 × height in cm) - (5 × age in years) + 5) × activity level
	   * Women = ((10 × weight in kg) + (6.25 × height in cm) - (5 × age in years) - 161) × activity level
	   */
	  public static int kulutus(String skpuoli, double ika, double pituus, double paino, String aktiivisuus, int kevyt, int kohtalainen, int kuormittava, int raskas) {
	    int kulutus = 0;
	    double bmi=0;

		if (skpuoli.equals("mies")) {
			bmi = ((10 * paino) + (6.25 * pituus) - (5 * ika) + 5);

			if (aktiivisuus.equals("Todella kevyt (oleilua)")) {
				bmi = bmi * 1.2;
			}
			else if(aktiivisuus.equals("Kevyt (istumatyöskentelyä)")) {
				bmi = bmi * 1.375;
			}
			else if(aktiivisuus.equals("Kohtalainen (seisomista ja liikkumista jonkin verran)")) {
				bmi = bmi * 1.55;
			}
			else if(aktiivisuus.equals("Raskas (fyysisesti raskas työ)")){
				bmi = bmi * 1.725;
			}
		}

		else if (skpuoli.equals("nainen")) { //nainen
	    	bmi = (10 * paino) + (6.25 * pituus) - (5 * ika) - 161;

	    	if (aktiivisuus.equals("Todella kevyt (oleilua)")) {
	    		bmi = bmi * 1.2;
			}
			else if(aktiivisuus.equals("Kevyt (istumatyöskentelyä)")) {
				bmi = bmi * 1.375;
			}
			else if(aktiivisuus.equals("Kohtalainen (seisomista ja liikkumista jonkin verran)")) {
				bmi = bmi * 1.55;
			}
			else if(aktiivisuus.equals("Raskas (fyysisesti raskas työ)")){
				bmi = bmi * 1.725;
			}
	    }
		// kulutukseen lisätään päivän liikunta
		kulutus = (int)(bmi + 0.5d);

		// kevyt kuormittaa 170 kcal/h
		int kevytta = (int)(kevyt * 2.8);
		kulutus = kulutus + kevytta;

		// kohtalainen kuormittaa 250 kcal/h
		int kohtalaista = (int)(kohtalainen * 4.2);
		kulutus = kulutus + kohtalaista;

		//kuormittava kuormittaa 440 kcal/h
		int kuormittavaa = (int)(kuormittava * 7.3);
		kulutus = kulutus + kuormittavaa;

		// raskas kuormittaa 1000 kcal/h
		int raskasta = (int)(raskas * 16.7);
		kulutus = kulutus + raskasta;

	    return kulutus;
	  }
}
