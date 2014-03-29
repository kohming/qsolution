package id.qsolution.pojos;

import java.io.Serializable;

@SuppressWarnings("serial")
public class KunjunganSurveyorRakSku implements Serializable {

	private String kodeKunjungan;
	private String nomorUrut;
	private String kodeSku;
	private Integer jumlahUnit;
	private Integer jumlahFacing;
	private Integer hargaJual;
	private String kode;
	private boolean lblPrice;
	private String kodeVolum;
	private String kodePackage;
	private String namaSku;
	
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
	public String getKodeSku() {
		return kodeSku;
	}
	public void setKodeSku(String kodeSku) {
		this.kodeSku = kodeSku;
	}
	public Integer getJumlahUnit() {
		return jumlahUnit;
	}
	public void setJumlahUnit(Integer jumlahUnit) {
		this.jumlahUnit = jumlahUnit;
	}
	public Integer getJumlahFacing() {
		return jumlahFacing;
	}
	public void setJumlahFacing(Integer jumlahFacing) {
		this.jumlahFacing = jumlahFacing;
	}
	public Integer getHargaJual() {
		return hargaJual;
	}
	public void setHargaJual(Integer hargaJual) {
		this.hargaJual = hargaJual;
	}
	public String getKode() {
		return kode;
	}
	public void setKode(String kode) {
		this.kode = kode;
	}
	public boolean isLblPrice() {
		return lblPrice;
	}
	public void setLblPrice(boolean lblPrice) {
		this.lblPrice = lblPrice;
	}
	public String getKodeVolum() {
		return kodeVolum;
	}
	public void setKodeVolum(String kodeVolum) {
		this.kodeVolum = kodeVolum;
	}
	public String getKodePackage() {
		return kodePackage;
	}
	public void setKodePackage(String kodePackage) {
		this.kodePackage = kodePackage;
	}
	public String getNamaSku() {
		return namaSku;
	}
	public void setNamaSku(String namaSku) {
		this.namaSku = namaSku;
	}
	

}
