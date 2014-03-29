package id.qsolution.main;



import id.qsolution.models.DaftarOutletSurvey;
import id.qsolution.models.TmOutlet;
import id.qsolution.models.TmPromosiSku;
import id.qsolution.models.TmSku;
import id.qsolution.models.TmSurveyor;
import id.qsolution.models.TtDKunjunganSurveyorRak;
import id.qsolution.models.TtDKunjunganSurveyorRakSkuPromosi;
import id.qsolution.models.TtMKunjunganSurveyor;
import id.qsolution.models.dao.TmPromosiSkuDao;
import id.qsolution.models.dao.TtDKunjunganSurveyorRakSkuPromosiDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class PromosiActivity extends Activity {

	private Spinner spnJenis;
	private EditText txtPromo;
	private Button btnTambah;
	private TmPromosiSku promosi;
	private TmPromosiSkuDao promosiDao;
	private List<TmPromosiSku> listPromosi;
	private String[] lsPromosi;
	private TmSurveyor surveyor;
	private TmOutlet outlet;
	private TmSku sku;
	private TtDKunjunganSurveyorRak rak;
	private DaftarOutletSurvey kategori;
	private TtMKunjunganSurveyor kunjungan;
	private String xcoord;
	private String ycoord;
	private boolean locked;
	private ArrayList<TmPromosiSku> listPromosiTmp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_promo);
		setTitle("PROMOTIONS");
		surveyor = (TmSurveyor) getIntent().getSerializableExtra("surveyor");
		outlet = (TmOutlet) getIntent().getSerializableExtra("outlet");
		sku = (TmSku) getIntent().getSerializableExtra("sku");
		rak = (TtDKunjunganSurveyorRak) getIntent().getSerializableExtra("rak");
		kategori = (DaftarOutletSurvey) getIntent().getSerializableExtra("kategori");
		kunjungan = (TtMKunjunganSurveyor) getIntent().getSerializableExtra("kunjungan");
		xcoord = getIntent().getStringExtra("xcoord");
		ycoord = getIntent().getStringExtra("ycoord");
		locked = getIntent().getBooleanExtra("locked", false);
		initView();
		loadPromosi();
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(PromosiActivity.this,TabSurveySku.class);
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

	private void initView() {
		spnJenis = (Spinner) findViewById(R.id.spnJenis);
		txtPromo = (EditText) findViewById(R.id.txtPromo);
		btnTambah = (Button) findViewById(R.id.btnTambah);
		btnTambah.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					if(!isNullOrEmpty(txtPromo)){
						TtDKunjunganSurveyorRakSkuPromosi promosiSku = new TtDKunjunganSurveyorRakSkuPromosi();
						TtDKunjunganSurveyorRakSkuPromosiDao promosiDao = new TtDKunjunganSurveyorRakSkuPromosiDao(getApplicationContext());
						promosiSku.setKode(outlet.getKode());
						promosiSku.setKodePromosi(promosi.getKode());
						promosiSku.setKodeSku(sku.getKode());
						promosiSku.setDeskripsi(txtPromo.getText().toString());
						promosiSku.setKodeKunjungan(outlet.getKode() +kategori.getKodeKategori()+ new SimpleDateFormat("ddMMyy").format(new Date()));
						promosiSku.setNamaPromosi(promosi.getNama());
						promosiSku.setNamaSku(sku.getDeskripsi());
						promosiSku.setNomorUrut(rak.getNomorUrut());
						promosiDao.insert(promosiSku);
						txtPromo.setText("");
						Toast.makeText(getApplicationContext(), "Produk "+sku.getDeskripsi()+" berhasil disimpan", Toast.LENGTH_LONG).show();
					}
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "Produk harus dipilih terlebih dahulu", Toast.LENGTH_LONG).show();
				}
			}
		});
	}
	

	
	private boolean isNullOrEmpty(EditText txt) {
		if (txt.getText().toString() == null || txt.getText().toString().equals("")) {
			return true;
		}
		return false;
	}

	private void loadPromosi() {
		promosi = new TmPromosiSku();
		promosiDao = new TmPromosiSkuDao(getApplicationContext());
		listPromosi= new ArrayList<TmPromosiSku>();
		listPromosiTmp= new ArrayList<TmPromosiSku>();
		listPromosi = promosiDao.listAll();
		
		if(outlet.getKode().substring(0, 2).equals("MT")){
			for (int i = 0;i< listPromosi.size();i++) {
				if(toInt(listPromosi.get(i).getKode()) > 0 && toInt(listPromosi.get(i).getKode()) < 21 ){
					listPromosiTmp.add(listPromosi.get(i));
					//lsPromosi[i] = listPromosi.get(i).getNama();
				}
				//lsPromosi[i] = listPromosi.get(i).getNama();
			}
		} else{
			for (int i = 0;i< listPromosi.size();i++) {
				if(toInt(listPromosi.get(i).getKode()) > 20 && toInt(listPromosi.get(i).getKode()) < 41 ){
					//lsPromosi[i] = listPromosi.get(i).getNama();
					listPromosiTmp.add(listPromosi.get(i));
				}
			}
		}
		lsPromosi = new String[listPromosiTmp.size()];
		
		for (int i = 0;i< listPromosiTmp.size();i++) {
			lsPromosi[i] = listPromosiTmp.get(i).getNama();
		}
		
		ArrayAdapter<String> adapterPromosi = new ArrayAdapter<String>(
				PromosiActivity.this,
				android.R.layout.simple_spinner_item, lsPromosi);
		adapterPromosi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnJenis.setAdapter(adapterPromosi);
		spnJenis.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int index, long arg3) {
				promosi = new TmPromosiSku();
				promosi = listPromosiTmp.get(index);
				//Toast.makeText(getApplicationContext(), "promosi  "+ lsPromosi[index], Toast.LENGTH_LONG).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}

	

	private int toInt(String kode) {
		int result = 0;
		try {
			result = Integer.valueOf(kode);
		} catch (Exception e) {
			return result;
		}
		return result;
	}
}
