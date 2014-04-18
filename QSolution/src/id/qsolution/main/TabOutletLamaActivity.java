package id.qsolution.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import id.qsolution.adapter.PhotoAdapter;
import id.qsolution.models.TmOutlet;
import id.qsolution.models.TmSurveyor;
import id.qsolution.models.TtPhoto;
import id.qsolution.models.dao.TmOutletDao;
import id.qsolution.models.dao.TtPhotoDao;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class TabOutletLamaActivity extends TabActivity {
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
	private TtPhoto photo;
	private TtPhotoDao photoDao;
	private PhotoAdapter fotoAdapter;
	private List<TtPhoto> listPhoto;
	private ListView lsPhoto;
	private TmOutletDao outletDao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_update_outlet);
		setTitle("EXISTING OUTLET");
		surveyor = (TmSurveyor) getIntent().getSerializableExtra("surveyor");
		outlet = (TmOutlet) getIntent().getSerializableExtra("outlet");
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
		
		spec = tabHost.newTabSpec("Photo")
				.setIndicator("Photo", res.getDrawable(R.drawable.ic_action_photo))
				.setContent(R.id.form2);
		tabHost.addTab(spec);
		tabHost.setCurrentTab(0);
		initView();
		loadForm();
		viewPhoto();
		setListener();
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
				outlet.setKode(outlet.getKode());
				outlet.setXCoord(Double.valueOf(txtLat.getText().toString()));
				outlet.setYCoord(Double.valueOf(txtLon.getText().toString()));
				outletDao = new TmOutletDao(getApplicationContext());
				outletDao.update(outlet);
				loadAudit();
				finish();
				//Toast.makeText(getApplicationContext(), "Valid", Toast.LENGTH_LONG).show();
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	private void loadAudit() {
		//TmOutlet outletSelected = (TmOutlet) adapter.getItem(i);
		Intent intent = new Intent(TabOutletLamaActivity.this, TabSurveyAudit.class);
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
				intent = new Intent(TabOutletLamaActivity.this, LocationActivity.class);
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

	private void loadForm() {
		txtNama.setText(outlet.getNama());
		txtAlamat.setText(outlet.getAlamat());
		txtLon.setText(String.valueOf(validateDouble(outlet.getYCoord())));
		txtLat.setText(String.valueOf(validateDouble(outlet.getXCoord())));
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
		txtNama = (EditText) findViewById(R.id.txtNama);
		txtAlamat = (EditText) findViewById(R.id.txtAlamat);
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
		case 200:
			Bitmap img = (Bitmap) data.getExtras().get("data");
			Uri selectedImageUri = data.getData();
			path = getRealPathFromURI(selectedImageUri);
			final EditText input = new EditText(TabOutletLamaActivity.this);
			AlertDialog.Builder alert = new AlertDialog.Builder(TabOutletLamaActivity.this);
			alert.setTitle("Diskripsi Foto"); // Set Alert dialog//
			alert.setMessage("Silakan Tulis Diskripsi"); // Message here
			alert.setView(input);
			alert.setPositiveButton("Simpan",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							try {
								photo.setKode_outlet(outlet.getKode());
								int i = photoDao.listByExample(photo).size() + 1;
								photo.setKode(String.valueOf(i));
								photo.setKode_outlet(outlet.getKode());
								photo.setNama(path);
								photo.setDeskripsi(input.getText().toString());
								photoDao.insert(photo);
								viewPhoto();
								Toast.makeText(getApplicationContext(), path, Toast.LENGTH_LONG).show();
							} catch (Exception e) {
								Log.i("onSave", e.getMessage());
							}
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
			break;
		
		default:
			break;
		}
	}
	
	private void viewPhoto() {
		photo = new TtPhoto();
		photoDao = new TtPhotoDao(getApplicationContext());
		listPhoto = new ArrayList<TtPhoto>();
		photo.setKode_outlet(outlet.getKode());
		listPhoto = photoDao.listByExample(photo);
		fotoAdapter = new PhotoAdapter(TabOutletLamaActivity.this, listPhoto);
		lsPhoto.setAdapter(fotoAdapter);
		fotoAdapter.notifyDataSetChanged();
		//lblNumberOfPhoto.setText("Foto("+listPhoto.size()+")");
		lsPhoto.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int i, long index) {	
				try {
					photo = listPhoto.get(i);
					photoDao.delete(photo.getId());
					File file = new File(photo.getNama());
					file.delete();
					Toast.makeText(getApplicationContext(), "Foto telah dihapus index "+i, Toast.LENGTH_LONG).show();
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
				TtPhoto foto = (TtPhoto) fotoAdapter.getItem(position);
				LayoutInflater inflater = getLayoutInflater();
				View layout = inflater.inflate(R.layout.toast_img_layout, (ViewGroup) findViewById(R.id.toast_layout_root));
				ImageView image = (ImageView) layout.findViewById(R.id.image);
				image.setImageDrawable(Drawable.createFromPath(foto.getNama()));
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
