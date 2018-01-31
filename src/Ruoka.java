/**
 *
 * @author Roope
 * @version 1.00 2017/4/25
 */
public class Ruoka {

	private String nimi;
	private double kalori;
	private double proteiini;
	private double hiilihydraatti;
	private double rasva;

	public Ruoka() {
		this.nimi = "";
		this.kalori = 0;
		this.proteiini = 0;
		this.hiilihydraatti = 0;
		this.rasva = 0;
	}

	public Ruoka(String nimi, double kalori, double proteiini, double hiilihydraatti, double rasva) {
		this.nimi = nimi;
		this.kalori = kalori;
		this.proteiini = proteiini;
		this.hiilihydraatti = hiilihydraatti;
		this.rasva = rasva;
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

}
