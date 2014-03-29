package id.qsolution.models;

import java.io.Serializable;

import com.turbomanage.storm.api.Entity;
import com.turbomanage.storm.api.Id;

@SuppressWarnings("serial")
@Entity
public class TtDKunjunganSurveyorOmsetKatagori implements Serializable {
	
	@Id
	private long id;
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
	public String getKodeKategori() {
		return kodeKategori;
	}
	public void setKodeKategori(String kodeKategori) {
		this.kodeKategori = kodeKategori;
	}
	public String getKodeOutlet() {
		return kodeOutlet;
	}
	public void setKodeOutlet(String kodeOutlet) {
		this.kodeOutlet = kodeOutlet;
	}
	public int getOmset() {
		return omset;
	}
	public void setOmset(int omset) {
		this.omset = omset;
	}
	public String getNamaKategori() {
		return namaKategori;
	}
	public void setNamaKategori(String namaKategori) {
		this.namaKategori = namaKategori;
	}
	private String kodeKunjungan;
	private String kodeKategori;
	private String kodeOutlet;
	private int omset;
	private String namaKategori;
	
	
}
