package id.qsolution.models;

import java.io.Serializable;
import com.turbomanage.storm.api.Entity;
import com.turbomanage.storm.api.Id;

@SuppressWarnings("serial")
@Entity
public class TmKategoriCompanyBrand implements Serializable {

	@Id
	private long id;
	private int kode;
	private String kode_company;
	private String kode_sub_kategori;
	private String kode_brand;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getKode() {
		return kode;
	}
	public void setKode(int kode) {
		this.kode = kode;
	}
	public String getKode_company() {
		return kode_company;
	}
	public void setKode_company(String kode_company) {
		this.kode_company = kode_company;
	}
	public String getKode_sub_kategori() {
		return kode_sub_kategori;
	}
	public void setKode_sub_kategori(String kode_sub_kategori) {
		this.kode_sub_kategori = kode_sub_kategori;
	}
	public String getKode_brand() {
		return kode_brand;
	}
	public void setKode_brand(String kode_brand) {
		this.kode_brand = kode_brand;
	}
}
