package id.qsolution.main;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import id.qsolution.adapter.PhotoRakAdapter;
import id.qsolution.adapter.PosmOutletAdapter;
import id.qsolution.models.DaftarOutletSurvey;
import id.qsolution.models.TmBrand;
import id.qsolution.models.TmCompany;
import id.qsolution.models.TmJabatanResponden;
import id.qsolution.models.TmKategoriBarang;
import id.qsolution.models.TmKategoriCompanyBrand;
import id.qsolution.models.TmOutlet;
import id.qsolution.models.TmPackage;
import id.qsolution.models.TmPosm;
import id.qsolution.models.TmRak;
import id.qsolution.models.TmSku;
import id.qsolution.models.TmSubKategoriBarang;
import id.qsolution.models.TmSurveyor;
import id.qsolution.models.TmVolum;
import id.qsolution.models.TtDKunjunganSurveyorOutletPosm;
import id.qsolution.models.TtDKunjunganSurveyorRak;
import id.qsolution.models.TtDKunjunganSurveyorRakFoto;
import id.qsolution.models.TtDKunjunganSurveyorRakPosm;
import id.qsolution.models.TtDKunjunganSurveyorRakSku;
import id.qsolution.models.TtMKunjunganSurveyor;
import id.qsolution.models.TtPhoto;
import id.qsolution.models.dao.TmBrandDao;
import id.qsolution.models.dao.TmCompanyDao;
import id.qsolution.models.dao.TmJabatanRespondenDao;
import id.qsolution.models.dao.TmKategoriBarangDao;
import id.qsolution.models.dao.TmKategoriCompanyBrandDao;
import id.qsolution.models.dao.TmPackageDao;
import id.qsolution.models.dao.TmPosmDao;
import id.qsolution.models.dao.TmRakDao;
import id.qsolution.models.dao.TmSubKategoriBarangDao;
import id.qsolution.models.dao.TmVolumDao;
import id.qsolution.models.dao.TtDKunjunganSurveyorOutletPosmDao;
import id.qsolution.models.dao.TtDKunjunganSurveyorRakDao;
import id.qsolution.models.dao.TtDKunjunganSurveyorRakFotoDao;
import id.qsolution.models.dao.TtDKunjunganSurveyorRakPosmDao;
import id.qsolution.models.dao.TtDKunjunganSurveyorRakSkuDao;
import id.qsolution.util.NamaFile;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class TabSurveySku extends TabActivity {

	private TmSurveyor surveyor;
	private TmOutlet outlet;
	private TabHost tabHost;
	private String tanggal;
	private String jamMulai;
	private TmJabatanResponden jabatan;
	private TmJabatanRespondenDao jabatanDao;
	private List<TmJabatanResponden> listJabatan;
	private Spinner spnJabatan;
	private String[] lsJabatan;
	private Spinner spnKatagori;
	private TmKategoriBarang kategoriBarang;
	private TmKategoriBarangDao kategoriDao;
	private ArrayList<TmKategoriBarang> listKategoriBarang;
	private Spinner spnSubKatagori;
	private String[] lsKategoriBarang;
	private TmSubKategoriBarang subKategori;
	private TmSubKategoriBarangDao subKategoriDao;
	private ArrayList<TmSubKategoriBarang> listSubKategori;
	private List<TmSubKategoriBarang> listSubKategoriBarang;
	private String[] lsSubKategoriBarang;
	private TmCompany company;
	private TmCompanyDao companyDao;
	private List<TmCompany> listCompany;
	private String[] lsCompany;
	private Spinner spnCompany;
	private Spinner spnBrand;
	private TmBrand brandRak;
	private TmBrandDao brandRakDao;
	private ArrayList<TmBrand> listRakBrand;
	private Spinner spnPosm;
	private TmPosm posm;
	private TmPosmDao posmDao;
	private ArrayList<TmPosm> listPosm;
	private String[] lsPosm;
	TmKategoriCompanyBrand ktcb;
	TmKategoriCompanyBrandDao ktcbDao;
	private Button btnTambahPosm;
	private EditText txtJumlah;
	private ListView lsOutletPosm;
	private TtDKunjunganSurveyorOutletPosm outletPosm;
	private TtDKunjunganSurveyorOutletPosmDao outletPosmDao;
	private PosmOutletAdapter adapter;
	private List<TtDKunjunganSurveyorOutletPosm> posms = new ArrayList<TtDKunjunganSurveyorOutletPosm>();
	private EditText txtNamaResponden;
	private EditText txtOmzet;
	private Button btnNext;
	private TtDKunjunganSurveyorRakPosm rakPosm;
	private TtDKunjunganSurveyorRakPosmDao rakPosmDao;
	private TtDKunjunganSurveyorRak rak;
	private String noKunjungan;
	private Button btnAddSku;
	private TmSku sku;
	private TextView txtProduk;
	private TextView txtBrand;
	private TmPackage paket;
	private TmPackageDao paketDao;
	private ArrayList<TmPackage> listPaket;
	private String[] lsPaket;
	private TmVolum volum;
	private TmVolumDao volumDao;
	private ArrayList<TmVolum> listVolum;
	private String[] lsVolum;
	private Spinner spnQuestion;
	private boolean label = false;
	private Button btnTambahPromosi;
	private TtDKunjunganSurveyorRakFotoDao photoRakDao;
	private TtDKunjunganSurveyorRakFoto photoRak;
	private ArrayList<TtDKunjunganSurveyorRakFoto> listPhotoRak;
	private PhotoRakAdapter fotoAdapter;
	private ListView viewPhoto;
	private Button btnPhoto;
	private String path;
	private Button btnBack;
	private Button simpanSku;
	private EditText txtJumlahFacing;
	private EditText txtHarga;
	private EditText txtJumlahUnit;
	private Button btnResult;
	private TextView lblLabel;
	private TmKategoriCompanyBrand skb;
	private ArrayList<TmKategoriCompanyBrand> lis;
	private TmKategoriCompanyBrandDao skbDao;
	private TextView lblCompanySku;
	private TextView lblBrandSku;
	private TtMKunjunganSurveyor kunjungan;
	private boolean locked;
	private String xcoord;
	private String ycoord;
	private DaftarOutletSurvey kategori;
	private ArrayList<TmPosm> listPosmTmp;
	private AutoCompleteTextView txtSearch;
	private static final int CAMERA_REQUEST = 300;
	private ArrayAdapter<String> arrayAdapter;
	private String[] lsBrandRak;
	private TmKategoriCompanyBrand tkcb;
	private TmKategoriCompanyBrandDao tkcbDao;
	private TmBrand brand;
	private TmCompany comp;
	private int iCompany = 0;
	private int iBrand = 0;
	private int iSubKategori= 0;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("SURVEY RAK");
		setTheme(android.R.style.Theme);
		setContentView(R.layout.activity_tab_audit_sku);
		//jamMulai = new SimpleDateFormat("HH:mm:ss").format(new Date());
		Log.d("Activity ", this.getClass().getName());
		ktcb = new TmKategoriCompanyBrand();
		ktcbDao = new TmKategoriCompanyBrandDao(getApplicationContext());
		outletPosm = new TtDKunjunganSurveyorOutletPosm();
		outletPosmDao = new TtDKunjunganSurveyorOutletPosmDao(getApplicationContext());
		tkcbDao = new TmKategoriCompanyBrandDao(getApplicationContext());
		brandRakDao = new TmBrandDao(getApplicationContext());
		surveyor = (TmSurveyor) getIntent().getSerializableExtra("surveyor");
		outlet = (TmOutlet) getIntent().getSerializableExtra("outlet");
		rak = (TtDKunjunganSurveyorRak) getIntent().getSerializableExtra("rak");
		kategori = (DaftarOutletSurvey) getIntent().getSerializableExtra("kategori");
		kunjungan = (TtMKunjunganSurveyor) getIntent().getSerializableExtra("kunjungan");
		
		brand = (TmBrand) getIntent().getSerializableExtra("brand");
		comp = (TmCompany) getIntent().getSerializableExtra("company");
		
		locked = getIntent().getBooleanExtra("locked", false);
		xcoord = getIntent().getStringExtra("xcoord");
		ycoord = getIntent().getStringExtra("ycoord");
		
		Resources res = getResources(); // Resource object to get Drawables
		tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		// Initialize a TabSpec for each tab and add it to the TabHost
		// crateKodeKunjungan();
		spec = tabHost
				.newTabSpec("Prod")
				.setIndicator("Prod",
						res.getDrawable(R.drawable.ic_action_user))
				.setContent(R.id.form1);
		tabHost.addTab(spec);

		spec = tabHost
				.newTabSpec("SKU")
				.setIndicator("SKU",
						res.getDrawable(R.drawable.ic_action_paste))
				.setContent(R.id.form2);
		tabHost.addTab(spec);

		spec = tabHost
				.newTabSpec("Foto")
				.setIndicator("Foto",
						res.getDrawable(R.drawable.ic_action_photo))
				.setContent(R.id.form3);
		tabHost.addTab(spec);
		tabHost.setCurrentTab(0);
		brandRak = new TmBrand();
		brandRakDao = new TmBrandDao(getApplicationContext());
		initView();
		//if(isBranded(rak)){
		loadSearch();	
		//} else{
		//	txtSearch.setVisibility(View.GONE);
		//}
		loadForm();
		loadKategori();
		loadPosm();
		createListener();
		loadQuestion();
		viewPhoto();
	}
	
	private String getBrand(String kodeBrand) {
		String result = "";
		try {
			TmBrand brand = new TmBrand();
			brand.setKode(kodeBrand);
			TmBrandDao brandDao = new TmBrandDao(getApplicationContext());
			brand = brandDao.getByExample(brand);
			result = brand.getNama();
			
		} catch (Exception e) {
			result = "";
		}
		return result;
	}

	private boolean isBranded(TtDKunjunganSurveyorRak rak2) {
		TmRak r = new TmRak();
		TmRakDao rDao = new TmRakDao(getApplicationContext());
		r.setKode(rak2.getKodeRak());
		try {
			r = rDao.getByExample(r);
			if(r.getBranded().equals("01")){
				return true;
			}else{
				return false;
			}
			
		} catch (Exception e) {
			return false;
		}
	}

	private void loadSearch() {
		TmBrandDao BrandDao = new TmBrandDao(getApplicationContext());
		TmSubKategoriBarang subKat = new TmSubKategoriBarang();
		TmSubKategoriBarangDao subKatDao = new TmSubKategoriBarangDao(
				getApplicationContext());
		subKat.setKode_kategori(kategori.getKodeKategori());
		TmKategoriCompanyBrand kcb = new TmKategoriCompanyBrand();
		TmKategoriCompanyBrandDao kcbDao = new TmKategoriCompanyBrandDao(
				getApplicationContext());
		List<TmBrand> listBrand = new ArrayList<TmBrand>();

		for (TmSubKategoriBarang subKategori : subKatDao.listByExample(subKat)) {
			kcb = new TmKategoriCompanyBrand();
			kcb.setKode_sub_kategori(subKategori.getKode());
			for (TmKategoriCompanyBrand bra : kcbDao.listByExample(kcb)) {
				TmBrand brand = new TmBrand();
				brand.setKode(bra.getKode_brand());
				brand = BrandDao.getByExample(brand);
				listBrand.add(brand);
			}
		}
		String[] brand = new String[listBrand.size()];
		for (int i = 0; i < listBrand.size(); i++) {
			brand[i] = listBrand.get(i).getNama();
		}
		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, brand);
		txtSearch.setThreshold(3);
		txtSearch.setAdapter(arrayAdapter);
		txtSearch.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long rowId) {
				//Toast.makeText(getApplicationContext(), "Selected Item "+arrayAdapter.getItem(position), Toast.LENGTH_LONG).show();
				String selection = (String) parent.getItemAtPosition(position);
				TmCompany perusahaan = new TmCompany();
				TmBrand brand = new TmBrand();
				brandRakDao = new TmBrandDao(getApplicationContext());
				brand.setNama(selection);
				brand = brandRakDao.getByExample(brand);
				perusahaan.setKode(brand.getKodeCompany());
				companyDao = new TmCompanyDao(getApplicationContext());
				perusahaan = companyDao.getByExample(perusahaan);
				setSpinner(brand, perusahaan);
			}

			private void setSpinner(TmBrand brand, TmCompany perusahaan) {
				int index = 0;
				brandRak = new TmBrand();
				brandRak.setKodeCompany(perusahaan.getKode());
				listRakBrand = new ArrayList<TmBrand>();
				listRakBrand = (ArrayList<TmBrand>) brandRakDao
						.listByExample(brandRak);
				lsBrandRak = new String[listRakBrand.size()];
				for (int i = 0; i < listRakBrand.size(); i++) {
					lsBrandRak[i] = listRakBrand.get(i).getNama();
					if(brand.getNama().equals(listRakBrand.get(i).getNama())){
						index = i;
					}
				}
				ArrayAdapter<String> adapterBrandRak = new ArrayAdapter<String>(
						TabSurveySku.this,
						android.R.layout.simple_spinner_item, lsBrandRak);
				adapterBrandRak
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spnBrand.setAdapter(adapterBrandRak);
				spnBrand.setSelection(index);
				spnBrand.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1, int index, long arg3) {
						brandRak = new TmBrand();
						brandRak = listRakBrand.get(index);
						txtBrand.setText(brandRak.getNama());
						txtProduk.setText("");
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
					}
				});
				
				// Load Company
				listCompany = new ArrayList<TmCompany>();
				company = new TmCompany();
				company.setKode(perusahaan.getKode());
				listCompany = (ArrayList<TmCompany>) companyDao
						.listByExample(company);
				lsCompany = new String[listCompany.size()];

				for (int i = 0; i < listCompany.size(); i++) {
					lsCompany[i] = listCompany.get(i).getNama();
					Toast.makeText(getApplicationContext(), "Company "+ listCompany.get(i).getNama(), Toast.LENGTH_LONG).show();
				}

				if (listCompany.size() > 0) {
					spnBrand.setVisibility(View.VISIBLE);
					lblBrandSku.setVisibility(View.VISIBLE);
					spnCompany.setVisibility(View.VISIBLE);
					lblCompanySku.setVisibility(View.VISIBLE);
				} else {
					spnBrand.setVisibility(View.GONE);
					lblBrandSku.setVisibility(View.GONE);
					spnCompany.setVisibility(View.GONE);
					lblCompanySku.setVisibility(View.GONE);
				}

				ArrayAdapter<String> adapterCompany = new ArrayAdapter<String>(
						TabSurveySku.this,
						android.R.layout.simple_spinner_item, lsCompany);
				adapterCompany
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spnCompany.setAdapter(adapterCompany);
				spnCompany
						.setOnItemSelectedListener(new OnItemSelectedListener() {

							@Override
							public void onItemSelected(AdapterView<?> arg0,
									View arg1, int index, long arg3) {
								company = new TmCompany();
								company = listCompany.get(index);
							}

							@Override
							public void onNothingSelected(AdapterView<?> arg0) {
							}
						});
				
				tkcb = new TmKategoriCompanyBrand();
				tkcb.setKode_company(perusahaan.getKode());
				tkcb.setKode_brand(brand.getKode());
				tkcb = tkcbDao.listByExample(tkcb).get(0);
				listSubKategoriBarang.clear();
				subKategori = new TmSubKategoriBarang();
				subKategori.setKode(tkcb.getKode_sub_kategori());
				listSubKategoriBarang = subKategoriDao.listByExample(subKategori);
				lsSubKategoriBarang = new String[listSubKategoriBarang.size()];
				for (int i = 0; i < listSubKategoriBarang.size(); i++) {
					lsSubKategoriBarang[i] = listSubKategoriBarang.get(i)
							.getNama();
				}
				ArrayAdapter<String> adapterSubKategori = new ArrayAdapter<String>(
						TabSurveySku.this,
						android.R.layout.simple_spinner_item,
						lsSubKategoriBarang);
				adapterSubKategori
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spnSubKatagori.setAdapter(adapterSubKategori);
				spnSubKatagori
						.setOnItemSelectedListener(new OnItemSelectedListener() {
							@Override
							public void onItemSelected(AdapterView<?> arg0,
									View arg1, int index, long arg3) {
								subKategori = new TmSubKategoriBarang();
								subKategori = listSubKategoriBarang.get(index);
							}

							@Override
							public void onNothingSelected(AdapterView<?> arg0) {
							}
						});
			}
		});
		
	}

	private void viewPhoto() {
		photoRak = new TtDKunjunganSurveyorRakFoto();
		photoRakDao = new TtDKunjunganSurveyorRakFotoDao(getApplicationContext());
		listPhotoRak = new ArrayList<TtDKunjunganSurveyorRakFoto>();
		photoRak.setKodeKunjungan(outlet.getKode() +kategori.getKodeKategori()+ new SimpleDateFormat("ddMMyy").format(new Date()));
		photoRak.setNomorUrut(rak.getNomorUrut());
		listPhotoRak = (ArrayList<TtDKunjunganSurveyorRakFoto>) photoRakDao.listByExample(photoRak);
		fotoAdapter = new PhotoRakAdapter(TabSurveySku.this, listPhotoRak);
		viewPhoto.setAdapter(fotoAdapter);
		fotoAdapter.notifyDataSetChanged();
		viewPhoto.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int i, long index) {
				try {
					photoRak = listPhotoRak.get(i);
					photoRakDao.delete(photoRak.getId());
					File file = new File(photoRak.getNamaFile());
					file.delete();
					Toast.makeText(getApplicationContext(),
							"Foto rak" + photoRak.getNamaFile() +" telah dihapus", Toast.LENGTH_LONG)
							.show();
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(),
							"Error " + e.getMessage(), Toast.LENGTH_LONG)
							.show();
				}
				viewPhoto();
				return false;
			}
		});

		viewPhoto.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				TtPhoto foto = (TtPhoto) fotoAdapter.getItem(position);
				LayoutInflater inflater = getLayoutInflater();
				View layout = inflater.inflate(R.layout.toast_img_layout,
						(ViewGroup) findViewById(R.id.toast_layout_root));
				ImageView image = (ImageView) layout.findViewById(R.id.image);
				image.setImageDrawable(Drawable.createFromPath(foto.getNama()));
				Toast toast = new Toast(getApplicationContext());
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setDuration(Toast.LENGTH_SHORT);
				toast.setView(layout);
				toast.show();

			}
		});
	}

	private void loadQuestion() {
		Resources r = getResources();
		String[] lsQuestion = r.getStringArray(R.array.question);
		ArrayAdapter<String> adapterQuestion = new ArrayAdapter<String>(
				TabSurveySku.this, android.R.layout.simple_spinner_item,
				lsQuestion);
		adapterQuestion
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnQuestion.setAdapter(adapterQuestion);
		spnQuestion.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if (arg2 == 0)
					label = true;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public void onBackPressed() {

	}

	
	private boolean validateSku() {

		if (isNullOrEmpty(txtJumlahUnit)) {
			Toast.makeText(getApplicationContext(), "Jumlah unit darus diisi",
					Toast.LENGTH_LONG).show();
			return false;
		}

		if (isNullOrEmpty(txtHarga)) {
			Toast.makeText(getApplicationContext(), "Jumlah harga harus diisi",
					Toast.LENGTH_LONG).show();
			return false;
		}

		if (isNullOrEmpty(txtJumlahFacing)) {
			Toast.makeText(getApplicationContext(),
					"Jumlah facing harus diisi", Toast.LENGTH_LONG).show();
			return false;
		}
		
		if(getInt(txtJumlahUnit) < getInt(txtJumlahFacing)){
			Toast.makeText(getApplicationContext(),
					"Jumlah facing melebihi jumlah unit", Toast.LENGTH_LONG).show();
			return false;
		}
		
		if (isEmptySku()) {
			Toast.makeText(getApplicationContext(),
					"Sku harus diisi terlebih dahulu", Toast.LENGTH_LONG).show();
			return false;
		}

		return true;

	}

	private int getInt(EditText text) {
		int result = 0;
		try {
			result =  Integer.valueOf(text.getText().toString());
		} catch (Exception e) {
			return result;
		}
		return result;
	}

	private boolean isEmptySku() {
		try {
			if (sku == null)
				return true;
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	private void createListener() {
		simpanSku.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (validateSku()) {
					TtDKunjunganSurveyorRakSku rakSku = new TtDKunjunganSurveyorRakSku();
					TtDKunjunganSurveyorRakSkuDao rakSkuDao = new TtDKunjunganSurveyorRakSkuDao(getApplicationContext());
					rakSku.setKode(outlet.getKode());
					rakSku.setKodeSku(sku.getKode());
					rakSku.setKodeKunjungan(outlet.getKode() +kategori.getKodeKategori()+ new SimpleDateFormat("ddMMyy").format(new Date()));
					rakSku.setJumlahUnit(Integer.valueOf(txtJumlahUnit.getText().toString()));
					rakSku.setJumlahFacing(Integer.valueOf(txtJumlahFacing.getText().toString()));
					rakSku.setHargaJual(Integer.valueOf(txtHarga.getText().toString().replace(",", "")));
					if(outlet.getKode().substring(0, 2).equals("MT")){
						if(spnQuestion.getSelectedItemPosition() == 0){
							rakSku.setLblPrice(true);
						}else{
							rakSku.setLblPrice(false);
						}
					}
					//if(rakSku.setLblPrice(lblPrice))
					rakSku.setKodePackage(sku.getKodePackage());
					rakSku.setKodeVolum(sku.getKodeVolum());
					rakSku.setNomorUrut(rak.getNomorUrut());
					rakSku.setNamaSku(sku.getDeskripsi());
					rakSkuDao.insert(rakSku);
					txtBrand.setText("");
					txtProduk.setText("");
					txtJumlahFacing.setText("");
					txtJumlahUnit.setText("");
					txtHarga.setText("");
					rakSku.setKodePackage(sku.getKodePackage());
					rakSku.setKodeVolum(sku.getKodeVolum());
					Toast.makeText(getApplicationContext(),
							"SKU berhasil disimpan", Toast.LENGTH_LONG)
							.show();
				}
			}
		});

		btnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tabHost.setCurrentTab(0);
			}
		});

		btnResult.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(TabSurveySku.this, TabListAuditProduk.class);
				intent.putExtra("surveyor", surveyor);
				intent.putExtra("outlet", outlet);
				intent.putExtra("sku", sku);
				intent.putExtra("kunjungan", kunjungan);
				intent.putExtra("kategori", kategori);
				intent.putExtra("rak", rak);
				intent.putExtra("locked", locked);
				intent.putExtra("xcoord", xcoord);
				intent.putExtra("ycoord", ycoord);
				startActivityForResult(intent, 100);
			}
		});

		btnPhoto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					Intent cameraIntent = new Intent(
							android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
					startActivityForResult(cameraIntent, CAMERA_REQUEST);

				} catch (Exception e) {
					Log.i(this.getClass().getName(), e.getMessage());
				}
			}
		});

		btnTambahPromosi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(validatePromo()){
					Intent intent = new Intent(TabSurveySku.this,
							PromosiActivity.class);
					intent.putExtra("surveyor", surveyor);
					intent.putExtra("outlet", outlet);
					intent.putExtra("sku", sku);
					intent.putExtra("kunjungan", kunjungan);
					intent.putExtra("kategori", kategori);
					intent.putExtra("rak", rak);
					intent.putExtra("locked", locked);
					intent.putExtra("xcoord", xcoord);
					intent.putExtra("ycoord", ycoord);
					startActivityForResult(intent, 100);
				}
			}

			private boolean validatePromo() {
				if (isEmptySku()) {
					Toast.makeText(getApplicationContext(),
							"Sku harus diisi terlebih dahulu", Toast.LENGTH_LONG)
							.show();
					return false;
				}
				return true;
			}
		});

		btnTambahPosm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (validatePosm()) {
					
					rakPosm = new TtDKunjunganSurveyorRakPosm();
					rakPosmDao = new TtDKunjunganSurveyorRakPosmDao(getApplicationContext());
					rakPosm.setKodeKunjungan(outlet.getKode() +kategori.getKodeKategori()+ new SimpleDateFormat("ddMMyy").format(new Date()));
					rakPosm.setKodePosm(posm.getKode());
					rakPosm.setNomorUrut(rak.getNomorUrut());
					if(rakPosmDao.listByExample(rakPosm).size()>0){
						rakPosm.setKode(outlet.getKode());
						rakPosm.setKodeBrand(isEmptyBrand(brandRak.getKode()));
						rakPosm.setJumlah(Integer.valueOf(txtJumlah.getText().toString()));
						rakPosm.setKodeKunjungan(outlet.getKode() +kategori.getKodeKategori()+ new SimpleDateFormat("ddMMyy").format(new Date()));
						rakPosm.setNomorUrut(rak.getNomorUrut());
						rakPosm.setKodePosm(isEmptyBrand(posm.getKode()));
						rakPosm.setNamaBrand(brandRak.getNama());
						rakPosm.setNamaPosm(posm.getNama());
						rakPosmDao.update(rakPosm);
						Toast.makeText(getApplicationContext(),
								"Posm rak berhasil diupdate ", Toast.LENGTH_LONG)
								.show();
					}else{
						rakPosm.setKode(outlet.getKode());
						rakPosm.setKodeBrand(isEmptyBrand(brandRak.getKode()));
						rakPosm.setJumlah(Integer.valueOf(txtJumlah.getText().toString()));
						rakPosm.setKodeKunjungan(outlet.getKode() +kategori.getKodeKategori()+ new SimpleDateFormat("ddMMyy").format(new Date()));
						rakPosm.setNomorUrut(rak.getNomorUrut());
						rakPosm.setKodePosm(isEmptyBrand(posm.getKode()));
						rakPosm.setNamaBrand(brandRak.getNama());
						rakPosm.setNamaPosm(posm.getNama());
						rakPosmDao.insert(rakPosm);
						Toast.makeText(getApplicationContext(),
								"Posm rak berhasil disimpan ", Toast.LENGTH_LONG)
								.show();
					}
					txtJumlah.setText("");
					
				}
			}
		});
		
		

		btnNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tabHost.setCurrentTab(1);
			}
		});

		btnAddSku.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
		
				Intent intent = new Intent(TabSurveySku.this, ProductArcivity.class);
				intent.putExtra("surveyor", surveyor);
				intent.putExtra("outlet", outlet);
				intent.putExtra("brand", brandRak);
				intent.putExtra("kunjungan", kunjungan);
				intent.putExtra("rak", rak);
				intent.putExtra("kategori", kategori);
				intent.putExtra("subkategori", subKategori);
				intent.putExtra("locked", locked);
				intent.putExtra("xcoord", xcoord);
				intent.putExtra("ycoord", ycoord);
				startActivityForResult(intent, 200);
			}
		});
	}
	
	private String isEmptyBrand(String kodeBrand) {
		String result = "00";
		try {
			if(!kodeBrand.equals("") || kodeBrand != null){
				result = kodeBrand;
			}
		} catch (Exception e) {
			return result;
		}
		return result;
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 100:
			try {
				surveyor = (TmSurveyor) data.getSerializableExtra("surveyor");
				outlet = (TmOutlet) data.getSerializableExtra("outlet");
				sku = (TmSku) data.getSerializableExtra("sku");
				kunjungan = (TtMKunjunganSurveyor) data.getSerializableExtra("kunjungan");
				kategori = (DaftarOutletSurvey) data.getSerializableExtra("kategori");
				kategoriBarang = (TmKategoriBarang) data.getSerializableExtra("kategoriBarang");
				rak = (TtDKunjunganSurveyorRak) data.getSerializableExtra("rak");
				xcoord = data.getStringExtra("xcoord");
				ycoord = data.getStringExtra("ycoord");
				locked = data.getBooleanExtra("locked", false);
				//Toast.makeText(getApplicationContext(), "Kunjungan "+kunjungan.getKodeOutlet(), Toast.LENGTH_LONG).show();
			} catch (Exception e) {
				Log.d("Error ", e.getMessage());
			}
			break;
			
		case 200:
			try {
				sku = (TmSku) data.getSerializableExtra("sku");
				brandRak = getBrand(sku);
				/*brandRak.setKode(sku.getKodeBrand());			
				brandRak = brandRakDao.getByExample(brandRak);*/
				outlet = (TmOutlet) data.getSerializableExtra("outlet");
				surveyor = (TmSurveyor) data.getSerializableExtra("surveyor");
				kunjungan = (TtMKunjunganSurveyor) data.getSerializableExtra("kunjungan");
				xcoord = data.getStringExtra("xcoord");
				ycoord = data.getStringExtra("ycoord");
				locked = data.getBooleanExtra("locked", false);
				txtProduk.setText(sku.getDeskripsi());
				txtBrand.setText(brandRak.getNama());
				/*if (sku == null) {
					txtProduk.setText("");
				} else {
					txtProduk.setText(sku.getDeskripsi());
				}*/
				//Toast.makeText(getApplicationContext(), "Kunjungan "+kunjungan.getKodeOutlet(), Toast.LENGTH_LONG).show();
			} catch (Exception e) {
				Log.i("error select product ", e.getMessage());
				txtProduk.setText("");
				txtBrand.setText("");
			}

			break;

		case 300:
			try {
				if(data != null){
					Bitmap img = (Bitmap) data.getExtras().get("data");
					Uri selectedImageUri = data.getData();
					path = getRealPathFromURI(selectedImageUri);
					final EditText input = new EditText(TabSurveySku.this);
					AlertDialog.Builder alert = new AlertDialog.Builder(TabSurveySku.this);
					alert.setTitle("Diskripsi Foto Rak"); // Set Alert dialog//
					alert.setMessage("Silakan Tulis Diskripsi Foto Rak"); // Message here
					alert.setView(input);
					alert.setPositiveButton("Simpan",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,int whichButton) {
									try {
										String fileName = changeFileName(path);
										photoRak =new TtDKunjunganSurveyorRakFoto();
										photoRak.setKodeKunjungan(outlet.getKode() +kategori.getKodeKategori()+ new SimpleDateFormat("ddMMyy").format(new Date()));
										photoRak.setDeskripsi(input.getText().toString());
										photoRak.setNamaFile(fileName);
										photoRak.setKodeOutlet(outlet.getKode());
										photoRak.setNomorUrut(rak.getNomorUrut());
										photoRakDao.insert(photoRak);
										viewPhoto();
										//Toast.makeText(getApplicationContext(), "Kunjungan "+kunjungan.getKodeOutlet(), Toast.LENGTH_LONG).show();
										//Toast.makeText(getApplicationContext(), "Foto " + path + " berhasil disimpan", Toast.LENGTH_LONG).show();
									} catch (Exception e) {
										Log.i("onSave", e.getMessage());
									}
								}

								private String changeFileName(String path) {
									NamaFile myHomePage = new NamaFile(path, '/', '.');
									TelephonyManager manager = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
									String result = myHomePage.path() +"/" +myHomePage.filename().replace("IMG", manager.getDeviceId());
									File oldfile =new File(path);
									File newfile =new File(result);
									oldfile.renameTo(newfile);
									return result;
								}
							});
					alert.setNegativeButton("Batal",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									try {
										File file = new File(path);
										file.delete();
									} catch (Exception e) {
										e.printStackTrace();
									}
									dialog.cancel();
								}
							}); // End of alert.setNegativeButton
					AlertDialog alertDialog = alert.create();
					alertDialog.show();
					
				} else{
					Toast.makeText(getApplicationContext(), "Ambil foto dibatalkan ",
							Toast.LENGTH_LONG).show();
				}
				
			} catch (Exception e) {
				Toast.makeText(getApplicationContext(), "error " + e.getMessage(), Toast.LENGTH_LONG).show();
			}

			break;

		default:
			break;
		}
	}

	private TmBrand getBrand(TmSku sku2) {
		TmBrand b = new TmBrand();
		try {
			b.setKode(sku2.getKodeBrand());
			b = brandRakDao.getByExample(b);
		} catch (Exception e) {
			return b;
		}
		
		return b;
	}

	private String getRealPathFromURI(Uri contentUri) {
		Uri uri = null;
		String photoPath = "";
		try {
			Cursor cursor = getContentResolver().query(
					Media.EXTERNAL_CONTENT_URI,
					new String[] { Media.DATA, Media.DATE_ADDED,
							MediaStore.Images.ImageColumns.ORIENTATION },
					Media.DATE_ADDED, null, "date_added ASC");
			if (cursor != null && cursor.moveToFirst()) {
				do {
					uri = contentUri.parse(cursor.getString(cursor
							.getColumnIndex(Media.DATA)));
					photoPath = uri.toString();
				} while (cursor.moveToNext());
				cursor.close();
			}
		} catch (Exception e) {
			return contentUri.getPath();
		}
		return photoPath;
	}

	private boolean validatePosm() {
		if (isNullOrEmpty(txtJumlah)) {
			Toast.makeText(getApplicationContext(), "Jumlah Harus Diisi",
					Toast.LENGTH_LONG).show();
			return false;
		}

		/*
		 * if(isNullOrEmpty(txtOmzet)){ Toast.makeText(getApplicationContext(),
		 * "Omset Harus Diisi", Toast.LENGTH_LONG).show(); return false; }
		 */

		return true;
	}

	private boolean isNullOrEmpty(EditText txt) {
		if (txt.getText().toString() == null
				|| txt.getText().toString().equals("")) {
			return true;
		}
		return false;
	}
	
	private boolean isNullOrEmpty(String txt) {
		
		try {
			if(txt.equals("") || txt == null) return true;
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	
	private void loadKategori() {
		kategoriBarang = new TmKategoriBarang();
		kategoriDao = new TmKategoriBarangDao(getApplicationContext());
		listKategoriBarang = new ArrayList<TmKategoriBarang>();
		kategoriBarang.setKode(kategori.getKodeKategori());
		listKategoriBarang = (ArrayList<TmKategoriBarang>) kategoriDao
				.listByExample(kategoriBarang);
		lsKategoriBarang = new String[listKategoriBarang.size()];
		for (int i = 0; i < listKategoriBarang.size(); i++) {
			lsKategoriBarang[i] = listKategoriBarang.get(i).getNama();
		}
		ArrayAdapter<String> adapterKategori = new ArrayAdapter<String>(
				TabSurveySku.this, android.R.layout.simple_spinner_item,
				lsKategoriBarang);
		adapterKategori
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnKatagori.setAdapter(adapterKategori);
		spnKatagori.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int index, long arg3) {
				kategoriBarang = new TmKategoriBarang();
				kategoriBarang = listKategoriBarang.get(index);
				loadSubKatagori(kategoriBarang);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void loadSubKatagori(TmKategoriBarang kategori) {
		subKategori = new TmSubKategoriBarang();
		subKategoriDao = new TmSubKategoriBarangDao(getApplicationContext());
		//listSubKategori = new ArrayList<TmSubKategoriBarang>();
		subKategori.setKode_kategori(kategori.getKode());
		listSubKategoriBarang = subKategoriDao.listByExample(subKategori);
		lsSubKategoriBarang = new String[listSubKategoriBarang.size()];
		for (int i = 0; i < listSubKategoriBarang.size(); i++) {
			lsSubKategoriBarang[i] = listSubKategoriBarang.get(i).getNama();
			if(getSubCategory(listSubKategoriBarang.get(i).getKode())){
				iSubKategori=i;
			}
		}
		ArrayAdapter<String> adapterSubKategori = new ArrayAdapter<String>(TabSurveySku.this, android.R.layout.simple_spinner_item,lsSubKategoriBarang);
		adapterSubKategori.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnSubKatagori.setAdapter(adapterSubKategori);
		spnSubKatagori.setSelection(iSubKategori);
		spnSubKatagori.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int index, long arg3) {
				if(listSubKategoriBarang.size() > 0){
					subKategori = new TmSubKategoriBarang();
					subKategori = listSubKategoriBarang.get(index);
					if(isBranded(rak)){
						loadCompany(subKategori);
					}else{
						spnCompany.setVisibility(View.GONE);
						spnBrand.setVisibility(View.GONE);
						lblBrandSku.setVisibility(View.GONE);
						lblCompanySku.setVisibility(View.GONE);
					}
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	private boolean getSubCategory(String kode) {
		try {
			TmKategoriCompanyBrand kcb = new TmKategoriCompanyBrand();
			TmKategoriCompanyBrandDao kcbDao = new TmKategoriCompanyBrandDao(getApplicationContext());
			kcb.setKode_brand(rak.getKodeBrand());
			kcb.setKode_company(comp.getKode());
			kcb = kcbDao.listByExample(kcb).get(0);
			if(kode.equals(kcb.getKode_sub_kategori())){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	private void loadCompany(TmSubKategoriBarang subKategori) {
		company = new TmCompany();
		companyDao = new TmCompanyDao(getApplicationContext());
		listCompany = new ArrayList<TmCompany>();
		listCompany.clear();
		listCompany = getListCompany(subKategori);
		if(listCompany.size() > 0){
			spnBrand.setVisibility(View.VISIBLE);
			lblBrandSku.setVisibility(View.VISIBLE);
			spnCompany.setVisibility(View.VISIBLE);
			lblCompanySku.setVisibility(View.VISIBLE);
		}else{
			/*brandRak = new TmBrand();
			txtBrand.setText(brandRak.getNama());
			txtProduk.setText("");*/
			spnBrand.setVisibility(View.GONE);
			lblBrandSku.setVisibility(View.GONE);
			spnCompany.setVisibility(View.GONE);
			lblCompanySku.setVisibility(View.GONE);
		}
		lsCompany = new String[listCompany.size()];
		for (int i = 0; i < listCompany.size(); i++) {
			if(listCompany.get(i).getKode().equals(comp.getKode())){
				iCompany = i;
			}
			lsCompany[i] = listCompany.get(i).getNama();
		}
		ArrayAdapter<String> adapterCompany = new ArrayAdapter<String>(TabSurveySku.this, android.R.layout.simple_spinner_item, lsCompany);
		adapterCompany.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnCompany.setAdapter(adapterCompany);
		spnCompany.setSelection(iCompany);
		spnCompany.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int index, long arg3) {
					if(listCompany.size() > 0){
						company = new TmCompany();
						company = listCompany.get(index);
						loadBrand(company);
					}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				//company = new TmCompany();
				//loadBrand(company);
			}
		});
	}

private List<TmCompany> getListCompany(TmSubKategoriBarang subKategori) {
	TmKategoriCompanyBrand kcb = new TmKategoriCompanyBrand();
	TmKategoriCompanyBrandDao kcbDao = new TmKategoriCompanyBrandDao(getApplicationContext());
	kcb.setKode_sub_kategori(subKategori.getKode());
	List<TmCompany> selectedCompany = new ArrayList<TmCompany>();
	HashMap<String, String> maps = new HashMap<String, String>();
	for (TmKategoriCompanyBrand tmKcb : kcbDao.listByExample(kcb)) {
		maps.put(tmKcb.getKode_company(), tmKcb.getKode_sub_kategori());
	}
	Iterator<String> keyIterator = maps.keySet().iterator();
	while (keyIterator.hasNext()) {
		String key = keyIterator.next();
		company = new TmCompany();
		company.setKode(key);
		company = companyDao.getByExample(company);
		selectedCompany.add(company);
	}
	return selectedCompany;
}

/*	private List<TmCompany> getListCompany(TmSubKategoriBarang subKategori2) {
		TmKategoriCompanyBrand kcb = new TmKategoriCompanyBrand();
		TmKategoriCompanyBrandDao kcbDao = new TmKategoriCompanyBrandDao(getApplicationContext());
		kcb.setKode_sub_kategori(subKategori2.getKode());
		List<TmCompany> selectedCompany = new ArrayList<TmCompany>();
		HashMap<String, String> maps = new HashMap<String, String>();
		for (TmKategoriCompanyBrand tmKcb : kcbDao.listByExample(kcb)) {
			maps.put(tmKcb.getKode_company(), tmKcb.getKode_sub_kategori());
		}
		Iterator<String> keyIterator = maps.keySet().iterator();
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
			company = new TmCompany();
			company.setKode(key);
			company = companyDao.getByExample(company);
			selectedCompany.add(company);
		}
		return selectedCompany;
	}*/

	

	private void loadPosm() {
		posm = new TmPosm();
		posmDao = new TmPosmDao(getApplicationContext());
		listPosm = new ArrayList<TmPosm>();
		listPosmTmp = new ArrayList<TmPosm>();
		spnPosm = (Spinner) findViewById(R.id.spnPosm);
		listPosm = (ArrayList<TmPosm>) posmDao.listAll();
		
		if(outlet.getKode().subSequence(0, 2).equals("MT")){
			for (int i = 0; i < listPosm.size(); i++) {
				int kode = Integer.valueOf(listPosm.get(i).getKode());
				if(kode > 40 && kode < 71){
					listPosmTmp.add(listPosm.get(i));
					//lsPosm[i] = listPosm.get(i).getNama();
				}
			}
		}else{
			for (int i = 0; i < listPosm.size(); i++) {
				int kode = Integer.valueOf(listPosm.get(i).getKode());
				if(kode > 70 && kode < 91){
					listPosmTmp.add(listPosm.get(i));
					//lsPosm[i] = listPosm.get(i).getNama();
				}
			}
		}
		
		lsPosm = new String[listPosmTmp.size()];
		for (int i = 0; i < listPosmTmp.size(); i++) {
			lsPosm[i] = listPosmTmp.get(i).getNama();
		}
		
		/*lsPosm = new String[listPosm.size()];
		for (int i = 0; i < listPosm.size(); i++) {
			lsPosm[i] = listPosm.get(i).getNama();
		}*/
		ArrayAdapter<String> adapterPosm = new ArrayAdapter<String>(
				TabSurveySku.this, android.R.layout.simple_spinner_item, lsPosm);
		adapterPosm
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnPosm.setAdapter(adapterPosm);
		spnPosm.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				posm = listPosm.get(arg2);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void loadBrand(TmCompany companyRak) {
		brandRak = new TmBrand();
		brandRakDao = new TmBrandDao(getApplicationContext());
		listRakBrand = new ArrayList<TmBrand>();
		brandRak.setKodeCompany(companyRak.getKode());
		//listRakBrand = (ArrayList<TmBrand>) brandRakDao.listByExample(brandRak);
		listRakBrand = getBrand(companyRak);
		String[]  lsBrandRak = new String[listRakBrand.size()];
		for (int i = 0; i < listRakBrand.size(); i++) {
			if(listRakBrand.get(i).getNama().equals(rak.getKodeNamaBrand())){
				iBrand = i;
			}
			lsBrandRak[i] = listRakBrand.get(i).getNama();
			
			/*if(listRakBrand.get(i).getKode().equals(brand.getKode())){
				iBrand = i;
			}*/
			/*if(kategoriBarang.getKode().equals(listRakBrand.get(i).getKode().substring(0, 2))){
				lsBrandRak[i] = listRakBrand.get(i).getNama();
			}*/
		}
		ArrayAdapter<String> adapterBrandRak = new ArrayAdapter<String>(
				TabSurveySku.this, android.R.layout.simple_spinner_item,
				lsBrandRak);
		adapterBrandRak
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnBrand.setAdapter(adapterBrandRak);
		spnBrand.setSelection(iBrand);
		spnBrand.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int index, long arg3) {
				if(listRakBrand.size() > 0){
					brandRak = listRakBrand.get(index);
					txtBrand.setText(brandRak.getNama());
					txtProduk.setText("");
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		adapterBrandRak.notifyDataSetChanged();
	}
	

	private ArrayList<TmBrand> getBrand(TmCompany companyRak) {
		ArrayList<TmBrand> listResult = new ArrayList<TmBrand>();
		TmKategoriCompanyBrand kcb = new TmKategoriCompanyBrand();
		TmKategoriCompanyBrandDao kcbDao = new TmKategoriCompanyBrandDao(getApplicationContext());
		kcb.setKode_company(companyRak.getKode());
		kcb.setKode_sub_kategori(subKategori.getKode());
		for(TmKategoriCompanyBrand k : kcbDao.listByExample(kcb)){
			brandRak = new TmBrand();
			brandRak.setKode(k.getKode_brand());
			brandRak = brandRakDao.getByExample(brandRak);
			listResult.add(brandRak);
		}
		return listResult;
	}

	private void initView() {
		txtSearch = (AutoCompleteTextView)findViewById(R.id.txtSearch);
		lblLabel = (TextView) findViewById(R.id.lblLabel);
		viewPhoto = (ListView) findViewById(R.id.lsFoto);
		txtBrand = (TextView) findViewById(R.id.txtBrand);
		spnKatagori = (Spinner) findViewById(R.id.spnKatagori);
		spnSubKatagori = (Spinner) findViewById(R.id.spnSubKatagori);
		spnCompany = (Spinner) findViewById(R.id.spnCompany);
		spnBrand = (Spinner) findViewById(R.id.spnBrand);
		spnPosm = (Spinner) findViewById(R.id.spnPosm);
		btnTambahPosm = (Button) findViewById(R.id.btnTambahPosm);
		txtJumlah = (EditText) findViewById(R.id.txtJumlah);
		txtNamaResponden = (EditText) findViewById(R.id.txtNamaResponden);
		txtOmzet = (EditText) findViewById(R.id.txtOmzet);
		txtJumlahFacing = (EditText) findViewById(R.id.txtJumlahFacing);
		lsOutletPosm = (ListView) findViewById(R.id.lsPosm);
		btnNext = (Button) findViewById(R.id.btnNext);
		btnAddSku = (Button) findViewById(R.id.btnAddSku);
		txtProduk = (TextView) findViewById(R.id.txtProduk);
		spnQuestion = (Spinner) findViewById(R.id.spnQuestion);
		btnTambahPromosi = (Button) findViewById(R.id.btnTambahPromosi);
		btnPhoto = (Button) findViewById(R.id.btnPhoto);
		btnBack = (Button) findViewById(R.id.btnBack);
		simpanSku = (Button) findViewById(R.id.btnSaveSku);
		txtHarga = (EditText) findViewById(R.id.txtHarga);
		txtJumlahUnit = (EditText) findViewById(R.id.txtJumlahUnit);
		btnResult = (Button) findViewById(R.id.btnResult);
		lblCompanySku = (TextView) findViewById(R.id.lblCompanySku);
		lblBrandSku = (TextView) findViewById(R.id.lblBrandSku);
		/*
		 * txtOutlet = (TextView) findViewById(R.id.txtOutlet); txtTanggalAudit2
		 * = (TextView) findViewById(R.id.txtTanggalAudit2); txtJamMulai2 =
		 * (TextView) findViewById(R.id.txtJamMulai2);
		 */
		// spnJabatan = (Spinner) findViewById(R.id.spnJabatanResponden);

	}

	private Double getDouble(String string) {
		double result = 0d;
		try {
			result = Double.valueOf(string);
		} catch (Exception e) {
			return result = 0d;
		}
		return result;
	}
	
	private void loadForm() {
		skb = new TmKategoriCompanyBrand();
		lis = new ArrayList<TmKategoriCompanyBrand>();
		skbDao = new TmKategoriCompanyBrandDao(getApplicationContext());
		tanggal = new SimpleDateFormat("ddMMyyyy").format(new Date());
		jamMulai = new SimpleDateFormat("HH:mm:ss").format(new Date());
		if(outlet.getKode().substring(0, 2).equals("GT")){
			lblLabel.setVisibility(View.GONE);
			spnQuestion.setVisibility(View.GONE);
		}
		txtHarga.addTextChangedListener( new TextWatcher() {
			
	        boolean isEdiging;
	        @Override public void onTextChanged(CharSequence s, int start, int before, int count) { }
	        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

	        @Override public void afterTextChanged(Editable s) {
	            if(isEdiging) return;
	            isEdiging = true;

	            String str = s.toString().replaceAll( "[^\\d]", "" );
	            double s1 = getDouble(str);

	            if((int)s1 > 0){
	            	 NumberFormat nf2 = NumberFormat.getInstance(Locale.ENGLISH);
	                 ((DecimalFormat)nf2).applyPattern("###,###.###");
	                 s.replace(0, s.length(), nf2.format(s1));
	            }else{
	            	s.replace(0, s.length(), "");
	            }
	          
	            isEdiging = false;
	        }
	    });
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.cancelsave, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menuCancel:
			AlertDialog.Builder alert = new AlertDialog.Builder(TabSurveySku.this);
			alert.setTitle("Informasi"); // Set Alert dialog//
			alert.setMessage("Apakah anda akan membatalkan transaksi rak ?"); // Message
			alert.setPositiveButton("Setuju",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							try {
								selesai();
							} catch (Exception e) {
								Log.i("onSave", e.getMessage());
							}
						}
					});
			alert.setNegativeButton("Tidak",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {

							dialog.cancel();
						}
					}); // End of alert.setNegativeButton
			AlertDialog alertDialog = alert.create();
			alertDialog.show();
			return true;
			
		case R.id.menuSave:
			alert = new AlertDialog.Builder(TabSurveySku.this);
			alert.setTitle("Informasi"); // Set Alert dialog//
			alert.setMessage("Apakah anda akan menyimpan transaksi rak ?"); // Message
			alert.setPositiveButton("Setuju",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							try {
								TtDKunjunganSurveyorRakDao kunjunganRakDao = new TtDKunjunganSurveyorRakDao(getApplicationContext());
								rak.setStatus("selesai");
								kunjunganRakDao.update(rak);
								Toast.makeText(getApplicationContext(), "Data sukses disimpan",
										Toast.LENGTH_LONG).show();
								selesai();
							} catch (Exception e) {
								Log.i("onSave", e.getMessage());
							}
						}
					});
			alert.setNegativeButton("Tidak",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {

							dialog.cancel();
						}
					}); // End of alert.setNegativeButton
			alertDialog = alert.create();
			alertDialog.show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void selesai() {
		Intent intent = new Intent( TabSurveySku.this, ActivityCreateRak.class);
		intent.putExtra("surveyor", surveyor);
		intent.putExtra("outlet", outlet);
		intent.putExtra("kunjungan", kunjungan);
		intent.putExtra("kategori", kategori);
		intent.putExtra("locked", locked);
		intent.putExtra("xcoord", xcoord);
		intent.putExtra("ycoord", ycoord);
		startActivity(intent);
		finish();
	}
	
	
}