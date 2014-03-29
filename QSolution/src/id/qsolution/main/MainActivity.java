package id.qsolution.main;


import id.qsolution.models.TmSurveyor;
import id.qsolution.models.dao.TmSurveyorDao;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	int[] iconItem = new int[] { R.drawable.tasklist, 
			R.drawable.sync, R.drawable.settings,
			R.drawable.exit };
	private String[] lstState;
	private String[] lstDesc;
	/*private TmOutletDao outletDao;
	private TmOutlet outlet;
	private GenericRespons<TmResult> outlets = new OutletsRespons();*/
	private TmSurveyor surveyour;
	private TmSurveyorDao auditorDao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Home QSolution");
		setContentView(R.layout.activity_main);
		validateUser();		
	}
	
	private void validateUser() {
		surveyour = new TmSurveyor();
		auditorDao = new TmSurveyorDao(getApplicationContext());
		if(auditorDao.listAll().size() > 0){
			lstState = getResources().getStringArray(R.array.menuHome);
			lstDesc = getResources().getStringArray(R.array.menuHomeDesc);
			setListAdapter(new MenuAdapter());
			surveyour = auditorDao.listAll().get(0);
			//intent.putExtra("outlet", outletSelected);
		} else {
			Intent intent = new Intent(MainActivity.this, LoginActivity.class);
			startActivity(intent);
			finish();
		}
	}
	
	public void longToast(CharSequence message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}
	
	

	public class MenuAdapter extends ArrayAdapter<CharSequence> {

		public MenuAdapter() {
			super(MainActivity.this, R.layout.menu_row, lstState);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = getLayoutInflater();
			View row = inflater.inflate(R.layout.menu_row, parent, false);
			TextView header = (TextView) row.findViewById(R.id.title);
			TextView detail = (TextView) row.findViewById(R.id.desc);
			ImageView icon = (ImageView) row.findViewById(R.id.list_image);

			icon.setImageResource(iconItem[position]);
			header.setText(lstState[position]);
			detail.setText(lstDesc[position]);

			return (row);
		}
	}

	public void onListItemClick(ListView parent, View v, int position, long id) {
		Intent intent;
		switch (position) {
		case 0:
			intent = new Intent(MainActivity.this, SurveyHomeActivity.class);
			intent.putExtra("surveyor", surveyour);
			startActivity(intent);
			//finish();
			break;

		case 1:
			intent = new Intent(MainActivity.this, SyncActivity.class);
			intent.putExtra("surveyor", surveyour);
			startActivity(intent);
			/*Toast.makeText(getApplicationContext(), "position " + position,
					Toast.LENGTH_LONG).show();*/
			break;

		case 2:
			Toast.makeText(getApplicationContext(), "position " + position,
					Toast.LENGTH_LONG).show();
			break;

		case 3:
			finish();
			break;

		/*case 4:
			Toast.makeText(getApplicationContext(), "position " + position,
					Toast.LENGTH_LONG).show();
			break;*/

		default:
			break;
		}
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.logout, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menuLogout:
			TmSurveyorDao surveyourDao = new TmSurveyorDao(getApplicationContext());
			surveyourDao.deleteAll();
			Intent intent = new Intent(MainActivity.this, LoginActivity.class);
			startActivity(intent);
			finish();
			break;
	
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	@Override
	public void onBackPressed() {
		
	}
}
