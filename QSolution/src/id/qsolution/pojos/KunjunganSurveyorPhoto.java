package id.qsolution.pojos;

import java.io.Serializable;

@SuppressWarnings("serial")
public class KunjunganSurveyorPhoto implements Serializable {

	private String kodeKunjungan;
	private String namaFile;
	private String deskripsi;
	private String status;
	private String kodeOutlet;
	
	public String getKodeKunjungan() {
		return kodeKunjungan;
	}
	public void setKodeKunjungan(String kodeKunjungan) {
		this.kodeKunjungan = kodeKunjungan;
	}
	public String getNamaFile() {
		return namaFile;
	}
	public void setNamaFile(String namaFile) {
		this.namaFile = namaFile;
	}
	public String getDeskripsi() {
		return deskripsi;
	}
	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getKodeOutlet() {
		return kodeOutlet;
	}
	public void setKodeOutlet(String kodeOutlet) {
		this.kodeOutlet = kodeOutlet;
	}
}
