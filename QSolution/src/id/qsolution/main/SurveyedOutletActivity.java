package id.qsolution.main;

import java.util.ArrayList;
import java.util.List;

import id.qsolution.adapter.OutletsAdapter;
import id.qsolution.models.TmOutlet;
import id.qsolution.models.TmSurveyor;
import id.qsolution.models.dao.TmOutletDao;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class SurveyedOutletActivity extends Activity {

	private TmSurveyor surveyor;
	private ListView lsOutlet;
	private TmOutletDao outletDao;
	private List<TmOutlet> outlets = new ArrayList<TmOutlet>();
	private OutletsAdapter adapter;
	private TmOutlet outlet;

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
	}
	
	@Override
	protected void onStart() {
		super.onStart();
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
		
		for(TmOutlet o : outletDao.listAll()){
			if(!isNull(o.getStatus())){
				outlets.add(o);
			}
		}
		//outlets = outletDao.listByExample(outlet);
		adapter = new OutletsAdapter(this, outlets);
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
