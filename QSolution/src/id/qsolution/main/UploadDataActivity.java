package id.qsolution.main;


import id.qsolution.global.GenericRespons;
import id.qsolution.global.GlobalVar;
import id.qsolution.models.Result;
import id.qsolution.models.TmOutlet;
import id.qsolution.models.TmOutletFasilitas;
import id.qsolution.models.TmResult;
import id.qsolution.models.TmSurveyor;
import id.qsolution.models.TtDKunjunganSurveyorOutletPosm;
import id.qsolution.models.TtDKunjunganSurveyorRak;
import id.qsolution.models.TtDKunjunganSurveyorRakFoto;
import id.qsolution.models.TtDKunjunganSurveyorRakPosm;
import id.qsolution.models.TtDKunjunganSurveyorRakSku;
import id.qsolution.models.TtDKunjunganSurveyorRakSkuPromosi;
import id.qsolution.models.TtMKunjunganSurveyor;
import id.qsolution.models.TtDKunjunganSurveyorPhoto;
import id.qsolution.models.TtPhotoRak;
import id.qsolution.models.dao.TmOutletDao;
import id.qsolution.models.dao.TmOutletFasilitasDao;
import id.qsolution.models.dao.TtDKunjunganSurveyorOutletPosmDao;
import id.qsolution.models.dao.TtDKunjunganSurveyorPhotoDao;
import id.qsolution.models.dao.TtDKunjunganSurveyorRakDao;
import id.qsolution.models.dao.TtDKunjunganSurveyorRakFotoDao;
import id.qsolution.models.dao.TtDKunjunganSurveyorRakPosmDao;
import id.qsolution.models.dao.TtDKunjunganSurveyorRakSkuDao;
import id.qsolution.models.dao.TtDKunjunganSurveyorRakSkuPromosiDao;
import id.qsolution.models.dao.TtMKunjunganSurveyorDao;
import id.qsolution.pojos.Kunjungan;
import id.qsolution.pojos.KunjunganSurveyor;
import id.qsolution.pojos.KunjunganSurveyorOutletPosm;
import id.qsolution.pojos.KunjunganSurveyorPhoto;
import id.qsolution.pojos.KunjunganSurveyorRak;
import id.qsolution.pojos.KunjunganSurveyorRakFoto;
import id.qsolution.pojos.KunjunganSurveyorRakPosm;
import id.qsolution.pojos.KunjunganSurveyorRakSku;
import id.qsolution.pojos.KunjunganSurveyorRakSkuPromosi;
import id.qsolution.pojos.Outlet;
import id.qsolution.pojos.OutletFasilitas;
import id.qsolution.pojos.Photo;
import id.qsolution.pojos.PhotoRak;
import id.qsolution.pojos.Upload;
import id.qsolution.respons.UploadResponsService;
import id.qsolution.util.NamaFile;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

public class UploadDataActivity extends TabActivity{
	
