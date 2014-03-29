package id.qsolution.pojos;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SurveyorRakSkuPromosi implements Serializable{

	private String kodeKunjungan;
	private String nomorUrut;
	private String kodeSku;
	private String kodePromosi;
	private String deskripsi;
	private String kode;
	
	public String getKodeKunjungan() {
		return kodeKunjungan;
	}
	public void setKodeKunjungan(String kodeKunjungan) {
		this.kodeKunjungan = kodeKunjungan;
	}
	public String getNomorUrut() {
		return nomorUrut;
	}
	public void setNomorUrut(String nomorUrut) {
		this.nomorUrut = nomorUrut;
	}
	public String getKodeSku() {
		return kodeSku;
	}
	public void setKodeSku(String kodeSku) {
		this.kodeSku = kodeSku;
	}
	public String getKodePromosi() {
		return kodePromosi;
	}
	public void setKodePromosi(String kodePromosi) {
		this.kodePromosi = kodePromosi;
	}
	public String getDeskripsi() {
		return deskripsi;
	}
	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}
	public String getKode() {
		return kode;
	}
	public void setKode(String kode) {
		this.kode = kode;
	}
}
