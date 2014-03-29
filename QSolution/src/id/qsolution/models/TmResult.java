package id.qsolution.models;

import java.util.ArrayList;
import java.util.List;


public class TmResult {
	
	List<TmOutlet> listOutlets = new ArrayList<TmOutlet>();
	List<TmKabupaten> listKabupaten = new ArrayList<TmKabupaten>();
	List<TmKecamatan> listKecamatan = new ArrayList<TmKecamatan>();
	List<TmKelurahan> listKelurahan = new ArrayList<TmKelurahan>();
	List<TmKlasifikasiOutlet> listKlasifikasiOutlet = new ArrayList<TmKlasifikasiOutlet>();
	List<TmStatusBangunan> listStatusBangunan = new ArrayList<TmStatusBangunan>();
	List<TmTipeOutlet> listTipeOutlet = new ArrayList<TmTipeOutlet>();
	List<TmJenisLokasi> listJenisLokasi = new ArrayList<TmJenisLokasi>();
	List<TmAksesKunjungan> listAksesKunjungan = new ArrayList<TmAksesKunjungan>();
	List<TmFasilitas> listFasilitas = new ArrayList<TmFasilitas>();
	List<TmJabatanResponden> listJabatanResponden = new ArrayList<TmJabatanResponden>();
	List<TmStatusKunjungan> listStatusKunjungan = new ArrayList<TmStatusKunjungan>();
	List<TmWaktuOperasi> listWaktuOperasi = new ArrayList<TmWaktuOperasi>();
	List<TmPelanggan> listPelanggan = new ArrayList<TmPelanggan>();
	List<TmKategoriBarang> listKategoriBarang= new ArrayList<TmKategoriBarang>();
	List<TmSubKategoriBarang> listSubKategoriBarang= new ArrayList<TmSubKategoriBarang>();
	List<TmGroupRak> listGroupRak= new ArrayList<TmGroupRak>();
	List<TmRak> listRak= new ArrayList<TmRak>();
	List<TmCompany> listCompany = new ArrayList<TmCompany>();
	List<TmBrand> listBrand = new ArrayList<TmBrand>();
	List<TmPosm> listPosm = new ArrayList<TmPosm>();
	List<TmSumberProduk> listSumber = new ArrayList<TmSumberProduk>();
	List<TmPromosiSku> listPromosi = new ArrayList<TmPromosiSku>();
	List<TmKategoriCompanyBrand> listKatagoryCompanyBrand = new ArrayList<TmKategoriCompanyBrand>();
	List<TmVolum> listVolum = new ArrayList<TmVolum>();
	List<TmVarian> listVarian = new ArrayList<TmVarian>();
	List<TmKategoriCompanyBrand> listKategoriCompanyBrand = new ArrayList<TmKategoriCompanyBrand>();
	List<DaftarOutletSurvey> listOutletSurvey = new ArrayList<DaftarOutletSurvey>();
	
	public List<DaftarOutletSurvey> getListOutletSurvey() {
		return listOutletSurvey;
	}
	public void setListOutletSurvey(List<DaftarOutletSurvey> listOutletSurvey) {
		this.listOutletSurvey = listOutletSurvey;
	}
	public List<TmKategoriCompanyBrand> getListKategoriCompanyBrand() {
		return listKategoriCompanyBrand;
	}
	public void setListKategoriCompanyBrand(
			List<TmKategoriCompanyBrand> listKategoriCompanyBrand) {
		this.listKategoriCompanyBrand = listKategoriCompanyBrand;
	}
	public List<TmVarian> getListVarian() {
		return listVarian;
	}
	public void setListVarian(List<TmVarian> listVarian) {
		this.listVarian = listVarian;
	}
	public List<TmVolum> getListVolum() {
		return listVolum;
	}
	public void setListVolum(List<TmVolum> listVolum) {
		this.listVolum = listVolum;
	}
	public List<TmPackage> getListPackage() {
		return listPackage;
	}
	public void setListPackage(List<TmPackage> listPackage) {
		this.listPackage = listPackage;
	}
	List<TmPackage> listPackage = new ArrayList<TmPackage>();
	
	
	public List<TmLuasBangunan> getListLuasBangunan() {
		return listLuasBangunan;
	}
	public void setListLuasBangunan(List<TmLuasBangunan> listLuasBangunan) {
		this.listLuasBangunan = listLuasBangunan;
	}
	public List<TmJumlahCoc> getListJumlahCoc() {
		return listJumlahCoc;
	}
	public void setListJumlahCoc(List<TmJumlahCoc> listJumlahCoc) {
		this.listJumlahCoc = listJumlahCoc;
	}
	List<TmLuasBangunan> listLuasBangunan = new ArrayList<TmLuasBangunan>();
	List<TmJumlahCoc> listJumlahCoc = new ArrayList<TmJumlahCoc>();
	
	public List<TmKategoriCompanyBrand> getListKatagoryCompanyBrand() {
		return listKatagoryCompanyBrand;
	}
	public void setListKatagoryCompanyBrand(
			List<TmKategoriCompanyBrand> listKatagoryCompanyBrand) {
		this.listKatagoryCompanyBrand = listKatagoryCompanyBrand;
	}
	public List<TmPromosiSku> getListPromosi() {
		return listPromosi;
	}
	public void setListPromosi(List<TmPromosiSku> listPromosi) {
		this.listPromosi = listPromosi;
	}
	public List<TmSumberProduk> getListSumber() {
		return listSumber;
	}
	public void setListSumber(List<TmSumberProduk> listSumber) {
		this.listSumber = listSumber;
	}
	public List<TmPosm> getListPosm() {
		return listPosm;
	}
	public void setListPosm(List<TmPosm> listPosm) {
		this.listPosm = listPosm;
	}
	public List<TmSku> getListSku() {
		return listSku;
	}
	public void setListSku(List<TmSku> listSku) {
		this.listSku = listSku;
	}
	List<TmSku> listSku = new ArrayList<TmSku>();
	
