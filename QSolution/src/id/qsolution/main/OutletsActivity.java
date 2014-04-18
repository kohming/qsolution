package id.qsolution.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import id.qsolution.adapter.OutletsAdapter;
import id.qsolution.models.TmOutlet;
import id.qsolution.models.TmSurveyor;
import id.qsolution.models.dao.TmOutletDao;
import id.qsolution.models.dao.TmSurveyorDao;

public class OutletsActivity extends Activity {

	// private EditText txtFind;
	private ListView lsOutlet;
	private List<TmOutlet> outlets = new ArrayList<TmOutlet>();
	private TmOutletDao outletDao;
	private OutletsAdapter adapter;
	private TmSurveyor surveyor;
	private  boolean locked;
	private String xcoord = "0";
	private String ycoord = "0";
	@SuppressWarnings("unused")
	private String mulai;
	@SuppressWarnings("unused")
	private TmOutlet outlet;
	// private Status status;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_outlets);
		Log.d("Activity ", this.getClass().getName());
		setTitle("DAFTAR OUTLET");
		locked = getIntent().getBooleanExtra("locked", false);
		xcoord =  getIntent().getStringExtra("xcoord");
		ycoord =  getIntent().getStringExtra("ycoord");
		surveyor = new TmSurveyor();
		surveyor = (TmSurveyor) getIntent().getSerializableExtra("surveyor");
		initView();
		outletDao = new TmOutletDao(getApplicationContext());
		loadOutlet();
		setListener();
	}
	
	@Override
	public void onBackPressed() {
		TmSurveyorDao gd = new TmSurveyorDao(getApplicationContext());
		surveyor = gd.listAll().get(0);
		Intent intent = new Intent( OutletsActivity.this, SurveyHomeActivity.class);
		intent.putExtra("locked", locked);
		intent.putExtra("xcoord", xcoord);
		intent.putExtra("ycoord", ycoord);
		intent.putExtra("surveyor", surveyor);
		startActivity(intent);
		finish();
	}

	private void setListener() {
		
		lsOutlet.setOnItemClickListener(new OnItemClickListener() {
			private int index;

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int i,
					long arg3) {
				index = i;
				AlertDialog.Builder alert = new AlertDialog.Builder(OutletsActivity.this);
				alert.setTitle("Pernyataan"); // Set Alert dialog//
				alert.setMessage("Outlet akan dikunjungi atau tidak dikunjungi ?"); 
				alert.setPositiveButton("Kunjungi",
						new DialogInterface.OnClickListener() {
							private Intent intent;
							public void onClick(DialogInterface dialog, int whichButton) {
								try {
									TmOutlet outletSelected = (TmOutlet) adapter.getItem(index);
									if (isNullOrEmpty(outletSelected.getNama())) {
										intent = new Intent( OutletsActivity.this, TabSurveyOutletActivity.class);
										intent.putExtra("mulai", new SimpleDateFormat("HH:mm:ss").format(new Date()));
										intent.putExtra("surveyor", surveyor);
										intent.putExtra("outlet", outletSelected);
										intent.putExtra("locked", locked);
										intent.putExtra("xcoord", xcoord);
										intent.putExtra("ycoord", ycoord);
										startActivity(intent);
										finish();
									} else {
										intent = new Intent( OutletsActivity.this, TabOutletLamaActivityNew.class);
										intent.putExtra("mulai", new SimpleDateFormat("HH:mm:ss").format(new Date()));
										intent.putExtra("surveyor", surveyor);
										intent.putExtra("outlet", outletSelected);
										intent.putExtra("locked", locked);
										intent.putExtra("xcoord", xcoord);
										intent.putExtra("ycoord", ycoord);
										startActivity(intent);
										finish();
									}
								} catch (Exception e) {
									Log.i("error " + e.getMessage(),
											e.getMessage());
								}
							}
						});
				alert.setNegativeButton("Tidak dikunjungi",
						new DialogInterface.OnClickListener() {

							private Intent intent;

							public void onClick(DialogInterface dialog,
									int whichButton) {
								try {
									TmOutlet outletSelected = (TmOutlet) adapter.getItem(index);
									intent = new Intent(OutletsActivity.this, ReasonActivity.class);
									intent.putExtra("surveyor", surveyor);
									intent.putExtra("locked", locked);
									intent.putExtra("xcoord", xcoord);
									intent.putExtra("ycoord", ycoord);
									intent.putExtra("outlet", outletSelected);
									startActivity(intent);
									finish();
								} catch (Exception e) {
									Log.i("error " + e.getMessage(),
											e.getMessage());
								}
								dialog.cancel();
							}
						}); // End of alert.setNegativeButton
				AlertDialog alertDialog = alert.create();
				alertDialog.show();
				
			}
		});
	}

	private void loadOutlet() {
		outlets.clear();
		for (TmOutlet o : outletDao.listAll()) {
			if (isNullOrEmpty(o.getStatus())) {
				outlets.add(o);
			}
		}
		adapter = new OutletsAdapter(this, outlets);
		lsOutlet.setAdapter(adapter);
		lsOutlet.invalidateViews();
		adapter.notifyDataSetChanged();
	}

	private boolean isNullOrEmpty(String txt) {
		try {
			if (txt == null || toString().equals("")) {
				return true;
			}
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	private void initView() {
		lsOutlet = (ListView) findViewById(R.id.lsOutlet);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.close, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_close:
			Intent intent = new Intent();
			intent.putExtra("locked", locked);
			intent.putExtra("xcoord", xcoord);
			intent.putExtra("ycoord", ycoord);
			setResult(100, intent);
			finish();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 100:
			xcoord = data.getStringExtra("xcoord");
			ycoord = data.getStringExtra("ycoord");
			locked = data.getBooleanExtra("locked", false);
			loadOutlet();
			break;

		default:
			break;
		}
	}
}
