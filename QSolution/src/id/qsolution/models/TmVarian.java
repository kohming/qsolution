package id.qsolution.models;

import com.turbomanage.storm.api.Entity;
import com.turbomanage.storm.api.Id;

// Generated Jan 29, 2014 12:26:12 AM by Hibernate Tools 3.4.0.CR1



@SuppressWarnings("serial")
@Entity
public class TmVarian implements java.io.Serializable {

	@Id
	private long id;
	private String kode;
	private String nama;


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
	

}
