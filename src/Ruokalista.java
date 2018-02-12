import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Roope
 * @version 1.00 2018/1/25
 */
public class Ruokalista extends Ruoka{

	private String nimi;
	private String maara;
	private Double energia;
	private Double kalori;
	private Double proteiini;
	private Double hiilihydraatti;
	private Double rasva;


	Ruokalista(String nimi, String maara, Double energia, Double kalori, Double proteiini, Double hiilihydraatti, Double rasva) {
		this.nimi = new String(nimi);
		this.kalori = new Double(kalori);
		this.proteiini = new Double(proteiini);
		this.hiilihydraatti = new Double(hiilihydraatti);
		this.rasva = new Double(rasva);
		this.maara = new String(maara);
		this.energia = new Double(energia);
	}


	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public double getKalori() {
		return kalori;
	}

	public void setKalori(double kalori) {
		this.kalori = kalori;
	}

	public double getProteiini() {
		return proteiini;
	}

	public void setProteiini(double proteiini) {
		this.proteiini = proteiini;
	}

	public double getHiilihydraatti() {
		return hiilihydraatti;
	}

	public void setHiilihydraatti(double hiilihydraatti) {
		this.hiilihydraatti = hiilihydraatti;
	}

	public double getRasva() {
		return rasva;
	}

	public void setRasva(double rasva) {
		this.rasva = rasva;
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
