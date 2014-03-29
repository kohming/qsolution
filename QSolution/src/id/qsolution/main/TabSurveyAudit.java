package id.qsolution.main;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import id.qsolution.adapter.OmsetKategoriAdapter;
import id.qsolution.adapter.PosmOutletAdapter;
import id.qsolution.models.TmBrand;
import id.qsolution.models.TmCompany;
import id.qsolution.models.TmJabatanResponden;
import id.qsolution.models.TmKategoriBarang;
import id.qsolution.models.TmKategoriCompanyBrand;
import id.qsolution.models.TmOutlet;
import id.qsolution.models.TmPosm;
import id.qsolution.models.TmSubKategoriBarang;
import id.qsolution.models.TmSurveyor;
import id.qsolution.models.TtDKunjunganSurveyorOmsetKatagori;
import id.qsolution.models.TtDKunjunganSurveyorOutletPosm;
import id.qsolution.models.TtMKunjunganSurveyor;
import id.qsolution.models.dao.TmBrandDao;
import id.qsolution.models.dao.TmCompanyDao;
import id.qsolution.models.dao.TmJabatanRespondenDao;
import id.qsolution.models.dao.TmKategoriBarangDao;
import id.qsolution.models.dao.TmKategoriCompanyBrandDao;
import id.qsolution.models.dao.TmPosmDao;
import id.qsolution.models.dao.TmSubKategoriBarangDao;
import id.qsolution.models.dao.TtDKunjunganSurveyorOmsetKatagoriDao;
import id.qsolution.models.dao.TtDKunjunganSurveyorOutletPosmDao;
import id.qsolution.models.dao.TtMKunjunganSurveyorDao;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class TabSurveyAudit extends TabActivity {
	
	private TmSurveyor surveyor;
	private TmOutlet outlet;
	private TabHost tabHost;
	//private String tanggal;
	private String jamMulai;
	private TmJabatanResponden jabatan;
	private TmJabatanRespondenDao jabatanDao;
	private List<TmJabatanResponden> listJabatan;
	private Spinner spnJabatan;
	private String[] lsJabatan;
	private Spinner spnKatagori;
	private TmKategoriBarang kategori;
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
	private ArrayList<TmCompany> listCompany;
	private String[] lsCompany;
	private Spinner spnCompany;
	private Spinner spnBrand;
	private TmBrand brandRak;
	private TmBrandDao brandRakDao;
	private ArrayList<TmBrand> listRakBrand;
	private String[] lsBrandRak;
	private Spinner spnPosm;
	private TmPosm posm;
	private TmPosmDao posmDao;
	private ArrayList<TmPosm> listPosm;
	private String[] lsPosm;
	private TmKategoriCompanyBrand ktcb;
	private TmKategoriCompanyBrandDao ktcbDao;
	private Button btnTambahPosm;
	private EditText txtJumlah;
	private ListView lsOutletPosm;
	private TtDKunjunganSurveyorOutletPosm outletPosm;
	private TtDKunjunganSurveyorOutletPosmDao outletPosmDao;
	private PosmOutletAdapter adapter;
	private List<TtDKunjunganSurveyorOutletPosm> posms = new ArrayList<TtDKunjunganSurveyorOutletPosm>();
	private EditText txtNamaResponden;
	private EditText txtOmzet;
	private Button btnTambahRak;
	private TtMKunjunganSurveyor kunjungan;
	private TtMKunjunganSurveyorDao kunjunganDao;
	private Intent intent;
	private ListView lsOmset;
	private Button btnTambahOmsetKategori;
	private EditText txtOmsetKategori;
	private TextView lblOutletOmsetKategori;
	private List<TtDKunjunganSurveyorOmsetKatagori> listOmsetKategori = new ArrayList<TtDKunjunganSurveyorOmsetKatagori>();
	private TtDKunjunganSurveyorOmsetKatagori omsetKategori;
	private TtDKunjunganSurveyorOmsetKatagoriDao omsetKategoriDao;
	private OmsetKategoriAdapter adapterOmsetKategori;
	private TextView lblCompanySku;
	private TextView lblBrandSku;
	private ArrayList<TmPosm> listPosmTmp;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("TAMBAH OUTLET POSM");  
		setContentView(R.layout.activity_tab_audit);
		ktcb = new TmKategoriCompanyBrand();
		ktcbDao = new TmKategoriCompanyBrandDao(getApplicationContext());
		outletPosm = new TtDKunjunganSurveyorOutletPosm();
		outletPosmDao = new TtDKunjunganSurveyorOutletPosmDao(getApplicationContext());
		surveyor = (TmSurveyor) getIntent().getSerializableExtra("surveyor");
		outlet = (TmOutlet) getIntent().getSerializableExtra("outlet");
		jamMulai = getIntent().getStringExtra("mulai");
		initView();
		Resources res = getResources(); // Resource object to get Drawables
		tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		// Initialize a TabSpec for each tab and add it to the TabHost
		//crateKodeKunjungan();
		spec = tabHost
				.newTabSpec("Outlet")
				.setIndicator("Outlet", res.getDrawable(R.drawable.ic_action_user))
				.setContent(R.id.form1);
		tabHost.addTab(spec);

		spec = tabHost.newTabSpec("Daftar Posm Outlet")
				.setIndicator("Daftar Posm", res.getDrawable(R.drawable.ic_action_copy))
				.setContent(R.id.form2);
		tabHost.addTab(spec);
		
		spec = tabHost.newTabSpec("Daftar Omset Kategori")
				.setIndicator("Daftar Omset Kategori", res.getDrawable(R.drawable.ic_action_paste))
				.setContent(R.id.form3);
		tabHost.addTab(spec);
		tabHost.setCurrentTab(0);
		
		if(outlet.getKode().subSequence(0, 2).equals("MT")){
			lblOutletOmsetKategori.setVisibility(View.GONE);
			btnTambahOmsetKategori.setVisibility(View.GONE);
			txtOmsetKategori.setVisibility(View.GONE);
		}
		loadKategori();
		//loadCompany();
		loadPosm();
		createListener();
		loadListPosm();
		loadJabatan();
		loadListOmsetKategori();
	}
	
	
	private void loadJabatan() {
		jabatan = new TmJabatanResponden();
		jabatanDao = new TmJabatanRespondenDao(getApplicationContext());
		listJabatan = new ArrayList<TmJabatanResponden>();
		listJabatan = jabatanDao.listAll();
		lsJabatan = new String[listJabatan.size()];
		for (int i = 0; i < listJabatan.size(); i++) {
			lsJabatan[i] = listJabatan.get(i).getNama();
		}
		ArrayAdapter<String> adapterJabatan = new ArrayAdapter<String>(
				TabSurveyAudit.this, android.R.layout.simple_spinner_item,
				lsJabatan);
		adapterJabatan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnJabatan.setAdapter(adapterJabatan);
		spnJabatan.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				jabatan = listJabatan.get(arg2);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}

	
	private void loadListPosm() {
		posms.clear();
		outletPosm = new TtDKunjunganSurveyorOutletPosm();
		outletPosm.setKode(outlet.getKode());
		posms = outletPosmDao.listByExample(outletPosm);
		adapter = new PosmOutletAdapter(this, posms);
		lsOutletPosm.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		lsOutletPosm.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
				outletPosm = new TtDKunjunganSurveyorOutletPosm();
				outletPosm = posms.get(index);
				outletPosmDao.delete(outletPosm.getId());
				Toast.makeText(getApplicationContext(), "Outlet posm berhasil dihapus", Toast.LENGTH_LONG).show();
				loadListPosm();
				return false;
			}
		});
	}
	
	private void loadListOmsetKategori() {
		listOmsetKategori.clear();
		omsetKategori = new TtDKunjunganSurveyorOmsetKatagori();
		omsetKategoriDao = new TtDKunjunganSurveyorOmsetKatagoriDao(getApplicationContext());
		omsetKategori.setKodeKunjungan(outlet.getKode()+new SimpleDateFormat("ddMMyyyy").format(new Date()));
		listOmsetKategori = omsetKategoriDao.listByExample(omsetKategori);
		adapterOmsetKategori = new OmsetKategoriAdapter(this, listOmsetKategori);
		lsOmset.setAdapter(adapterOmsetKategori);
		adapterOmsetKategori.notifyDataSetChanged();
		lsOmset.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
				omsetKategori = new TtDKunjunganSurveyorOmsetKatagori();
				omsetKategori = listOmsetKategori.get(index);
				omsetKategoriDao.delete(omsetKategori.getId());
				Toast.makeText(getApplicationContext(), "Omset Kategori Berhasil Dihapus", Toast.LENGTH_LONG).show();
				loadListPosm();
				return false;
			}
		});
	}
	
	@Override
	public void onBackPressed() {
	
	}

	private void createListener() {
		
		btnTambahPosm.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				if(validatePosm()){
					outletPosm = new TtDKunjunganSurveyorOutletPosm();
					kunjungan = new TtMKunjunganSurveyor();
					kunjunganDao = new TtMKunjunganSurveyorDao(getApplicationContext());
					outletPosm.setKodePosm(posm.getKode());
					if(outletPosmDao.listByExample(outletPosm).size()>0){
						outletPosm = outletPosmDao.getByExample(outletPosm);
						int i = outletPosm.getJumlah() + getInt(txtJumlah.getText().toString());
						outletPosm.setJumlah(i);
						outletPosmDao.update(outletPosm);
					} else{
						kunjungan.setKodeOutlet(outlet.getKode());
						outletPosm.setJumlah(getInt(txtJumlah.getText().toString()));
						outletPosm.setKodeBrand(isEmptyBrand(brandRak.getKode()));
						outletPosm.setKodeKunjungan(outlet.getKode()+new SimpleDateFormat("ddMMyyyy").format(new Date()));
						outletPosm.setKodePosm(isEmptyBrand(posm.getKode()));
						outletPosm.setNamaBrand(brandRak.getNama());
						outletPosm.setKode(outlet.getKode());
						outletPosm.setNamaPosm(posm.getNama());
						outletPosmDao.insert(outletPosm);
					}
					loadListPosm();
					txtJumlah.setText("");
					Toast.makeText(getApplicationContext(), "Posm outlet berhasil disimpan ", Toast.LENGTH_LONG).show();
				}
			}

			private String isEmptyBrand(String kode) {
				String result = "00";
				try {
					if(result!= null || !result.equals("")){
						result = kode;
					}
				} catch (Exception e) {
					return result;
				}
				return result;
			}

			private Integer getInt(String string) {
				int value = 0;
				try {
					value = Integer.valueOf(string);
				} catch (Exception e) {
					value = 0;
				}
				return value;
			}
		});
		
		btnTambahOmsetKategori.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					TtDKunjunganSurveyorOmsetKatagori omsetKategori = new TtDKunjunganSurveyorOmsetKatagori();
					TtDKunjunganSurveyorOmsetKatagoriDao omsetKategoriDao = new TtDKunjunganSurveyorOmsetKatagoriDao(getApplicationContext());
					omsetKategori.setKodeKategori(kategori.getKode());
					if(omsetKategoriDao.listByExample(omsetKategori).size()>0){
						omsetKategori = omsetKategoriDao.getByExample(omsetKategori);
						omsetKategori.setKodeKunjungan(outlet.getKode()+new SimpleDateFormat("ddMMyyyy").format(new Date()));
						omsetKategori.setKodeOutlet(outlet.getKode());
						omsetKategori.setNamaKategori(kategori.getNama());
						int jumlah = omsetKategori.getOmset() + isNullNumber(txtOmsetKategori);
						omsetKategori.setOmset(jumlah);
						omsetKategoriDao.update(omsetKategori);
					}else{
						omsetKategori.setKodeKunjungan(outlet.getKode()+new SimpleDateFormat("ddMMyyyy").format(new Date()));
						omsetKategori.setKodeOutlet(outlet.getKode());
						omsetKategori.setNamaKategori(kategori.getNama());
						omsetKategori.setOmset(isNullNumber(txtOmsetKategori));
						omsetKategoriDao.insert(omsetKategori);
					}
		
					loadListOmsetKategori();
					txtOmsetKategori.setText("");
					Toast.makeText(getApplicationContext(), "Omset Kategori Berhasil Disimpan", Toast.LENGTH_LONG).show();
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "Error "+e.getMessage(), Toast.LENGTH_LONG).show();
				}
			}

			private int isNullNumber(EditText txtOmsetKategori) {
				int result = 0;
				try {
					result = Integer.valueOf(txtOmsetKategori.getText().toString());
				} catch (Exception e) {
					return result;
				}
				return result;
			}
		});
		
		btnTambahRak.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(validateRak()){
					kunjunganDao = new TtMKunjunganSurveyorDao(getApplicationContext());
					intent = new Intent( TabSurveyAudit.this, ActivityCreateRak.class);
					intent.putExtra("surveyor", surveyor);
					intent.putExtra("outlet", outlet);
					intent.putExtra("mulai",jamMulai);		
					intent.putExtra("responden",txtNamaResponden.getText().toString());
					intent.putExtra("jabatan",jabatan.getKode());
					intent.putExtra("omset", txtOmzet.getText().toString());
					startActivity(intent);
					finish();
				}
			}
			

			private boolean validateRak() {
				if(isNullOrEmpty(txtNamaResponden)){
					Toast.makeText(getApplicationContext(), "Nama responden harus diisi", Toast.LENGTH_LONG).show();
					return false;
				}
				if(isNullOrEmpty(txtOmzet)){
					Toast.makeText(getApplicationContext(), "Jumlah omset harus diisi", Toast.LENGTH_LONG).show();
					return false;
				}
				return true;
			}
		});
	}
	
	private boolean validatePosm() {
		if(isNullOrEmpty(txtJumlah)){
			Toast.makeText(getApplicationContext(), "Jumlah posm harus diisi", Toast.LENGTH_LONG).show();
			return false;
		}
		return true;
	}
	
	private boolean isNullOrEmpty(EditText txt) {
		if (txt.getText().toString() == null || txt.getText().toString().equals("")) {
			return true;
		}
		return false;
	}


	private void loadKategori() {
		kategori = new TmKategoriBarang();
		kategoriDao = new TmKategoriBarangDao(getApplicationContext());
		listKategoriBarang = new ArrayList<TmKategoriBarang>();
		listKategoriBarang = (ArrayList<TmKategoriBarang>) kategoriDao.listAll();
		lsKategoriBarang = new String[listKategoriBarang.size()];
		for (int i = 0; i < listKategoriBarang.size(); i++) {
			lsKategoriBarang[i] = listKategoriBarang.get(i).getNama();
		}
		ArrayAdapter<String> adapterKategori = new ArrayAdapter<String>(TabSurveyAudit.this, android.R.layout.simple_spinner_item,lsKategoriBarang);
		adapterKategori.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnKatagori.setAdapter(adapterKategori);
		spnKatagori.setOnItemSelectedListener(new OnItemSelectedListener() {
			
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
						kategori = new TmKategoriBarang();
						kategori = listKategoriBarang.get(arg2);
						loadSubKatagori(kategori);
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
		listSubKategori = new ArrayList<TmSubKategoriBarang>();
		subKategori.setKode_kategori(kategori.getKode());
		listSubKategoriBarang = subKategoriDao.listByExample(subKategori);
		lsSubKategoriBarang = new String[listSubKategoriBarang.size()];
		for (int i = 0; i < listSubKategoriBarang.size(); i++) {
			lsSubKategoriBarang[i] = listSubKategoriBarang.get(i).getNama();
		}
		ArrayAdapter<String> adapterSubKategori = new ArrayAdapter<String>(TabSurveyAudit.this, android.R.layout.simple_spinner_item,lsSubKategoriBarang);
		adapterSubKategori.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnSubKatagori.setAdapter(adapterSubKategori);
		spnSubKatagori.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1, int index, long arg3) {
						subKategori = listSubKategoriBarang.get(index);
						ktcb.setKode_sub_kategori(subKategori.getKode());
						loadCompany(subKategori);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
					}
				});
	}
	
