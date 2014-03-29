package id.qsolution.pojos;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Photo implements Serializable {

	private String kode;
	public String getKode() {
		return kode;
	}
	public void setKode(String kode) {
		this.kode = kode;
	}
	public String getKode_outlet() {
		return kode_outlet;
	}
	public void setKode_outlet(String kode_outlet) {
		this.kode_outlet = kode_outlet;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getDeskripsi() {
		return deskripsi;
	}
	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}
	private String kode_outlet;
	private String nama;
	private String deskripsi;
	
}
