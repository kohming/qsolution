package id.qsolution.pojos;

import java.io.Serializable;

@SuppressWarnings("serial")
public class KunjunganSurveyorRakPosm implements Serializable {
	
	private String kode;
	private Integer jumlah;
	private String kodeKunjungan;
	private String nomorUrut;
	private String kodeBrand;
	private String kodePosm;
	
	public String getKode() {
		return kode;
	}
	public void setKode(String kode) {
		this.kode = kode;
	}
	public Integer getJumlah() {
		return jumlah;
	}
	public void setJumlah(Integer jumlah) {
		this.jumlah = jumlah;
	}
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

}
