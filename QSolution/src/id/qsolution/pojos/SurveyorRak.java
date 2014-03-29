package id.qsolution.pojos;

import java.io.Serializable;

import com.turbomanage.storm.api.Entity;
import com.turbomanage.storm.api.Id;

@SuppressWarnings("serial")
@Entity
public class SurveyorRak implements Serializable {
	
	@Id
	private long id;
	private String kode;
	private String kodeKunjungan;
	private String nomorUrut;
	private String kodeBrand;
	private String kodeRak;
	private String status;
	
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

}
