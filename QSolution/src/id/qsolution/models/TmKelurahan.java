package id.qsolution.models;

import com.turbomanage.storm.api.Entity;
import com.turbomanage.storm.api.Id;

// Generated Jan 29, 2014 12:26:12 AM by Hibernate Tools 3.4.0.CR1



/**
 * TmKelurahan generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
public class TmKelurahan implements java.io.Serializable {

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
	private String kodeKec;
	private String kodepos;

	public TmKelurahan() {
	}

	public TmKelurahan(String kode) {
		this.kode = kode;
	}

	public TmKelurahan(String kode, String nama, String kodeKec, String kodepos) {
		this.kode = kode;
		this.nama = nama;
		this.kodeKec = kodeKec;
		this.kodepos = kodepos;
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

	public String getKodeKec() {
		return this.kodeKec;
	}

	public void setKodeKec(String kodeKec) {
		this.kodeKec = kodeKec;
	}

	public String getKodepos() {
		return this.kodepos;
	}

	public void setKodepos(String kodepos) {
		this.kodepos = kodepos;
	}

}
