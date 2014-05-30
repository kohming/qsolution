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
	private String status;
	private String kodeJamOperasi;
	private long luasBangunan;
	private long pelanggan;
    private String kodeKeyAccount;
    private Integer jumlahCoc;
	

	public Outlet() {
	}

	public Outlet(String kode) {
		this.kode = kode;
	}
	
	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getRt() {
		return rt;
	}

	public void setRt(String rt) {
		this.rt = rt;
	}

	public String getRw() {
		return rw;
	}

	public void setRw(String rw) {
		this.rw = rw;
	}

	public String getKodeKelurahan() {
		return kodeKelurahan;
	}

	public void setKodeKelurahan(String kodeKelurahan) {
		this.kodeKelurahan = kodeKelurahan;
	}

	public String getKodeKlasifikasi() {
		return kodeKlasifikasi;
	}

	public void setKodeKlasifikasi(String kodeKlasifikasi) {
		this.kodeKlasifikasi = kodeKlasifikasi;
	}

	public String getKodePos() {
		return kodePos;
	}

	public void setKodePos(String kodePos) {
		this.kodePos = kodePos;
	}

	public String getLokasi() {
		return lokasi;
	}

	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}

	public String getAksesToko() {
		return aksesToko;
	}

	public void setAksesToko(String aksesToko) {
		this.aksesToko = aksesToko;
	}

	public Double getXCoord() {
		return XCoord;
	}

	public void setXCoord(Double xCoord) {
		XCoord = xCoord;
	}

	public Double getYCoord() {
		return YCoord;
	}

	public void setYCoord(Double yCoord) {
		YCoord = yCoord;
	}

	public String getPemilik() {
		return pemilik;
	}

	public void setPemilik(String pemilik) {
		this.pemilik = pemilik;
	}

	public String getTelepon() {
		return telepon;
	}

	public void setTelepon(String telepon) {
		this.telepon = telepon;
	}

	public String getInsertBy() {
		return insertBy;
	}

	public void setInsertBy(String insertBy) {
		this.insertBy = insertBy;
	}

	public String getStatusBangunan() {
		return statusBangunan;
	}

	public void setStatusBangunan(String statusBangunan) {
		this.statusBangunan = statusBangunan;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getKodeJamOperasi() {
		return kodeJamOperasi;
	}

	public void setKodeJamOperasi(String kodeJamOperasi) {
		this.kodeJamOperasi = kodeJamOperasi;
	}

	public long getLuasBangunan() {
		return luasBangunan;
	}

	public void setLuasBangunan(long luasBangunan) {
		this.luasBangunan = luasBangunan;
	}

	public long getPelanggan() {
		return pelanggan;
	}

	public void setPelanggan(long pelanggan) {
		this.pelanggan = pelanggan;
	}

	public Integer getJumlahCoc() {
		return jumlahCoc;
	}

	public void setJumlahCoc(Integer jumlahCoc) {
		this.jumlahCoc = jumlahCoc;
	}
	
	public String getKodeKeyAccount() {
		return kodeKeyAccount;
	}

	public void setKodeKeyAccount(String kodeKeyAccount) {
		this.kodeKeyAccount = kodeKeyAccount;
	}


}
