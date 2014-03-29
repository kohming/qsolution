package id.qsolution.models;

import java.io.Serializable;
import com.turbomanage.storm.api.Entity;
import com.turbomanage.storm.api.Id;


@SuppressWarnings("serial")
@Entity
public class TmPosm implements Serializable {


	@Id
	private long id;
	private String kode = "00";
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
