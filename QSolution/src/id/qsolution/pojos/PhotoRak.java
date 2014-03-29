package id.qsolution.pojos;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PhotoRak implements Serializable {
	
	private long id;
	private String kode;
	private String kodKategori;
	private String kodeOutlet;
	private String nama;
	private String namaFoto;
	private String deskripsi;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKode() {
		return kode;
	}
	public void setKode(String kode) {
		this.kode = kode;
	}
	public String getKodKategori() {
		return kodKategori;
	}
	public void setKodKategori(String kodKategori) {
		this.kodKategori = kodKategori;
	}
	public String getKodeOutlet() {
		return kodeOutlet;
	}
	public void setKodeOutlet(String kodeOutlet) {
		this.kodeOutlet = kodeOutlet;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getNamaFoto() {
		return namaFoto;
	}
	public void setNamaFoto(String namaFoto) {
		this.namaFoto = namaFoto;
	}
	public String getDeskripsi() {
		return deskripsi;
	}
	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}


}
