package id.qsolution.main;

import java.util.ArrayList;
import java.util.List;
import id.qsolution.models.TmBrand;
import id.qsolution.models.TmCompany;
import id.qsolution.models.TmKlasifikasiOutlet;
import id.qsolution.models.TmOutlet;
import id.qsolution.models.TmPosm;
import id.qsolution.models.TmStatusKunjungan;
import id.qsolution.models.TmSumberProduk;
import id.qsolution.models.TmSurveyor;
import id.qsolution.models.TtDKunjunganSurveyorRakPosm;
import id.qsolution.models.dao.TmBrandDao;
import id.qsolution.models.dao.TmCompanyDao;
import id.qsolution.models.dao.TmKlasifikasiOutletDao;
import id.qsolution.models.dao.TmPosmDao;
import id.qsolution.models.dao.TmSumberProdukDao;
import id.qsolution.models.dao.TtDKunjunganSurveyorRakPosmDao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

public class BrandActivity extends Activity {

	private Spinner spnCompany;
	private Spinner spnBrand;
	private Spinner spnSumberProduk;
	private Spinner spnJenisPosm;
	private TmSurveyor surveyor;
	private TmOutlet outlet;
	private TmCompany company;
	private TmCompanyDao companyDao;
	private List<TmCompany> listCompany;
	private String[] lsCompany;
	private TmBrand brand;
	private TmBrandDao brandDao;
	private List<TmBrand> listBrand;
	private String[] lsBrand;
	private TmKlasifikasiOutlet klasifikasi;
	private TmKlasifikasiOutletDao klasifikasiDao;
	private ArrayList<TmKlasifikasiOutlet> listKlasifikasi;
	private TextView lblSumberProduk;
	private TmPosm posm;
	private TmPosmDao posmDao;
	private List<TmPosm> listPosm;
	private String[] lsPosm;
	private TmSumberProduk sumber;
	private TmSumberProdukDao sumberDao;
	private List<TmSumberProduk> listSumber;
	private String[] lsSumber;
	private Button btnTambah;
	private TmStatusKunjungan kunjungan;
	private EditText txtJumlah;
	private String kodeKunjungan;
	private String rak;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_brand);
		setTitle("POSM BRAND");
		initView();
		surveyor = (TmSurveyor) getIntent().getSerializableExtra("surveyor");
		outlet = (TmOutlet) getIntent().getSerializableExtra("outlet");
		company = (TmCompany) getIntent().getSerializableExtra("company");
		posm = (TmPosm) getIntent().getSerializableExtra("posm");
		kodeKunjungan = getIntent().getStringExtra("kunjungan");
		rak = getIntent().getStringExtra("rak");
		if(!gt()){
			lblSumberProduk.setVisibility(View.GONE);
			spnSumberProduk.setVisibility(View.GONE);
		}
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		loadBrand();
		loadPosm();
		loadSumber();
	}

	private void loadSumber() {
		sumber = new TmSumberProduk();
		sumberDao = new TmSumberProdukDao(getApplicationContext());
		listSumber= new ArrayList<TmSumberProduk>();
		listSumber = sumberDao.listAll();
		lsSumber = new String[listSumber.size()];
		for (int i = 0;i< listSumber.size();i++) {
			lsSumber[i] = listSumber.get(i).getNama();
		}
		ArrayAdapter<String> adapterSumber = new ArrayAdapter<String>(
				BrandActivity.this,
				android.R.layout.simple_spinner_item, lsSumber);
		adapterSumber.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnSumberProduk.setAdapter(adapterSumber);
		spnSumberProduk.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				sumber = listSumber.get(arg2);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	private void loadPosm() {
		posm = new TmPosm();
		posmDao = new TmPosmDao(getApplicationContext());
		listPosm = new ArrayList<TmPosm>();
		listPosm = posmDao.listAll();
		lsPosm = new String[listPosm.size()+1];
		for (int i = 0;i< listPosm.size() + 1;i++) {
			if(i==0){
				lsPosm[0] = "";
			}else{
				lsPosm[i] = listPosm.get(i-1).getNama();
			}
		}
		ArrayAdapter<String> adapterPosm = new ArrayAdapter<String>(
				BrandActivity.this,
				android.R.layout.simple_spinner_item, lsPosm);
		adapterPosm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnJenisPosm.setAdapter(adapterPosm);
		spnJenisPosm.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int index, long arg3) {
				//posm = listPosm.get(arg2-1);
				if(index > 0){
					posm = listPosm.get(index-1);
				}else{
					posm = new TmPosm();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private boolean gt() {
		try {
			klasifikasi = new TmKlasifikasiOutlet();
			klasifikasiDao = new TmKlasifikasiOutletDao(getApplicationContext());
			listKlasifikasi = new ArrayList<TmKlasifikasiOutlet>();
			klasifikasi.setKode(outlet.getKodeKlasifikasi());
			klasifikasi = klasifikasiDao.getByExample(klasifikasi);
			String kodeType = klasifikasi.getKodeTipe();
			if(kodeType.equals("02")) return true;
		} catch (Exception e) {
			return false;
		}
	
		return false;
	}

	private void loadBrand() {
		companyDao = new TmCompanyDao(getApplicationContext());
		listCompany = new ArrayList<TmCompany>();
		if(brand == null){
			listCompany= companyDao.listAll();
		}else{
			listCompany= companyDao.listByExample(company);
		}
		lsCompany = new String[listCompany.size()];
		lsCompany = new String[listCompany.size()];
		for (int i = 0;i< listCompany.size();i++) {
			lsCompany[i] = listCompany.get(i).getNama();
		}
		ArrayAdapter<String> adapterCompany = new ArrayAdapter<String>(
				BrandActivity.this,
				android.R.layout.simple_spinner_item, lsCompany);
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
		
		
		
		btnTambah.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!isNullOrEmpty(txtJumlah)){
					TtDKunjunganSurveyorRakPosm rakPosm = new TtDKunjunganSurveyorRakPosm();
					TtDKunjunganSurveyorRakPosmDao rakPosmDao = new TtDKunjunganSurveyorRakPosmDao(getApplicationContext());
					rakPosm.setKode(outlet.getKode());
					rakPosm.setKodeKunjungan(kodeKunjungan);
					rakPosm.setKodeBrand(isEmptyBrand(brand.getKode()));
					rakPosm.setKodePosm(isEmptyBrand(posm.getKode()));
					rakPosm.setNomorUrut(rak);
					rakPosm.setJumlah(Integer.valueOf(txtJumlah.getText().toString()));
					rakPosmDao.insert(rakPosm);
				}
				Intent inten = new Intent();
				inten.putExtra("surveyor", surveyor);
				inten.putExtra("outlet", outlet);
				inten.putExtra("brand", brand);
				BrandActivity.this.setResult(100, inten);
				finish();
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
	
	private boolean isNullOrEmpty(EditText txt) {
		if (txt.getText().toString() == null || txt.getText().toString().equals("")) {
			return true;
		}
		return false;
	}

	
	private void loadBrand(TmCompany company) {
		brand = new TmBrand();
		brandDao = new TmBrandDao(getApplicationContext());
		listBrand = new ArrayList<TmBrand>();
		brand.setKodeCompany(company.getKode());
		listBrand= brandDao.listByExample(brand);
		lsBrand = new String[listBrand.size()];
		for (int i = 0;i< listBrand.size();i++) {
			lsBrand[i] = listBrand.get(i).getNama();
		}
		ArrayAdapter<String> adapterBrand = new ArrayAdapter<String>(
				BrandActivity.this,
				android.R.layout.simple_spinner_item, lsBrand);
		adapterBrand.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnBrand.setAdapter(adapterBrand);
		spnBrand.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int index, long arg3) {
				brand = listBrand.get(index);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void initView() {
		spnCompany = (Spinner) findViewById(R.id.spnCompany);
		spnBrand = (Spinner) findViewById(R.id.spnBrand);
		spnSumberProduk = (Spinner) findViewById(R.id.spnSumberProduk);
		spnJenisPosm = (Spinner) findViewById(R.id.spnJenisPosm);
		lblSumberProduk = (TextView) findViewById(R.id.lblSumberProduk);
		btnTambah = (Button) findViewById(R.id.btnTambah);
		txtJumlah = (EditText) findViewById(R.id.txtJumlah);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent inten = new Intent();
		inten.putExtra("surveyor", surveyor);
		inten.putExtra("outlet", outlet);
		inten.putExtra("brand", brand);
		BrandActivity.this.setResult(100, inten);
		finish();
	}
}

