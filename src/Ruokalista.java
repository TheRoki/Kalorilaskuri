import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Roope
 * @version 1.00 2018/1/25
 */
public class Ruokalista {

	private SimpleStringProperty nimi = new SimpleStringProperty(this, "nimi", "");
	private SimpleDoubleProperty maara = new SimpleDoubleProperty(this, "maara", 0.0);
	private SimpleDoubleProperty energia = new SimpleDoubleProperty(this, "energia", 0.0);
	private SimpleDoubleProperty kalori = new SimpleDoubleProperty(this, "kalori", 0.0);
	private SimpleDoubleProperty proteiini = new SimpleDoubleProperty(this, "proteiini", 0.0);
	private SimpleDoubleProperty hiilihydraatti = new SimpleDoubleProperty(this, "hiilihydraatti", 0.0);
	private SimpleDoubleProperty rasva = new SimpleDoubleProperty(this, "rasva", 0.0);

	/*
	Ruokalista(String nimi, double kalori, Double proteiini, double hiilihydraatti, double rasva, double maara, double energia) {
		this.nimi = new SimpleStringProperty(nimi);
		this.kalori = new SimpleDoubleProperty(kalori);
		this.proteiini = new SimpleDoubleProperty(proteiini);
		this.hiilihydraatti = new SimpleDoubleProperty(hiilihydraatti);
		this.rasva = new SimpleDoubleProperty(rasva);
		this.maara = new SimpleDoubleProperty(maara);
		this.energia = new SimpleDoubleProperty(energia);
	}
	*/

	public String getNimi() {
		return nimi.get();
	}

	public void setNimi(String enimi) {
		this.nimi.set(enimi);
	}

	public Double getKalori() {
		return kalori.get();
	}

	public void setKalori(Double kalori) {
		this.kalori.set(kalori);
	}

	public double getProteiini() {
		return proteiini.get();
	}

	public void setProteiini(Double proteiini) {
		this.proteiini.set(proteiini);
	}

	public double getHiilihydraatti() {
		return hiilihydraatti.get();
	}

	public void setHiilihydraatti(Double hiilihydraatti) {
		this.hiilihydraatti.set(hiilihydraatti);
	}

	public double getRasva() {
		return rasva.get();
	}

	public void setRasva(Double rasva) {
		this.rasva.set(rasva);
	}


	public double getMaara() {
		return maara.get();
	}

	public void setMaara(Double maara) {
		this.maara.set(maara);
	}

	public double getEnergia() {
		return energia.get();
	}

	public void setEnergia(Double energia) {
		this.energia.set(energia);;
	}

}
