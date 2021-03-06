package id.qsolution.models;

import com.turbomanage.storm.api.Entity;
import com.turbomanage.storm.api.Id;

// Generated Jan 29, 2014 12:26:12 AM by Hibernate Tools 3.4.0.CR1


/**
 * TtMKunjunganSurveyor generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
public class TtMKunjunganSurveyor implements java.io.Serializable {

	@Id
	private long id;
	private String kode;
	private String name;
	private String tglSurveySkrg;
	private String tglSurveyBerikut;
	private String kodeOutlet;
	private String responden;
	private String kodeSurveyor;
	private String jamMulai;
	private String jamSelesai;
	private Double omzet;
	private String waktuOperasi;
	private String jabatanResponden;
	private String kodeKategori;
	private Double omzetKategori;
	private Double xcoord;
	private Double ycoord;
	private String status;
	private String namaKategori;
	
	public String getNamaKategori() {
		return namaKategori;
	}
	public void setNamaKategori(String namaKategori) {
		this.namaKategori = namaKategori;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getKodeStatus() {
		return kodeStatus;
	}
	public void setKodeStatus(String kodeStatus) {
		this.kodeStatus = kodeStatus;
	}
	private String kodeStatus;
	
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
	public String getTglSurveySkrg() {
		return tglSurveySkrg;
	}
	public void setTglSurveySkrg(String tglSurveySkrg) {
		this.tglSurveySkrg = tglSurveySkrg;
	}
	public String getTglSurveyBerikut() {
		return tglSurveyBerikut;
	}
	public void setTglSurveyBerikut(String tglSurveyBerikut) {
		this.tglSurveyBerikut = tglSurveyBerikut;
	}
	public String getKodeOutlet() {
		return kodeOutlet;
	}
	public void setKodeOutlet(String kodeOutlet) {
		this.kodeOutlet = kodeOutlet;
	}
	public String getResponden() {
		return responden;
	}
	public void setResponden(String responden) {
		this.responden = responden;
	}
	public String getKodeSurveyor() {
		return kodeSurveyor;
	}
	public void setKodeSurveyor(String kodeSurveyor) {
		this.kodeSurveyor = kodeSurveyor;
	}
	public String getJamMulai() {
		return jamMulai;
	}
	public void setJamMulai(String jamMulai) {
		this.jamMulai = jamMulai;
	}
	public String getJamSelesai() {
		return jamSelesai;
	}
	public void setJamSelesai(String jamSelesai) {
		this.jamSelesai = jamSelesai;
	}
	public Double getOmzet() {
		return omzet;
	}
	public void setOmzet(Double omzet) {
		this.omzet = omzet;
	}
	public String getWaktuOperasi() {
		return waktuOperasi;
	}
	public void setWaktuOperasi(String waktuOperasi) {
		this.waktuOperasi = waktuOperasi;
	}
	public String getJabatanResponden() {
		return jabatanResponden;
	}
	public void setJabatanResponden(String jabatanResponden) {
		this.jabatanResponden = jabatanResponden;
	}
	public String getKodeKategori() {
		return kodeKategori;
	}
	public void setKodeKategori(String kodeKategori) {
		this.kodeKategori = kodeKategori;
	}
	public Double getOmzetKategori() {
		return omzetKategori;
	}
	public void setOmzetKategori(Double omzetKategori) {
		this.omzetKategori = omzetKategori;
	}
	public Double getXcoord() {
		return xcoord;
	}
	public void setXcoord(Double xcoord) {
		this.xcoord = xcoord;
	}
	public Double getYcoord() {
		return ycoord;
	}
	public void setYcoord(Double ycoord) {
		this.ycoord = ycoord;
	}
	

}
