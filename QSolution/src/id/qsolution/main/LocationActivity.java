package id.qsolution.main;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


public class LocationActivity extends Activity{

	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters
	private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in Milliseconds
	protected LocationManager locationManager; 
	protected Button btnGetLocation;
	private CheckBox checkLockLocation;
	private EditText txtLon;
	private EditText txtLat;
	private MyLocationListener myLocationListener;
	private boolean locked;
	private String xcoord;
	private String ycoord;
	private int idx;
	private int counter = 0;
//	private int position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("DAPATKAN POSISI");
		setContentView(R.layout.activity_location);
		txtLon = (EditText) findViewById(R.id.txtLon);
		txtLat = (EditText) findViewById(R.id.txtLat);
		btnGetLocation = (Button) findViewById(R.id.btnGetPosition);
		checkLockLocation = (CheckBox) findViewById(R.id.checkLockLocation);
		locked = getIntent().getBooleanExtra("locked", false);
		xcoord =  getIntent().getStringExtra("xcoord");
		ycoord =  getIntent().getStringExtra("ycoord");
		idx = getIntent().getIntExtra("segment", 0);
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER )) 
		{ 
			turnGPSOn(); 
		}
		
		myLocationListener = new MyLocationListener();

		if (!locked) {
			checkLockLocation.setChecked(false);
			txtLon.setText("0");
			txtLat.setText("0");
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 
					MINIMUM_TIME_BETWEEN_UPDATES, 
					MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
					myLocationListener
			);
		} else {
			turnGPSOff();
			txtLon.setText(ycoord);
			txtLat.setText(xcoord);
			checkLockLocation.setChecked(true);
		}


		checkLockLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked){
					locationManager.removeUpdates(myLocationListener);
				} else {
					txtLon.setText("0");
					txtLat.setText("0");
					locationManager.requestLocationUpdates(
							LocationManager.GPS_PROVIDER, 
							MINIMUM_TIME_BETWEEN_UPDATES, 
							MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
							myLocationListener
					);
				}
			}
		});

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
		public void run() {
			   counter += 1;
			  // getLocation();
		      //here you can start your Activity B.
			  //  Toast.makeText(getApplicationContext(), "3 menit", Toast.LENGTH_LONG).show();
		   }

		}, 180000);
		
		btnGetLocation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(counter >= 180000 ){
					AlertDialog confirm = new AlertDialog.Builder(LocationActivity.this).create();
					confirm.setTitle("Peringatan");
					confirm.setMessage("Dikarenakan data posisi tidak dapatkan, silahkan lanjut ke step berikutnya");
					confirm.setButton("Ya", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent();
							if (!checkLockLocation.isChecked()) {
								locationManager.removeUpdates(myLocationListener);
								locked = false;
							} else {
								locked = true;
							}
							intent.putExtra("locked", locked);
							intent.putExtra("xcoord", xcoord);
							intent.putExtra("ycoord", ycoord);
							setResult(100, intent);
							turnGPSOff();
							finish();
						}
					});
					confirm.setButton2("Tidak", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
						}
					});
					confirm.show();
				} else{
					 Toast.makeText(getApplicationContext(), "Selesaikan pencarian lokasi terlebih dahulu", Toast.LENGTH_LONG).show();
				}
			}
		});

	}

	protected void getLocation() {
		if (txtLon.getText().toString().equals("0") && txtLat.getText().toString().equals("0")){
			AlertDialog confirm = new AlertDialog.Builder(LocationActivity.this).create();
			confirm.setTitle("Peringatan");
			confirm.setMessage("Anda belum mendapatkan koordinat fix GPS, Anda tetap ingin menggunakan koordinat ini ?");
			confirm.setButton("Ya", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Intent intent = new Intent();
					if (!checkLockLocation.isChecked()) {
						locationManager.removeUpdates(myLocationListener);
						locked = false;
					} else {
						locked = true;
					}
					intent.putExtra("locked", locked);
					intent.putExtra("xcoord", xcoord);
					intent.putExtra("ycoord", ycoord);
					setResult(100, intent);
					finish();
				}
			});
			confirm.setButton2("Tidak", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
			confirm.show();
		} else {
			Intent inten = new Intent();
			if (!checkLockLocation.isChecked()) {
				locationManager.removeUpdates(myLocationListener);
				inten.putExtra("locked", false);
			} else {
				inten.putExtra("locked", true);
			}
			inten.putExtra("xcoord", txtLat.getText().toString());
			inten.putExtra("ycoord", txtLon.getText().toString());
			inten.putExtra("segment", idx);
//			inten.putExtra("position", position);
			LocationActivity.this.setResult(100, inten);
			finish();
			
		}
		
	}

	protected void showCurrentLocation() {
		Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		if (location != null) {
			String message = String.format(
					"Current Location \n Longitude: %1$s \n Latitude: %2$s",
					location.getLongitude(), location.getLatitude()
			);
			longToast(message);
		}
	}

	public void longToast(CharSequence message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}


	private class MyLocationListener implements LocationListener {

		public void onLocationChanged(Location location) {
//			String message = String.format(
//					"New Location \n Longitude: %1$s \n Latitude: %2$s",
//					location.getLongitude(), location.getLatitude()
//
//			);

			txtLon.setText(String.valueOf(location.getLongitude()));
			txtLat.setText(String.valueOf(location.getLatitude()));

//			longToast(message);
			//			Toast.makeText(TabDataActivity.this, message, Toast.LENGTH_LONG).show();
		}

		public void onStatusChanged(String s, int i, Bundle b) {
//			longToast("Provider status changed");
		}

		public void onProviderDisabled(String s) {
			longToast("GPS turned off");
		}

		public void onProviderEnabled(String s) {
			longToast("GPS turned on");
		}

	}

	private void turnGPSOn(){
		String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

		if(!provider.contains("gps")){
			final Intent poke = new Intent();
			poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider"); 
			poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
			poke.setData(Uri.parse("3")); 
			sendBroadcast(poke);
		}
	}

	private void turnGPSOff(){
		String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

		if(provider.contains("gps")){
			final Intent poke = new Intent();
			poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
			poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
			poke.setData(Uri.parse("3")); 
			sendBroadcast(poke);
		}
	}

	@Override
	public void onBackPressed() {
		if(counter >= 180000 ){
			getLocation();
		}else{
			Toast.makeText(getApplicationContext(), "Selesaikan pencarian lokasi terlebih dahulu", Toast.LENGTH_LONG).show();
		}
		
	}
}
