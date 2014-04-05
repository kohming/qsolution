package id.qsolution.main;

import id.qsolution.adapter.FasilitasAdapter;
import id.qsolution.adapter.KatagoriAdapter;
import id.qsolution.adapter.PhotoAdapter;
import id.qsolution.models.TmAksesKunjungan;
import id.qsolution.models.TmFasilitas;
import id.qsolution.models.TmJenisLokasi;
import id.qsolution.models.TmKabupaten;
import id.qsolution.models.TmKecamatan;
import id.qsolution.models.TmKelurahan;
import id.qsolution.models.TmKlasifikasiOutlet;
import id.qsolution.models.TmOutlet;
import id.qsolution.models.TmOutletFasilitas;
import id.qsolution.models.TmStatusBangunan;
import id.qsolution.models.TmSurveyor;
import id.qsolution.models.TmWaktuOperasi;
import id.qsolution.models.TtDKunjunganSurveyorPhoto;
import id.qsolution.models.dao.TmAksesKunjunganDao;
import id.qsolution.models.dao.TmFasilitasDao;
import id.qsolution.models.dao.TmJenisLokasiDao;
import id.qsolution.models.dao.TmKabupatenDao;
import id.qsolution.models.dao.TmKecamatanDao;
import id.qsolution.models.dao.TmKelurahanDao;
import id.qsolution.models.dao.TmKlasifikasiOutletDao;
import id.qsolution.models.dao.TmOutletDao;
import id.qsolution.models.dao.TmOutletFasilitasDao;
import id.qsolution.models.dao.TmStatusBangunanDao;
import id.qsolution.models.dao.TmWaktuOperasiDao;
import id.qsolution.models.dao.TtDKunjunganSurveyorPhotoDao;
import id.qsolution.util.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class TabSurveyOutletActivity extends TabActivity {

	private TmOutlet outlet;
	private TmKabupaten kabupaten;
	private TmKabupatenDao kabupatenDao;
	private List<TmKabupaten> listKabupaten;
	private String[] lsKabupaten;
	private Spinner spnKabKota;
	private TmKecamatan kecamatan;
	private TmKecamatanDao kecamatanDao;
	private List<TmKecamatan> listKecamatan;
	private String[] lsKecamatan;
	private Spinner spnKecamatan;
	private TmKelurahan kelurahan;
	private TmKelurahanDao kelurahanDao;
	private List<TmKelurahan> listKelurahan;
	private String[] lsKelurahan;
	private Spinner spnKelurahan;
	private TmKlasifikasiOutlet klasifikasi;
	private TmKlasifikasiOutletDao klasifikasiDao;
	private List<TmKlasifikasiOutlet> listKlasifikasi;
	private Spinner spnKlasifikasi;
	private String[] lsKlasifikasi;
	private TmStatusBangunanDao statusBangunanDao;
	private List<TmStatusBangunan> listStatusBangunan;
	private Spinner spnStatusBangunan;
	private String[] lsStatusBangunan;
	private TmJenisLokasiDao jenisLokasiDao;
	private List<TmJenisLokasi> listJenisLokasi;
	private Spinner spnJenisLokasi;
	private String[] lsJenisLokasi;
	private TmAksesKunjunganDao aksesMasukDao;
	private List<TmAksesKunjungan> listAksesMasuk;
	private Spinner spnAksesMasuk;
	private String[] lsAksesMasuk;
	private TmFasilitas fasilitas;
	private TmFasilitasDao fasilitasDao;
	private List<TmFasilitas> listFasilitas;
	private Spinner spnFasilitas;
	private String[] lsFasilitas;
	private TextView lblPemilik;
	private TextView lblHp;
	private EditText txtPemilik;
	private EditText txtHp;
	private TextView lblOltName;
	private Button btnGetPosition;
	private EditText txtLon;
	private EditText txtLat;
	private Button btnSaveFasilitas;
	private TmOutletFasilitas fasilitasOutlet;
	private TmOutletFasilitasDao fasilitasOutletDao;
	private List<TmOutletFasilitas> listFasilitasOutlet;
	private EditText txtJumlah;
	private ListView viewFasilitas;
	private FasilitasAdapter fasilitasOutletAdapter;
	private Button btnPhoto;
	private static final int CAMERA_REQUEST = 200;
	private TtDKunjunganSurveyorPhoto photo;
	private TtDKunjunganSurveyorPhotoDao photoDao;
	private PhotoAdapter fotoAdapter;
	private List<TtDKunjunganSurveyorPhoto> listPhoto;
	private ListView viewPhoto;
	private TextView lblNumberOfPhoto;
	private String path;
	private EditText txtNamaOlt;
	private EditText txtAlamatOlt;
	private EditText txtRt;
	private EditText txtRw;
	private EditText txtKodePos;
	private TmSurveyor surveyor;
	private TmOutletDao outletDao;
	private TmWaktuOperasiDao waktuOperasiDao;
	private ArrayList<TmWaktuOperasi> listWaktuOperasi;
	private Spinner spnWaktuOperasi;
	private String[] lsWaktuOperasi;
	private String statusOutlet;
	private TextView lblAksesMasuk;
	private TextView lblStatusBangunan;
	private TextView lblLuasBangunan;
	private TextView lblJenisPelanggan;
	private TextView lblWaktuOperasi;
	private TextView lblJumlahCoc;
	private String mulai;
	private EditText txtJenisPelanggan;
	private EditText txtJumlahCoc;
	private EditText txtLuasBangunan;
	private boolean locked;
	private String xcoord;
	private String ycoord;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_survey);
		Log.d("Activity ", this.getClass().getName());
		setTitle("SURVEY OUTLETS");  
		initView();
		surveyor = new TmSurveyor();
		surveyor = (TmSurveyor) getIntent().getSerializableExtra("surveyor");
		outlet = (TmOutlet) getIntent().getSerializableExtra("outlet");
		locked = getIntent().getBooleanExtra("locked", false);
		xcoord =  getIntent().getStringExtra("xcoord");
		ycoord =  getIntent().getStringExtra("ycoord");
		mulai = getIntent().getStringExtra("mulai");
		statusOutlet = outlet.getKode().substring(0, 2);
		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		spec = tabHost
				.newTabSpec("Data Outlet")
				.setIndicator("Data Outlet",
						res.getDrawable(R.drawable.ic_action_user))
				.setContent(R.id.form1);
		tabHost.addTab(spec);
		
		spec = tabHost
				.newTabSpec("Fasilitas Outlet")
				.setIndicator("Fasilitas Outlet",
						res.getDrawable(R.drawable.ic_action_paste))
				.setContent(R.id.form2);
		tabHost.addTab(spec);

		spec = tabHost.newTabSpec("Foto")
				.setIndicator("Foto", res.getDrawable(R.drawable.ic_action_photo))
				.setContent(R.id.form3);
		tabHost.addTab(spec);
		tabHost.setCurrentTab(0);
		
		loadOutlet();
		viewFasilitas();
		viewPhoto();
		setListener();
	}
	
	@Override
	public void onBackPressed() {
		finish();
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
				try {
					outlet.setKode(outlet.getKode());
					outlet.setNama(txtNamaOlt.getText().toString());
					outlet.setAlamat(txtAlamatOlt.getText().toString());
					outlet.setRt(txtRt.getText().toString());
					outlet.setRw(txtRw.getText().toString());
					outlet.setKodePos(txtKodePos.getText().toString());
					if(statusOutlet.equals("MT")){	
						outlet.setPelanggan(isNullNumber(txtLuasBangunan.getText().toString()));
						outlet.setKodeJamOperasi(isNull(listWaktuOperasi.get(spnWaktuOperasi.getSelectedItemPosition()).getKode()));
						outlet.setJumlahCoc(isNullNumber(txtJumlahCoc.getText().toString()));
					}else{
						outlet.setPelanggan(isNullNumber(txtJenisPelanggan.getText().toString()));
						outlet.setAksesToko(isNull(listAksesMasuk.get(spnAksesMasuk.getSelectedItemPosition()).getKode()));
						outlet.setStatusBangunan(isNull(listStatusBangunan.get(spnStatusBangunan.getSelectedItemPosition()).getKode()));
						outlet.setPemilik(txtPemilik.getText().toString());
						outlet.setTelepon(txtHp.getText().toString());
					}
					outlet.setKodeKelurahan(isNull(listKelurahan.get(spnKelurahan.getSelectedItemPosition()).getKode()));
					outlet.setLokasi(isNull(listJenisLokasi.get(spnJenisLokasi.getSelectedItemPosition()).getKode()));
					outlet.setKodeKlasifikasi(isNull(listKlasifikasi.get(spnKlasifikasi.getSelectedItemPosition()).getKode()));
					outlet.setXCoord(Double.valueOf(txtLat.getText().toString()));
					outlet.setYCoord(Double.valueOf(txtLon.getText().toString()));
					outlet.setInsertBy(surveyor.getKode());
					outletDao = new TmOutletDao(getApplicationContext());
					outletDao.update(outlet);
					loadAudit();
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "Error "+e.getMessage(), Toast.LENGTH_LONG).show();
				}
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private long isNullNumber(String string) {
		long result = 0l;
		try {
			result = Long.valueOf(string);
		} catch (Exception e) {
			result = 0l;
		}
		return result;
	}

	private String isNull(String kode) {
		String result = "";
		try {
			result = kode;
		} catch (Exception e) {
			return result;
		}
		return result;
	}

	
	
	private void loadAudit() {
		//Intent intent = new Intent(TabSurveyOutletActivity.this, TabSurveyAudit.class);
		Intent intent = new Intent(TabSurveyOutletActivity.this, TabOutletLamaActivityNew.class);
		intent.putExtra("locked", locked);
		intent.putExtra("xcoord", xcoord);
		intent.putExtra("ycoord", ycoord);
		intent.putExtra("surveyor", surveyor);
		intent.putExtra("outlet", outlet);
		intent.putExtra("mulai", mulai);
		startActivity(intent);
		finish();
	}
	
	private boolean validateFasilitas() {
		
		if(isNullOrEmpty(txtJumlah)){
			Toast.makeText(getApplicationContext(), "Jumlah fasilitas belum diisi", Toast.LENGTH_LONG).show();
			return false;
		}
		return true;
	}

	private boolean validate() {
		
		if(isNullOrEmpty(txtAlamatOlt)){
			Toast.makeText(getApplicationContext(), "Alamat belum diisi", Toast.LENGTH_LONG).show();
			return false;
		}
		
		if(statusOutlet.equals("GT")){
			if(isNullOrEmpty(txtNamaOlt)){
				Toast.makeText(getApplicationContext(), "Nama outlet belum diisi", Toast.LENGTH_LONG).show();
				return false;
			}
			
			if(isNullOrEmpty(txtJenisPelanggan)){
				Toast.makeText(getApplicationContext(), "Jenis belum diisi", Toast.LENGTH_LONG).show();
				return false;
			}
			
			if(isNullOrEmpty(txtHp)){
				Toast.makeText(getApplicationContext(), "Telephone belum diisi", Toast.LENGTH_LONG).show();
				return false;
			}
		} else{
			if(isNullOrEmpty(txtLuasBangunan)){
				Toast.makeText(getApplicationContext(), "Luas bangunan belum diisi", Toast.LENGTH_LONG).show();
				return false;
			}
			
			if(isNullOrEmpty(txtJumlahCoc)){
				Toast.makeText(getApplicationContext(), "Jumlah COC belum diisi", Toast.LENGTH_LONG).show();
				return false;
			}
		}
		
		
		if(isNullOrEmpty(txtKodePos)){
			Toast.makeText(getApplicationContext(), "Kodepos belum diisi", Toast.LENGTH_LONG).show();
			return false;
		}
		if(isNullOrEmpty(txtRt)){
			Toast.makeText(getApplicationContext(), "RT belum diisi", Toast.LENGTH_LONG).show();
			return false;
		}
		if(isNullOrEmpty(txtRw)){
			Toast.makeText(getApplicationContext(), "RW belum diisi", Toast.LENGTH_LONG).show();
			return false;
		}
		
		if(isNullOrEmpty(txtLon)){
			Toast.makeText(getApplicationContext(), "Koordinat harus diisi", Toast.LENGTH_LONG).show();
			return false;
		}
		
		if(isNullOrEmpty(txtLat)){
			Toast.makeText(getApplicationContext(), "Koordinat harus diisi", Toast.LENGTH_LONG).show();
			return false;
		}
		if(isMax5(txtKodePos)){
			Toast.makeText(getApplicationContext(), "Kodepos harus 5 digit angka", Toast.LENGTH_LONG).show();
			return false;
		}
		if(listPhoto.size() <= 0){
			Toast.makeText(getApplicationContext(), "Photo masih kosong", Toast.LENGTH_LONG).show();
			return false;
		}
		return true;
	}

	private boolean isMax5(EditText txt) {
		if (txt.getText().toString().length() < 5) {
			return true;
		}
		return false;
	}

	private boolean isNullOrEmpty(EditText txt) {
		if (txt.getText().toString() == null || txt.getText().toString().equals("")) {
			return true;
		}
		return false;
	}

	
	
	private void loadOutlet() {
		lblOltName.setText(outlet.getKode());
		txtNamaOlt.setText(outlet.getNama());
		txtAlamatOlt.setText(outlet.getAlamat());
		txtRt.setText(outlet.getRt());
		txtRw.setText(outlet.getRw());
		txtKodePos.setText(outlet.getKodePos());
		txtLat.setText(xcoord);
		txtLon.setText(ycoord);
		loadKabupaten();
		loadKecamatan();
		
		loadJenisLokasi();
		if(statusOutlet.equals("MT")){
			//loadLuasBangunan();
			loadWaktuOperasi();
			//loadJenisCoc();
			lblAksesMasuk.setVisibility(View.GONE);
			lblStatusBangunan.setVisibility(View.GONE);
			lblJenisPelanggan.setVisibility(View.GONE);
			spnAksesMasuk.setVisibility(View.GONE);
			spnStatusBangunan.setVisibility(View.GONE);
			txtJenisPelanggan.setVisibility(View.GONE);
			txtPemilik.setVisibility(View.GONE);
			lblPemilik.setVisibility(View.GONE);
			txtHp.setVisibility(View.GONE);
			lblHp.setVisibility(View.GONE);
		} else{
			loadAksesMasuk();
			loadStatusBangunan();
		//	loadPelanggan();
			lblLuasBangunan.setVisibility(View.GONE);
			lblWaktuOperasi.setVisibility(View.GONE);
			lblJumlahCoc.setVisibility(View.GONE);
			txtLuasBangunan.setVisibility(View.GONE);
			spnWaktuOperasi.setVisibility(View.GONE);
			txtJumlahCoc.setVisibility(View.GONE);
			txtPemilik.setText(outlet.getPemilik());
			txtHp.setText(outlet.getPemilik());
		}
		loadKlasifikasi();
		
		//Fasilitas
		loadFasilitasOutlet();
		//loadWaktuOperasi();
		
		
	
	}

	private void loadWaktuOperasi() {
		new TmWaktuOperasi();
		waktuOperasiDao = new TmWaktuOperasiDao(getApplicationContext());
		listWaktuOperasi = new ArrayList<TmWaktuOperasi>();
		listWaktuOperasi = (ArrayList<TmWaktuOperasi>) waktuOperasiDao.listAll();
		lsWaktuOperasi = new String[listWaktuOperasi.size()];
		for (int i = 0; i < listWaktuOperasi.size(); i++) {
			lsWaktuOperasi[i] = listWaktuOperasi.get(i).getNama();
		}
		ArrayAdapter<String> adapterWaktu = new ArrayAdapter<String>( TabSurveyOutletActivity.this, android.R.layout.simple_spinner_item, lsWaktuOperasi);
		adapterWaktu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnWaktuOperasi.setAdapter(adapterWaktu);
		spnWaktuOperasi.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				listWaktuOperasi.get(arg2);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void viewPhoto() {
		photo = new TtDKunjunganSurveyorPhoto();
		photoDao = new TtDKunjunganSurveyorPhotoDao(getApplicationContext());
		listPhoto = new ArrayList<TtDKunjunganSurveyorPhoto>();
		photo.setKodeKunjungan(outlet.getKode()+new SimpleDateFormat("ddMMyyyy").format(new Date()));
		listPhoto = photoDao.listByExample(photo);
		fotoAdapter = new PhotoAdapter(TabSurveyOutletActivity.this, listPhoto);
		viewPhoto.setAdapter(fotoAdapter);
		fotoAdapter.notifyDataSetChanged();
		lblNumberOfPhoto.setText("Foto("+listPhoto.size()+")");
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

	private void viewFasilitas() {
		fasilitasOutlet = new TmOutletFasilitas();
		fasilitasOutlet.setKodeOutlet(outlet.getKode());
		listFasilitasOutlet = new ArrayList<TmOutletFasilitas>();
		listFasilitasOutlet.clear();
		listFasilitasOutlet = fasilitasOutletDao.listByExample(fasilitasOutlet);
		fasilitasOutletAdapter= new FasilitasAdapter(TabSurveyOutletActivity.this, listFasilitasOutlet);
		viewFasilitas.setAdapter(fasilitasOutletAdapter);
		viewFasilitas.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int i, long index) {
				    fasilitasOutlet = new TmOutletFasilitas();
				    fasilitasOutlet = listFasilitasOutlet.get(i);
				    fasilitasOutletDao.delete(fasilitasOutlet.getId());
				    Toast.makeText(getApplicationContext(), "Fasilitas outlet berhasil dihapus", Toast.LENGTH_LONG).show();
					viewFasilitas();
				return false;
			}
		});
		fasilitasOutletAdapter.notifyDataSetChanged();
	
	}

	private void setListener() {
		
		btnPhoto.setOnClickListener(new OnClickListener() {
			
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

		btnGetPosition.setOnClickListener(new OnClickListener() {
			
			private Intent intent;

			@Override
			public void onClick(View v) {
				intent = new Intent(TabSurveyOutletActivity.this, LocationActivity.class);
				intent.putExtra("xcoord", xcoord);
				intent.putExtra("ycoord", ycoord);
				intent.putExtra("locked", locked);
				startActivityForResult(intent, 100);
			}
		});
		
		btnSaveFasilitas.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(validateFasilitas()){
					fasilitasOutlet.setKodeOutlet(outlet.getKode());
					fasilitasOutlet.setKodeFasilitas(fasilitas.getKode());
					
					if(fasilitasOutletDao.listByExample(fasilitasOutlet).size()>0){
						fasilitasOutlet.setJumlah(getJumlah(txtJumlah.getText().toString()));
						fasilitasOutletDao.update(fasilitasOutlet);
						Toast.makeText(getApplicationContext(), "Fasilitas berhasil diupdate", Toast.LENGTH_LONG).show();
					}else{
						fasilitasOutlet.setJumlah(getJumlah(txtJumlah.getText().toString()));
						fasilitasOutletDao.insert(fasilitasOutlet);
						Toast.makeText(getApplicationContext(), "Fasilitas berhasil diinsert", Toast.LENGTH_LONG).show();
					}
					txtJumlah.setText("");
					viewFasilitas();
				}
			}

			private Integer getJumlah(String valueOf) {
				int result = 0;
				try {
					if(valueOf.equals("") ||valueOf == null){
						result = 0 ;
					}else{
						result = Integer.valueOf(valueOf) ;
					}
				} catch (Exception e) {
					return result;
				}
				return result;
			}
		});
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 100:
			xcoord = data.getStringExtra("xcoord");
			ycoord = data.getStringExtra("ycoord");
			locked = data.getBooleanExtra("locked", false);
			txtLat.setText(xcoord);
			txtLon.setText(ycoord);
			break;
			
		case 200:
			// img = (Bitmap) data.getExtras().get("data");
			try {
				if(data != null){
					Uri selectedImageUri = data.getData();
					path = getRealPathFromURI(selectedImageUri);
					final EditText input = new EditText(TabSurveyOutletActivity.this);
					AlertDialog.Builder alert = new AlertDialog.Builder(TabSurveyOutletActivity.this);
					alert.setTitle("Diskripsi Foto"); // Set Alert dialog//
					alert.setMessage("Silakan Tulis Diskripsi"); // Message here
					alert.setView(input);
					alert.setPositiveButton("Simpan",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int whichButton) {
									try {
										String fileName = changeFileName(path);
										photo.setKodeKunjungan(outlet.getKode()+new SimpleDateFormat("ddMMyyyy").format(new Date()));
										photo.setDeskripsi(input.getText().toString());
										photo.setNamaFile(fileName);
										photo.setKodeOutlet(outlet.getKode());
										photoDao.insert(photo);
										/*	if(photoDao.listByExample(photo).size()>0){
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
										/*outlet.getKode()+new SimpleDateFormat("ddMMyyyy").format(new Date())
										 * photo.setKode_outlet(outlet.getKode());
										int i = photoDao.listByExample(photo).size() + 1;
										photo.setKode(String.valueOf(i));
										photo.setKode_outlet(outlet.getKode());
										photo.setNama(path);
										photo.setDeskripsi(input.getText().toString());
										photoDao.insert(photo);*/
										viewPhoto();
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
					Toast.makeText(getApplicationContext(), "Ambil foto batal ", Toast.LENGTH_LONG).show();
				}
			} catch (Exception e) {
				Toast.makeText(getApplicationContext(), "Error : "+e.getMessage(), Toast.LENGTH_LONG).show();
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

	@SuppressWarnings("unused")
	private void setNoTitle() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	private void initView() {
		lblPemilik = (TextView) findViewById(R.id.lblNamaPemilik);
		lblHp = (TextView) findViewById(R.id.lblHpPemilik);
		txtPemilik = (EditText) findViewById(R.id.txtNamaPemilik);
		txtHp = (EditText) findViewById(R.id.txtHpPemilik);
		//lblOltId = (TextView) findViewById(R.id.lblOltId);
		lblOltName = (TextView) findViewById(R.id.txtKode);
		lblNumberOfPhoto = (TextView) findViewById(R.id.txtFoto);
		btnGetPosition = (Button) findViewById(R.id.btnGetPos);
		btnSaveFasilitas = (Button) findViewById(R.id.btnTambah);
		btnPhoto = (Button) findViewById(R.id.btnPhoto);
		txtLon = (EditText) findViewById(R.id.txtLongitude);
		txtLat = (EditText) findViewById(R.id.txtLatitude);
		txtJumlah = (EditText) findViewById(R.id.txtJumlah);
		viewPhoto = (ListView) findViewById(R.id.lsFoto);
		viewFasilitas = (ListView) findViewById(R.id.lsFasilitas);
		spnKlasifikasi = (Spinner) findViewById(R.id.spnKlasifikasi);
		spnAksesMasuk = (Spinner) findViewById(R.id.spnAksesMasuk);
		spnFasilitas = (Spinner) findViewById(R.id.spnFasilitas);
		spnStatusBangunan = (Spinner) findViewById(R.id.spnStatusBangunan);
		spnJenisLokasi = (Spinner) findViewById(R.id.spnJenisLokasi);
		spnKabKota = (Spinner) findViewById(R.id.spnKabKota);
		spnKecamatan = (Spinner) findViewById(R.id.spnKecamatan);
		txtNamaOlt = (EditText) findViewById(R.id.txtNamaOlt);
		txtAlamatOlt = (EditText) findViewById(R.id.txtAlamatOlt);
		txtRt = (EditText) findViewById(R.id.txtRt);
		txtRw = (EditText) findViewById(R.id.txtRw);
		txtKodePos = (EditText) findViewById(R.id.txtKodePos);
		spnKelurahan = (Spinner) findViewById(R.id.spnKelurahan);
		txtJenisPelanggan = (EditText) findViewById(R.id.txtJenisPelanggan);
		txtJumlahCoc = (EditText) findViewById(R.id.txtJumlahCoc);
		txtLuasBangunan = (EditText) findViewById(R.id.txtLuasBangunan);
		spnWaktuOperasi = (Spinner) findViewById(R.id.spnWaktuOperasi);
		lblAksesMasuk = (TextView) findViewById(R.id.lblAksesMasuk);
		lblStatusBangunan = (TextView) findViewById(R.id.lblStatusBangunan);
		lblLuasBangunan = (TextView) findViewById(R.id.lblLuasBangunan);
		lblJenisPelanggan = (TextView) findViewById(R.id.lblJenisPelanggan);
		lblWaktuOperasi = (TextView) findViewById(R.id.lblWaktuOperasi);
		lblJumlahCoc = (TextView) findViewById(R.id.lblJumlahCoc);
	}

	private void loadFasilitasOutlet() {
		fasilitas = new TmFasilitas();
		fasilitasDao = new TmFasilitasDao(getApplicationContext());
		listFasilitas = new ArrayList<TmFasilitas>();
		if(outlet.getKode().substring(0, 2).equals("MT")){
			fasilitas.setTipeOutlet("01");
		}else{
			fasilitas.setTipeOutlet("02");
		}
		listFasilitas = fasilitasDao.listByExample(fasilitas);
		lsFasilitas = new String[listFasilitas.size()];
		for (int i = 0; i < listFasilitas.size(); i++) {
			lsFasilitas[i] = listFasilitas.get(i).getNama();
		}
		fasilitasOutlet = new TmOutletFasilitas();
		fasilitasOutletDao = new TmOutletFasilitasDao(getApplicationContext());
		ArrayAdapter<String> adapterFasilitas = new ArrayAdapter<String>(
				TabSurveyOutletActivity.this,
				android.R.layout.simple_spinner_item, lsFasilitas);
		adapterFasilitas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnFasilitas.setAdapter(adapterFasilitas);
		spnFasilitas.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int index, long arg3) {
					fasilitas = listFasilitas.get(index);
					fasilitasOutlet.setKodeOutlet(outlet.getKode());
					fasilitasOutlet.setKodeFasilitas(fasilitas.getKode());
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	}

	private void loadAksesMasuk() {
		new TmAksesKunjungan();
		aksesMasukDao = new TmAksesKunjunganDao(getApplicationContext());
		listAksesMasuk = new ArrayList<TmAksesKunjungan>();
		listAksesMasuk = aksesMasukDao.listAll();
		lsAksesMasuk = new String[listAksesMasuk.size()];
		for (int i = 0; i < listAksesMasuk.size(); i++) {
			lsAksesMasuk[i] = listAksesMasuk.get(i).getName();
		}
		
		ArrayAdapter<String> adapterAksesMasuk = new ArrayAdapter<String>(
				TabSurveyOutletActivity.this,
				android.R.layout.simple_spinner_item, lsAksesMasuk);
		adapterAksesMasuk.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnAksesMasuk.setAdapter(adapterAksesMasuk);
		spnAksesMasuk.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				listAksesMasuk.get(arg2);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	}

	private void loadJenisLokasi() {
		new TmJenisLokasi();
		jenisLokasiDao = new TmJenisLokasiDao(getApplicationContext());
		listJenisLokasi = new ArrayList<TmJenisLokasi>();
		listJenisLokasi = jenisLokasiDao.listAll();
		lsJenisLokasi = new String[listJenisLokasi.size()];
		for (int i = 0; i < listJenisLokasi.size(); i++) {
			lsJenisLokasi[i] = listJenisLokasi.get(i).getNama();
		}
		ArrayAdapter<String> adapterStatusBangunan= new ArrayAdapter<String>(
				TabSurveyOutletActivity.this,
				android.R.layout.simple_spinner_item, lsJenisLokasi);
		adapterStatusBangunan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnJenisLokasi.setAdapter(adapterStatusBangunan);
		spnJenisLokasi.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				listJenisLokasi.get(arg2);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	}

	private void loadStatusBangunan() {
		new TmStatusBangunan();
		statusBangunanDao = new TmStatusBangunanDao(getApplicationContext());
		listStatusBangunan = new ArrayList<TmStatusBangunan>();
		listStatusBangunan = statusBangunanDao.listAll();
		lsStatusBangunan = new String[listStatusBangunan.size()];
		for (int i = 0; i < listStatusBangunan.size(); i++) {
			lsStatusBangunan[i] = listStatusBangunan.get(i).getName();
		}
		
		ArrayAdapter<String> adapterStatusBangunan= new ArrayAdapter<String>(
				TabSurveyOutletActivity.this,
				android.R.layout.simple_spinner_item, lsStatusBangunan);
		adapterStatusBangunan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnStatusBangunan.setAdapter(adapterStatusBangunan);
		spnStatusBangunan.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				listStatusBangunan.get(arg2);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	}

	private void loadKlasifikasi() {
		klasifikasi = new TmKlasifikasiOutlet();
		klasifikasiDao = new TmKlasifikasiOutletDao(getApplicationContext());
		listKlasifikasi = new ArrayList<TmKlasifikasiOutlet>();
		if(statusOutlet.equals("MT")){
			klasifikasi.setKodeTipe("01");
		} else{
			klasifikasi.setKodeTipe("02");
		}
		listKlasifikasi = klasifikasiDao.listByExample(klasifikasi);
		lsKlasifikasi = new String[listKlasifikasi.size()];
		for (int i = 0; i < listKlasifikasi.size(); i++) {
			lsKlasifikasi[i] = listKlasifikasi.get(i).getNama();
		}
		ArrayAdapter<String> adapterKlasifikasi = new ArrayAdapter<String>(
				TabSurveyOutletActivity.this,
				android.R.layout.simple_spinner_item, lsKlasifikasi);
		adapterKlasifikasi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnKlasifikasi.setAdapter(adapterKlasifikasi);
		spnKlasifikasi.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int index, long arg3) {
				klasifikasi = listKlasifikasi.get(index);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	}
	

	private void loadKelurahan(TmKecamatan kecamatan) {
		kelurahan = new TmKelurahan();
		kelurahan.setKodeKec(kecamatan.getKode());
		kelurahanDao = new TmKelurahanDao(getApplicationContext());
		listKelurahan = new ArrayList<TmKelurahan>();
		listKelurahan = kelurahanDao.listByExample(kelurahan);
		lsKelurahan = new String[listKelurahan.size()];
		for (int i = 0; i < listKelurahan.size(); i++) {
			kelurahan = listKelurahan.get(i);
			lsKelurahan[i] = kelurahan.getNama();
		}
		
		ArrayAdapter<String> adapterKelurahan = new ArrayAdapter<String>(
				TabSurveyOutletActivity.this,
				android.R.layout.simple_spinner_item, lsKelurahan);
		adapterKelurahan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnKelurahan.setAdapter(adapterKelurahan);
		spnKelurahan.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int index, long arg3) {
				kelurahan = listKelurahan.get(index);
				//txtKodePos.setText(kelurahan.getKodepos());
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	}

	private void loadKecamatan() {
		kecamatan = new TmKecamatan();
		kecamatan = getKecamatan();
		kecamatanDao = new TmKecamatanDao(getApplicationContext());
		listKabupaten = new ArrayList<TmKabupaten>();
		listKecamatan = kecamatanDao.listByExample(kecamatan);
		lsKecamatan = new String[listKecamatan.size()];
		for (int i = 0; i < listKecamatan.size(); i++) {
			kecamatan = listKecamatan.get(i);
			lsKecamatan[i] = kecamatan.getNama();
		}
		
		ArrayAdapter<String> adapterKecamatan = new ArrayAdapter<String>(
				TabSurveyOutletActivity.this,
				android.R.layout.simple_spinner_item, lsKecamatan);
		adapterKecamatan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnKecamatan.setAdapter(adapterKecamatan);
		spnKecamatan.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int index, long arg3) {
				kecamatan = listKecamatan.get(index);
				loadKelurahan(kecamatan);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub			
			}
		});
	}
	

	private TmKecamatan getKecamatan() {
		TmKecamatan result = new TmKecamatan();
		result.setKode(outlet.getKode().substring(2, 9));
		return result;
	}

	private void loadKabupaten() {
		kabupaten = new TmKabupaten();
		kabupaten = getKabupaten();
		kabupatenDao = new TmKabupatenDao(getApplicationContext());
		listKabupaten = new ArrayList<TmKabupaten>();
		listKabupaten = kabupatenDao.listByExample(kabupaten);
		lsKabupaten = new String[listKabupaten.size()];
		for (int i = 0; i < listKabupaten.size(); i++) {
			kabupaten = listKabupaten.get(i);
			lsKabupaten[i] = kabupaten.getNama();
		}
		ArrayAdapter<String> adapterKabupaten = new ArrayAdapter<String>(
				TabSurveyOutletActivity.this,
				android.R.layout.simple_spinner_item, lsKabupaten);
		adapterKabupaten.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnKabKota.setAdapter(adapterKabupaten);
		spnKabKota.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				//kabupaten = listKabupaten.get(arg2);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	}

	private TmKabupaten getKabupaten() {
		TmKabupaten result = new TmKabupaten();
		String kabupatenKode = outlet.getKode().substring(2, 6);
		result.setKode(kabupatenKode);
		return result;
	}
}
