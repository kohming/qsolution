package id.qsolution.models;

// Generated Jan 29, 2014 12:26:12 AM by Hibernate Tools 3.4.0.CR1



/**
 * TmUnit generated by hbm2java
 */
@SuppressWarnings("serial")
public class TmUnit implements java.io.Serializable {

	private String kode;
	private String nama;
	private String kodeVarian;

	public TmUnit() {
	}

	public TmUnit(String kode) {
		this.kode = kode;
	}

	public TmUnit(String kode, String nama, String kodeVarian) {
		this.kode = kode;
		this.nama = nama;
		this.kodeVarian = kodeVarian;
	}

	public String getKode() {
		return this.kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getKodeVarian() {
		return this.kodeVarian;
	}

	public void setKodeVarian(String kodeVarian) {
		this.kodeVarian = kodeVarian;
	}

}
