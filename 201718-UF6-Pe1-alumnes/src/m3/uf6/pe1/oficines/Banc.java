package m3.uf6.pe1.oficines;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.LinkedList;

@Entity
@NamedQueries({
		@NamedQuery(name="Banc.findAll",
				query="SELECT b FROM Banc b ORDER BY b.codi"),
})
public class Banc {
	@Basic(optional=false) private int codi;
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
