package id.qsolution.main;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import id.qsolution.global.GenericRespons;
import id.qsolution.global.GlobalVar;
import id.qsolution.models.DaftarOutletSurvey;
import id.qsolution.models.TmAksesKunjungan;
import id.qsolution.models.TmBrand;
import id.qsolution.models.TmCompany;
import id.qsolution.models.TmFasilitas;
import id.qsolution.models.TmJabatanResponden;
import id.qsolution.models.TmJenisLokasi;
import id.qsolution.models.TmJumlahCoc;
import id.qsolution.models.TmKabupaten;
import id.qsolution.models.TmKategoriBarang;
import id.qsolution.models.TmKategoriCompanyBrand;
import id.qsolution.models.TmKecamatan;
import id.qsolution.models.TmKelurahan;
import id.qsolution.models.TmKlasifikasiOutlet;
import id.qsolution.models.TmLuasBangunan;
import id.qsolution.models.TmOutlet;
import id.qsolution.models.TmPackage;
import id.qsolution.models.TmPelanggan;
import id.qsolution.models.TmPosm;
import id.qsolution.models.TmPromosiSku;
import id.qsolution.models.TmRak;
import id.qsolution.models.TmResult;
import id.qsolution.models.TmSku;
import id.qsolution.models.TmStatusBangunan;
import id.qsolution.models.TmStatusKunjungan;
import id.qsolution.models.TmSubKategoriBarang;
import id.qsolution.models.TmSumberProduk;
import id.qsolution.models.TmSurveyor;
import id.qsolution.models.TmTipeOutlet;
import id.qsolution.models.TmVarian;
import id.qsolution.models.TmVolum;
import id.qsolution.models.TmWaktuOperasi;
import id.qsolution.models.dao.DaftarOutletSurveyDao;
import id.qsolution.models.dao.TmAksesKunjunganDao;
import id.qsolution.models.dao.TmBrandDao;
import id.qsolution.models.dao.TmCompanyDao;
import id.qsolution.models.dao.TmFasilitasDao;
import id.qsolution.models.dao.TmGroupRakDao;
import id.qsolution.models.dao.TmJabatanRespondenDao;
import id.qsolution.models.dao.TmJenisLokasiDao;
import id.qsolution.models.dao.TmJumlahCocDao;
import id.qsolution.models.dao.TmKabupatenDao;
import id.qsolution.models.dao.TmKategoriBarangDao;
import id.qsolution.models.dao.TmKategoriCompanyBrandDao;
import id.qsolution.models.dao.TmKecamatanDao;
import id.qsolution.models.dao.TmKelurahanDao;
import id.qsolution.models.dao.TmKlasifikasiOutletDao;
import id.qsolution.models.dao.TmLuasBangunanDao;
import id.qsolution.models.dao.TmOutletDao;
import id.qsolution.models.dao.TmPackageDao;
import id.qsolution.models.dao.TmPelangganDao;
import id.qsolution.models.dao.TmPosmDao;
import id.qsolution.models.dao.TmPromosiSkuDao;
import id.qsolution.models.dao.TmRakDao;
import id.qsolution.models.dao.TmSkuDao;
import id.qsolution.models.dao.TmStatusBangunanDao;
import id.qsolution.models.dao.TmStatusKunjunganDao;
import id.qsolution.models.dao.TmSubKategoriBarangDao;
import id.qsolution.models.dao.TmSumberProdukDao;
import id.qsolution.models.dao.TmSurveyorDao;
import id.qsolution.models.dao.TmTipeOutletDao;
import id.qsolution.models.dao.TmVarianDao;
import id.qsolution.models.dao.TmVolumDao;
import id.qsolution.models.dao.TmWaktuOperasiDao;
import id.qsolution.models.dao.TtDKunjunganSurveyorOmsetKatagoriDao;
import id.qsolution.models.dao.TtDKunjunganSurveyorOutletPosmDao;
import id.qsolution.models.dao.TtDKunjunganSurveyorPhotoDao;
import id.qsolution.models.TmGroupRak;
import id.qsolution.respons.OutletsRespons;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class SyncActivity extends Activity {

	private Button btnDownload;
	private ProgressDialog progressDialog;
	private GenericRespons<TmResult> outlets = new OutletsRespons();
	private TmOutletDao outletDao;
	private TmSurveyor surveyor;
	private TmSurveyorDao surveyorDao;
	private TmKabupatenDao kabupatenDao;
	private TmKecamatanDao kecamatanDao;
	private TmKelurahanDao kelurahanDao;
	private TmFasilitasDao fasilitasOutletDao;
	private TmKlasifikasiOutletDao klasifikasiOutletDao;
	private TmStatusBangunanDao statusBangunanDao;
	private TmJenisLokasiDao jenisLokasiDao;
	private TmAksesKunjunganDao aksesKunjunganDao;
	private TmTipeOutletDao typeOutletDao;
	private TmJabatanRespondenDao jabatanRespondenDao;
	private TmWaktuOperasiDao waktuOperasiDao;
	private TmPelangganDao pelangganDao;
	private TmKategoriBarangDao kategoriBarangDao;
	private TmSubKategoriBarangDao subKategoriBarangDao;
	private TmGroupRakDao groupRakDao;
	private TmRakDao rakDao;
	private TmCompanyDao companyDao;
	private TmBrandDao brandDao;
	private int responsCode;
	private TmStatusKunjunganDao statusKunjunganDao;
	private TmSubKategoriBarangDao subKategoriBarang;
	private TmPosmDao posmDao;
	private TmSkuDao skuDao;
	private TmSumberProdukDao sumberDao;
	private TmPromosiSkuDao promosiDao;
	private TmKategoriCompanyBrandDao katagoriCompanyBrandDao;
	private TmLuasBangunanDao luasBangunanDao;
	private TmJumlahCoc jumlahCoc;
	private TmJumlahCocDao jumlahCocDao;
	private TmVolumDao volumDao;
	private TmPackageDao pkgDao;
	private TmVarianDao varianDao;
	private TmKategoriCompanyBrandDao kategoriCompanyBrand;
	private DaftarOutletSurveyDao daftarOutletSurveyDao;
	private TtDKunjunganSurveyorOmsetKatagoriDao transaksiOmsetKategoriDao;
	private TtDKunjunganSurveyorOutletPosmDao transaksiPosmOutletDao;
	private TtDKunjunganSurveyorPhotoDao transaksiKunjunganPhotoDao;
	private DaftarOutletSurveyDao daftarOutletDao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sync);
		setTitle("SYNCHRONOUS");
		btnDownload = (Button) findViewById(R.id.btnDownload);
		surveyor = new TmSurveyor();
		surveyor = (TmSurveyor) getIntent().getSerializableExtra("surveyor");
		daftarOutletDao = new DaftarOutletSurveyDao(getApplicationContext());
		surveyorDao = new TmSurveyorDao(getApplicationContext());
		outletDao = new TmOutletDao(getApplicationContext());
		kecamatanDao = new TmKecamatanDao(getApplicationContext());
		kabupatenDao = new TmKabupatenDao(getApplicationContext());
		kelurahanDao = new TmKelurahanDao(getApplicationContext());
		klasifikasiOutletDao = new TmKlasifikasiOutletDao(getApplication());
		fasilitasOutletDao = new TmFasilitasDao(getApplicationContext());
		subKategoriBarang = new TmSubKategoriBarangDao(getApplicationContext());
		statusBangunanDao = new TmStatusBangunanDao(getApplication());
		jenisLokasiDao = new TmJenisLokasiDao(getApplication());
		aksesKunjunganDao = new TmAksesKunjunganDao(getApplicationContext());
		typeOutletDao = new TmTipeOutletDao(getApplicationContext());
		statusKunjunganDao = new TmStatusKunjunganDao(getApplicationContext());
		jabatanRespondenDao = new TmJabatanRespondenDao(getApplicationContext());
		waktuOperasiDao = new TmWaktuOperasiDao(getApplicationContext());
		pelangganDao = new TmPelangganDao(getApplicationContext());
		kategoriBarangDao = new TmKategoriBarangDao(getApplicationContext());
		subKategoriBarangDao = new TmSubKategoriBarangDao(getApplicationContext());
		groupRakDao = new TmGroupRakDao(getApplicationContext());
		rakDao = new TmRakDao(getApplicationContext());
		companyDao = new TmCompanyDao(getApplicationContext());
		brandDao = new TmBrandDao(getApplicationContext());
		posmDao = new TmPosmDao(getApplicationContext());
		skuDao = new TmSkuDao(getApplicationContext());
		sumberDao = new TmSumberProdukDao(getApplicationContext());
		promosiDao = new TmPromosiSkuDao(getApplicationContext());
		katagoriCompanyBrandDao = new TmKategoriCompanyBrandDao(getApplicationContext());
		luasBangunanDao = new TmLuasBangunanDao(getApplicationContext());
		jumlahCocDao = new TmJumlahCocDao(getApplicationContext());
		volumDao = new TmVolumDao(getApplicationContext());
		pkgDao = new TmPackageDao(getApplicationContext());
		varianDao = new TmVarianDao(getApplicationContext());
		kategoriCompanyBrand = new TmKategoriCompanyBrandDao(getApplicationContext());
		daftarOutletSurveyDao = new DaftarOutletSurveyDao(getApplicationContext());
		transaksiOmsetKategoriDao = new TtDKunjunganSurveyorOmsetKatagoriDao(getApplicationContext());
		transaksiPosmOutletDao = new TtDKunjunganSurveyorOutletPosmDao(getApplicationContext());
		transaksiKunjunganPhotoDao = new TtDKunjunganSurveyorPhotoDao(getApplicationContext());
		
		surveyor = surveyorDao.listAll().get(0);
		
		tabDownload();
		/*Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		spec = tabHost
				.newTabSpec("Download")
				.setIndicator("Download",
						res.getDrawable(R.drawable.ic_launcher))
				.setContent(R.id.form1);
		tabHost.addTab(spec);

		spec = tabHost
				.newTabSpec("Upload")
				.setIndicator("Upload", res.getDrawable(R.drawable.ic_launcher))
				.setContent(R.id.form2);
		tabHost.addTab(spec);
		tabHost.setCurrentTab(0);
		
		tabDownload();*/
	}
	
	@Override
	public void onBackPressed() {
		finish();
	}
	
	

	private void tabDownload() {
		btnDownload.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			
				if (!isNetworkAvailable()) {
					Toast.makeText(getApplicationContext(), "Network not available", Toast.LENGTH_LONG).show();
				} else {
					daftarOutletDao.deleteAll();
					outletDao.deleteAll();
					kabupatenDao.deleteAll();
					kecamatanDao.deleteAll();
					kelurahanDao.deleteAll();
					typeOutletDao.deleteAll();
					klasifikasiOutletDao.deleteAll();
					statusBangunanDao.deleteAll();
					fasilitasOutletDao.deleteAll();
					jenisLokasiDao.deleteAll();
					subKategoriBarang.deleteAll();
					aksesKunjunganDao.deleteAll();
					jabatanRespondenDao.deleteAll();
					statusKunjunganDao.deleteAll();
					waktuOperasiDao.deleteAll();
					pelangganDao.deleteAll();
					kategoriBarangDao.deleteAll();
					groupRakDao.deleteAll();
					rakDao.deleteAll();
					companyDao.deleteAll();
					brandDao.deleteAll();
					skuDao.deleteAll();
					sumberDao.deleteAll();
					promosiDao.deleteAll();
					posmDao.deleteAll();
					luasBangunanDao.deleteAll();
					jumlahCocDao.deleteAll();
					volumDao.deleteAll();
					pkgDao.deleteAll();
					varianDao.deleteAll();
					daftarOutletSurveyDao.deleteAll();
					katagoriCompanyBrandDao.deleteAll();
					GlobalVar.LOGIN_URL = getString(R.string.download_data);
					if(URLIsReachable(GlobalVar.LOGIN_URL)){
						performSearch(surveyor.getUsername());
					} else{
						Toast.makeText(getApplicationContext(), "Service tidak ditemukan", Toast.LENGTH_LONG).show();
					}
				}
			}
		});
	}

	public boolean URLIsReachable(String urlString){
		try {
			URL url = new URL(urlString);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			responsCode = urlConnection.getResponseCode();
			urlConnection.disconnect();
			return responsCode != 200;
		} catch (MalformedURLException e) {
			Log.i("MalformedURLException ", e.getMessage());
			return false;
		} catch (IOException e) {
			Log.i("IOException ", e.getMessage());
			return false;
		}
	}
	
	protected void performSearch(String user) {
		try {
			progressDialog = ProgressDialog.show(SyncActivity.this,
					"Silakan tunggu", "Proses sedang berjalan", true, true);
			PerformUserSearchTask task = new PerformUserSearchTask();
			task.execute(user);
			progressDialog.setOnCancelListener(new CancelTaskOnCancelListener(
					task));
		} catch (Exception e) {
			longToast("Login Failure " + e.getMessage());
		}

	}

	private class CancelTaskOnCancelListener implements OnCancelListener {
		private AsyncTask<?, ?, ?> task;

		public CancelTaskOnCancelListener(AsyncTask<?, ?, ?> task) {
			this.task = task;
		}

		@Override
		public void onCancel(DialogInterface dialog) {
			if (task != null) {
				task.cancel(true);
			}
		}
	}

	private class PerformUserSearchTask extends
			AsyncTask<String, Void, TmResult> {

		@Override
		protected TmResult doInBackground(String... params) {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("username", params[0]));
			// nameValuePairs.add(new BasicNameValuePair("password",
			// params[1]));
			return outlets.find(nameValuePairs);
		}

		@Override
		protected void onPostExecute(final TmResult result) {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					try {
						if (progressDialog != null) {
							progressDialog.dismiss();
							progressDialog = null;
						}
						if (validate(result)) {
							for (TmOutlet outlet : result.getListOutlets()) {
								TmOutlet o = new TmOutlet();
								o.setKode(outlet.getKode());
								if(outletDao.listByExample(o).size() > 0){
									long i = outletDao.update(outlet);
								}else{
									long i = outletDao.insert(outlet);
								}
								// longToast(outlet.getAlamat());
							}
							for (TmKabupaten kabupaten : result.getListKabupaten()) {
								long i = kabupatenDao.insert(kabupaten);
							}
							for (TmKecamatan kecamatan : result.getListKecamatan()) {
								long i = kecamatanDao.insert(kecamatan);
							}
							for (TmKelurahan kelurahan : result.getListKelurahan()) {
								long i = kelurahanDao.insert(kelurahan);
							}
							
							for(TmTipeOutlet typeOutlet : result.getListTipeOutlet()){
								long i = typeOutletDao.insert(typeOutlet);
							}

							for (TmKlasifikasiOutlet klasifikasi : result.getListKlasifikasiOutlet()) {
								long i = klasifikasiOutletDao.insert(klasifikasi);
							}
							
							for (TmStatusBangunan statusBangunan : result.getListStatusBangunan()) {
								long i = statusBangunanDao.insert(statusBangunan);
							}
							
							for (TmFasilitas fasilitas : result.getListFasilitas()) {
								long i = fasilitasOutletDao.insert(fasilitas);
							}
							
							for (TmJenisLokasi jenisLokasi : result.getListJenisLokasi()){
								long i = jenisLokasiDao.insert(jenisLokasi);
							}
							
							for(TmAksesKunjungan aksesKunjungan : result.getListAksesKunjungan()){
								long i = aksesKunjunganDao.insert(aksesKunjungan);
							}
							
							for(TmJabatanResponden jabatanResponden : result.getListJabatanResponden()){
								long i = jabatanRespondenDao.insert(jabatanResponden);
							}
							
							for(TmStatusKunjungan jabatanResponden : result.getListStatusKunjungan()){
								long i = statusKunjunganDao.insert(jabatanResponden);
							}
							
							for(TmWaktuOperasi waktuOperasi : result.getListWaktuOperasi()){
								long i = waktuOperasiDao.insert(waktuOperasi);
							}
							
							for(TmPelanggan pelanggan : result.getListPelanggan()){
								long i = pelangganDao.insert(pelanggan);
							}
							
							for(TmKategoriBarang kategoriBarang : result.getListKategoriBarang()){
								long i = kategoriBarangDao.insert(kategoriBarang);
							}
							
							for(TmSubKategoriBarang subKategoriBarang : result.getListSubKategoriBarang()){
								long i = subKategoriBarangDao.insert(subKategoriBarang);
							}
							
							for(TmGroupRak groupRak: result.getListGroupRak()){
								long i = groupRakDao.insert(groupRak);
							}
							
							for(TmRak rak: result.getListRak()){
								long i = rakDao.insert(rak);
							}
							
							for(TmCompany company: result.getListCompany()){
								long i = companyDao.insert(company);
							}
							
							for(TmBrand brand: result.getListBrand()){
								long i = brandDao.insert(brand);
							}
							
							for(TmPosm posm : result.getListPosm()){
								long i = posmDao.insert(posm);
							}
							
							for(TmSku sku : result.getListSku()){
								long i = skuDao.insert(sku);
							}
							for(TmSumberProduk sumber : result.getListSumber()){
								long i = sumberDao.insert(sumber);
							}
							
							for(TmPromosiSku promosi : result.getListPromosi()){
								long i = promosiDao.insert(promosi);
							}
							
							for(TmKategoriCompanyBrand kagoriCompany : result.getListKatagoryCompanyBrand()){
								long i = katagoriCompanyBrandDao.insert(kagoriCompany);
							}
							
							for(TmLuasBangunan luasBangunan : result.getListLuasBangunan()){
								long i = luasBangunanDao.insert(luasBangunan);
							}
							
							for(TmJumlahCoc jumlahCoc : result.getListJumlahCoc()){
								long i = jumlahCocDao.insert(jumlahCoc);
							}
							
							for (TmVolum volume : result.getListVolum()) {
								long i =volumDao.insert(volume);
							}
							
							for (TmPackage pkg : result.getListPackage()) {
								long i =pkgDao.insert(pkg);
							}
							
							for(TmVarian varian : result.getListVarian()){
								long i = varianDao.insert(varian);
							}
							
							for(DaftarOutletSurvey outletSurvey : result.getListOutletSurvey()){
								long i = daftarOutletSurveyDao.insert(outletSurvey);
							}
							
							longToast("Data sukses didownload");
							finish();
						} else {
							longToast("Ada data yang kosong");
						}
					} catch (Exception e) {
						longToast("Error : " + e.getMessage());
					}
				}

				private boolean validate(TmResult result) {
					if(result.getListOutlets().size() > 0) return true;
					if(result.getListKabupaten().size() > 0) return true;
					if(result.getListKecamatan().size() > 0) return true;
					if(result.getListKelurahan().size() > 0) return true;
					if(result.getListKlasifikasiOutlet().size() > 0) return true;
					if(result.getListStatusBangunan().size() > 0) return true;
					if(result.getListTipeOutlet().size() > 0) return true;
					if(result.getListJenisLokasi().size() > 0) return true;
					if(result.getListAksesKunjungan().size() > 0) return true;
					if(result.getListFasilitas().size() > 0) return true;
					if(result.getListJabatanResponden().size() > 0) return true;
					if(result.getListWaktuOperasi().size() > 0) return true;
					if(result.getListPelanggan().size() > 0) return true;
					if(result.getListKategoriBarang().size() > 0) return true;
					if(result.getListSubKategoriBarang().size() > 0) return true;
					if(result.getListGroupRak().size() > 0) return true;
					if(result.getListRak().size() > 0) return true;
					if(result.getListCompany().size() > 0) return true;
					if(result.getListBrand().size() > 0) return true;
					if(result.getListVarian().size() > 0) return true;
					if(result.getListOutletSurvey().size() > 0) return true;
					return false;
				}
			});
		}
	}

	public void longToast(CharSequence message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}

	public boolean isNetworkAvailable() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		// if no network is available networkInfo will be null
		// otherwise check if we are connected
		if (networkInfo != null && networkInfo.isConnected()) {
			return true;
		}
		return false;
	}

}
