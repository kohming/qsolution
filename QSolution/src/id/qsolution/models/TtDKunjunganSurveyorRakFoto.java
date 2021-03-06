package id.qsolution.models;

import com.turbomanage.storm.api.Entity;
import com.turbomanage.storm.api.Id;

// Generated Mar 2, 2014 9:51:43 AM by Hibernate Tools 3.4.0.CR1


/**
 * TtDKunjunganSurveyorRakFoto generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
public class TtDKunjunganSurveyorRakFoto implements java.io.Serializable {

	@Id
	private long id;
	private String deskripsi;
	private String kodeKunjungan;
	private String nomorUrut;
	private String namaFile;
	private String status;
	private String kodeOutlet;
	
	public String getKodeOutlet() {
		return kodeOutlet;
	}
	public void setKodeOutlet(String kodeOutlet) {
		this.kodeOutlet = kodeOutlet;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDeskripsi() {
		return deskripsi;
	}
	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
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
	public String getNamaFile() {
		return namaFile;
	}
	public void setNamaFile(String namaFile) {
		this.namaFile = namaFile;
	}
	
	@Override
	public String toString() {
		return kodeKunjungan;
	}
	
}
