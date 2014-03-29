package id.qsolution.main;

import id.qsolution.models.TmSurveyor;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class SurveyHomeActivity extends ListActivity {

	int[] iconItem = new int[] { 
			R.drawable.location,
			R.drawable.survey, 
			R.drawable.reader,
			R.drawable.upload };
	private String[] lstState;
	private String[] lstDesc;
	private String xcoord = "0";
	private String ycoord = "0";
	private boolean locked;
	private TmSurveyor surveyor;
	//private TmOutlet outlet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("SURVEY TRACKING");
		setContentView(R.layout.activity_main);
		surveyor = new TmSurveyor();
		surveyor = (TmSurveyor) getIntent().getSerializableExtra("surveyor");
		locked = getIntent().getBooleanExtra("locked", false);
		xcoord =  getIntent().getStringExtra("xcoord");
		ycoord =  getIntent().getStringExtra("ycoord");
		lstState = getResources().getStringArray(R.array.menuHomeSurvey);
		lstDesc = getResources().getStringArray(R.array.menuHomeSurveyDesc);
		setListAdapter(new MenuAdapter());
	}

	public class MenuAdapter extends ArrayAdapter<CharSequence> {

		public MenuAdapter() {
			super(SurveyHomeActivity.this, R.layout.menu_row, lstState);
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
		Intent intent = new Intent();
		switch (position) {
		case 0:
			intent = new Intent(SurveyHomeActivity.this, LocationActivity.class);
			intent.putExtra("xcoord", xcoord);
			intent.putExtra("ycoord", ycoord);
			intent.putExtra("locked", locked);
			startActivityForResult(intent, 100);
			break;

		case 1:
			intent = new Intent(SurveyHomeActivity.this, OutletsActivity.class);
			intent.putExtra("xcoord", xcoord);
			intent.putExtra("ycoord", ycoord);
			intent.putExtra("locked", locked);
			intent.putExtra("surveyor", surveyor);
			startActivity(intent);
			finish();
			break;

		case 2:
			intent = new Intent(SurveyHomeActivity.this, SurveyedOutletActivity.class);
			intent.putExtra("surveyor", surveyor);
			startActivity(intent);
			break;

		case 3:
			intent = new Intent(SurveyHomeActivity.this, UploadDataActivity.class);
			intent.putExtra("surveyor", surveyor);
			startActivity(intent);
			break;

		default:
			break;
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
			break;
			
		case 200:
			surveyor = new TmSurveyor();
			surveyor = (TmSurveyor) data.getSerializableExtra("surveyor");
			xcoord = data.getStringExtra("xcoord");
			ycoord = data.getStringExtra("ycoord");
			locked = data.getBooleanExtra("locked", false);
			break;
		
		default:
			break;
		}
	}
	

}
