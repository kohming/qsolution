package id.qsolution.models;

import java.io.Serializable;
import com.turbomanage.storm.api.Entity;
import com.turbomanage.storm.api.Id;

@Entity
@SuppressWarnings("serial")
public class TmLuasBangunan implements Serializable {

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