private void loadCompany(TmSubKategoriBarang subKategori) {
		company = new TmCompany();
		companyDao = new TmCompanyDao(getApplicationContext());
		listCompany = new ArrayList<TmCompany>();
		listCompany.clear();
		listCompany = (ArrayList<TmCompany>) getListCompany(subKategori);
		if(listCompany.size() <= 0){
			brandRak = new TmBrand();
			spnBrand.setVisibility(View.GONE);
			lblBrandSku.setVisibility(View.GONE);
			spnCompany.setVisibility(View.GONE);
			lblCompanySku.setVisibility(View.GONE);
		}else{
			spnBrand.setVisibility(View.VISIBLE);
			lblBrandSku.setVisibility(View.VISIBLE);
			spnCompany.setVisibility(View.VISIBLE);
			lblCompanySku.setVisibility(View.VISIBLE);
		}
		lsCompany = new String[listCompany.size()];
		for (int i = 0; i < listCompany.size(); i++) {
			lsCompany[i] = listCompany.get(i).getNama();
		}
		ArrayAdapter<String> adapterCompany = new ArrayAdapter<String>(TabSurveyAudit.this, android.R.layout.simple_spinner_item, lsCompany);
		adapterCompany.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnCompany.setAdapter(adapterCompany);
		spnCompany.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				company = new TmCompany();
				company = listCompany.get(arg2);
				loadBrand(company);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				company = new TmCompany();
				loadBrand(company);
			}
		});
	}

