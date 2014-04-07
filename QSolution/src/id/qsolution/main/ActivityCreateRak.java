package id.qsolution.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import id.qsolution.adapter.RakAdapter;
import id.qsolution.models.DaftarOutletSurvey;
import id.qsolution.models.TmBrand;
import id.qsolution.models.TmCompany;
import id.qsolution.models.TmGroupRak;
import id.qsolution.models.TmOutlet;
import id.qsolution.models.TmRak;
import id.qsolution.models.TmSurveyor;
import id.qsolution.models.TtDKunjunganSurveyorRak;
import id.qsolution.models.TtMKunjunganSurveyor;
import id.qsolution.models.dao.DaftarOutletSurveyDao;
import id.qsolution.models.dao.TmBrandDao;
import id.qsolution.models.dao.TmCompanyDao;
import id.qsolution.models.dao.TmGroupRakDao;
import id.qsolution.models.dao.TmOutletDao;
import id.qsolution.models.dao.TmRakDao;
import id.qsolution.models.dao.TtDKunjunganSurveyorRakDao;
import id.qsolution.models.dao.TtMKunjunganSurveyorDao;
import android.annotation.SuppressLint;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class ActivityCreateRak extends TabActivity {

	private TmOutlet outlet;
	private TmSurveyor surveyor;
	private Spinner spnGroupRak;
	private Spinner spnJenisRak;
	private TmGroupRak groupRak;
	private TmGroupRakDao groupRakDao;
	private ArrayList<TmGroupRak> listGroupRak;
	private String[] lsGroupRak;
	private TmRak jenisRak;
	private TmRakDao jenisRakDao;
	private ArrayList<TmRak> listJenisRak;
	private String[] lsJenisRak;
	private TextView lblCompanyBranded;
	private TextView lblBrandedBranded;
	private Spinner spnBrandedBrand;
	private Spinner spnCompanyBrand;
	private TmCompany companyBrand;
	private TmCompanyDao companyBrandDao;
	private ArrayList<TmCompany> listCompanyBrand;
	private String[] lsCompanyBrand;
	private TmBrand brandRak;
	private TmBrandDao brandRakDao;
	private ArrayList<TmBrand> listRakBrand;
	private String[] lsBrandRak;
	private ListView lsPosmRak;
	private List<TtDKunjunganSurveyorRak> listPosmRak = new ArrayList<TtDKunjunganSurveyorRak>();
	private RakAdapter adapterPosmRak;
	private Button btnCreateRak;
	private TtDKunjunganSurveyorRak posmRak;
	private TtDKunjunganSurveyorRakDao posmRakDao;
	private TtDKunjunganSurveyorRakDao surveyorRakDao;
	private TtDKunjunganSurveyorRak surveyorRak;
	private EditText txtNamaRak;
	private boolean locked;
	private String xcoord;
	private String ycoord;
	private TtMKunjunganSurveyor kunjungan;
	private DaftarOutletSurvey kategori;
	private TtMKunjunganSurveyorDao surveyDao;
	private Double omsetKategori;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_rak);
		Log.d("Activity ", this.getClass().getName());
		setTitle("BUAT RAK");
		outlet = (TmOutlet) getIntent().getSerializableExtra("outlet");
		surveyor = (TmSurveyor) getIntent().getSerializableExtra("surveyor");
		kategori = (DaftarOutletSurvey) getIntent().getSerializableExtra("kategori");
		kunjungan = (TtMKunjunganSurveyor) getIntent().getSerializableExtra("kunjungan");
		surveyDao = new TtMKunjunganSurveyorDao(getApplicationContext());
		locked = getIntent().getBooleanExtra("locked", false);
		xcoord = getIntent().getStringExtra("xcoord");
		ycoord = getIntent().getStringExtra("ycoord");
		//omsetKategori = kunjungan.getOmzetKategori(); 
	/*	kunjungan =  getIntent().getStringExtra("kunjungan");*/
		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost.newTabSpec("Rak").setIndicator("Rak", res.getDrawable(R.drawable.ic_action_attach)).setContent(R.id.form1);
		tabHost.addTab(spec);
		spec = tabHost.newTabSpec("Daftar Rak").setIndicator("Daftar Rak", res.getDrawable(R.drawable.ic_action_select_all)).setContent(R.id.form2);
		tabHost.addTab(spec);
		tabHost.setCurrentTab(0);
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				if (getTabHost().getCurrentTabTag().equals("Daftar Rak")) { 
					loadRak();
				}
			}
		});
		initView();
		loadGroupRak();
		loadRak();
		setListener();
		//Toast.makeText(getApplicationContext(), "Kunjungan "+kunjungan.getKodeOutlet()+" kategori "+kategori.getKodeKategori(), Toast.LENGTH_LONG).show();
	}
	
	private String isEmptyBrand(String kode) {
		String result = "00";
		try {
			if(!kode.equals("") || kode != null ){
				result = kode;
			}
		} catch (Exception e) {
			return result;
		}
		return result;
	}
	
	private void setListener() {
		
		btnCreateRak.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					//if(adaRak()){
						surveyorRak = new TtDKunjunganSurveyorRak();
						surveyorRakDao = new TtDKunjunganSurveyorRakDao(getApplicationContext());
						surveyorRak.setKode(outlet.getKode());
						surveyorRak.setNomorUrut(String.format("%03d", getNo(surveyorRakDao.listByExample(surveyorRak))));
						if(brandRak!=null){
							surveyorRak.setKodeBrand(isEmptyBrand(brandRak.getKode()));
							surveyorRak.setNamaRak(jenisRak.getNama());
							surveyorRak.setKodeNamaBrand(brandRak.getNama());
						}
						surveyorRak.setKodeKunjungan(outlet.getKode() +kategori.getKodeKategori()+ new SimpleDateFormat("ddMMyy").format(new Date()));
						surveyorRak.setKodeRak(jenisRak.getKode());
						surveyorRak.setNamaRak(txtNamaRak.getText().toString() +" "+ jenisRak.getNama());
						surveyorRakDao.insert(surveyorRak);
						loadRak();
						txtNamaRak.setText("");
						Toast.makeText(getApplicationContext(), "Rak berhasil dibuat", Toast.LENGTH_LONG).show();
					/*}else{
						Toast.makeText(getApplicationContext(), "Selesaikan semua rak yang dibuat", Toast.LENGTH_LONG).show();
					}*/
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "Error "+e.getMessage(), Toast.LENGTH_LONG).show();
				}
			}
			private int getNo(List<TtDKunjunganSurveyorRak> list) {
				int result = 0;
				for(int i = 0; i <list.size();i++){
					if(i == (list.size()-1)){
						result = (int) list.get(i).getId();
					}
				}
				return result + 1;
			}
		});
	}

	private void loadRak() {
		posmRak = new TtDKunjunganSurveyorRak();
		posmRakDao = new TtDKunjunganSurveyorRakDao(getApplicationContext());
		posmRak.setKodeKunjungan(outlet.getKode() +kategori.getKodeKategori()+ new SimpleDateFormat("ddMMyy").format(new Date()));
		listPosmRak = posmRakDao.listByExample(posmRak);
		adapterPosmRak = new RakAdapter(this, listPosmRak);
		lsPosmRak.setAdapter(adapterPosmRak);
		lsPosmRak.setOnItemClickListener(new OnItemClickListener() {
			private Intent intent;
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int i,long arg3) {
				posmRak = listPosmRak.get(i);
				if(isRakBaru(posmRak.getStatus())){
					intent = new Intent( ActivityCreateRak.this, TabSurveySku.class);
					intent.putExtra("surveyor", surveyor);
					intent.putExtra("outlet", outlet);
					intent.putExtra("rak", posmRak);
					intent.putExtra("kategori", kategori);
					intent.putExtra("kunjungan", kunjungan);
					intent.putExtra("locked",locked);	
					intent.putExtra("xcoord",xcoord);	
					intent.putExtra("ycoord",ycoord);	
					startActivity(intent);
					finish();
					//startActivity(intent);
				} else{
					Toast.makeText(getApplicationContext(), "Survey rak telah selesai, lanjutkan dengan yang lain", Toast.LENGTH_LONG).show();
				}
			}

			private boolean isRakBaru(String status) {
				try {
					if(status.equals("selesai")) return false;
				} catch (Exception e) {
					return true;
				}
				return true;
			}
		});
		
		lsPosmRak.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int index, long arg3) {
				TtDKunjunganSurveyorRak rak = listPosmRak.get(index);
				TtDKunjunganSurveyorRakDao rakDao = new TtDKunjunganSurveyorRakDao(getApplicationContext());
				rakDao.delete(rak.getId());
				Toast.makeText(getApplicationContext(), "Rak berhasil dihapus", Toast.LENGTH_LONG).show();
				loadRak();
				return false;
			}
		});
	}
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 100:
			outlet = (TmOutlet) data.getSerializableExtra("outlet");
			surveyor = (TmSurveyor) getIntent().getSerializableExtra("surveyor");
			kunjungan = (TtMKunjunganSurveyor) getIntent().getSerializableExtra("kunjungan");
			kategori = (DaftarOutletSurvey) getIntent().getSerializableExtra("kategori");
			locked = getIntent().getBooleanExtra("locked", false);
			xcoord = getIntent().getStringExtra("xcoord");
			ycoord = getIntent().getStringExtra("ycoord");
			loadRak();
			break;
		
		default:
			break;
		}
	}
	
	private void initView() {
		txtNamaRak = (EditText) findViewById(R.id.txtNamaRak);
		btnCreateRak = (Button) findViewById(R.id.btnCreateRak);
		lsPosmRak = (ListView) findViewById(R.id.lsPosmRak);
		spnGroupRak = (Spinner) findViewById(R.id.spnGroupRak);
		spnJenisRak = (Spinner) findViewById(R.id.spnJenisRak);
		lblCompanyBranded = (TextView) findViewById(R.id.lblCompanyBranded);
		lblBrandedBranded = (TextView) findViewById(R.id.lblBrandedBranded);
		spnBrandedBrand = (Spinner) findViewById(R.id.spnBrandedBrand);
		spnCompanyBrand = (Spinner) findViewById(R.id.spnCompanyBrand);
	}

	private void loadGroupRak() {
		groupRak = new TmGroupRak();
		String typeOutlet = "";
		if(outlet.getKode().substring(0, 2).equalsIgnoreCase("MT")) {
			typeOutlet = "01";
			groupRak.setKodeTypeOutlet(typeOutlet);
		}else{
			typeOutlet = "02";
			groupRak.setKodeTypeOutlet(typeOutlet);
		}
		groupRakDao = new TmGroupRakDao(getApplicationContext());
		listGroupRak = new ArrayList<TmGroupRak>();
		listGroupRak = (ArrayList<TmGroupRak>) groupRakDao.listByExample(groupRak);
		lsGroupRak = new String[listGroupRak.size()];
		for (int i = 0; i < listGroupRak.size(); i++) {
			lsGroupRak[i] = listGroupRak.get(i).getNama();
		}
		ArrayAdapter<String> adapterGroupRak = new ArrayAdapter<String>(ActivityCreateRak.this, android.R.layout.simple_spinner_item,lsGroupRak);
		adapterGroupRak.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnGroupRak.setAdapter(adapterGroupRak);
		spnGroupRak.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int index, long arg3) {
				groupRak = new TmGroupRak();
				groupRak = listGroupRak.get(index);
				loadJenisRak(groupRak);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void loadJenisRak(TmGroupRak groupRak) {
		jenisRak = new TmRak();
		jenisRakDao = new TmRakDao(getApplicationContext());
		listJenisRak = new ArrayList<TmRak>();
		jenisRak.setKodeGroup(groupRak.getKode());
		listJenisRak = (ArrayList<TmRak>) jenisRakDao.listByExample(jenisRak);
		lsJenisRak = new String[listJenisRak.size()];
		for (int i = 0; i < listJenisRak.size(); i++) {
			lsJenisRak[i] = listJenisRak.get(i).getNama();
		}
		ArrayAdapter<String> adapterJenisRak = new ArrayAdapter<String>(
				ActivityCreateRak.this, android.R.layout.simple_spinner_item,
				lsJenisRak);
		adapterJenisRak.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnJenisRak.setAdapter(adapterJenisRak);
		spnJenisRak.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int index, long arg3) {
				jenisRak = new TmRak();
				jenisRak = listJenisRak.get(index);
				if (jenisRak.getBranded() == null) {
					spnBrandedBrand.setVisibility(View.GONE);
					spnCompanyBrand.setVisibility(View.GONE);
					lblBrandedBranded.setVisibility(View.GONE);
					lblCompanyBranded.setVisibility(View.GONE);
				} else {
					loadCompanyRak(jenisRak);
				}
				// Toast.makeText(getApplicationContext(),
				// "branded "+jenisRak.getBranded(), Toast.LENGTH_LONG).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	}

	private void loadCompanyRak(TmRak rak) {
		spnBrandedBrand.setVisibility(View.VISIBLE);
		spnCompanyBrand.setVisibility(View.VISIBLE);
		lblBrandedBranded.setVisibility(View.VISIBLE);
		lblCompanyBranded.setVisibility(View.VISIBLE);
		companyBrand = new TmCompany();
		companyBrand.setKode(rak.getBranded());
		companyBrandDao = new TmCompanyDao(getApplicationContext());
		listCompanyBrand = new ArrayList<TmCompany>();
		listCompanyBrand = (ArrayList<TmCompany>) companyBrandDao.listByExample(companyBrand);
		lsCompanyBrand = new String[listCompanyBrand.size()];
		for (int i = 0; i < listCompanyBrand.size(); i++) {
			lsCompanyBrand[i] = listCompanyBrand.get(i).getNama();
		}
		ArrayAdapter<String> adapterCompany = new ArrayAdapter<String>(
				ActivityCreateRak.this, android.R.layout.simple_spinner_item,
				lsCompanyBrand);
		adapterCompany
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnCompanyBrand.setAdapter(adapterCompany);
		spnCompanyBrand.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int index, long arg3) {
				companyBrand = new TmCompany();
				companyBrand = listCompanyBrand.get(index);
				loadBrandRak(companyBrand);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	private void loadBrandRak(TmCompany companyRak) {
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
				ActivityCreateRak.this, android.R.layout.simple_spinner_item,
				lsBrandRak);
		adapterBrandRak
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnBrandedBrand.setAdapter(adapterBrandRak);
		spnBrandedBrand.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int index, long arg3) {
				brandRak = listRakBrand.get(index);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.selesai, menu);

		return true;
	}
	
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.mnuEnd:
			if(isRakExits()){
				surveyDao = new TtMKunjunganSurveyorDao(getApplicationContext());
				//Toast.makeText(getApplicationContext(), "omset "+kunjungan.getOmzet()+" omset kategori " + kunjungan.getOmzetKategori(), Toast.LENGTH_LONG).show();
				long id = surveyDao.listAll().size() + 1;
				kunjungan.setId(id);
				kunjungan.setKode(outlet.getKode() + kategori.getKodeKategori() + new SimpleDateFormat("ddMMyy").format(new Date()));
				kunjungan.setKodeKategori(kategori.getKodeKategori());
				kunjungan.setKodeStatus("01");
				kunjungan.setName(outlet.getNama());
				kunjungan.setKodeOutlet(outlet.getKode());
				kunjungan.setKodeSurveyor(surveyor.getKode());
				kunjungan.setJamSelesai(new SimpleDateFormat("HH:mm:ss").format(new Date()));
				kunjungan.setTglSurveySkrg(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
				kunjungan.setTglSurveyBerikut(getTanggalBerikut());
				kunjungan.setWaktuOperasi(getWaktuOperasi());
				kunjungan.setXcoord(getX(xcoord));
				kunjungan.setYcoord(getY(ycoord));
				kunjungan.setOmzetKategori(getOmsetKategori(kunjungan.getOmzetKategori()));
				surveyDao.insert(kunjungan);
				DaftarOutletSurveyDao daftarOutletDao = new DaftarOutletSurveyDao(getApplicationContext());
				kategori.setStatus("selesai");
				kategori.setSudahSurvey("1");
				daftarOutletDao.update(kategori);
				kategori.setKodeOutlet(outlet.getKode());
				if(validateOutlet()){
					TmOutletDao outletDao =new TmOutletDao(getApplicationContext());
					outlet.setStatus("selesai");
					outletDao.update(outlet);
					Toast.makeText(getApplicationContext(), "Data berhasil disimpan", Toast.LENGTH_LONG).show();
					loadOutlets();
					
				}else{
					loadKategori();
				}
			}else{
				Toast.makeText(getApplicationContext(), "Masih ada rak yang belum selesai diaudit", Toast.LENGTH_LONG).show();
			}
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private Double getOmsetKategori(Double o) {
		Double  dValue= 0d;
		try {
			if(!o.isNaN()) {
				dValue = o;
			}
		} catch (Exception e) {
			dValue= 0d;
		}
		return dValue;
	}

	private Double getY(String value) {
		double result = 0d;
		try {
			result = Double.valueOf(value);
		} catch (Exception e) {
			return result;
		}
		return result;
	}
	
	private Double getX(String value) {
		double result = 0d;
		try {
			result = Double.valueOf(value);
		} catch (Exception e) {
			return result;
		}
		return result;
	}

	private String getTanggalBerikut() {
		String untildate= new SimpleDateFormat("yyyy-MM-dd").format(new Date());//can take any date in current format    
		SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );   
		String convertedDate = "";
		Calendar cal = Calendar.getInstance();    
		try {
			cal.setTime( dateFormat.parse(untildate));
			if(outlet.getKode().substring(0, 2).equals("MT")){
				cal.add( Calendar.DATE, 7 ); 
			}else{
				cal.add( Calendar.DATE, 30 ); 
			}
			convertedDate = dateFormat.format(cal.getTime()); 
		} catch (ParseException e) {
			Log.d("error getTanggalBerikut ", e.getMessage());
		} 
		//System.out.println("Date increase by one.."+convertedDate);
		return convertedDate;
	}

	private String getWaktuOperasi() {
		String dateStart = kunjungan.getJamMulai();
		String dateStop = new SimpleDateFormat("HH:mm:ss").format(new Date());
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date d1 = null;
		Date d2 = null;
		long diffMinutes = 0l;
		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);
			long diff = d2.getTime() - d1.getTime();
			diffMinutes = diff / (60 * 1000) % 60;
			//in milliseconds
			/*long diff = d2.getTime() - d1.getTime();
			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);*/
 
			/*System.out.print(diffDays + " days, ");
			System.out.print(diffHours + " hours, ");
			System.out.print(diffMinutes + " minutes, ");
			System.out.print(diffSeconds + " seconds.");*/
 
		} catch (Exception e) {
			Log.d("Activity Create Rak ", e.getMessage());
		}
		return String.valueOf(diffMinutes);
	}

	/**
	 * Get a diff between two dates
	 * @param date1 the oldest date
	 * @param date2 the newest date
	 * @param timeUnit the unit in which you want the diff
	 * @return the diff value, in the provided unit
	 */
	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}

	private boolean isRakExits() {
		TtDKunjunganSurveyorRak rak = new TtDKunjunganSurveyorRak();
		TtDKunjunganSurveyorRakDao rakDao = new TtDKunjunganSurveyorRakDao(getApplicationContext());
		rak.setStatus("selesai");
		if(rakDao.listByExample(rak).size() < listPosmRak.size()){
			return false;
		}
		return true;
	}

	private void loadKategori() {
		Intent intent = new Intent( ActivityCreateRak.this, ActivityPilihKategori.class);
		intent.putExtra("surveyor", surveyor);
		intent.putExtra("outlet", outlet);
		intent.putExtra("locked",locked);	
		intent.putExtra("xcoord",xcoord);	
		intent.putExtra("ycoord",ycoord);	
		intent.putExtra("kunjungan",kunjungan);
		startActivity(intent);
		finish();
		
	}

	private boolean validateOutlet() {
		DaftarOutletSurvey doR = new DaftarOutletSurvey();
		DaftarOutletSurvey doP = new DaftarOutletSurvey();
		DaftarOutletSurveyDao surveyDao = new DaftarOutletSurveyDao(getApplicationContext());
		doR.setKodeOutlet(outlet.getKode());
		doP.setKodeOutlet(outlet.getKode());
		doP.setStatus("selesai");
		int a = surveyDao.listByExample(doR).size();
		int b = surveyDao.listByExample(doP).size();
		if(b<a){
			return false;
		}
		return true;
	}

	private void loadOutlets() {
		Intent intent = new Intent( ActivityCreateRak.this, OutletsActivity.class);
		intent.putExtra("xcoord", xcoord);
		intent.putExtra("ycoord", ycoord);
		intent.putExtra("locked", locked);
		startActivityForResult(intent, 100);
		finish();
	}


		
}
