package id.qsolution.models;

import java.io.Serializable;
import com.turbomanage.storm.api.Entity;
import com.turbomanage.storm.api.Id;


@SuppressWarnings("serial")
@Entity
public class DaftarOutletSurvey implements Serializable {
	
	@Id
	private long id;
	private String sudahSurvey;
	private String outletBaru;
	private String kodeOutlet;
	private String kodeSurveyor;
	private String periodeMulai;
	private String periodeSelesai;
	private String kodeKategori;
	private String status;
	
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
	public String getSudahSurvey() {
		return sudahSurvey;
	}
	public void setSudahSurvey(String sudahSurvey) {
		this.sudahSurvey = sudahSurvey;
	}
	public String getOutletBaru() {
		return outletBaru;
	}
	public void setOutletBaru(String outletBaru) {
		this.outletBaru = outletBaru;
	}
	public String getKodeOutlet() {
		return kodeOutlet;
	}
	public void setKodeOutlet(String kodeOutlet) {
		this.kodeOutlet = kodeOutlet;
	}
	public String getKodeSurveyor() {
		return kodeSurveyor;
	}
	public void setKodeSurveyor(String kodeSurveyor) {
		this.kodeSurveyor = kodeSurveyor;
	}
	public String getPeriodeMulai() {
		return periodeMulai;
	}
	public void setPeriodeMulai(String periodeMulai) {
		this.periodeMulai = periodeMulai;
	}
	public String getPeriodeSelesai() {
		return periodeSelesai;
	}
	public void setPeriodeSelesai(String periodeSelesai) {
		this.periodeSelesai = periodeSelesai;
	}
	public String getKodeKategori() {
		return kodeKategori;
	}
	public void setKodeKategori(String kodeKategori) {
		this.kodeKategori = kodeKategori;
	}
	
	
	
}
