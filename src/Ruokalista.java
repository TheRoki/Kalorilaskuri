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
	private double energia;

	Ruokalista(String nimi, String maara, double energia, double kalori, double proteiini, double hiilihydraatti, double rasva) {
		super (nimi, kalori, proteiini, hiilihydraatti, rasva);
		
		this.maara = maara;
		this.energia = energia;
		
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