	public List<TmStatusKunjungan> getListStatusKunjungan() {
		return listStatusKunjungan;
	}
	public void setListStatusKunjungan(List<TmStatusKunjungan> listStatusKunjungan) {
		this.listStatusKunjungan = listStatusKunjungan;
	}
	
	public List<TmWaktuOperasi> getListWaktuOperasi() {
		return listWaktuOperasi;
	}
	public void setListWaktuOperasi(List<TmWaktuOperasi> listWaktuOperasi) {
		this.listWaktuOperasi = listWaktuOperasi;
	}
	public List<TmPelanggan> getListPelanggan() {
		return listPelanggan;
	}
	public void setListPelanggan(List<TmPelanggan> listPelanggan) {
		this.listPelanggan = listPelanggan;
	}
	public List<TmKategoriBarang> getListKategoriBarang() {
		return listKategoriBarang;
	}
	public void setListKategoriBarang(List<TmKategoriBarang> listKategoriBarang) {
		this.listKategoriBarang = listKategoriBarang;
	}
	public List<TmSubKategoriBarang> getListSubKategoriBarang() {
		return listSubKategoriBarang;
	}
	public void setListSubKategoriBarang(
			List<TmSubKategoriBarang> listSubKategoriBarang) {
		this.listSubKategoriBarang = listSubKategoriBarang;
	}
	public List<TmGroupRak> getListGroupRak() {
		return listGroupRak;
	}
	public void setListGroupRak(List<TmGroupRak> listGroupRak) {
		this.listGroupRak = listGroupRak;
	}
	public List<TmRak> getListRak() {
		return listRak;
	}
	public void setListRak(List<TmRak> listRak) {
		this.listRak = listRak;
	}
	public List<TmCompany> getListCompany() {
		return listCompany;
	}
	public void setListCompany(List<TmCompany> listCompany) {
		this.listCompany = listCompany;
	}
	public List<TmBrand> getListBrand() {
		return listBrand;
	}
	public void setListBrand(List<TmBrand> listBrand) {
		this.listBrand = listBrand;
	}
	
	
	public List<TmJabatanResponden> getListJabatanResponden() {
		return listJabatanResponden;
	}
	public void setListJabatanResponden(
			List<TmJabatanResponden> listJabatanResponden) {
		this.listJabatanResponden = listJabatanResponden;
	}
	private String massage;
	
	public String getMassage() {
		return massage;
	}
	public void setMassage(String massage) {
		this.massage = massage;
	}
	
	public List<TmOutlet> getListOutlets() {
		return listOutlets;
	}
	public void setListOutlets(List<TmOutlet> listOutlets) {
		this.listOutlets = listOutlets;
	}
	public List<TmKabupaten> getListKabupaten() {
		return listKabupaten;
	}
	public void setListKabupaten(List<TmKabupaten> listKabupaten) {
		this.listKabupaten = listKabupaten;
	}
	public List<TmKecamatan> getListKecamatan() {
		return listKecamatan;
	}
	public void setListKecamatan(List<TmKecamatan> listKecamatan) {
		this.listKecamatan = listKecamatan;
	}
	public List<TmKelurahan> getListKelurahan() {
		return listKelurahan;
	}
	public void setListKelurahan(List<TmKelurahan> listKelurahan) {
		this.listKelurahan = listKelurahan;
	}
	public List<TmKlasifikasiOutlet> getListKlasifikasiOutlet() {
		return listKlasifikasiOutlet;
	}
	public void setListKlasifikasiOutlet(
			List<TmKlasifikasiOutlet> listKlasifikasiOutlet) {
		this.listKlasifikasiOutlet = listKlasifikasiOutlet;
	}
	public List<TmStatusBangunan> getListStatusBangunan() {
		return listStatusBangunan;
	}
	public void setListStatusBangunan(List<TmStatusBangunan> listStatusBangunan) {
		this.listStatusBangunan = listStatusBangunan;
	}
	public List<TmTipeOutlet> getListTipeOutlet() {
		return listTipeOutlet;
	}
	public void setListTipeOutlet(List<TmTipeOutlet> listTipeOutlet) {
		this.listTipeOutlet = listTipeOutlet;
	}
	public List<TmJenisLokasi> getListJenisLokasi() {
		return listJenisLokasi;
	}
	public void setListJenisLokasi(List<TmJenisLokasi> listJenisLokasi) {
		this.listJenisLokasi = listJenisLokasi;
	}
	public List<TmAksesKunjungan> getListAksesKunjungan() {
		return listAksesKunjungan;
	}
	public void setListAksesKunjungan(List<TmAksesKunjungan> listAksesKunjungan) {
		this.listAksesKunjungan = listAksesKunjungan;
	}
	public List<TmFasilitas> getListFasilitas() {
		return listFasilitas;
	}
	public void setListFasilitas(List<TmFasilitas> listFasilitas) {
		this.listFasilitas = listFasilitas;
	}
	
}
