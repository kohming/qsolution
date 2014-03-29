package id.qsolution.pojos;

import java.io.Serializable;
@SuppressWarnings("serial")

public class KunjunganSurveyorOutletPosm implements Serializable{
	
	private String kode;
	private String kodeKunjungan;
	private String kodeBrand;
	private String kodePosm;
	private Integer jumlah;
	private String namaBrand;
	private String namaPosm;
	
	public String getKode() {
		return kode;
	}
	public void setKode(String kode) {
		this.kode = kode;
	}
	public String getKodeKunjungan() {
		return kodeKunjungan;
	}
	public void setKodeKunjungan(String kodeKunjungan) {
		this.kodeKunjungan = kodeKunjungan;
	}
	public String getKodeBrand() {
		return kodeBrand;
	}
	public void setKodeBrand(String kodeBrand) {
		this.kodeBrand = kodeBrand;
	}
	public String getKodePosm() {
		return kodePosm;
	}
	public void setKodePosm(String kodePosm) {
		this.kodePosm = kodePosm;
	}
	public Integer getJumlah() {
		return jumlah;
	}
	public void setJumlah(Integer jumlah) {
		this.jumlah = jumlah;
	}
	public String getNamaBrand() {
		return namaBrand;
	}
	public void setNamaBrand(String namaBrand) {
		this.namaBrand = namaBrand;
	}
	public String getNamaPosm() {
		return namaPosm;
	}
	public void setNamaPosm(String namaPosm) {
		this.namaPosm = namaPosm;
	}


}
