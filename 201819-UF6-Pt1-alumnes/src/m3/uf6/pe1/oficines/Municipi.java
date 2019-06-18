package m3.uf6.pe1.oficines;

import java.util.LinkedList;

public class Municipi {
	private String nom;
	private String comarca;
	private String provincia;
	private int cp;
	private LinkedList<Oficina> oficines;

	public Municipi(String nom, String comarca, String provincia, int cp) {
		super();
		this.nom = nom;
		this.comarca = comarca;
		this.provincia = provincia;
		this.cp = cp;
		this.oficines = new LinkedList<Oficina>();
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getComarca() {
		return comarca;
	}
	public void setComarca(String comarca) {
		this.comarca = comarca;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public int getCp() {
		return cp;
	}
	public void setCp(int cp) {
		this.cp = cp;
	}
	public void addOficina(Oficina oficina) {
		this.oficines.add(oficina);
	}
	public void removeOficina(Oficina oficina) {
		this.oficines.remove(oficina);
	}


}
