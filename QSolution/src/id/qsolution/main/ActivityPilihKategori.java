package id.qsolution.main;

import java.util.ArrayList;
import id.qsolution.adapter.KatagoriAdapter;
import id.qsolution.models.DaftarOutletSurvey;
import id.qsolution.models.TmOutlet;
import id.qsolution.models.TmSurveyor;
import id.qsolution.models.TtMKunjunganSurveyor;
import id.qsolution.models.dao.DaftarOutletSurveyDao;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class ActivityPilihKategori extends Activity{

	private TextView lblId;
	private TextView lblName;
	private ListView lsKategori;
	private TmSurveyor surveyor;
	private TmOutlet outlet;
	private TtMKunjunganSurveyor kunjungan;
	private boolean locked;
	private String xcoord;
	private String ycoord;
	private DaftarOutletSurvey outletSurvey;
	private ArrayList<DaftarOutletSurvey> listOutletSurvey;
	private DaftarOutletSurveyDao outletSurveyDao;
	private KatagoriAdapter kategoriAdapter;
	private ArrayList<DaftarOutletSurvey> listselectedOutletSurvey;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pilih_kategori);
		Log.d("Activity ", this.getClass().getName());
		initView();
		surveyor = (TmSurveyor) getIntent().getSerializableExtra("surveyor");
		outlet = (TmOutlet) getIntent().getSerializableExtra("outlet");
		kunjungan = (TtMKunjunganSurveyor) getIntent().getSerializableExtra("kunjungan");
		locked = getIntent().getBooleanExtra("locked", false);
		xcoord = getIntent().getStringExtra("xcoord");
		ycoord = getIntent().getStringExtra("ycoord");
		loadForm();
	}
	
	
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(ActivityPilihKategori.this, OutletsActivity.class);
		intent.putExtra("xcoord", xcoord);
		intent.putExtra("ycoord", ycoord);
		intent.putExtra("locked", locked);
		intent.putExtra("surveyor", surveyor);
		//intent.putExtra("surveyor", surveyor);
		startActivity(intent);
		finish();
		/*Intent intent = new Intent( ActivityPilihKategori.this, TabOutletLamaActivityNew.class);
		intent.putExtra("mulai", kunjungan.getJamMulai());
		intent.putExtra("surveyor", surveyor);
		intent.putExtra("outlet", outlet);
		intent.putExtra("locked", locked);
		intent.putExtra("xcoord", xcoord);
		intent.putExtra("ycoord", ycoord);
		startActivity(intent);
		finish();*/
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.kembali, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menuKembali:
			
			Intent intent = new Intent(ActivityPilihKategori.this, OutletsActivity.class);
			intent.putExtra("xcoord", xcoord);
			intent.putExtra("ycoord", ycoord);
			intent.putExtra("locked", locked);
			//intent.putExtra("surveyor", surveyor);
			startActivity(intent);
			finish();
			/*Intent intent = new Intent( ActivityPilihKategori.this, TabOutletLamaActivityNew.class);
			intent.putExtra("mulai", kunjungan.getJamMulai());
			intent.putExtra("surveyor", surveyor);
			intent.putExtra("outlet", outlet);
			intent.putExtra("locked", locked);
			intent.putExtra("xcoord", xcoord);
			intent.putExtra("ycoord", ycoord);
			startActivity(intent);
			finish();*/
			break;
		default:
			return super.onOptionsItemSelected(item);
		}

		return true;
	}
	

	private void loadForm() {
		lblId.setText(outlet.getKode());
		lblName.setText(outlet.getNama()+", "+outlet.getAlamat());
		viewKategori();
	}

	private void viewKategori() {
		outletSurvey = new DaftarOutletSurvey();
		outletSurveyDao = new DaftarOutletSurveyDao(getApplicationContext());
		outletSurvey.setKodeOutlet(outlet.getKode());
		listOutletSurvey = new ArrayList<DaftarOutletSurvey>();
		listOutletSurvey = (ArrayList<DaftarOutletSurvey>) outletSurveyDao.listByExample(outletSurvey);
		listselectedOutletSurvey = new ArrayList<DaftarOutletSurvey>();
		for (DaftarOutletSurvey daftarOutletSurvey : listOutletSurvey) {
			if(!getStatus(daftarOutletSurvey)){
				listselectedOutletSurvey.add(daftarOutletSurvey);
			}
		}
	
		kategoriAdapter= new KatagoriAdapter(ActivityPilihKategori.this, listselectedOutletSurvey);
		lsKategori.setAdapter(kategoriAdapter);
		lsKategori.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				Intent intent = new Intent( ActivityPilihKategori.this, TabSurveyOutlet.class);
				intent.putExtra("kunjungan", kunjungan);
				intent.putExtra("surveyor", surveyor);
				intent.putExtra("kategori", listselectedOutletSurvey.get(index));
				intent.putExtra("outlet", outlet);
				intent.putExtra("locked", locked);
				intent.putExtra("xcoord", xcoord);
				intent.putExtra("ycoord", ycoord);
				startActivity(intent);
				finish();
			}
		});
		kategoriAdapter.notifyDataSetChanged();
		
	}

	private boolean getStatus(DaftarOutletSurvey daftarOutletSurvey) {
		try {
			if(daftarOutletSurvey.getStatus().equals("selesai"))
			{
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	private void initView() {
		lblId = (TextView) findViewById(R.id.lblId);
		lblName = (TextView) findViewById(R.id.lblName);
		lsKategori = (ListView) findViewById(R.id.lsKategori);
	}
}
