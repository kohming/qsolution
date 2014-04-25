package id.qsolution.main;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import id.qsolution.adapter.PosmOutletAdapter;
import id.qsolution.models.DaftarOutletSurvey;
import id.qsolution.models.TmBrand;
import id.qsolution.models.TmCompany;
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
import id.qsolution.models.dao.TmKategoriBarangDao;
import id.qsolution.models.dao.TmKategoriCompanyBrandDao;
import id.qsolution.models.dao.TmPosmDao;
import id.qsolution.models.dao.TmSubKategoriBarangDao;
import id.qsolution.models.dao.TtDKunjunganSurveyorOmsetKatagoriDao;
import id.qsolution.models.dao.TtDKunjunganSurveyorOutletPosmDao;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class TabSurveyOutlet extends TabActivity {

	private TmSurveyor surveyor;
	private TmOutlet outlet;
	private TtMKunjunganSurveyor kunjungan;
	private boolean locked;
	private String xcoord;
	private String ycoord;
	private DaftarOutletSurvey kategori;
	private TextView lblId;
	private TextView lblName;
	private EditText txtOmzet;
	private TextView lblSubkatagori;
	private Spinner spnSubKatagori;
	private TextView lblCompanySku;
	private Spinner spnCompany;
	private TextView lblBrandSku;
	private Spinner spnBrand;
	private TextView lblBranded;
	private Spinner spnPosm;
	private EditText txtJumlah;
	private TabHost tabHost;
	private String[] lsPosm;
	private TmSubKategoriBarang subKategori;
	private TmSubKategoriBarangDao subKategoriDao;
	private ArrayList<TmSubKategoriBarang> listSubKategori;
	private List<TmSubKategoriBarang> listSubKategoriBarang;
	private String[] lsSubKategoriBarang;
	private TmKategoriCompanyBrand ktcb;
	private TmKategoriCompanyBrandDao ktcbDao;
	private TmCompany company;
	private TmCompanyDao companyDao;
	private ArrayList<TmCompany> listCompany;
	private TmBrand brandRak;
	private String[] lsCompany;
	private TmBrandDao brandRakDao;
	private ArrayList<TmBrand> listRakBrand;
	private String[] lsBrandRak;
	private TmKategoriBarang katBarang;
	private ListView lsOutletPosm;
	private TmPosm posm;
	private TmPosmDao posmDao;
	private ArrayList<TmPosm> listPosm;
	private ListView viewPosm;
	private Button btnTambahPosm;
	private TtDKunjunganSurveyorOutletPosm outletPosm;
	private TtDKunjunganSurveyorOutletPosmDao outletPosmDao;
	private PosmOutletAdapter adapter;
	private List<TtDKunjunganSurveyorOutletPosm> posms = new ArrayList<TtDKunjunganSurveyorOutletPosm>();
	private ArrayList<TmPosm> listPosmTmp;
	private AutoCompleteTextView txtSearch;
	private ArrayAdapter<String> arrayAdapter;
	private TmKategoriCompanyBrand tkcb;
	private TmKategoriCompanyBrandDao tkcbDao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("Activity ", this.getClass().getName());
		setTitle("Data Omset dan POSM");
		setContentView(R.layout.activity_data_omset_posm);
		surveyor = (TmSurveyor) getIntent().getSerializableExtra("surveyor");
		kategori = (DaftarOutletSurvey) getIntent().getSerializableExtra("kategori");
		outlet = (TmOutlet) getIntent().getSerializableExtra("outlet");
		kunjungan = (TtMKunjunganSurveyor) getIntent().getSerializableExtra("kunjungan");
		locked = getIntent().getBooleanExtra("locked", false);
		xcoord = getIntent().getStringExtra("xcoord");
		ycoord = getIntent().getStringExtra("ycoord");
		ktcb = new TmKategoriCompanyBrand();
		subKategoriDao = new TmSubKategoriBarangDao(getApplicationContext());
		outletPosmDao = new TtDKunjunganSurveyorOutletPosmDao(getApplicationContext());
		initView();
		Resources res = getResources(); // Resource object to get Drawables
		tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		// Initialize a TabSpec for each tab and add it to the TabHost
		// crateKodeKunjungan();
		spec = tabHost
				.newTabSpec("Outlet Omset & Posm")
				.setIndicator("Outlet",
						res.getDrawable(R.drawable.ic_action_user))
				.setContent(R.id.form1);
		tabHost.addTab(spec);

		spec = tabHost
				.newTabSpec("Daftar Posm Outlet")
				.setIndicator("Daftar Posm Outlet",
						res.getDrawable(R.drawable.ic_action_copy))
				.setContent(R.id.form2);
		tabHost.addTab(spec);
		tabHost.setCurrentTab(0);
		
		
		tkcb = new TmKategoriCompanyBrand();
		tkcbDao = new TmKategoriCompanyBrandDao(
				getApplicationContext());

		/*
		 * company = new TmCompany(); companyDao = new
		 * TmCompanyDao(getApplicationContext());
		 */
		listCompany = new ArrayList<TmCompany>();
		brandRak = new TmBrand();
		brandRakDao = new TmBrandDao(getApplicationContext());
		listRakBrand = new ArrayList<TmBrand>();

		// Load sub kategori spinner
		subKategori = new TmSubKategoriBarang();
		subKategoriDao = new TmSubKategoriBarangDao(getApplicationContext());
		listSubKategori = new ArrayList<TmSubKategoriBarang>();
		loadForm();
		loadSearch();
		katBarang = new TmKategoriBarang();
		katBarang.setKode(kategori.getKodeKategori());
		loadSubKatagori(katBarang);
		loadPosm();
		setListener();
		loadListPosm();

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
		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, brand);
		txtSearch.setThreshold(3);
		txtSearch.setAdapter(arrayAdapter);
		txtSearch.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long rowId) {
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
				brandRak = new TmBrand();
				brandRak.setKodeCompany(perusahaan.getKode());
				listRakBrand = (ArrayList<TmBrand>) brandRakDao
						.listByExample(brandRak);
				lsBrandRak = new String[listRakBrand.size()];
				for (int i = 0; i < listRakBrand.size(); i++) {
					lsBrandRak[i] = listRakBrand.get(i).getNama();
				}
				ArrayAdapter<String> adapterBrandRak = new ArrayAdapter<String>(
						TabSurveyOutlet.this,
						android.R.layout.simple_spinner_item, lsBrandRak);
				adapterBrandRak
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spnBrand.setAdapter(adapterBrandRak);
				spnBrand.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1, int index, long arg3) {
						brandRak = new TmBrand();
						brandRak = listRakBrand.get(index);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
					}
				});
				
				// Load Company
				listCompany.clear();
				company.setKode(perusahaan.getKode());
				listCompany = (ArrayList<TmCompany>) companyDao
						.listByExample(company);
				lsCompany = new String[listCompany.size()];

				for (int i = 0; i < listCompany.size(); i++) {
					lsCompany[i] = listCompany.get(i).getNama();
				}

				if (listCompany.size() <= 0) {
					spnBrand.setVisibility(View.GONE);
					lblBrandSku.setVisibility(View.GONE);
					spnCompany.setVisibility(View.GONE);
					lblCompanySku.setVisibility(View.GONE);
				} else {
					spnBrand.setVisibility(View.VISIBLE);
					lblBrandSku.setVisibility(View.VISIBLE);
					spnCompany.setVisibility(View.VISIBLE);
					lblCompanySku.setVisibility(View.VISIBLE);
				}

				ArrayAdapter<String> adapterCompany = new ArrayAdapter<String>(
						TabSurveyOutlet.this,
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
						TabSurveyOutlet.this,
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

	private void setListener() {

		btnTambahPosm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				TtDKunjunganSurveyorOutletPosm posmOutlet = new TtDKunjunganSurveyorOutletPosm();
				TtDKunjunganSurveyorOutletPosmDao posmOutletDao = new TtDKunjunganSurveyorOutletPosmDao(
						getApplicationContext());
				posmOutlet.setKodeKunjungan(outlet.getKode()
						+ kategori.getKodeKategori()
						+ new SimpleDateFormat("ddMMyy").format(new Date()));
				posmOutlet.setKodePosm(posm.getKode());
				if (posmOutletDao.listByExample(posmOutlet).size() > 0) {
					posmOutlet = posmOutletDao.getByExample(posmOutlet);
					posmOutlet.setJumlah(getJumlah(txtJumlah.getText()
							.toString()));
					posmOutletDao.update(posmOutlet);
					Toast.makeText(getApplicationContext(),
							"Posm outlet berhasil  ", Toast.LENGTH_LONG).show();
				} else {
					posmOutlet.setKodeKunjungan(outlet.getKode()
							+ kategori.getKodeKategori()
							+ new SimpleDateFormat("ddMMyy").format(new Date()));
					posmOutlet.setJumlah(getJumlah(txtJumlah.getText()
							.toString()));
					posmOutlet.setKodeBrand(getBrand(brandRak.getKode()));
					posmOutlet.setKodePosm(posm.getKode());
					posmOutlet.setNamaPosm(posm.getNama());
					posmOutlet.setNamaBrand(brandRak.getNama());
					posmOutlet.setKode(outlet.getKode());
					posmOutletDao.insert(posmOutlet);
					Toast.makeText(getApplicationContext(),
							"Posm outlet berhasil dibuat ", Toast.LENGTH_LONG)
							.show();
				}
				txtJumlah.setText("");
				loadListPosm();
			}

			private String getBrand(String kode) {
				String result = "00";
				try {
					result = kode;
					;
				} catch (Exception e) {
					return result;
				}
				return result;
			}

			private Integer getJumlah(String string) {
				int result = 0;
				try {
					result = Integer.valueOf(string);
				} catch (Exception e) {
					return 0;
				}
				return result;
			}
		});

	}

	private void loadPosm() {
		posm = new TmPosm();
		posmDao = new TmPosmDao(getApplicationContext());
		listPosm = new ArrayList<TmPosm>();
		listPosmTmp = new ArrayList<TmPosm>();
		spnPosm = (Spinner) findViewById(R.id.spnPosm);
		listPosm = (ArrayList<TmPosm>) posmDao.listAll();
		lsPosm = new String[listPosm.size()];
		/*
		 * for (int i = 0; i < listPosm.size(); i++) { lsPosm[i] =
		 * listPosm.get(i).getNama(); }
		 */
		if (outlet.getKode().subSequence(0, 2).equals("MT")) {
			for (int i = 0; i < listPosm.size(); i++) {
				int kode = Integer.valueOf(listPosm.get(i).getKode());
				if (kode > 10 && kode < 21) {
					listPosmTmp.add(listPosm.get(i));
					// lsPosm[i] = listPosm.get(i).getNama();
				}
			}
		} else {
			for (int i = 0; i < listPosm.size(); i++) {
				int kode = Integer.valueOf(listPosm.get(i).getKode());
				if (kode > 20 && kode < 41) {
					listPosmTmp.add(listPosm.get(i));
					// lsPosm[i] = listPosm.get(i).getNama();
				}
			}
		}
		lsPosm = new String[listPosmTmp.size()];
		for (int i = 0; i < listPosmTmp.size(); i++) {
			lsPosm[i] = listPosmTmp.get(i).getNama();
		}
		ArrayAdapter<String> adapterPosm = new ArrayAdapter<String>(
				TabSurveyOutlet.this, android.R.layout.simple_spinner_item,
				lsPosm);
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

	private void loadListPosm() {
		posms.clear();
		outletPosm = new TtDKunjunganSurveyorOutletPosm();
		outletPosm.setKodeKunjungan(outlet.getKode()
				+ kategori.getKodeKategori()
				+ new SimpleDateFormat("ddMMyy").format(new Date()));
		posms = outletPosmDao.listByExample(outletPosm);
		adapter = new PosmOutletAdapter(this, posms);
		lsOutletPosm.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		lsOutletPosm.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int index, long arg3) {
				outletPosm = new TtDKunjunganSurveyorOutletPosm();
				outletPosm = posms.get(index);
				outletPosmDao.delete(outletPosm.getId());
				Toast.makeText(getApplicationContext(),
						"Outlet posm berhasil dihapus", Toast.LENGTH_LONG)
						.show();
				loadListPosm();
				return false;
			}
		});
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

		lblId.setText(outlet.getKode());
		lblName.setText(outlet.getNama() + ", " + outlet.getAlamat());
		txtOmzet.addTextChangedListener( new TextWatcher() {
			
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
		ArrayAdapter<String> adapterSubKategori = new ArrayAdapter<String>(
				TabSurveyOutlet.this, android.R.layout.simple_spinner_item,
				lsSubKategoriBarang);
		adapterSubKategori
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnSubKatagori.setAdapter(adapterSubKategori);
		spnSubKatagori.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int index, long arg3) {
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
		brandRak = new TmBrand();
		companyDao = new TmCompanyDao(getApplicationContext());
		listCompany = new ArrayList<TmCompany>();
		listCompany.clear();
		listCompany = (ArrayList<TmCompany>) getListCompany(subKategori);
		if (listCompany.size() <= 0) {
			spnBrand.setVisibility(View.GONE);
			lblBrandSku.setVisibility(View.GONE);
			spnCompany.setVisibility(View.GONE);
			lblCompanySku.setVisibility(View.GONE);
		} else {
			spnBrand.setVisibility(View.VISIBLE);
			lblBrandSku.setVisibility(View.VISIBLE);
			spnCompany.setVisibility(View.VISIBLE);
			lblCompanySku.setVisibility(View.VISIBLE);
		}

		lsCompany = new String[listCompany.size() + 1];

		for (int i = 0; i < listCompany.size() + 1; i++) {
			if (i == 0) {
				lsCompany[0] = "Pilih company";
			} else {
				lsCompany[i] = listCompany.get(i - 1).getNama();
			}

		}
		ArrayAdapter<String> adapterCompany = new ArrayAdapter<String>(
				TabSurveyOutlet.this, android.R.layout.simple_spinner_item,
				lsCompany);
		adapterCompany
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnCompany.setAdapter(adapterCompany);
		spnCompany.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int index, long arg3) {
				if (index == 0) {
					spnBrand.setVisibility(View.GONE);
					lblBrandSku.setVisibility(View.GONE);
				} else {
					spnBrand.setVisibility(View.VISIBLE);
					lblBrandSku.setVisibility(View.VISIBLE);
					company = new TmCompany();
					company = listCompany.get((index - 1));
					loadBrand(company);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				company = new TmCompany();
				loadBrand(company);
			}
		});
	}

	private void loadBrand(TmCompany companyRak) {
		brandRak = new TmBrand();
		brandRakDao = new TmBrandDao(getApplicationContext());
		listRakBrand = new ArrayList<TmBrand>();
		brandRak.setKodeCompany(companyRak.getKode());
		listRakBrand = getBrand(companyRak);
		lsBrandRak = new String[listRakBrand.size()];
		for (int i = 0; i < listRakBrand.size(); i++) {
			lsBrandRak[i] = listRakBrand.get(i).getNama();
			/*
			 * if(katBarang.getKode().equals(listRakBrand.get(i).getKode().substring
			 * (0, 2))){ lsBrandRak[i] = listRakBrand.get(i).getNama(); }
			 */
		}
		ArrayAdapter<String> adapterBrandRak = new ArrayAdapter<String>(
				TabSurveyOutlet.this, android.R.layout.simple_spinner_item,
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

	private ArrayList<TmBrand> getBrand(TmCompany companyRak) {
		ArrayList<TmBrand> listResult = new ArrayList<TmBrand>();
		TmKategoriCompanyBrand kcb = new TmKategoriCompanyBrand();
		TmKategoriCompanyBrandDao kcbDao = new TmKategoriCompanyBrandDao(
				getApplicationContext());
		kcb.setKode_company(companyRak.getKode());
		kcb.setKode_sub_kategori(subKategori.getKode());
		for (TmKategoriCompanyBrand k : kcbDao.listByExample(kcb)) {
			brandRak = new TmBrand();
			brandRak.setKode(k.getKode_brand());
			brandRak = brandRakDao.getByExample(brandRak);
			listResult.add(brandRak);
		}
		return listResult;
	}

	
	private List<TmCompany> getListCompany(TmSubKategoriBarang subKategori2) {
		TmKategoriCompanyBrand kcb = new TmKategoriCompanyBrand();
		TmKategoriCompanyBrandDao kcbDao = new TmKategoriCompanyBrandDao(
				getApplicationContext());
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

	private void initView() {
		txtSearch = (AutoCompleteTextView) findViewById(R.id.txtSearch);
		txtSearch.setTextColor(Color.BLACK);
		lblId = (TextView) findViewById(R.id.lblId);
		lblName = (TextView) findViewById(R.id.lblName);
		txtOmzet = (EditText) findViewById(R.id.txtOmzet);
		lblSubkatagori = (TextView) findViewById(R.id.lblSubkatagori);
		spnSubKatagori = (Spinner) findViewById(R.id.spnSubKatagori);
		lblCompanySku = (TextView) findViewById(R.id.lblCompanySku);
		spnCompany = (Spinner) findViewById(R.id.spnCompany);
		lblBrandSku = (TextView) findViewById(R.id.lblBrandSku);
		spnBrand = (Spinner) findViewById(R.id.spnBrand);
		lblBranded = (TextView) findViewById(R.id.lblBranded);
		spnPosm = (Spinner) findViewById(R.id.spnPosm);
		txtJumlah = (EditText) findViewById(R.id.txtJumlah);
		lsOutletPosm = (ListView) findViewById(R.id.lsPosm);
		btnTambahPosm = (Button) findViewById(R.id.btnTambahPosm);
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(TabSurveyOutlet.this,
				ActivityPilihKategori.class);
		intent.putExtra("surveyor", surveyor);
		intent.putExtra("outlet", outlet);
		intent.putExtra("locked", locked);
		intent.putExtra("xcoord", xcoord);
		intent.putExtra("ycoord", ycoord);
		intent.putExtra("kunjungan", kunjungan);
		startActivity(intent);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.batalanjut, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		switch (item.getItemId()) {
		case R.id.menuBatal:
			intent = new Intent(TabSurveyOutlet.this,
					ActivityPilihKategori.class);
			intent.putExtra("surveyor", surveyor);
			intent.putExtra("outlet", outlet);
			intent.putExtra("locked", locked);
			intent.putExtra("xcoord", xcoord);
			intent.putExtra("ycoord", ycoord);
			intent.putExtra("kunjungan", kunjungan);
			startActivity(intent);
			finish();
			break;

		case R.id.menuLanjut:
			intent = new Intent(TabSurveyOutlet.this, ActivityCreateRak.class);
			kunjungan.setOmzetKategori(getOmset(txtOmzet.getText().toString().replace(",", "")));
			intent.putExtra("surveyor", surveyor);
			intent.putExtra("outlet", outlet);
			intent.putExtra("kunjungan", kunjungan);
			intent.putExtra("kategori", kategori);
			intent.putExtra("locked", locked);
			intent.putExtra("xcoord", xcoord);
			intent.putExtra("ycoord", ycoord);
			startActivity(intent);
			saveOmset();
			finish();
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	private Double getOmset(String string) {
		double result = 0d;
		try {
			result = Double.valueOf(string);
		} catch (Exception e) {
			return result;
		}
		return result;
	}

	private void saveOmset() {
		TtDKunjunganSurveyorOmsetKatagori omsetKategori = new TtDKunjunganSurveyorOmsetKatagori();
		TtDKunjunganSurveyorOmsetKatagoriDao omsetKategoriDao = new TtDKunjunganSurveyorOmsetKatagoriDao(
				getApplicationContext());
		omsetKategori.setKodeKategori(kategori.getKodeKategori());
		omsetKategori.setKodeKunjungan(outlet.getKode()
				+ kategori.getKodeKategori()
				+ new SimpleDateFormat("ddMMyy").format(new Date()));
		omsetKategori.setKodeOutlet(outlet.getKode());
		TmKategoriBarang katBarang = new TmKategoriBarang();
		TmKategoriBarangDao katBarangDao = new TmKategoriBarangDao(
				getApplicationContext());
		katBarang.setKode(kategori.getKodeKategori());
		katBarang = katBarangDao.getByExample(katBarang);
		omsetKategori.setNamaKategori(katBarang.getNama());
		if (omsetKategoriDao.listByExample(omsetKategori).size() > 0) {
			omsetKategoriDao.update(omsetKategori);
			// Toast.makeText(getApplicationContext(),
			// "Omset kategori berhasil diupdate", Toast.LENGTH_LONG).show();
		} else {
			omsetKategoriDao.insert(omsetKategori);
			// Toast.makeText(getApplicationContext(),
			// "Omset kategori berhasil disimpan", Toast.LENGTH_LONG).show();
		}
	}

}
