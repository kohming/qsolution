package id.qsolution.main;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnCancelListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import id.qsolution.global.GenericRespons;
import id.qsolution.global.GlobalVar;
import id.qsolution.global.QsolutionActivity;
import id.qsolution.models.TmSurveyor;
import id.qsolution.models.dao.TmSurveyorDao;

import id.qsolution.respons.LoginRespons;

public class LoginActivity extends QsolutionActivity {

	private GenericRespons<TmSurveyor> surveyour = new LoginRespons();
	private EditText txtUser;
	private EditText txtPassword;
	private Button btnLogin;
	private ProgressDialog progressDialog;
	private int responsCode;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Log.d(getClass().getName(), "OnCreate");
		
		GlobalVar.LOGIN_URL = LoginActivity.this.getString(R.string.login_web);
		setupView();
		setListener();
	}

	

	private void setListener() {
		btnLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				TelephonyManager manager = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
				//Toast.makeText(getApplicationContext(), "imei "+manager.getDeviceId(), Toast.LENGTH_LONG).show();
				if (isNetworkAvailable()) {
					
					if(URLIsReachable(GlobalVar.LOGIN_URL)){
						
						performSearch(txtUser.getText().toString(), txtPassword
								.getText().toString(), manager.getDeviceId());
					} else{
						longToast("Service tidak ditemukan");
					}		
				} else {
					longToast("No connection available");
				}
			}
		});
	}
	

	public boolean URLIsReachable(String urlString){
		try {
			URL url = new URL(urlString);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			responsCode = urlConnection.getResponseCode();
			urlConnection.disconnect();
			return responsCode != 200;
		} catch (MalformedURLException e) {
			Log.i("MalformedURLException ", e.getMessage());
			return false;
		} catch (IOException e) {
			Log.i("IOException ", e.getMessage());
			return false;
		}
	}

	private void setupView() {
		txtUser = (EditText) findViewById(R.id.txtUserName);
		txtPassword = (EditText) findViewById(R.id.txtPassword);
		btnLogin = (Button) findViewById(R.id.btnLogin);
	}

	public boolean isNetworkAvailable() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		// if no network is available networkInfo will be null
		// otherwise check if we are connected
		if (networkInfo != null && networkInfo.isConnected()) {
			return true;
		}
		return false;
	}

	protected void performSearch(String user, String pass, String imei) {
		try {
			progressDialog = ProgressDialog.show(LoginActivity.this,
					this.getString(R.string.please_wait),
					this.getString(R.string.progress_running), true, true);
			PerformUserSearchTask task = new PerformUserSearchTask();
			task.execute(user, pass, imei);
			progressDialog.setOnCancelListener(new CancelTaskOnCancelListener(
					task));
		} catch (Exception e) {
			longToast("Login Failure " + e.getMessage());
		}

	}

	private class CancelTaskOnCancelListener implements OnCancelListener {
		private AsyncTask<?, ?, ?> task;

		public CancelTaskOnCancelListener(AsyncTask<?, ?, ?> task) {
			this.task = task;
		}

		@Override
		public void onCancel(DialogInterface dialog) {
			if (task != null) {
				task.cancel(true);
			}
		}
	}

	private class PerformUserSearchTask extends
			AsyncTask<String, Void, TmSurveyor> {

		@Override
		protected TmSurveyor doInBackground(String... params) {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("username", params[0]));
			nameValuePairs.add(new BasicNameValuePair("password", params[1]));
			nameValuePairs.add(new BasicNameValuePair("imei", params[2]));
			return surveyour.find(nameValuePairs);
		}

		@Override
		protected void onPostExecute(final TmSurveyor result) {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					try {
						if (progressDialog != null) {
							progressDialog.dismiss();
							progressDialog = null;
						}
						if(validate(result)){
							TmSurveyorDao surveyourDao = new TmSurveyorDao(getApplicationContext());
							surveyourDao.insert(result);
							Intent intent = new Intent(LoginActivity.this, MainActivity.class);
							startActivity(intent);
							finish();
						}else{
							longToast(getString(R.string.user_not_found));
						}
					} catch (Exception e) {
						longToast("Login Failure " + e.getMessage());
					}
				}

				private boolean validate(TmSurveyor result) {
					boolean r = true;
					try {
						if (result.getUsername().equals("")
								|| result.getUsername() == null) {
							r = false;
						}
					} catch (Exception e) {
						r = false;
					}
					return r;
				}
			});
		}
	}

	public void longToast(CharSequence message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}
}
