import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Roope
 * @version 1.00 2018/1/25
 */
public class Ruokalista extends Ruoka{

	private String maara;
	private Double energia;

	Ruokalista(String nimi, String maara, Double energia, Double kalori, Double proteiini, Double hiilihydraatti, Double rasva) {
		super (nimi, kalori, proteiini, hiilihydraatti, rasva);
		this.maara = new String(maara);
		this.energia = new Double(energia);
	}

	public String getMaara() {
		return maara;
	}

	public void setMaara(String maara) {
		this.maara = maara;
	}

	public double getEnergia() {
		return energia;
	}

	public void setEnergia(double energia) {
		this.energia = energia;
	}

	public String toCSV() {
		return getNimi() + "," + getMaara() + "," + getEnergia() + "," + getKalori() + "," + getProteiini() + "," + getHiilihydraatti() + "," + getRasva();
	}

}
