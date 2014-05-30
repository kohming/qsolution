package id.qsolution.main;


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
import android.os.Handler;
import android.os.SystemClock;
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
	private Handler customHandler = new Handler();
	private long startTime = 0L;
	long timeInMilliseconds = 0L;
	long timeSwapBuff = 0L;
	long updatedTime = 0L;
	private int mins;
	private Location currentBestLocation = null;
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

		startTime = SystemClock.uptimeMillis();
		customHandler.postDelayed(updateTimerThread, 0);
		
		btnGetLocation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if(mins < 3 ){
					Toast.makeText(getApplicationContext(), "Selesaikan pencarian lokasi terlebih dahulu ", Toast.LENGTH_LONG).show();
				} else{
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
							intent.putExtra("xcoord", txtLat.getText().toString());
							intent.putExtra("ycoord", txtLon.getText().toString());
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
				}
			}
		});
	}
	
	private Runnable updateTimerThread = new Runnable() {
		 
        

		public void run() {
 
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;
 
            int secs = (int) (updatedTime / 1000);
            mins = secs / 60;
            secs = secs % 60;
            @SuppressWarnings("unused")
			int milliseconds = (int) (updatedTime % 1000);
           /* timerValue.setText("" + mins + ":"
                    + String.format("%02d", secs) + ":"
                    + String.format("%03d", milliseconds));*/
            customHandler.postDelayed(this, 0);
        }
 
    };

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
					intent.putExtra("xcoord", txtLat.getText().toString());
					intent.putExtra("ycoord", txtLon.getText().toString());
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

	private static final int TWO_MINUTES = 1000 * 60 * 2;

	/** Determines whether one Location reading is better than the current Location fix
	  * @param location  The new Location that you want to evaluate
	  * @param currentBestLocation  The current Location fix, to which you want to compare the new one
	  */
	protected boolean isBetterLocation(Location location, Location currentBestLocation) {
	    if (currentBestLocation == null) {
	        // A new location is always better than no location
	        return true;
	    }

	    // Check whether the new location fix is newer or older
	    long timeDelta = location.getTime() - currentBestLocation.getTime();
	    boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
	    boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
	    boolean isNewer = timeDelta > 0;

	    // If it's been more than two minutes since the current location, use the new location
	    // because the user has likely moved
	    if (isSignificantlyNewer) {
	        return true;
	    // If the new location is more than two minutes older, it must be worse
	    } else if (isSignificantlyOlder) {
	        return false;
	    }

	    // Check whether the new location fix is more or less accurate
	    int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
	    boolean isLessAccurate = accuracyDelta > 0;
	    boolean isMoreAccurate = accuracyDelta < 0;
	    boolean isSignificantlyLessAccurate = accuracyDelta > 200;

	    // Check if the old and new location are from the same provider
	    boolean isFromSameProvider = isSameProvider(location.getProvider(),
	            currentBestLocation.getProvider());

	    // Determine location quality using a combination of timeliness and accuracy
	    if (isMoreAccurate) {
	        return true;
	    } else if (isNewer && !isLessAccurate) {
	        return true;
	    } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
	        return true;
	    }
	    return false;
	}

	/** Checks whether two providers are the same */
	private boolean isSameProvider(String provider1, String provider2) {
	    if (provider1 == null) {
	      return provider2 == null;
	    }
	    return provider1.equals(provider2);
	}

	private class MyLocationListener implements LocationListener {

		public void onLocationChanged(Location newLocation) {
//			String message = String.format(
//					"New Location \n Longitude: %1$s \n Latitude: %2$s",
//					location.getLongitude(), location.getLatitude()
//
//			);
			
			if(isBetterLocation(newLocation, currentBestLocation)) {
	                currentBestLocation = newLocation;
	                if(currentBestLocation.hasAccuracy() && currentBestLocation.getAccuracy() <= 100) {
	                	getLocation();
	                	//finish();
	                }
			}
			
		/*	int value  = 0;
			value = (int) currentBestLocation.getLongitude();*/
			
			txtLon.setText(String.valueOf(currentBestLocation.getLongitude()));
			txtLat.setText(String.valueOf(currentBestLocation.getLatitude()));
			
			//if(value != 0){
				
			//}
		
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
		if(mins < 3 ){
			Toast.makeText(getApplicationContext(), "Selesaikan pencarian lokasi terlebih dahulu ", Toast.LENGTH_LONG).show();
		}else{
			getLocation();
		}
		
	}
}