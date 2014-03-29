package id.qsolution.models;

import java.io.Serializable;

import com.turbomanage.storm.api.Entity;
import com.turbomanage.storm.api.Id;

@SuppressWarnings("serial")
@Entity
public class TtDKunjunganSurveyorPhoto implements Serializable {
	
	@Id
	private long id;
	private String kodeKunjungan;
	private String namaFile;
	private String deskripsi;
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
	@Override
	public String toString() {
		return kodeKunjungan;
	}
}
