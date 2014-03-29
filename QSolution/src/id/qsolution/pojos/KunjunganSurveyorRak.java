package id.qsolution.pojos;

import java.io.Serializable;

@SuppressWarnings("serial")
public class KunjunganSurveyorRak implements Serializable {
	
	private String kode;
	private String kodeKunjungan;
	private String nomorUrut;
	private String kodeBrand;
	private String kodeRak;
	private String status;
	private String kodeNamaBrand;
	private String namaRak;
	
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
	public String getKodeRak() {
		return kodeRak;
	}
	public void setKodeRak(String kodeRak) {
		this.kodeRak = kodeRak;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getKodeNamaBrand() {
		return kodeNamaBrand;
	}
	public void setKodeNamaBrand(String kodeNamaBrand) {
		this.kodeNamaBrand = kodeNamaBrand;
	}
	public String getNamaRak() {
		return namaRak;
	}
	public void setNamaRak(String namaRak) {
		this.namaRak = namaRak;
	}
	
}
