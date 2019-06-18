package m3.uf6.pe1.oficines;

public class Oficina {
	private Banc banc;
	private Municipi municipi;
	private boolean caixerExtern;

	public Oficina(Banc banc, Municipi municipi, boolean caixerExtern) {
		super();
		this.banc = banc;
		this.municipi = municipi;
		this.caixerExtern = caixerExtern;
		this.banc.addSucursal(this);
		this.municipi.addOficina(this);
	}
	public Banc getBanc() {
		return banc;
	}
	public Municipi getMunicipi() {
		return municipi;
	}
	public boolean isCaixerExtern() {
		return caixerExtern;
	}
	public void setCaixerExtern(boolean caixerExtern) {
		this.caixerExtern = caixerExtern;
	}
}
