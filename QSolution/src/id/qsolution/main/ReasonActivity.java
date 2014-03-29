package id.qsolution.main;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import id.qsolution.adapter.PhotoAdapter;
import id.qsolution.models.TmOutlet;
import id.qsolution.models.TmStatusKunjungan;
import id.qsolution.models.TmSurveyor;
import id.qsolution.models.TtDKunjunganSurveyorPhoto;
import id.qsolution.models.TtMKunjunganSurveyor;
import id.qsolution.models.dao.TmOutletDao;
import id.qsolution.models.dao.TmStatusKunjunganDao;
import id.qsolution.models.dao.TtDKunjunganSurveyorPhotoDao;
import id.qsolution.models.dao.TtMKunjunganSurveyorDao;
import id.qsolution.util.NamaFile;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class ReasonActivity extends TabActivity {
	
	private TmSurveyor surveyor;
	private TmOutlet outlet;
	private TmStatusKunjungan statusKunjungan;
	private TmStatusKunjunganDao statusKunjunganDao;
	private List<TmStatusKunjungan> listKunjungan;
	private ArrayAdapter<TmStatusKunjungan> statusKunjunganAdapter;
	private ListView lsData;
	private Button btnGetPosition;
	private String jamMulai;
	private TmOutletDao outletDao;
	private boolean locked;
	private String xcoord;
	private String ycoord;
	private Button btnGetPhoto;
	private EditText txtLon;
	private EditText txtLat;
	private ListView viewPhoto;
	private TtDKunjunganSurveyorPhoto photo;
	private TtDKunjunganSurveyorPhotoDao photoDao;
	private ArrayList<TtDKunjunganSurveyorPhoto> listPhoto;
	private static final int CAMERA_REQUEST = 200;
	private PhotoAdapter fotoAdapter;
	private String path;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reason);
		setTitle("REASONS");
		jamMulai = new SimpleDateFormat("HH:mm:ss").format(new Date());
		outlet = new TmOutlet();
		outletDao = new TmOutletDao(getApplicationContext());
		surveyor = (TmSurveyor) getIntent().getSerializableExtra("surveyor");
		outlet = (TmOutlet) getIntent().getSerializableExtra("outlet");
		locked = getIntent().getBooleanExtra("locked", false);
		xcoord =  getIntent().getStringExtra("xcoord");
		ycoord =  getIntent().getStringExtra("ycoord");
		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		spec = tabHost
				.newTabSpec("Alasan Kunjangan")
				.setIndicator("Alasan Kunjangan",
						res.getDrawable(R.drawable.ic_action_user))
				.setContent(R.id.form1);
		tabHost.addTab(spec);
		
		spec = tabHost
				.newTabSpec("Photo Kunjungan")
				.setIndicator("Photo Kunjungan",
						res.getDrawable(R.drawable.ic_action_photo))
				.setContent(R.id.form2);
		tabHost.addTab(spec);
		tabHost.setCurrentTab(0);
		initView();
		loadForm();
		loadStatus();
		viewPhoto();
		setListener();
	}
	
	private void loadForm() {
		txtLat.setText(xcoord);
		txtLon.setText(ycoord);
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(ReasonActivity.this, OutletsActivity.class);
		intent.putExtra("xcoord", xcoord);
		intent.putExtra("ycoord", ycoord);
		intent.putExtra("locked", locked);
		intent.putExtra("surveyor", surveyor);
		startActivity(intent);
		finish();
	}

	private void initView() {
		viewPhoto = (ListView) findViewById(R.id.lsFoto);
		lsData = (ListView) findViewById(R.id.lsData);
		btnGetPosition = (Button) findViewById(R.id.btnGetPosition);
		btnGetPhoto = (Button) findViewById(R.id.btnGetPhoto);
		txtLon = (EditText) findViewById(R.id.txtLon);
		txtLat = (EditText) findViewById(R.id.txtLat);
	}
	
	private void viewPhoto() {
		photo = new TtDKunjunganSurveyorPhoto();
		photoDao = new TtDKunjunganSurveyorPhotoDao(getApplicationContext());
		listPhoto = new ArrayList<TtDKunjunganSurveyorPhoto>();
		photo.setKodeKunjungan(outlet.getKode()+new SimpleDateFormat("ddMMyyyy").format(new Date()));
		listPhoto = (ArrayList<TtDKunjunganSurveyorPhoto>) photoDao.listByExample(photo);
		fotoAdapter = new PhotoAdapter(ReasonActivity.this, listPhoto);
		viewPhoto.setAdapter(fotoAdapter);
		fotoAdapter.notifyDataSetChanged();
		viewPhoto.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int i, long index) {	
				try {
					photo = listPhoto.get(i);
					photoDao.delete(photo.getId());
					File file = new File(photo.getNamaFile());
					file.delete();
					Toast.makeText(getApplicationContext(), "Foto telah dihapus index "+i, Toast.LENGTH_LONG).show();
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "Error "+e.getMessage(), Toast.LENGTH_LONG).show();
				}
				viewPhoto();
				return false;
			}
		});
		
		viewPhoto.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				TtDKunjunganSurveyorPhoto foto = (TtDKunjunganSurveyorPhoto) fotoAdapter.getItem(position);
				LayoutInflater inflater = getLayoutInflater();
				View layout = inflater.inflate(R.layout.toast_img_layout, (ViewGroup) findViewById(R.id.toast_layout_root));
				ImageView image = (ImageView) layout.findViewById(R.id.image);
				image.setImageDrawable(Drawable.createFromPath(foto.getNamaFile()));
				Toast toast = new Toast(getApplicationContext());
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setDuration(Toast.LENGTH_SHORT);
				toast.setView(layout);
				toast.show();
			}
		});
	}

	private void setListener() {
		btnGetPosition.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ReasonActivity.this, LocationActivity.class);
				intent.putExtra("xcoord", xcoord);
				intent.putExtra("ycoord", ycoord);
				intent.putExtra("locked", locked);
				startActivityForResult(intent, 100);
			}
		});
		
		btnGetPhoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					Intent cameraIntent = new Intent(
							android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
					startActivityForResult(cameraIntent, CAMERA_REQUEST);
					
				} catch (Exception e) {
					Log.i(this.getClass().getName(), e.getMessage());
				}
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.save, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menuSave:
			if(validate()){
				save();
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private boolean validate() {
		if(!validateCoor()){
			Toast.makeText(getApplicationContext(), "Koordinat belum ditemukan", Toast.LENGTH_LONG).show();
			return false;
		}
		if(!validatePhoto()){
			Toast.makeText(getApplicationContext(), "Photo belum diambil", Toast.LENGTH_LONG).show();
			return false;
		}
		return true;
	}

	private boolean validateCoor() {
		if (txtLon.getText().toString().equals("") && txtLat.getText().toString().equals("")){
			return false;
		}
		return true;
	}
	
	private boolean validatePhoto() {
		if (listPhoto.size() > 0){
			return true;
		}
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 100:
			xcoord = data.getStringExtra("xcoord");
			ycoord = data.getStringExtra("ycoord");
			locked = data.getBooleanExtra("locked", false);
			loadForm();
			break;
		case 200 :
			try {
				if(data != null){
					Uri selectedImageUri = data.getData();
					path = getRealPathFromURI(selectedImageUri);
					final EditText input = new EditText(ReasonActivity.this);
					AlertDialog.Builder alert = new AlertDialog.Builder(ReasonActivity.this);
					alert.setTitle("Diskripsi Foto"); // Set Alert dialog//
					alert.setMessage("Silakan Tulis Diskripsi"); // Message here
					alert.setView(input);
					alert.setPositiveButton("Simpan",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int whichButton) {
									try {
										photo.setKodeKunjungan(outlet.getKode()+new SimpleDateFormat("ddMMyyyy").format(new Date()));
										String fileName = changeFileName(path);
										photo.setDeskripsi(input.getText().toString());
										photo.setNamaFile(fileName);
										photo.setKodeOutlet(outlet.getKode());
										photoDao.insert(photo);
										/*if(photoDao.listByExample(photo).size() > 0){
											photo.setDeskripsi(input.getText().toString());
											photo.setNamaFile(fileName);
											photo.setKodeOutlet(outlet.getKode());
											photoDao.update(photo);
										} else {
											photo.setDeskripsi(input.getText().toString());
											photo.setNamaFile(fileName);
											photo.setKodeOutlet(outlet.getKode());
											photoDao.insert(photo);
										}*/
										viewPhoto();
										//Toast.makeText(getApplicationContext(), path, Toast.LENGTH_LONG).show();
									} catch (Exception e) {
										Log.i("onSave", e.getMessage());
									}
								}

								private String changeFileName(String path) {
									NamaFile myHomePage = new NamaFile(path, '/', '.');
									TelephonyManager manager = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
									String result = myHomePage.path() +"/" +myHomePage.filename().replace("IMG", manager.getDeviceId());
									File oldfile =new File(path);
									File newfile =new File(result);
									oldfile.renameTo(newfile);
									return result;
								}
							});
					alert.setNegativeButton("Batal",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int whichButton) {
									try {
										File file = new File(path);
										file.delete();
									} catch (Exception e) {
										e.printStackTrace();
									}
									dialog.cancel();
								}
							}); // End of alert.setNegativeButton
					AlertDialog alertDialog = alert.create();
					alertDialog.show();
				} else{
					Toast.makeText(getApplicationContext(), "Ambil foto batal", Toast.LENGTH_LONG).show();
				}
			} catch (Exception e) {
				Toast.makeText(getApplicationContext(), "Error "+e.getMessage(), Toast.LENGTH_LONG).show();
			}
			
			break;
		
		default:
			break;
		}
	}
	
	@SuppressWarnings("static-access")
	private String getRealPathFromURI(Uri contentUri) {
		Uri uri = null;
		String photoPath = "";
		try {
			Cursor cursor = getContentResolver().query(
					Media.EXTERNAL_CONTENT_URI,
					new String[] { Media.DATA, Media.DATE_ADDED,
							MediaStore.Images.ImageColumns.ORIENTATION },
					Media.DATE_ADDED, null, "date_added ASC");
			if (cursor != null && cursor.moveToFirst()) {
				do {
					uri = contentUri.parse(cursor.getString(cursor
							.getColumnIndex(Media.DATA)));
					photoPath = uri.toString();
				} while (cursor.moveToNext());
				cursor.close();
			}
		} catch (Exception e) {
			return contentUri.getPath();
		}
		return photoPath;
	}
	
	private void save(){
		Intent inten = new Intent();
		TtMKunjunganSurveyor ttKunjungan = new TtMKunjunganSurveyor();
		TtMKunjunganSurveyorDao ttKunjunganDao = new TtMKunjunganSurveyorDao(getApplicationContext());
		ttKunjungan.setJamMulai(jamMulai);
		ttKunjungan.setJamSelesai(new SimpleDateFormat("HH:mm:ss").format(new Date()));
		ttKunjungan.setTglSurveySkrg(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
		ttKunjungan.setKodeOutlet(outlet.getKode());
		ttKunjungan.setKodeSurveyor(surveyor.getKode());
		ttKunjungan.setKode(outlet.getKode()+new SimpleDateFormat("ddMMyyyy").format(new Date()));
		ttKunjungan.setKodeStatus(statusKunjungan.getKode());
		ttKunjungan.setXcoord(getDouble(txtLat.getText().toString()));;
		ttKunjungan.setYcoord(getDouble(txtLon.getText().toString()));
		ttKunjunganDao.insert(ttKunjungan);
		outlet.setStatus("selesai");
		outletDao.update(outlet);
		ReasonActivity.this.setResult(100, inten);
		Toast.makeText(getApplicationContext(), "Outlet telah disimpan", Toast.LENGTH_LONG).show();
		ReasonActivity.this.setResult(100, inten);
		finish();
	}

	private Double getDouble(String string) {
		double result = 0d;
		try {
			result = Double.valueOf(string);
		} catch (Exception e) {
			return result;
		}
		return result;
	}

	private void loadStatus() {
		
		statusKunjungan = new TmStatusKunjungan();
		statusKunjunganDao = new TmStatusKunjunganDao(getApplicationContext());
		listKunjungan = new ArrayList<TmStatusKunjungan>();
		for (TmStatusKunjungan o : statusKunjunganDao.listAll()) {
			if(!o.getNama().equalsIgnoreCase("Sukses")){
				listKunjungan.add(o);
			}
		}
		statusKunjunganAdapter = new ArrayAdapter<TmStatusKunjungan>(this, android.R.layout.simple_list_item_single_choice ,listKunjungan);
		lsData.setAdapter(statusKunjunganAdapter);
		lsData.setItemsCanFocus(false);
		lsData.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		lsData.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int index, long arg3) {
				statusKunjungan = new TmStatusKunjungan();
				statusKunjungan = listKunjungan.get(index);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
		
	}

}