private List<TmCompany> getListCompany(TmSubKategoriBarang subKategori2) {
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
}


	private void loadCompany() {
		company = new TmCompany();
		companyDao = new TmCompanyDao(getApplicationContext());
		listCompany = new ArrayList<TmCompany>();
		listCompany.clear();
		listCompany = (ArrayList<TmCompany>) companyDao.listAll();
		lsCompany = new String[listCompany.size()];
		
		for (int i = 0; i < listCompany.size(); i++) {
			lsCompany[i] = listCompany.get(i).getNama();
		}
		
		ArrayAdapter<String> adapterCompany = new ArrayAdapter<String>(TabSurveyAudit.this, android.R.layout.simple_spinner_item, lsCompany);
		adapterCompany.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnCompany.setAdapter(adapterCompany);
		spnCompany.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				company = new TmCompany();
				company = listCompany.get(arg2);
				loadBrand(company);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	private boolean isCompanyExits(String kode, List<TmKategoriCompanyBrand> kcb) {
		try {
			for (TmKategoriCompanyBrand tmKategoriCompanyBrand : kcb) {
				if(kode.equals(tmKategoriCompanyBrand.getKode())) return true;
			}
		} catch (Exception e) {
			return false;
		}
		
		return false;
	}

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
				if(kode > 10 && kode < 21){
					listPosmTmp.add(listPosm.get(i));
					//lsPosm[i] = listPosm.get(i).getNama();
				}
			}
		}else{
			for (int i = 0; i < listPosm.size(); i++) {
				int kode = Integer.valueOf(listPosm.get(i).getKode());
				if(kode > 20 && kode < 41){
					listPosmTmp.add(listPosm.get(i));
					//lsPosm[i] = listPosm.get(i).getNama();
				}
			}
		}
		
		lsPosm = new String[listPosmTmp.size()];
		for (int i = 0; i < listPosmTmp.size(); i++) {
			lsPosm[i] = listPosmTmp.get(i).getNama();
		}
		
		ArrayAdapter<String> adapterPosm = new ArrayAdapter<String>(TabSurveyAudit.this, android.R.layout.simple_spinner_item,
				lsPosm);
		adapterPosm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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
		listRakBrand = (ArrayList<TmBrand>) brandRakDao.listByExample(brandRak);
		lsBrandRak = new String[listRakBrand.size()];
		for (int i = 0; i < listRakBrand.size(); i++) {
			lsBrandRak[i] = listRakBrand.get(i).getNama();
		}
		ArrayAdapter<String> adapterBrandRak = new ArrayAdapter<String>(
				TabSurveyAudit.this, android.R.layout.simple_spinner_item,
				lsBrandRak);
		adapterBrandRak
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnBrand.setAdapter(adapterBrandRak);
		spnBrand.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				brandRak = listRakBrand.get(arg2);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

	}

	private void initView() {
		lblOutletOmsetKategori = (TextView) findViewById(R.id.lblOutletOmsetKategori);
		txtOmsetKategori = (EditText) findViewById(R.id.txtOmsetKategori);
		btnTambahOmsetKategori = (Button) findViewById(R.id.btnTambahOmsetKategori);
		lsOmset = (ListView) findViewById(R.id.lsOmset);
		spnKatagori = (Spinner) findViewById(R.id.spnKatagori);
		spnSubKatagori = (Spinner) findViewById(R.id.spnSubKatagori);
		spnCompany = (Spinner) findViewById(R.id.spnCompany);
		spnBrand = (Spinner) findViewById(R.id.spnBrand);
		spnPosm = (Spinner) findViewById(R.id.spnPosm);
		btnTambahPosm = (Button) findViewById(R.id.btnTambahPosm);
		txtJumlah = (EditText) findViewById(R.id.txtJumlah);
		txtNamaResponden = (EditText) findViewById(R.id.txtNamaResponden);
		spnJabatan = (Spinner) findViewById(R.id.spnResponden);
		txtOmzet = (EditText) findViewById(R.id.txtOmzet);
		lsOutletPosm = (ListView) findViewById(R.id.lsPosm);
		btnTambahRak = (Button) findViewById(R.id.btnTambahRak);
		lblCompanySku = (TextView) findViewById(R.id.lblCompanySku);
		lblBrandSku = (TextView) findViewById(R.id.lblBrandSku);
	}

}
