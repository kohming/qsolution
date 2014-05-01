package id.qsolution.main;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import id.qsolution.adapter.KatagoriAdapter;
import id.qsolution.adapter.PhotoAdapter;
import id.qsolution.models.TmJabatanResponden;
import id.qsolution.models.TmOutlet;
import id.qsolution.models.TmSurveyor;
import id.qsolution.models.TtDKunjunganSurveyorPhoto;
import id.qsolution.models.TtMKunjunganSurveyor;
import id.qsolution.models.TtPhoto;
import id.qsolution.models.dao.TmJabatanRespondenDao;
import id.qsolution.models.dao.TmOutletDao;
import id.qsolution.models.dao.TtDKunjunganSurveyorPhotoDao;
import id.qsolution.models.dao.TtMKunjunganSurveyorDao;
import id.qsolution.models.dao.TtPhotoDao;
import id.qsolution.util.NamaFile;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class TabOutletLamaActivityNew extends TabActivity {
	private TmSurveyor surveyor;
	private TmOutlet outlet;
	private EditText txtNama;
	private EditText txtAlamat;
	private Button btnGetPhoto;
	private Button btnGetPosition;
	private String xcoord;
	private String ycoord;
	private boolean locked;
	private EditText txtLon;
	private EditText txtLat;
	private static final int CAMERA_REQUEST = 200;
	private String path;
	private TtDKunjunganSurveyorPhoto photo;
	private TtDKunjunganSurveyorPhotoDao photoDao;
	private PhotoAdapter fotoAdapter;
	private List<TtDKunjunganSurveyorPhoto> listPhoto;
	private ListView lsPhoto;
	private TmOutletDao outletDao;
	private String mulai;
	private TextView lblId;
	private TextView lblName;
	private EditText txtNamaResponden;
	private Spinner spnResponden;
	private EditText txtOmzet;
	private TmJabatanResponden jabatan;
	private TmJabatanRespondenDao jabatanDao;
	private ArrayList<TmJabatanResponden> listJabatan;
	private String[] lsJabatan;
	private Spinner spnJabatan;
	private TextView lblOmset;
	private TtMKunjunganSurveyor kunjungan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_update_outlet_new);
		setTitle("SURVEY AUDIT");
		kunjungan = new TtMKunjunganSurveyor();
		Log.d("Activity ", this.getClass().getName());
		mulai = getIntent().getStringExtra("mulai");
		surveyor = (TmSurveyor) getIntent().getSerializableExtra("surveyor");
		outlet = (TmOutlet) getIntent().getSerializableExtra("outlet");
		locked = getIntent().getBooleanExtra("locked", false);
		xcoord = getIntent().getStringExtra("xcoord");
		ycoord = getIntent().getStringExtra("ycoord");
		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost
				.newTabSpec("Outlet")
				.setIndicator("Outlet",
						res.getDrawable(R.drawable.ic_action_user))
				.setContent(R.id.form1);
		tabHost.addTab(spec);
		
		spec = tabHost.newTabSpec("Foto Survey")
				.setIndicator("Foto Survey", res.getDrawable(R.drawable.ic_action_photo))
				.setContent(R.id.form2);
		tabHost.addTab(spec);
		tabHost.setCurrentTab(0);
		
		initView();
		loadForm();
		loadJabatan();
		viewPhoto();
		setListener();
	}
	
	private void loadJabatan() {
		jabatan = new TmJabatanResponden();
		jabatanDao = new TmJabatanRespondenDao(getApplicationContext());
		listJabatan = new ArrayList<TmJabatanResponden>();
		listJabatan = (ArrayList<TmJabatanResponden>) jabatanDao.listAll();
		lsJabatan = new String[listJabatan.size()];
		for (int i = 0; i < listJabatan.size(); i++) {
			lsJabatan[i] = listJabatan.get(i).getNama();
		}
		ArrayAdapter<String> adapterJabatan = new ArrayAdapter<String>(
				TabOutletLamaActivityNew.this, android.R.layout.simple_spinner_item,
				lsJabatan);
		adapterJabatan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnJabatan.setAdapter(adapterJabatan);
		spnJabatan.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				jabatan = listJabatan.get(arg2);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(TabOutletLamaActivityNew.this, OutletsActivity.class);
		intent.putExtra("xcoord", xcoord);
		intent.putExtra("ycoord", ycoord);
		intent.putExtra("locked", locked);
		intent.putExtra("surveyor", surveyor);
		startActivityForResult(intent, 100);
		finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.batalanjut, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menuBatal:
			Intent intent = new Intent(TabOutletLamaActivityNew.this, OutletsActivity.class);
			intent.putExtra("xcoord", xcoord);
			intent.putExtra("ycoord", ycoord);
			intent.putExtra("locked", locked);
			intent.putExtra("surveyor", surveyor);
			startActivityForResult(intent, 100);
			finish();
			break;
			
		case R.id.menuLanjut:
			kunjungan = new TtMKunjunganSurveyor();
			kunjungan.setKodeOutlet(outlet.getKode());
			kunjungan.setKodeStatus("01");
			kunjungan.setJabatanResponden(jabatan.getKode());
			kunjungan.setJamMulai(mulai);
			kunjungan.setOmzet(getDouble(txtOmzet.getText().toString().replace(",", "")));
			kunjungan.setKodeSurveyor(surveyor.getKode());
			kunjungan.setResponden(txtNamaResponden.getText().toString());
			kunjungan.setTglSurveySkrg(new SimpleDateFormat("ddMMyyyy").format(new Date()));
			outlet.setXCoord(getDouble(txtLat.getText().toString()));
			outlet.setYCoord(getDouble(txtLon.getText().toString()));
			intent = new Intent( TabOutletLamaActivityNew.this, ActivityPilihKategori.class);
			intent.putExtra("surveyor", surveyor);
			intent.putExtra("outlet", outlet);
			intent.putExtra("locked",locked);	
			intent.putExtra("xcoord",xcoord);	
			intent.putExtra("ycoord",ycoord);	
			intent.putExtra("kunjungan",kunjungan);
			startActivity(intent);
			finish();
			break;	
		default:
			return super.onOptionsItemSelected(item);
		}

		return true;
	}
	


	private Double getDouble(String string) {
		double result = 0d;
		try {
			result = Double.valueOf(string);
		} catch (Exception e) {
			return result = 0d;
		}
		return result;
	}

	private void loadAudit() {
		//TmOutlet outletSelected = (TmOutlet) adapter.getItem(i);
		Intent intent = new Intent(TabOutletLamaActivityNew.this, TabSurveyAudit.class);
		intent.putExtra("surveyor", surveyor);
		intent.putExtra("outlet", outlet);
		startActivity(intent);
		finish();
	}

	private boolean validate() {
		if(outlet == null) return false;
		return true;
	}

	private void setListener() {
		btnGetPosition.setOnClickListener(new OnClickListener() {
			
			private Intent intent;
			@Override
			public void onClick(View v) {
				intent = new Intent(TabOutletLamaActivityNew.this, LocationActivity.class);
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
					Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
					startActivityForResult(cameraIntent, CAMERA_REQUEST);
				} catch (Exception e) {
					Log.i(this.getClass().getName(), e.getMessage());
				}
			}
		});
	}

	private void loadForm() {
		lblId.setText(outlet.getKode());
		lblName.setText(outlet.getNama()+", "+outlet.getAlamat());
		txtLon.setText(String.valueOf(validateDouble(outlet.getYCoord())));
		txtLat.setText(String.valueOf(validateDouble(outlet.getXCoord())));
		if(outlet.getKode().substring(0, 2).equals("MT")){
			lblOmset.setVisibility(View.GONE);
			txtOmzet.setVisibility(View.GONE);
		}
		txtOmzet.addTextChangedListener( new TextWatcher() {
			
	        boolean isEdiging;
	        @Override public void onTextChanged(CharSequence s, int start, int before, int count) { }
	        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

	        @Override public void afterTextChanged(Editable s) {
	            if(isEdiging) return;
	            isEdiging = true;

	            String str = s.toString().replaceAll( "[^\\d]", "" );
	            double s1 = getDouble(str);

	            if((int)s1 > 0){
	            	 NumberFormat nf2 = NumberFormat.getInstance(Locale.ENGLISH);
	                 ((DecimalFormat)nf2).applyPattern("###,###.###");
	                 s.replace(0, s.length(), nf2.format(s1));
	            }else{
	            	s.replace(0, s.length(), "");
	            }
	          
	            isEdiging = false;
	        }
	    });
	}

	private String validateDouble(Double yCoord2) {
		String value;
		try {
			value = String.valueOf(yCoord2);
			if(value.equals("null") || value == null){
				value = "0";
			}
		} catch (Exception e) {
			value = "0";
		}
		return value;
	}

	private void initView() {
		lblId = (TextView) findViewById(R.id.lblId);
		lblName = (TextView) findViewById(R.id.lblName);
		lblOmset = (TextView) findViewById(R.id.lblOmset);
		txtNamaResponden = (EditText) findViewById(R.id.txtNamaResponden);
		spnJabatan = (Spinner) findViewById(R.id.spnJabatan);
		txtOmzet = (EditText) findViewById(R.id.txtOmzet);
		txtLon = (EditText) findViewById(R.id.txtLon);
		txtLat = (EditText) findViewById(R.id.txtLat);
		btnGetPhoto = (Button) findViewById(R.id.btnGetPhoto);
		btnGetPosition = (Button) findViewById(R.id.btnGetPosition);
		lsPhoto = (ListView) findViewById(R.id.lsFoto);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		
		case 100:
			xcoord = data.getStringExtra("xcoord");
			ycoord = data.getStringExtra("ycoord");
			locked = data.getBooleanExtra("locked", false);
			txtLon.setText(ycoord);
			txtLat.setText(xcoord);
			break;
			
		case CAMERA_REQUEST:
			try {
				if(data != null){
					//Bitmap img = (Bitmap) data.getExtras().get("data");
					Uri selectedImageUri = data.getData();
					
					path = getRealPathFromURI(selectedImageUri);
					final EditText input = new EditText(TabOutletLamaActivityNew.this);
					AlertDialog.Builder alert = new AlertDialog.Builder(TabOutletLamaActivityNew.this);
					alert.setTitle("Diskripsi Foto"); // Set Alert dialog//
					alert.setMessage("Silakan Tulis Diskripsi"); // Message here
					alert.setView(input);
					alert.setPositiveButton("Simpan",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int whichButton) {
									try {
										String fileName = changeFileName(path);
										photo.setKodeKunjungan((outlet.getKode()+new SimpleDateFormat("ddMMyyyy").format(new Date())));
										photo.setDeskripsi(input.getText().toString());
										photo.setNamaFile(fileName);
										photo.setKodeOutlet(outlet.getKode());
										photoDao.insert(photo);
										/*if(photoDao.listByExample(photo).size() > 0){
											photo.setDeskripsi(input.getText().toString());
											photo.setNamaFile(path);
											photo.setKodeOutlet(outlet.getKode());
											photoDao.update(photo);
										}else{
											photo.setDeskripsi(input.getText().toString());
											photo.setNamaFile(path);
											photo.setKodeOutlet(outlet.getKode());
											photoDao.insert(photo);
										}*/
										viewPhoto();
										Toast.makeText(getApplicationContext(), "Foto outlet berhasil diambil", Toast.LENGTH_LONG).show();
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


								public String join(Collection<String> strCollection, String delimiter) {
								    String joined = "";
								    int noOfItems = 0;
								    for (String item : strCollection) {
								        joined += item;
								        if (++noOfItems < strCollection.size())
								            joined += delimiter;
								    }
								    return joined;
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
					Toast.makeText(getApplicationContext(), "Batal ambil foto", Toast.LENGTH_LONG).show();
				}
			} catch (Exception e) {
				Toast.makeText(getApplicationContext(), "Batal ambil foto", Toast.LENGTH_LONG).show();
			}
			break;
		
		default:
			break;
		}
	}
	
	
	private void viewPhoto() {
		photo = new TtDKunjunganSurveyorPhoto();
		photoDao = new TtDKunjunganSurveyorPhotoDao(getApplicationContext());
		listPhoto = new ArrayList<TtDKunjunganSurveyorPhoto>();
		photo.setKodeKunjungan(outlet.getKode()+new SimpleDateFormat("ddMMyyyy").format(new Date()));
		listPhoto = photoDao.listByExample(photo);
		fotoAdapter = new PhotoAdapter(TabOutletLamaActivityNew.this, listPhoto);
		lsPhoto.setAdapter(fotoAdapter);
		fotoAdapter.notifyDataSetChanged();
		//lblNumberOfPhoto.setText("Foto("+listPhoto.size()+")");
		lsPhoto.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int i, long index) {	
				try {
					photo = listPhoto.get(i);
					photoDao.delete(photo.getId());
					File file = new File(photo.getNamaFile());
					file.delete();
					Toast.makeText(getApplicationContext(), "Foto telah dihapus", Toast.LENGTH_LONG).show();
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "Error "+e.getMessage(), Toast.LENGTH_LONG).show();
				}
				viewPhoto();
				return false;
			}
		});
		
		lsPhoto.setOnItemClickListener(new OnItemClickListener() {

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
}
