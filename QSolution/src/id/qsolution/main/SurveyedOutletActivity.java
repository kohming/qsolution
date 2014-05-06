package id.qsolution.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import id.qsolution.adapter.KunjunganOutletsAdapter;
import id.qsolution.adapter.OutletsAdapter;
import id.qsolution.models.TmOutlet;
import id.qsolution.models.TmSurveyor;
import id.qsolution.models.TtMKunjunganSurveyor;
import id.qsolution.models.dao.TmOutletDao;
import id.qsolution.models.dao.TtMKunjunganSurveyorDao;
import id.qsolution.pojos.Kunjungan;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SurveyedOutletActivity extends Activity {

	private TmSurveyor surveyor;
	private ListView lsOutlet;
	private TmOutletDao outletDao;
	private List<TmOutlet> outlets = new ArrayList<TmOutlet>();
	private KunjunganOutletsAdapter adapter;
	private TmOutlet outlet;
	private TtMKunjunganSurveyor kunjungan;
	private TtMKunjunganSurveyorDao kunjunganDao;
	private ArrayList<TtMKunjunganSurveyor> listKategori;
	private ArrayAdapter<Kunjungan> kunjunganAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_outlets);
		setTitle("SURVEYED OUTLETS");
		surveyor = new TmSurveyor();
		surveyor = (TmSurveyor) getIntent().getSerializableExtra("surveyor");
		initView();
		outlet = new TmOutlet();
		outletDao = new TmOutletDao(getApplicationContext());
		kunjungan = new TtMKunjunganSurveyor();
		kunjunganDao = new TtMKunjunganSurveyorDao(getApplicationContext());
		loadOutlet();
		
	}
	

	private void initView() {
		lsOutlet = (ListView) findViewById(R.id.lsOutlet);
	}
	
	
	/*private boolean isNullOrEmpty(String txt) {
		try {
			if (txt == null || toString().equals("")) {
				return true;
			}
		} catch (Exception e) {
			return true;
		}
		
		return false;
	}
	*/
	private void loadOutlet() {
		
		/*ArrayList<String> lst = new ArrayList<String>();
	
		for (TtMKunjunganSurveyor k : kunjunganDao.listAll()) {
			lst.add(k.getKodeOutlet());
		}
		
		Object[] st = lst.toArray();
	      for (Object s : st) {
	        if (lst.indexOf(s) != lst.lastIndexOf(s)) {
	            lst.remove(lst.lastIndexOf(s));
	         }
	      }
		for (String s : lst) {
			outlet = new TmOutlet();
			outlet.setKode(s);
			outlet = outletDao.getByExample(outlet);
			outlets.add(outlet);
		}
		
		for(TmOutlet o : outletDao.listAll()){
			if(!isNull(o.getStatus())){
				outlets.add(o);
			}
		}*/
		//outlets = outletDao.listByExample(outlet);
		kunjungan = new TtMKunjunganSurveyor();
		kunjunganDao = new TtMKunjunganSurveyorDao(getApplicationContext());
		kunjungan.setStatus("upload");
		adapter = new KunjunganOutletsAdapter(this, kunjunganDao.listByExample(kunjungan));
		lsOutlet.setAdapter(adapter);
		lsOutlet.invalidateViews();
		adapter.notifyDataSetChanged();
	}

	private boolean isNull(String status) {
		try {
			if(status.equals("") || status == null) return true;
		} catch (Exception e) {
			return true;
		}
		return false;
	}
}
