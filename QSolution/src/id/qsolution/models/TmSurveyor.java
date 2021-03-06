package id.qsolution.models;

import com.turbomanage.storm.api.Entity;
import com.turbomanage.storm.api.Id;

// Generated Jan 29, 2014 12:26:12 AM by Hibernate Tools 3.4.0.CR1


/**
 * TmSurveyor generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
public class TmSurveyor implements java.io.Serializable {


	@Id
	private long id;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private String kode;
	private String nama;
	private String alamat;
	private String telepon;
	private String username;
	private String password;
	private String imei;

	public TmSurveyor() {
	}

	public TmSurveyor(String kode) {
		this.kode = kode;
	}

	public TmSurveyor(String kode, String nama, String alamat, String telepon,
			String username, String password, String imei) {
		this.kode = kode;
		this.nama = nama;
		this.alamat = alamat;
		this.telepon = telepon;
		this.username = username;
		this.password = password;
		this.imei = imei;
	}

	public String getKode() {
		return this.kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
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

	public String getTelepon() {
		return this.telepon;
	}

	public void setTelepon(String telepon) {
		this.telepon = telepon;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImei() {
		return this.imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

}
