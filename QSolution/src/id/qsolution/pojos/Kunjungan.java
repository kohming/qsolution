package id.qsolution.pojos;

import id.qsolution.models.TmKategoriBarang;
import id.qsolution.models.dao.TmKategoriBarangDao;

import java.io.Serializable;

import android.app.Activity;

@SuppressWarnings("serial")
public class Kunjungan implements Serializable {

	private String kode;
	private String name;
	private String tglSurveySkrg;
	private String tglSurveyBerikut;
	private String responden;
	private String kodeSurveyor;
	private String jamMulai;
	private String jamSelesai;
	private String pelanggan;
	private Double omzet;
	private String waktuOperasi;
	private String jabatanResponden;
	private String kodeOutlet;
	private String kodeStatus;
	private String kodeKategori;
	private String namaKategori;
	private Activity activity;
	
	public Kunjungan(Activity act) {
		this.activity = act;
	}
	
	public String getNamaKategori() {
		return namaKategori;
	}
	public void setNamaKategori(String namaKategori) {
		this.namaKategori = namaKategori;
	}
	public String getKodeKategori() {
		return kodeKategori;
	}
	public void setKodeKategori(String kodeKategori) {
		this.kodeKategori = kodeKategori;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getPelanggan() {
		return pelanggan;
	}
	public void setPelanggan(String pelanggan) {
		this.pelanggan = pelanggan;
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
	public String getKodeOutlet() {
		return kodeOutlet;
	}
	public void setKodeOutlet(String kodeOutlet) {
		this.kodeOutlet = kodeOutlet;
	}
	public String getKodeStatus() {
		return kodeStatus;
	}
	public void setKodeStatus(String kodeStatus) {
		this.kodeStatus = kodeStatus;
	}
	
	@Override
	public String toString() {
		String result = "";
		try {
			if(!getKodeKategori().equals("")){
				result = kodeOutlet +" - "+NamaKategori(getKodeKategori());
			}
		} catch (Exception e) {
			return kodeOutlet;
		}
		return result;
	}
	private String NamaKategori(String kodeKategori2) {
		TmKategoriBarang kategori = new TmKategoriBarang();
		TmKategoriBarangDao kategoriDao = new TmKategoriBarangDao(activity.getApplicationContext());
		kategori.setKode(kodeKategori2);
		kategori = kategoriDao.getByExample(kategori);

		return kategori.getNama();
	}
}
