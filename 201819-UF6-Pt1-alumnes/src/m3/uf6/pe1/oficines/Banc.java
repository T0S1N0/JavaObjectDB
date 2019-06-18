package m3.uf6.pe1.oficines;

import java.util.LinkedList;

public class Banc {
	private int codi;
	private String nom;
	private LinkedList<Oficina> sucursals;

	public Banc(int codi, String nom) {
		super();
		this.codi = codi;
		this.nom = nom;
		this.sucursals = new LinkedList<Oficina>();
	}
	public int getCodi() {
		return codi;
	}
	public void setCodi(int codi) {
		this.codi = codi;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public LinkedList<Oficina> getSucursals() {
		return sucursals;
	}
	public void addSucursal(Oficina oficina) {
		this.sucursals.add(oficina);
	}
	public void removeSucursal(Oficina oficina) {
		this.sucursals.remove(oficina);
	}
}
