package id.qsolution.models;

import com.turbomanage.storm.api.Entity;
import com.turbomanage.storm.api.Id;

// Generated Jan 29, 2014 12:26:12 AM by Hibernate Tools 3.4.0.CR1


/**
 * TtDaftarOutletSurvey generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
public class TtDaftarOutletSurvey implements java.io.Serializable {

	@Id
	private long id;
	private String kodeOutlet;
	private String kodeSurveyor;
	private String periodeMulai;
	private String periodeSelesai;
	private String sudahSurvey;
	private String outletBaru;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
}