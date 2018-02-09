/**
 *
 * @author Roope
 * @version 1.00 2018/1/25
 */
public class Ruokalista extends Ruoka {

	private double maara;
	private double energia;

	public Ruokalista() {

		this.maara = 0;
		this.energia = 0;
	}

	public Ruokalista(String nimi, double maara, double energia, double kalori, double proteiini, double hiilihydraatti, double rasva) {
		super (nimi, kalori, proteiini, hiilihydraatti, rasva);
		this.maara = maara;
		this.energia = energia;
	}

	public double getMaara() {
		return maara;
	}

	public void setMaara(double maara) {
		this.maara = maara;
	}

	public double getEnergia() {
		return energia;
	}

	public void setEnergia(double energia) {
		this.energia = energia;
	}

}
