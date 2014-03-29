package id.qsolution.models;

import com.turbomanage.storm.api.Entity;
import com.turbomanage.storm.api.Id;

// Generated Jan 29, 2014 12:26:12 AM by Hibernate Tools 3.4.0.CR1


/**
 * TmOutletFasilitas generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
public class TmOutletFasilitas implements java.io.Serializable {

	//private TmOutletFasilitasId id;
	@Id
	private long id;
	private Integer jumlah;
	private String kodeOutlet;
	private String kodeFasilitas;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
/*	public TmOutletFasilitas() {
	}

	public TmOutletFasilitas(TmOutletFasilitasId id) {
		this.id = id;
	}

	public TmOutletFasilitas(TmOutletFasilitasId id, Integer jumlah) {
		this.id = id;
		this.jumlah = jumlah;
	}


	public TmOutletFasilitasId getId() {
		return this.id;
	}

	public void setId(TmOutletFasilitasId id) {
		this.id = id;
	}*/

	public Integer getJumlah() {
		return this.jumlah;
	}

	public void setJumlah(Integer jumlah) {
		this.jumlah = jumlah;
	}

}
