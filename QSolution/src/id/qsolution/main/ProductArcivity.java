package id.qsolution.main;


import java.util.ArrayList;
import java.util.List;
import id.qsolution.adapter.SkuAdapter;
import id.qsolution.models.DaftarOutletSurvey;
import id.qsolution.models.TmBrand;
import id.qsolution.models.TmOutlet;
import id.qsolution.models.TmSku;
import id.qsolution.models.TmSurveyor;
import id.qsolution.models.TmVarian;
import id.qsolution.models.TtDKunjunganSurveyorRak;
import id.qsolution.models.TtMKunjunganSurveyor;
import id.qsolution.models.dao.TmSkuDao;
import id.qsolution.models.dao.TmVarianDao;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;

public class ProductArcivity extends Activity {

	private TmBrand brand;
	private ListView lsSku;
	private TmSkuDao skuDao;
	private SkuAdapter adapter;
	private List<TmSku> skus = new ArrayList<TmSku>();
	private TmSku sku;
	private TmOutlet outlet;
	private TmSurveyor surveyor;
	private TmVarian varian;
	private TmVarianDao varianDao;
	private ArrayList<TmVarian> listVarian;
	private String[] lsVarian;
	private Spinner spnVarian;
	private int index = 0;
	private boolean locked;
	private String xcoord;
	private String ycoord;
	private TtDKunjunganSurveyorRak rak;
	private DaftarOutletSurvey kategori;
	private TtMKunjunganSurveyor kunjungan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_products);
		spnVarian = (Spinner) findViewById(R.id.spnVarian);
		setTitle("PRODUCTS");
		outlet = (TmOutlet) getIntent().getSerializableExtra("outlet");
		brand = (TmBrand) getIntent().getSerializableExtra("brand");
		surveyor = (TmSurveyor) getIntent().getSerializableExtra("surveyor");
		rak = (TtDKunjunganSurveyorRak) getIntent().getSerializableExtra("rak");
		kategori = (DaftarOutletSurvey) getIntent().getSerializableExtra("kategori");
		kunjungan = (TtMKunjunganSurveyor) getIntent().getSerializableExtra("kunjungan");
		locked = getIntent().getBooleanExtra("locked", false);
		xcoord =  getIntent().getStringExtra("xcoord");
		ycoord =  getIntent().getStringExtra("ycoord");

		lsSku = (ListView) findViewById(R.id.lsOutlet);
		skuDao = new TmSkuDao(getApplicationContext());
		loadVarian();
		loadSku();
	}

		private void loadVarian() {
		varian = new TmVarian();
		varianDao = new TmVarianDao(getApplicationContext());
		listVarian = new ArrayList<TmVarian>();
		listVarian = (ArrayList<TmVarian>) varianDao.listAll();
		lsVarian = new String[listVarian.size() + 1];
		for (int i = 0; i < listVarian.size() + 1; i++) {
			if(i==0){
				lsVarian[i] = "All";
			}else{
				lsVarian[i] = listVarian.get(i - 1).getNama();
			}
		}
		ArrayAdapter<String> adapterKategori = new ArrayAdapter<String>(ProductArcivity.this, android.R.layout.simple_spinner_item,lsVarian);
		adapterKategori.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnVarian.setAdapter(adapterKategori);
		spnVarian.setOnItemSelectedListener(new OnItemSelectedListener() {
			
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
						if(arg2 != 0){
							index = arg2 - 1;
						}
						loadSku();
						//loadSubKatagori(kategori);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
					}
				});

	}
		
		private boolean isNullOrEmpty(String txt) {
			try {
				if(txt.equals("") || txt == null) return true;
			} catch (Exception e) {
				return true;
			}
			return false;
		}

	private void loadSku() {
		sku = new TmSku();
		skus.clear();
		if(brand.getKode().equals("00")){
			skus = skuDao.listAll();
		}else{
			varian = listVarian.get(index);
			sku.setKodeBrand(isNull(brand.getKode()));
			if(index != 0){
				sku.setKodeVarian(varian.getKode());
			}
			skus = skuDao.listByExample(sku);
		}
		adapter = new SkuAdapter(this, skus);
		lsSku.setAdapter(adapter);
		lsSku.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				sku = skus.get(index);
				Intent intent = new Intent();
				intent.putExtra("surveyor", surveyor);
				intent.putExtra("outlet", outlet);
				intent.putExtra("sku", sku);
				intent.putExtra("kunjungan", kunjungan);
				intent.putExtra("kategori", kategori);
				intent.putExtra("rak", rak);
				intent.putExtra("locked", locked);
				intent.putExtra("xcoord", xcoord);
				intent.putExtra("ycoord", ycoord);
				ProductArcivity.this.setResult(200, intent);
				finish();
			}
		});
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

	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		intent.putExtra("surveyor", surveyor);
		intent.putExtra("outlet", outlet);
		intent.putExtra("sku", sku);
		intent.putExtra("locked", locked);
		intent.putExtra("xcoord", xcoord);
		intent.putExtra("ycoord", ycoord);
		ProductArcivity.this.setResult(200, intent);
		finish();
	}
}
