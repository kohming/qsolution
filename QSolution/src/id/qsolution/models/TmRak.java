package id.qsolution.models;

// Generated Jan 29, 2014 12:26:12 AM by Hibernate Tools 3.4.0.CR1

import com.turbomanage.storm.api.Entity;
import com.turbomanage.storm.api.Id;

/**
 * TmRak generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
public class TmRak implements java.io.Serializable {

	@Id
	private long id;
	private String kode;
	private String nama;
	private String branded;
	private String kodeGroup;
	
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
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getBranded() {
		return branded;
	}
	public void setBranded(String branded) {
		this.branded = branded;
	}
	public String getKodeGroup() {
		return kodeGroup;
	}
	public void setKodeGroup(String kodeGroup) {
		this.kodeGroup = kodeGroup;
	}
}