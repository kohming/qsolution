package id.qsolution.pojos;

// Generated Jan 29, 2014 12:26:12 AM by Hibernate Tools 3.4.0.CR1

import java.util.Date;


/**
 * TmOutlet generated by hbm2java
 */
@SuppressWarnings("serial")
public class Outlet implements java.io.Serializable {

	private String kode;
	private String nama;
	private String alamat;
	private String rt;
	private String rw;
	private String kodeKelurahan;
	private String kodeKlasifikasi;
	private String kodePos;
	private String lokasi;
	private String aksesToko;
	private Double XCoord;
	private Double YCoord;
	private String pemilik;
	private String telepon;
	private String insertBy;
	private String statusBangunan;
	private Date insertDate;
	private Date updateDate;

	public Outlet() {
	}

	public Outlet(String kode) {
		this.kode = kode;
	}
	
	public String getKode() {
		return this.kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}
	
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getAlamat() {
		return this.alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getRt() {
		return this.rt;
	}

	public void setRt(String rt) {
		this.rt = rt;
	}


	public String getRw() {
		return this.rw;
	}

	public void setRw(String rw) {
		this.rw = rw;
	}

	public String getKodeKelurahan() {
		return this.kodeKelurahan;
	}

	public void setKodeKelurahan(String kodeKelurahan) {
		this.kodeKelurahan = kodeKelurahan;
	}

	public String getKodePos() {
		return this.kodePos;
	}

	public void setKodePos(String kodePos) {
		this.kodePos = kodePos;
	}

	public String getLokasi() {
		return this.lokasi;
	}

	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}

	public String getAksesToko() {
		return this.aksesToko;
	}

	public void setAksesToko(String aksesToko) {
		this.aksesToko = aksesToko;
	}

	public Double getXCoord() {
		return this.XCoord;
	}

	public void setXCoord(Double XCoord) {
		this.XCoord = XCoord;
	}

	public Double getYCoord() {
		return this.YCoord;
	}

	public void setYCoord(Double YCoord) {
		this.YCoord = YCoord;
	}

	public String getPemilik() {
		return this.pemilik;
	}

	public void setPemilik(String pemilik) {
		this.pemilik = pemilik;
	}


	public String getTelepon() {
		return this.telepon;
	}

	public void setTelepon(String telepon) {
		this.telepon = telepon;
	}

	public String getInsertBy() {
		return this.insertBy;
	}

	public void setInsertBy(String insertBy) {
		this.insertBy = insertBy;
	}

	public String getStatusBangunan() {
		return this.statusBangunan;
	}

	public void setStatusBangunan(String statusBangunan) {
		this.statusBangunan = statusBangunan;
	}

	public Date getInsertDate() {
		return this.insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}


	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public String getKodeKlasifikasi() {
		return kodeKlasifikasi;
	}

	public void setKodeKlasifikasi(String kodeKlasifikasi) {
		this.kodeKlasifikasi = kodeKlasifikasi;
	}

	

}