	private ArrayList<TmOutlet> listData = new ArrayList<TmOutlet>();
	private ArrayAdapter<TtMKunjunganSurveyor> dataAdapter;
	private ArrayList<TtDKunjunganSurveyorPhoto> listPhoto = new ArrayList<TtDKunjunganSurveyorPhoto>();
	private ArrayList<TtDKunjunganSurveyorPhoto> fotoListSelected = new ArrayList<TtDKunjunganSurveyorPhoto>();
	private ArrayList<TtDKunjunganSurveyorRakFoto> fotoRakListSelected = new ArrayList<TtDKunjunganSurveyorRakFoto>();
	private ArrayAdapter<TtDKunjunganSurveyorPhoto> fotoAdapter;
	private TmOutlet outlet;
	private TmOutletDao outletDao;
	private TmSurveyor surveyor;
	private ListView lsData;
	private ListView lsPhoto;
	private TtDKunjunganSurveyorPhotoDao fotoDao;
	private Button btnUploadData;
	private Button btnUploadFoto;
	private GenericRespons<TmResult> dataUpload = new UploadResponsService();
	private ProgressDialog progressDialog;
	private int responsCode;
	private Result respons = new Result();
	private ProgressDialog dialogUpload;
	private ProgressDialog dialogUploadRak;
	private Button btnUploadFotoRak;
	private TtDKunjunganSurveyorRakFotoDao fotoRakDao;
	private ArrayList<TtDKunjunganSurveyorRakFoto> listPhotoRak;
	private ArrayAdapter<TtDKunjunganSurveyorRakFoto> fotoRakAdapter;
	private ListView lsFotoRak;
	private TtMKunjunganSurveyor kunjungan;
	private TtMKunjunganSurveyorDao kunjunganDao;
	private ArrayList<TtMKunjunganSurveyor> listKategori;
	private ArrayAdapter<Kunjungan> kunjunganAdapter;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_upload);
		initView();
		setTitle("UPLOAD SURVEYED");
		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost
				.newTabSpec("Data Survey")
				.setIndicator("Data Survey",
						res.getDrawable(R.drawable.ic_action_done))
				.setContent(R.id.form1);
		tabHost.addTab(spec);
		
		spec = tabHost.newTabSpec("Data Foto")
				.setIndicator("Data Foto", res.getDrawable(R.drawable.ic_action_photo))
				.setContent(R.id.form2);
		tabHost.addTab(spec);
		
		spec = tabHost.newTabSpec("Data Foto Rak")
				.setIndicator("Data Foto Rak", res.getDrawable(R.drawable.ic_action_photo))
				.setContent(R.id.form3);
		tabHost.addTab(spec);
		tabHost.setCurrentTab(0);
		
		surveyor = (TmSurveyor) getIntent().getSerializableExtra("surveyor");
		//loadSurvey();
		loadOutlet();
		loadFoto();
		loadFotoRak();
		buttonListener();
	}
	
	private void loadOutlet() {
		kunjungan = new TtMKunjunganSurveyor();
		kunjunganDao = new TtMKunjunganSurveyorDao(getApplicationContext());
		listKategori = (ArrayList<TtMKunjunganSurveyor>) kunjunganDao.listAll();
		List<Kunjungan> visits = new ArrayList<Kunjungan>();
		
		for (TtMKunjunganSurveyor ttMKunjunganSurveyor : listKategori) {
			if(!isUpload(ttMKunjunganSurveyor.getStatus())){
				visits.add(getVisit(ttMKunjunganSurveyor));
			}
		}
		kunjunganAdapter = new ArrayAdapter<Kunjungan>(this, android.R.layout.simple_list_item_multiple_choice , visits);
		lsData.setAdapter(kunjunganAdapter);
		lsData.setItemsCanFocus(false);
		lsData.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	}

	private Kunjungan getVisit(TtMKunjunganSurveyor value) {
		Kunjungan result = new Kunjungan(this);
		result.setName(value.getName());
		result.setJabatanResponden(value.getJabatanResponden());
		result.setJamMulai(value.getJamMulai());
		result.setJamSelesai(value.getJamSelesai());
		result.setKode(value.getKode());
		result.setKodeOutlet(value.getKodeOutlet());
		result.setKodeStatus(value.getStatus());
		result.setKodeSurveyor(value.getKodeSurveyor());
		result.setKodeKategori(value.getKodeKategori());
		result.setOmzet(value.getOmzet());
		return result;
	}

	private void loadFotoRak() {
		fotoRakDao = new TtDKunjunganSurveyorRakFotoDao(getApplicationContext());
		List<TtDKunjunganSurveyorRakFoto> photoRakSelected = new ArrayList<TtDKunjunganSurveyorRakFoto>();
		listPhotoRak = (ArrayList<TtDKunjunganSurveyorRakFoto>) fotoRakDao.listAll();
		for (TtDKunjunganSurveyorRakFoto ttPhotoRak : listPhotoRak) {
			if(isNull(ttPhotoRak.getStatus())){
				photoRakSelected.add(ttPhotoRak);
			}
		}
		fotoRakAdapter = new ArrayAdapter<TtDKunjunganSurveyorRakFoto>(this, android.R.layout.simple_list_item_multiple_choice ,photoRakSelected);
		lsFotoRak.setAdapter(fotoRakAdapter);
		lsFotoRak.setItemsCanFocus(false);
		lsFotoRak.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	}

	private void initView() {
		lsData = (ListView) findViewById(R.id.lsData);
		lsPhoto = (ListView) findViewById(R.id.lsFoto);
		lsFotoRak = (ListView) findViewById(R.id.lsFotoRak);
		btnUploadData = (Button) findViewById(R.id.btnUploadData);
		btnUploadFoto = (Button) findViewById(R.id.btnUploadFoto);
		btnUploadFotoRak = (Button) findViewById(R.id.btnUploadFotoRak);
	}

	protected void performSearch(String data) {
		GlobalVar.LOGIN_URL = getString(R.string.upload_data);
		if(URLIsReachable(GlobalVar.LOGIN_URL)){
			progressDialog = ProgressDialog.show(UploadDataActivity.this,
					"Proses upload data", "Silakan tunggu", true, true);

			PerformDataUploadTask task = new PerformDataUploadTask();
			task.execute(data);
			progressDialog.setOnCancelListener(new CancelTaskOnCancelListener(task));
		}
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
	
	private class PerformDataUploadTask extends AsyncTask<String, Void, TmResult> {

		@Override
		protected TmResult doInBackground(String... params) {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("opt", "krm"));
			nameValuePairs.add(new BasicNameValuePair("data", params[0]));
			return dataUpload.find(nameValuePairs);
		}

		@Override
		protected void onPostExecute(final TmResult result) {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					try {
						if (progressDialog != null) {
							progressDialog.dismiss();
							progressDialog = null;
						}
						if(result.getMassage().equals("200")){
							kunjunganDao = new TtMKunjunganSurveyorDao(getApplicationContext());
							long[] lChecked = lsData.getCheckItemIds();
							for(int i = 0;i<lChecked.length;i++){
								Kunjungan o = kunjunganAdapter.getItem((int) lChecked[i]);
								TtMKunjunganSurveyor ku = new TtMKunjunganSurveyor();
								ku.setKode(o.getKode());
								ku = kunjunganDao.getByExample(ku);
								ku.setStatus("upload");
								kunjunganDao.update(ku);
							}
							loadOutlet();
							//loadSurvey();
							longToast("upload berhasil");
						}else{
							longToast("upload gagal "+result.getMassage());
						}
						//longToast("upload berhasil");
					} catch (Exception e) {
						longToast("upload gagal");
					}
				}
			});
		}

		protected void longToast(String msg) {
			Toast.makeText(getApplicationContext(), msg , Toast.LENGTH_LONG).show();
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
	
	

	class ImageUploadTask extends AsyncTask<Void, Void, String> {
		private Bitmap bm;

		@Override
		protected String doInBackground(Void... unsued) {
			try {
				String strRespons = "";
				for (TtDKunjunganSurveyorPhoto foto : fotoListSelected) {
					Log.d("foto ", foto.getNamaFile());
					String sResponse = "";
					bm = BitmapFactory.decodeFile(foto.getNamaFile());
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					bm.compress(CompressFormat.JPEG, 70, bos);
					
					InputStream in = new ByteArrayInputStream(bos.toByteArray());
					ContentBody img = new InputStreamBody(in,"image", foto.getNamaFile());
					
					byte[] data = bos.toByteArray();
					HttpClient httpClient = new DefaultHttpClient();
					HttpPost postRequest = new HttpPost(GlobalVar.UPLOAD_FOTO);
					ByteArrayBody bab = new ByteArrayBody(data, foto.getNamaFile());
					MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
					//reqEntity.addPart("file", bab);
					reqEntity.addPart("file", img);
					reqEntity.addPart("photoCaption", new StringBody("sfsdfsdf"));
					postRequest.setEntity(reqEntity);
					HttpResponse response = httpClient.execute(postRequest);

					BufferedReader reader = new BufferedReader(
							new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
					StringBuilder s = new StringBuilder();

					while ((sResponse = reader.readLine()) != null) {
						s = s.append(sResponse);
						Log.i("s : ", s.toString());		
					}

					strRespons = s.toString();
					Log.i("strRespons ", strRespons);
								
					convertToObject(strRespons);
					
					/*String path = "/mnt/sdcard/DCIM/Camera/";
					if(respons.getId().equals("200")){
						
					}*/
				}
				return strRespons;

			} catch (Exception e) {
				Log.i("Exception ", e.getMessage());
			}
			return respons.getStatus();
		}

		@Override
		protected void onProgressUpdate(Void... unsued) {

		}

		@Override
		protected void onPostExecute(String msg) {
				if (dialogUpload != null) {
					dialogUpload.dismiss();
					dialogUpload = null;
				}
				for (TtDKunjunganSurveyorPhoto x : fotoListSelected) {
					fotoDao = new TtDKunjunganSurveyorPhotoDao(getApplicationContext());
					x.setStatus("upload");
					fotoDao.update(x);
					Toast.makeText(getApplicationContext(), "Foto "+respons.getStatus()+" sukses diupload", Toast.LENGTH_LONG).show();
				}
				loadFoto();
		}
	}
	

	class ImageRakUploadTask extends AsyncTask<Void, Void, String> {
		private Bitmap bm;

		@Override
		protected String doInBackground(Void... unsued) {
			try {
				String strRespons = "";
				for (TtDKunjunganSurveyorRakFoto fotoRak : fotoRakListSelected) {
					Log.d("foto rak", fotoRak.getNamaFile());
					String sResponse = "";
					bm = BitmapFactory.decodeFile(fotoRak.getNamaFile());
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					bm.compress(CompressFormat.JPEG, 70, bos);
					
					InputStream in = new ByteArrayInputStream(bos.toByteArray());
					ContentBody img = new InputStreamBody(in,"image", fotoRak.getNamaFile());
					
					byte[] data = bos.toByteArray();
					HttpClient httpClient = new DefaultHttpClient();
					HttpPost postRequest = new HttpPost(GlobalVar.UPLOAD_FOTO);
					ByteArrayBody bab = new ByteArrayBody(data, fotoRak.getNamaFile());
					MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
					//reqEntity.addPart("file", bab);
					reqEntity.addPart("file", img);
					reqEntity.addPart("photoCaption", new StringBody("sfsdfsdf"));
					postRequest.setEntity(reqEntity);
					HttpResponse response = httpClient.execute(postRequest);

					BufferedReader reader = new BufferedReader(
							new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
					StringBuilder s = new StringBuilder();

					while ((sResponse = reader.readLine()) != null) {
						s = s.append(sResponse);
						Log.i("s : ", s.toString());		
					}

					strRespons = s.toString();
					Log.i("strRespons ", strRespons);
								
					convertToObject(strRespons);
					String path = "/mnt/sdcard/DCIM/Camera/";
					
				}
				return strRespons;

			} catch (Exception e) {
				Log.i("Exception ", e.getMessage());
			}
			return respons.getStatus();
		}

		@Override
		protected void onProgressUpdate(Void... unsued) {

		}

		@Override
		protected void onPostExecute(String msg) {
				if (dialogUploadRak != null) {
					dialogUploadRak.dismiss();
					dialogUploadRak = null;
				}
				
				for (TtDKunjunganSurveyorRakFoto x : fotoRakListSelected) {
								TtDKunjunganSurveyorRakFotoDao fotoRDao = new TtDKunjunganSurveyorRakFotoDao(getApplicationContext());
								x.setStatus("upload");
								fotoRDao.update(x);
								Toast.makeText(getApplicationContext(), "Foto Rak "+respons.getStatus()+" sukses diupload", Toast.LENGTH_LONG).show();
				}
						
				loadFotoRak();
		}
	}
	
	private void convertToObject(String strRespons) {
		Gson gson = new Gson();
		try {
			JSONObject test = new JSONObject(strRespons);
			respons = gson.fromJson(test.getString("result"), Result.class);
		} catch (JSONException e) {
			Toast.makeText(getApplicationContext(), "Error "+e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
	
	private void buttonListener() {
		
		btnUploadFotoRak.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				uploadFotoRak();
			}

			private void uploadFotoRak() {
				long[] lChecked = lsFotoRak.getCheckItemIds();
				for(int i = 0;i<lChecked.length;i++){
					fotoRakListSelected.add(fotoRakAdapter.getItem((int) lChecked[i]));
				}
				if(fotoRakListSelected.size() > 0 ){
					try {
						dialogUploadRak = ProgressDialog.show(UploadDataActivity.this, "Uploading Foto ", "Please wait...", true);
						ImageRakUploadTask fotoTask = new ImageRakUploadTask();
						fotoTask.execute();
						dialogUploadRak.setOnCancelListener(new CancelTaskOnCancelListener(fotoTask));
					} catch (Exception e) {
						Log.i(getClass().getName(), e.getMessage());
					}	
				} else {
					Toast.makeText(getApplicationContext(),"Tidak ada foto yang dipilih",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		
		btnUploadFoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				uploadFoto();
			}

			private void uploadFoto() {
				long[] lChecked = lsPhoto.getCheckItemIds();
				for(int i = 0;i<lChecked.length;i++){
					fotoListSelected.add(fotoAdapter.getItem((int) lChecked[i]));
				}
				if(fotoListSelected.size() > 0 ){
					try {
						dialogUpload = ProgressDialog.show(UploadDataActivity.this, "Uploading Foto ", "Please wait...", true);
						ImageUploadTask fotoTask = new ImageUploadTask();
						fotoTask.execute();
						dialogUpload.setOnCancelListener(new CancelTaskOnCancelListener(fotoTask));
					} catch (Exception e) {
						Log.i(getClass().getName(), e.getMessage());
					}	
				} else {
					Toast.makeText(getApplicationContext(),"Tidak ada foto yang dipilih",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		

		btnUploadData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				ArrayList<Kunjungan> outletListSelected = new ArrayList<Kunjungan>();
				long[] lChecked = lsData.getCheckItemIds();
				for(int i = 0;i<lChecked.length;i++){
					outletListSelected.add(kunjunganAdapter.getItem((int) lChecked[i]));
				}
				String data = getJSONFormat(outletListSelected);
				//Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG).show();
				performSearch(data);
			}

			private String getJSONFormat(ArrayList<Kunjungan> outletListSelected) {
				String result = "";
				Upload upload = new Upload();
				TmOutlet outlet;
				TtMKunjunganSurveyor kunjungan;
				TtDKunjunganSurveyorOutletPosm outletPosm;
				TtDKunjunganSurveyorPhoto kunjunganPhoto;
				TtDKunjunganSurveyorRak outletRak;
				try {
					TtDKunjunganSurveyorOutletPosmDao outletPosmDao = new TtDKunjunganSurveyorOutletPosmDao(getApplicationContext());
					TtDKunjunganSurveyorPhotoDao kunjunganSurveyourDao = new TtDKunjunganSurveyorPhotoDao(getApplicationContext());
					TtDKunjunganSurveyorRakDao outletRakDao = new TtDKunjunganSurveyorRakDao(getApplicationContext());
					TtDKunjunganSurveyorRakFotoDao rakPhotoDao = new TtDKunjunganSurveyorRakFotoDao(getApplicationContext());
					TtDKunjunganSurveyorRakPosmDao rakPosmDao = new TtDKunjunganSurveyorRakPosmDao(getApplicationContext());
					TtDKunjunganSurveyorRakSkuDao rakSkuDao = new TtDKunjunganSurveyorRakSkuDao(getApplicationContext());
					TtDKunjunganSurveyorRakSkuPromosiDao rakSkuPromosiDao = new TtDKunjunganSurveyorRakSkuPromosiDao(getApplicationContext());
					TmOutletFasilitasDao outletFasilitasDao = new TmOutletFasilitasDao(getApplicationContext());
					List<KunjunganSurveyorRak> lsRak = new ArrayList<KunjunganSurveyorRak>();
					List<Outlet> lsStore = new ArrayList<Outlet>();
					List<KunjunganSurveyor> lsKunjungan = new ArrayList<KunjunganSurveyor>();
					List<KunjunganSurveyorOutletPosm> lsOutletPosm = new ArrayList<KunjunganSurveyorOutletPosm>();
					List<KunjunganSurveyorPhoto> lsKunjunganSurveyorPhoto = new ArrayList<KunjunganSurveyorPhoto>();
					List<TtDKunjunganSurveyorRakFoto> lsKunjunganRakPhoto = new ArrayList<TtDKunjunganSurveyorRakFoto>();
					List<TtDKunjunganSurveyorRakPosm> lsKunjunganSurveyorRakPosm = new ArrayList<TtDKunjunganSurveyorRakPosm>();
					List<TtDKunjunganSurveyorRakSku> lsKunjunganSurveyorRakSku = new ArrayList<TtDKunjunganSurveyorRakSku>();
					List<TtDKunjunganSurveyorRakSkuPromosi> lsKunjunganSurveyorRakSkuPromosi = new ArrayList<TtDKunjunganSurveyorRakSkuPromosi>();
					List<TmOutletFasilitas> lsOutletFasilitas = new ArrayList<TmOutletFasilitas>();
					
					if(outletListSelected.size()>0){
						for(int i = 0; i < outletListSelected.size(); i++){
							
							//Done
							outlet = new TmOutlet();
							outletDao = new TmOutletDao(getApplicationContext());
							outlet.setKode(outletListSelected.get(i).getKodeOutlet());
							outlet = outletDao.listByExample(outlet).get(0);
							lsStore.add(getStore(outlet));
							
							//Done
							kunjungan = new TtMKunjunganSurveyor();
							kunjungan.setKodeOutlet(outletListSelected.get(i).getKodeOutlet());
							kunjungan = kunjunganDao.listByExample(kunjungan).get(0);
							lsKunjungan.add(getKunjungan(kunjungan));
							
							//Done
							outletPosm = new TtDKunjunganSurveyorOutletPosm();
							outletPosm.setKode(outletListSelected.get(i).getKodeOutlet());
							lsOutletPosm.addAll(getOutletPosm(outletPosmDao.listByExample(outletPosm)));
							
							//Done
							kunjunganPhoto = new TtDKunjunganSurveyorPhoto();
							kunjunganPhoto.setKodeOutlet(outletListSelected.get(i).getKodeOutlet());
							lsKunjunganSurveyorPhoto.addAll(getSurveyPhoto(kunjunganSurveyourDao.listByExample(kunjunganPhoto)));
							
							//done
							outletRak = new TtDKunjunganSurveyorRak();
							outletRak.setKodeKunjungan(outletListSelected.get(i).getKode());
							lsRak.addAll(getKunjunganRak(outletRakDao.listByExample(outletRak)));
							
							//done
							TtDKunjunganSurveyorRakFoto rakPhoto = new TtDKunjunganSurveyorRakFoto();
							rakPhoto.setKodeOutlet(outletListSelected.get(i).getKodeOutlet());
							lsKunjunganRakPhoto.addAll(rakPhotoDao.listByExample(rakPhoto));
							
							//done
							TtDKunjunganSurveyorRakPosm rakPosm = new TtDKunjunganSurveyorRakPosm();
							rakPosm.setKodeKunjungan(outletListSelected.get(i).getKode());
							rakPosmDao = new TtDKunjunganSurveyorRakPosmDao(getApplicationContext());
							lsKunjunganSurveyorRakPosm.addAll(rakPosmDao.listAll());
							
							//done
							TtDKunjunganSurveyorRakSku rakSku = new TtDKunjunganSurveyorRakSku();
							rakSku.setKodeKunjungan(outletListSelected.get(i).getKode());
							lsKunjunganSurveyorRakSku.addAll(rakSkuDao.listByExample(rakSku));
							
							//Done
							TtDKunjunganSurveyorRakSkuPromosi rakPromo = new TtDKunjunganSurveyorRakSkuPromosi();
							rakPromo.setKodeKunjungan(outletListSelected.get(i).getKode());
							lsKunjunganSurveyorRakSkuPromosi.addAll(rakSkuPromosiDao.listByExample(rakPromo));
						
							TmOutletFasilitas outletFasilitas = new TmOutletFasilitas();
							outletFasilitas.setKodeOutlet(outletListSelected.get(i).getKodeOutlet());
							lsOutletFasilitas.addAll(outletFasilitasDao.listByExample(outletFasilitas));
						}
					}
					upload.setOutlets(lsStore);
					upload.setKunjunganSurveyor(lsKunjungan);
					upload.setOutletPosm(lsOutletPosm);
					upload.setKunjunganPhoto(lsKunjunganSurveyorPhoto);
					upload.setKunjunganRak(lsRak);
					upload.setKunjunganRakFoto(getFotoRak(lsKunjunganRakPhoto));
					upload.setKunjunganRakPosm(getRakPosm(lsKunjunganSurveyorRakPosm));
					upload.setKunjunganRakSku(getRakSku(lsKunjunganSurveyorRakSku));
					upload.setKunjunganRakSkuPromosi(getPromosi(lsKunjunganSurveyorRakSkuPromosi));
					upload.setOutletFasilitas(gatFasilitas(lsOutletFasilitas));
					Gson gson = new Gson();
					result = gson.toJson(upload);
				} catch (Exception e) {
					Log.i("gson error ", e.getMessage());
				}
				return result;
			}
			
			

			private List<OutletFasilitas> gatFasilitas(List<TmOutletFasilitas> lsOutletFasilitas) {
				List<OutletFasilitas> result = new ArrayList<OutletFasilitas>();
				OutletFasilitas o = new OutletFasilitas();
				for (TmOutletFasilitas tmOutletFasilitas : lsOutletFasilitas) {
					o.setJumlah(tmOutletFasilitas.getJumlah());
					o.setKodeFasilitas(tmOutletFasilitas.getKodeFasilitas());
					o.setKodeOutlet(tmOutletFasilitas.getKodeOutlet());
					result.add(o);
				}
				return result;
			}

			private List<KunjunganSurveyorPhoto> getSurveyPhoto(List<TtDKunjunganSurveyorPhoto> listByExample) {
				List<KunjunganSurveyorPhoto> results = new ArrayList<KunjunganSurveyorPhoto>();
				KunjunganSurveyorPhoto foto = new KunjunganSurveyorPhoto();
				for (TtDKunjunganSurveyorPhoto f : listByExample) {
					foto = new KunjunganSurveyorPhoto();
					foto.setDeskripsi(f.getDeskripsi());
					foto.setKodeKunjungan(f.getKodeKunjungan());
					foto.setKodeOutlet(f.getKodeOutlet());
					/*String[] parts = f.getNamaFile().split("/");
					foto.setNamaFile(parts[parts.length]+".jpg");*/
					String FPATH = f.getNamaFile() + ".jpg";
					NamaFile myHomePage = new NamaFile(FPATH, '/', '.');
					foto.setNamaFile(myHomePage.filename());
					results.add(foto);
				}
				return results;
			}

			private List<KunjunganSurveyorRakFoto> getFotoRak(List<TtDKunjunganSurveyorRakFoto> lsKunjunganRakPhoto) {
				List<KunjunganSurveyorRakFoto> results = new ArrayList<KunjunganSurveyorRakFoto>();
				KunjunganSurveyorRakFoto fotoRak = new KunjunganSurveyorRakFoto();
				for (TtDKunjunganSurveyorRakFoto rF : lsKunjunganRakPhoto) {
					fotoRak = new KunjunganSurveyorRakFoto();
					fotoRak.setDeskripsi(rF.getDeskripsi());
					fotoRak.setKodeKunjungan(rF.getKodeKunjungan());
					/*String[] parts = rF.getNamaFile().split("/");
					fotoRak.setNamaFile(parts[parts.length]+".jpg");*/
					String FPATH = rF.getNamaFile() + ".jpg";
					NamaFile myHomePage = new NamaFile(FPATH, '/', '.');
					fotoRak.setNamaFile(myHomePage.filename());
					fotoRak.setKodeOutlet(rF.getKodeOutlet());
					fotoRak.setNomorUrut(rF.getNomorUrut());
					fotoRak.setStatus(rF.getStatus());
					results.add(fotoRak);
				}
				return results;
			}

			private List<KunjunganSurveyorRakSkuPromosi> getPromosi(List<TtDKunjunganSurveyorRakSkuPromosi> listByExample) {
				List<KunjunganSurveyorRakSkuPromosi>  listRakSku = new ArrayList<KunjunganSurveyorRakSkuPromosi>();
				for(TtDKunjunganSurveyorRakSkuPromosi promo: listByExample){
					KunjunganSurveyorRakSkuPromosi p = new KunjunganSurveyorRakSkuPromosi();
					p.setKode(promo.getKode());
					p.setKodeKunjungan(promo.getKodeKunjungan());
					p.setKodePromosi(promo.getKodePromosi());
					p.setDeskripsi(promo.getDeskripsi());
					p.setKodeSku(promo.getKodeSku());
					p.setNomorUrut(promo.getNomorUrut());
					listRakSku.add(p);
				}
				return listRakSku;
			}

			private List<KunjunganSurveyorRakSku> getRakSku(List<TtDKunjunganSurveyorRakSku> listByExample) {
				List<KunjunganSurveyorRakSku>  listRakSku = new ArrayList<KunjunganSurveyorRakSku>();
				for (TtDKunjunganSurveyorRakSku surveyorRakSku : listByExample) {
					KunjunganSurveyorRakSku s = new KunjunganSurveyorRakSku();
					s.setKode(surveyorRakSku.getKode());
					s.setHargaJual(surveyorRakSku.getHargaJual());
					s.setJumlahFacing(surveyorRakSku.getJumlahFacing());
					s.setJumlahUnit(surveyorRakSku.getJumlahUnit());
					if(surveyorRakSku.isLblPrice() == true){
						s.setLblPrice(true);
					}else{
						s.setLblPrice(false);
					}
					s.setKodePackage(surveyorRakSku.getKodePackage());
					s.setKodeVolum(surveyorRakSku.getKodeVolum());
					s.setNomorUrut(surveyorRakSku.getNomorUrut());
					s.setKodeKunjungan(surveyorRakSku.getKodeKunjungan());
					s.setKodeSku(surveyorRakSku.getKodeSku());
					listRakSku.add(s);
				}
				return listRakSku;
			}

			private List<KunjunganSurveyorRakPosm> getRakPosm(List<TtDKunjunganSurveyorRakPosm> listByExample) {
				List<KunjunganSurveyorRakPosm> result = new ArrayList<KunjunganSurveyorRakPosm>();
				for (TtDKunjunganSurveyorRakPosm surveyorRakPosm : listByExample) {
					KunjunganSurveyorRakPosm s = new KunjunganSurveyorRakPosm();
					s.setNomorUrut(surveyorRakPosm.getNomorUrut());
					s.setKode(surveyorRakPosm.getKode());
					s.setJumlah(surveyorRakPosm.getJumlah());
					s.setNomorUrut(surveyorRakPosm.getNomorUrut());
					s.setKodePosm(surveyorRakPosm.getKodePosm());
					s.setKodeBrand(surveyorRakPosm.getKodeBrand());
					s.setKodeKunjungan(surveyorRakPosm.getKodeKunjungan());
					result.add(s);
				}
				return result;
			}

			private List<KunjunganSurveyorRak> getKunjunganRak(List<TtDKunjunganSurveyorRak> listByExample) {
				List<KunjunganSurveyorRak> listRak = new ArrayList<KunjunganSurveyorRak>();
				for (TtDKunjunganSurveyorRak ttDKunjunganSurveyorRak : listByExample) {
					KunjunganSurveyorRak r = new KunjunganSurveyorRak();
					r.setKode(ttDKunjunganSurveyorRak.getKode());
					r.setKodeBrand(ttDKunjunganSurveyorRak.getKodeBrand());
					r.setKodeKunjungan(ttDKunjunganSurveyorRak.getKodeKunjungan());
					r.setKodeRak(ttDKunjunganSurveyorRak.getKodeRak());
					r.setNomorUrut(ttDKunjunganSurveyorRak.getNomorUrut());
					listRak.add(r);
				}
				return listRak;
			}

			private List<KunjunganSurveyorOutletPosm> getOutletPosm(List<TtDKunjunganSurveyorOutletPosm> listByExample) {
				List<KunjunganSurveyorOutletPosm> result = new ArrayList<KunjunganSurveyorOutletPosm>();
				for (TtDKunjunganSurveyorOutletPosm surveyorOutletPosm : listByExample) {
					KunjunganSurveyorOutletPosm o = new KunjunganSurveyorOutletPosm();
					o.setKode(surveyorOutletPosm.getKode());
					o.setKodeBrand(surveyorOutletPosm.getKodeBrand());
					o.setKodeKunjungan(surveyorOutletPosm.getKodeKunjungan());
					o.setKodePosm(surveyorOutletPosm.getKodePosm());
					o.setJumlah(surveyorOutletPosm.getJumlah());
					result.add(o);
				}
				return result;
			}

			private List<PhotoRak>  getPhotoRak(List<TtPhotoRak> listByExample) {
			
				List<PhotoRak> result = new ArrayList<PhotoRak>();
				for (TtPhotoRak ttPhotoRak : listByExample) {
					PhotoRak r = new PhotoRak();
					r.setKode(ttPhotoRak.getKode());
					r.setKodeOutlet(ttPhotoRak.getKodeOutlet());
					r.setKodKategori(ttPhotoRak.getKodeKategori());
					r.setNama(ttPhotoRak.getNama());
					r.setNamaFoto(ttPhotoRak.getNamaFoto());
					r.setDeskripsi(ttPhotoRak.getDeskripsi());
					result.add(r);
				}
				return result;
			}

			private List<Photo> getPhoto(List<TtDKunjunganSurveyorPhoto> listByExample) {
				List<Photo> result = new ArrayList<Photo>();
				for (TtDKunjunganSurveyorPhoto ttPhoto : listByExample) {
					Photo p = new Photo();
					p.setKode(ttPhoto.getKodeKunjungan());
					p.setNama(ttPhoto.getNamaFile());
					p.setDeskripsi(ttPhoto.getDeskripsi());
					result.add(p);
				}
				return result;
			}

			private KunjunganSurveyor getKunjungan(TtMKunjunganSurveyor kunjungan) {
				KunjunganSurveyor k = new KunjunganSurveyor();
				k.setKode(kunjungan.getKode());
				k.setTglSurveySkrg(kunjungan.getTglSurveySkrg());
				k.setTglSurveyBerikut(kunjungan.getTglSurveyBerikut());
				k.setKodeOutlet(kunjungan.getKodeOutlet());
				k.setKodeStatus(kunjungan.getKodeStatus());
				k.setResponden(kunjungan.getResponden());
				k.setKodeSurveyor(kunjungan.getKodeSurveyor());
				k.setJamMulai(kunjungan.getJamMulai());
				k.setJamSelesai(kunjungan.getJamSelesai());
				k.setOmzet(kunjungan.getOmzet());
				k.setWaktuOperasi(kunjungan.getWaktuOperasi());
				k.setJabatanResponden(kunjungan.getJabatanResponden());
				k.setKodeKategori(kunjungan.getKodeKategori());
				k.setOmzetKategori(kunjungan.getOmzetKategori());
				k.setXcoord(kunjungan.getXcoord());
				k.setYcoord(kunjungan.getYcoord());
				return k;
			}

			private Outlet getStore(TmOutlet outlet) {
				Outlet olt;
				olt = new Outlet();
				olt.setAksesToko(outlet.getAksesToko());
				olt.setAlamat(outlet.getAlamat());
				olt.setInsertBy(outlet.getInsertBy());
				olt.setInsertDate(outlet.getInsertDate());
				olt.setKode(outlet.getKode());
				olt.setKodeKelurahan(outlet.getKodeKelurahan());
				olt.setKodeKlasifikasi(outlet.getKodeKlasifikasi());
				olt.setKodePos(outlet.getKodePos());
				olt.setLokasi(outlet.getLokasi());
				olt.setNama(outlet.getNama());
				olt.setPemilik(outlet.getPemilik());
				olt.setRt(outlet.getRt());
				olt.setRw(outlet.getRw());
				olt.setStatusBangunan(outlet.getStatusBangunan());
				olt.setXCoord(outlet.getXCoord());
				olt.setYCoord(outlet.getYCoord());
				olt.setUpdateDate(outlet.getUpdateDate());
				olt.setTelepon(outlet.getTelepon());
				return olt;
			}

			private List<Outlet> getOutlet(List<TmOutlet> listByExample) {
				List<Outlet> result = new ArrayList<Outlet>();
				Outlet olt;
				for(TmOutlet o : listByExample ){
					olt = new Outlet();
					olt.setAksesToko(o.getAksesToko());
					olt.setAlamat(o.getAlamat());
					olt.setInsertBy(o.getInsertBy());
					olt.setInsertDate(o.getInsertDate());
					olt.setKode(o.getKode());
					olt.setKodeKelurahan(o.getKodeKelurahan());
					olt.setKodeKlasifikasi(o.getKodeKlasifikasi());
					olt.setLokasi(o.getLokasi());
					olt.setNama(o.getNama());
					olt.setPemilik(o.getPemilik());
					olt.setRt(o.getRt());
					olt.setRw(o.getRw());
					olt.setStatusBangunan(o.getStatusBangunan());
					olt.setXCoord(o.getXCoord());
					olt.setYCoord(o.getYCoord());
					olt.setUpdateDate(o.getUpdateDate());
					olt.setTelepon(o.getTelepon());
					result.add(olt);
				}
				return result;
			}
		});
		
		
		
	}
	
	public void onUpload(View v)
    {
        Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();
    } 

	private void loadFoto() {
		fotoDao = new TtDKunjunganSurveyorPhotoDao(getApplicationContext());
		List<TtDKunjunganSurveyorPhoto> photoSelected = new ArrayList<TtDKunjunganSurveyorPhoto>();
		listPhoto = (ArrayList<TtDKunjunganSurveyorPhoto>) fotoDao.listAll();
		for (TtDKunjunganSurveyorPhoto ttPhoto : listPhoto) {
			if(isNull(ttPhoto.getStatus())){
				photoSelected.add(ttPhoto);
			}
		}
		fotoAdapter = new ArrayAdapter<TtDKunjunganSurveyorPhoto>(this, android.R.layout.simple_list_item_multiple_choice ,photoSelected);
		lsPhoto.setAdapter(fotoAdapter);
		lsPhoto.setItemsCanFocus(false);
		lsPhoto.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	}

	private boolean isNull(String status) {
		try {
			if(status.equals("") || status == null) return true;
		} catch (Exception e) {
			return true;
		}
		return false;
	}


	private boolean isSelesai(String status) {
		try {
			if(status.equals("selesai")) return true;
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
	private boolean isUpload(String status) {
		try {
			if(status.equals("upload")) return true;
		} catch (Exception e) {
			return false;
		}
		return false;
	}

}
