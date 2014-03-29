package id.qsolution.main;

import java.util.List;
import id.qsolution.adapter.PosmOutletAdapter;
import id.qsolution.adapter.PosmRakAdapter;
import id.qsolution.adapter.PromosiAdapter;
import id.qsolution.adapter.RakAdapter;
import id.qsolution.adapter.SurveyProdukAdapter;
import id.qsolution.models.DaftarOutletSurvey;
import id.qsolution.models.TmBrand;
import id.qsolution.models.TmOutlet;
import id.qsolution.models.TmSku;
import id.qsolution.models.TmSurveyor;
import id.qsolution.models.TtDKunjunganSurveyorOutletPosm;
import id.qsolution.models.TtDKunjunganSurveyorRak;
import id.qsolution.models.TtDKunjunganSurveyorRakPosm;
import id.qsolution.models.TtDKunjunganSurveyorRakSku;
import id.qsolution.models.TtDKunjunganSurveyorRakSkuPromosi;
import id.qsolution.models.TtMKunjunganSurveyor;
import id.qsolution.models.dao.TtDKunjunganSurveyorOutletPosmDao;
import id.qsolution.models.dao.TtDKunjunganSurveyorRakPosmDao;
import id.qsolution.models.dao.TtDKunjunganSurveyorRakSkuDao;
import id.qsolution.models.dao.TtDKunjunganSurveyorRakSkuPromosiDao;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class TabListAuditProduk extends TabActivity {

	private ListView lsSku;
	private List<TtDKunjunganSurveyorRakSku> listSku;
	private SurveyProdukAdapter adapterSku;
	private ListView lsPosmOutlet;
	private TmOutlet outlet;
	private List<TtDKunjunganSurveyorOutletPosm> listPosmOutlet;
	private PosmOutletAdapter adapterPosm;
	private ListView lsPosmRak;
	private List<TtDKunjunganSurveyorRakPosm> listPosmRak;
	private PosmRakAdapter adapterPosmRak;
	private List<TtDKunjunganSurveyorRakSkuPromosi> listPromosi;
	private PromosiAdapter adapterPromo;
	private ListView lsPromosi;
	private ListView lsRak;
	private List<TtDKunjunganSurveyorRak> listRak;
	private RakAdapter adapterRak;
	private TtDKunjunganSurveyorRak rak;
	private TmBrand brand;
	private TmSurveyor surveyor;
	private boolean locked;
	private String xcoord;
	private String ycoord;
	private DaftarOutletSurvey kategori;
	private TtMKunjunganSurveyor kunjungan;
	private TmSku sku;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_produk);
		setTitle("AUDIT SUMMARY");
		surveyor = (TmSurveyor) getIntent().getSerializableExtra("surveyor");
		outlet = (TmOutlet) getIntent().getSerializableExtra("outlet");
		sku = (TmSku) getIntent().getSerializableExtra("sku");
		rak = (TtDKunjunganSurveyorRak) getIntent().getSerializableExtra("rak");
		kategori = (DaftarOutletSurvey) getIntent().getSerializableExtra("kategori");
		kunjungan = (TtMKunjunganSurveyor) getIntent().getSerializableExtra("kunjungan");
		xcoord = getIntent().getStringExtra("xcoord");
		ycoord = getIntent().getStringExtra("ycoord");
		locked = getIntent().getBooleanExtra("locked", false);
		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost
				.newTabSpec("SKU")
				.setIndicator("SKU",
						res.getDrawable(R.drawable.ic_action_attach))
				.setContent(R.id.form1);
		tabHost.addTab(spec);
		/*
		 * spec = tabHost.newTabSpec("Rak") .setIndicator("Rak",
		 * res.getDrawable(R.drawable.ic_action_select_all))
		 * .setContent(R.id.form2); tabHost.addTab(spec);
		 */

		spec = tabHost
				.newTabSpec("POSM Outlet")
				.setIndicator("POSM Outlet",
						res.getDrawable(R.drawable.ic_action_paste))
				.setContent(R.id.form2);
		tabHost.addTab(spec);

		spec = tabHost
				.newTabSpec("POSM Rak")
				.setIndicator("POSM Rak",
						res.getDrawable(R.drawable.ic_action_share))
				.setContent(R.id.form3);
		tabHost.addTab(spec);

		spec = tabHost
				.newTabSpec("Promosi")
				.setIndicator("Promosi",
						res.getDrawable(R.drawable.ic_action_star))
				.setContent(R.id.form4);
		tabHost.addTab(spec);
		tabHost.setCurrentTab(0);
		loadProduk();
		loadPosmOutlet();
		loadPosmRak();
		loadPromo();
		// loadRak();
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(TabListAuditProduk.this,TabSurveySku.class);
		intent.putExtra("surveyor", surveyor);
		intent.putExtra("outlet", outlet);
		intent.putExtra("sku", sku);
		intent.putExtra("kunjungan", kunjungan);
		intent.putExtra("kategori", kategori);
		intent.putExtra("rak", rak);
		intent.putExtra("locked", locked);
		intent.putExtra("xcoord", xcoord);
		intent.putExtra("ycoord", ycoord);
		setResult(100, intent);
		finish();
	}

	
	private void loadPromo() {
		lsPromosi = (ListView) findViewById(R.id.lsPromosi);
		TtDKunjunganSurveyorRakSkuPromosi promosi = new TtDKunjunganSurveyorRakSkuPromosi();
		TtDKunjunganSurveyorRakSkuPromosiDao promsiDao = new TtDKunjunganSurveyorRakSkuPromosiDao(
				getApplicationContext());
		promosi.setNomorUrut(rak.getNomorUrut());
		listPromosi = promsiDao.listByExample(promosi);
		adapterPromo = new PromosiAdapter(this, listPromosi);
		lsPromosi.setAdapter(adapterPromo);
		lsPromosi.setOnItemClickListener(new OnItemClickListener() {
			private int index;
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				index = arg2;
				AlertDialog.Builder alert = new AlertDialog.Builder(TabListAuditProduk.this);
				alert.setTitle("Edit promosi");
				alert.setMessage("Edit promosi "+listPromosi.get(index).getNamaPromosi()+" ?");
				final TextView lblPromo = new TextView(TabListAuditProduk.this);
				lblPromo.setText("Nama Promosi");
				lblPromo.setTextColor(Color.WHITE);
				final EditText promo = new EditText(TabListAuditProduk.this);
				promo.setText(String.valueOf(listPromosi.get(index).getDeskripsi()));
				LinearLayout layout = new LinearLayout(getApplicationContext());
				layout.setOrientation(LinearLayout.VERTICAL);
				layout.addView(lblPromo);
				layout.addView(promo);
				alert.setView(layout);
				alert.setPositiveButton("Simpan",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int whichButton) {
								TtDKunjunganSurveyorRakSkuPromosi promosi = new TtDKunjunganSurveyorRakSkuPromosi();
								TtDKunjunganSurveyorRakSkuPromosiDao promsiDao = new TtDKunjunganSurveyorRakSkuPromosiDao(getApplicationContext());
								promosi = listPromosi.get(index);
								promosi.setDeskripsi(promo.getText().toString());
								promsiDao.update(promosi);
								Toast.makeText(getApplicationContext(), "Promosi sukses diupdate", Toast.LENGTH_LONG).show();
								loadPromo();
							}
						});

				alert.setNegativeButton("Batal",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								// Canceled.
							}
						});
				alert.show();
			}
		});		
	}
	
	

	private void loadPosmRak() {
		lsPosmRak = (ListView) findViewById(R.id.lsPosmRak);
		TtDKunjunganSurveyorRakPosm posmRak = new TtDKunjunganSurveyorRakPosm();
		TtDKunjunganSurveyorRakPosmDao posmRakDao = new TtDKunjunganSurveyorRakPosmDao(
				getApplicationContext());
		posmRak.setNomorUrut(rak.getNomorUrut());
		posmRak.setKode(outlet.getKode());
		listPosmRak = posmRakDao.listByExample(posmRak);
		adapterPosmRak = new PosmRakAdapter(this, listPosmRak);
		lsPosmRak.setAdapter(adapterPosmRak);
		lsPosmRak.setOnItemClickListener(new OnItemClickListener() {

			private int index;

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				index = arg2;
				AlertDialog.Builder alert = new AlertDialog.Builder(TabListAuditProduk.this);
				alert.setTitle("Edit posm rak");
				alert.setMessage("Edit posm rak rak "+listPosmRak.get(index).getJumlah()+" ?");
				final TextView lblJumlahPosmRak = new TextView(TabListAuditProduk.this);
				lblJumlahPosmRak.setText("Jumlah Posm Rak");
				lblJumlahPosmRak.setTextColor(Color.WHITE);
				final EditText value = new EditText(TabListAuditProduk.this);
				value.setText(String.valueOf(listPosmRak.get(index).getJumlah()));
				LinearLayout layout = new LinearLayout(getApplicationContext());
				layout.setOrientation(LinearLayout.VERTICAL);
				layout.addView(lblJumlahPosmRak);
				layout.addView(value);
				alert.setView(layout);
				alert.setPositiveButton("Simpan",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int whichButton) {
								TtDKunjunganSurveyorRakPosm posmRak = new TtDKunjunganSurveyorRakPosm();
								TtDKunjunganSurveyorRakPosmDao posmRakDao = new TtDKunjunganSurveyorRakPosmDao(getApplicationContext());
								posmRak = listPosmRak.get(index);
								posmRak.setJumlah(Integer.valueOf(value.getText().toString()));
								posmRakDao.update(posmRak);
								Toast.makeText(getApplicationContext(), "Posm Rak sukses diupdate", Toast.LENGTH_LONG).show();
								loadPosmRak();
							}
						});

				alert.setNegativeButton("Batal",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								// Canceled.
							}
						});
				alert.show();
			}
		});

	}

	private void loadPosmOutlet() {
		lsPosmOutlet = (ListView) findViewById(R.id.lsPosmOutlet);
		TtDKunjunganSurveyorOutletPosm posmOutlet = new TtDKunjunganSurveyorOutletPosm();
		TtDKunjunganSurveyorOutletPosmDao posmOutletDao = new TtDKunjunganSurveyorOutletPosmDao(
				getApplicationContext());
		posmOutlet.setKodeKunjungan(rak.getKodeKunjungan());
		listPosmOutlet = posmOutletDao.listByExample(posmOutlet);
		adapterPosm = new PosmOutletAdapter(this, listPosmOutlet);
		lsPosmOutlet.setAdapter(adapterPosm);
		lsPosmOutlet.setOnItemClickListener(new OnItemClickListener() {

			private int index;

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				index = arg2;
				AlertDialog.Builder alert = new AlertDialog.Builder(TabListAuditProduk.this);
				alert.setTitle("Edit posm outlet");
				alert.setMessage("Edit posm outlet "+listPosmOutlet.get(index).getNamaPosm()+" ?");
				final TextView lblJumlahPosmOutlet = new TextView(TabListAuditProduk.this);
				lblJumlahPosmOutlet.setText("Jumlah Posm Outlet");
				lblJumlahPosmOutlet.setTextColor(Color.WHITE);
				final EditText value = new EditText(TabListAuditProduk.this);
				value.setText(String.valueOf(listPosmOutlet.get(index).getJumlah()));
				LinearLayout layout = new LinearLayout(getApplicationContext());
				layout.setOrientation(LinearLayout.VERTICAL);
				layout.addView(lblJumlahPosmOutlet);
				layout.addView(value);
				alert.setView(layout);
				alert.setPositiveButton("Simpan",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int whichButton) {
								TtDKunjunganSurveyorOutletPosm posmOutlet = new TtDKunjunganSurveyorOutletPosm();
								TtDKunjunganSurveyorOutletPosmDao posmOutletDao = new TtDKunjunganSurveyorOutletPosmDao(getApplicationContext());
								posmOutlet = listPosmOutlet.get(index);
								posmOutlet.setJumlah(Integer.valueOf(value.getText().toString()));
								posmOutletDao.update(posmOutlet);
								Toast.makeText(getApplicationContext(), "Posm Rak sukses diupdate", Toast.LENGTH_LONG).show();
								loadPosmOutlet();
							}
						});

				alert.setNegativeButton("Batal",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								// Canceled.
							}
						});
				alert.show();
			}
		});
	}



	private void loadProduk() {
		lsSku = (ListView) findViewById(R.id.lsSku);
		TtDKunjunganSurveyorRakSku sku = new TtDKunjunganSurveyorRakSku();
		TtDKunjunganSurveyorRakSkuDao skuDao = new TtDKunjunganSurveyorRakSkuDao(
				getApplicationContext());
		sku.setNomorUrut(rak.getNomorUrut());
		sku.setKode(outlet.getKode());
		listSku = skuDao.listByExample(sku);
		adapterSku = new SurveyProdukAdapter(this, listSku);
		lsSku.setAdapter(adapterSku);
		lsSku.setOnItemClickListener(new OnItemClickListener() {
			private int index;
			private Dialog mapView;

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int x,
					long arg3) {
				index = x;
				AlertDialog.Builder alert = new AlertDialog.Builder(TabListAuditProduk.this);
				alert.setTitle("Edit produk");
				alert.setMessage("Edit produk "+listSku.get(index).getNamaSku()+" ?");
				// Set an EditText view to get user input
				final TextView lblUnit = new TextView(TabListAuditProduk.this);
				lblUnit.setText("Jumlah Unit");
				lblUnit.setTextColor(Color.WHITE);
				final EditText unit = new EditText(TabListAuditProduk.this);
				unit.setText(String.valueOf(listSku.get(index).getJumlahUnit()));
				final TextView lblFacing = new TextView(TabListAuditProduk.this);
				lblFacing.setText("Jumlah Facing");
				lblFacing.setTextColor(Color.WHITE);
				final EditText facing = new EditText(TabListAuditProduk.this);
				facing.setText(String.valueOf(listSku.get(index).getJumlahFacing()));
				final TextView lblHarga = new TextView(TabListAuditProduk.this);
				lblHarga.setText("Jumlah Harga");
				lblHarga.setTextColor(Color.WHITE);
				final EditText harga = new EditText(TabListAuditProduk.this);
				harga.setText(String.valueOf(listSku.get(index).getHargaJual()));
				LinearLayout layout = new LinearLayout(getApplicationContext());
				layout.setOrientation(LinearLayout.VERTICAL);
				layout.addView(lblUnit);
				layout.addView(unit);
				layout.addView(lblFacing);
				layout.addView(facing);
				layout.addView(lblHarga);
				layout.addView(harga);
				alert.setView(layout);
				alert.setPositiveButton("Simpan",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int whichButton) {
								TtDKunjunganSurveyorRakSku sku = new TtDKunjunganSurveyorRakSku();
								TtDKunjunganSurveyorRakSkuDao skuDao = new TtDKunjunganSurveyorRakSkuDao(getApplicationContext());
								sku = listSku.get(index);
								sku.setJumlahFacing(Integer.valueOf(facing.getText().toString()));
								sku.setJumlahUnit(Integer.valueOf(unit.getText().toString()));
								sku.setHargaJual(Integer.valueOf(harga.getText().toString()));
								skuDao.update(sku);
								Toast.makeText(getApplicationContext(), "Produk sukses diupdate", Toast.LENGTH_LONG).show();
								loadProduk();
							}
						});

				alert.setNegativeButton("Batal",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								// Canceled.
							}
						});
				alert.show();
			}
		});

	}
}
