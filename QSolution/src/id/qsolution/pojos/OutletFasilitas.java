package id.qsolution.pojos;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OutletFasilitas implements Serializable {
	
	private Integer jumlah;
	private String kodeOutlet;
	private String kodeFasilitas;
	
	public Integer getJumlah() {
		return jumlah;
	}
	public void setJumlah(Integer jumlah) {
		this.jumlah = jumlah;
	}
	public String getKodeOutlet() {
		return kodeOutlet;
	}
	public void setKodeOutlet(String kodeOutlet) {
		this.kodeOutlet = kodeOutlet;
	}
	public String getKodeFasilitas() {
		return kodeFasilitas;
	}
	public void setKodeFasilitas(String kodeFasilitas) {
		this.kodeFasilitas = kodeFasilitas;
	}
	
}
