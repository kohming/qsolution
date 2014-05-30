package id.qsolution.main;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import id.qsolution.adapter.SkuAdapter;
import id.qsolution.models.DaftarOutletSurvey;
import id.qsolution.models.TmBrand;
import id.qsolution.models.TmOutlet;
import id.qsolution.models.TmRak;
import id.qsolution.models.TmSku;
import id.qsolution.models.TmSubKategoriBarang;
import id.qsolution.models.TmSurveyor;
import id.qsolution.models.TmVarian;
import id.qsolution.models.TtDKunjunganSurveyorRak;
import id.qsolution.models.TtMKunjunganSurveyor;
import id.qsolution.models.dao.TmBrandDao;
import id.qsolution.models.dao.TmRakDao;
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
import android.widget.Toast;


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
	private TmSubKategoriBarang subKategori;
	private TmRak r;
	private TmRakDao rDao;
	private TmSku produk;
	private TmSkuDao produkDao;
	private ArrayList<TmSku> listProduk;
	private ArrayList<TmVarian> listVarianTmp;
	private TmBrandDao brandDao;

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
		subKategori = (TmSubKategoriBarang) getIntent().getSerializableExtra("subkategori");
		brandDao = new TmBrandDao(getApplicationContext());
		locked = getIntent().getBooleanExtra("locked", false);
		xcoord =  getIntent().getStringExtra("xcoord");
		ycoord =  getIntent().getStringExtra("ycoord");
		lsSku = (ListView) findViewById(R.id.lsOutlet);
		skuDao = new TmSkuDao(getApplicationContext());
		r = new TmRak();
		rDao = new TmRakDao(getApplicationContext());
		
		//loadVarian();
		/*loadVarianBySubCategoryBrand(subKategori, brand);
		loadSubKategoriSku(subKategori);
		*/
		if(isBrand()){
			loadVarianBySubCategoryBrand(subKategori, brand);
			loadSubKategoriSku();
		}else{
			loadVarianBySubCategory(subKategori);
			loadSubKategoriSku();
			//loadSku();
		}
		setListener();
	}
	
	private void loadVarianBySubCategoryBrand(TmSubKategoriBarang subKategori2,
			TmBrand brand2) {
		produk = new TmSku();
		produkDao = new TmSkuDao(getApplicationContext());
		listProduk = new ArrayList<TmSku>();
		produk.setKodeSubKategori(subKategori.getKode());
		produk.setKodeBrand(brand2.getKode());
		listProduk = (ArrayList<TmSku>) produkDao.listByExample(produk);
		varianDao = new TmVarianDao(getApplicationContext());
		listVarian = new ArrayList<TmVarian>();
		listVarianTmp = new ArrayList<TmVarian>();

		for (TmSku lsProduk : listProduk) {
			varian = new TmVarian();
			varian.setKode(lsProduk.getKodeVarian());
			varian = varianDao.getByExample(varian);
			listVarianTmp.add(varian);
		}

		listVarian = filterVariant(listVarianTmp);

		lsVarian = new String[listVarian.size() + 1];
		for (int i = 0; i < listVarian.size() + 1; i++) {
			if (i == 0) {
				lsVarian[i] = "All";
			} else {
				lsVarian[i] = listVarian.get(i - 1).getNama();
			}
		}
		ArrayAdapter<String> adapterKategori = new ArrayAdapter<String>(
				ProductArcivity.this, android.R.layout.simple_spinner_item,
				lsVarian);
		adapterKategori
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnVarian.setAdapter(adapterKategori);
		spnVarian.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int i,
					long arg3) {
				if (i != 0) {
					index = i - 1;
				}
				loadSubKategoriSku();
				// loadSku();
				// loadSubKatagori(kategori);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		
	}

	private void loadVarianBySubCategory(final TmSubKategoriBarang subKategori) {
		produk = new TmSku();
		produkDao = new TmSkuDao(getApplicationContext());
		listProduk = new ArrayList<TmSku>();
		produk.setKodeSubKategori(subKategori.getKode());
		listProduk = (ArrayList<TmSku>) produkDao.listByExample(produk);
		varianDao = new TmVarianDao(getApplicationContext());
		listVarian = new ArrayList<TmVarian>();
		listVarianTmp = new ArrayList<TmVarian>();

		for (TmSku lsProduk : listProduk) {
			varian = new TmVarian();
			varian.setKode(lsProduk.getKodeVarian());
			varian = varianDao.getByExample(varian);
			listVarianTmp.add(varian);
		}

		listVarian = filterVariant(listVarianTmp);

		lsVarian = new String[listVarian.size() + 1];
		for (int i = 0; i < listVarian.size() + 1; i++) {
			if (i == 0) {
				lsVarian[i] = "All";
			} else {
				lsVarian[i] = listVarian.get(i - 1).getNama();
			}
		}
		ArrayAdapter<String> adapterKategori = new ArrayAdapter<String>(
				ProductArcivity.this, android.R.layout.simple_spinner_item,
				lsVarian);
		adapterKategori
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnVarian.setAdapter(adapterKategori);
		spnVarian.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int i,
					long arg3) {
				if (i != 0) {
					index = i - 1;
				}
				loadSubKategoriSku();
				// loadSku();
				// loadSubKatagori(kategori);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

	}

	
	private ArrayList<TmVarian> filterVariant(ArrayList<TmVarian> listByExample) {
		ArrayList <TmVarian> result = new ArrayList<TmVarian>();
		Set<String> titles = new HashSet<String>();
		for( TmVarian item : listByExample ) {
		    if( titles.add( item.getKode()) ) {
		        result.add( item );
		    }
		}
		return result;
	}

	private void setListener() {
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

	private void loadSubKategoriSku() {
		sku = new TmSku();
		skus = new ArrayList<TmSku>();
		//sku.setKodeSubKategori(subKategori.getKode());
		/*if(listVarian.size() > 0){
			varian = listVarian.get(index);
		}else{
			listVarian = (ArrayList<TmVarian>) varianDao.listAll();
			varian = listVarian.get(0);
		}
		
		if(index > 0){
			sku.setKodeSubKategori(subKategori.getKode());
			sku.setKodeVarian(varian.getKode());
			if(isBrand()){
				sku.setKodeBrand(brand.getKode());
			}
			skus = skuDao.listByExample(sku);
		} else{
			sku.setKodeSubKategori(subKategori.getKode());
			skus = skuDao.listByExample(sku);
		}
		*/
		if(isBrand()){
			sku.setKodeSubKategori(subKategori.getKode());
			sku.setKodeBrand(brand.getKode());
			if(index > 0){
				sku.setKodeVarian(varian.getKode());
			}
			skus = skuDao.listByExample(sku);
		}else{
			sku.setKodeSubKategori(subKategori.getKode());
			skus = skuDao.listByExample(sku);
		}
		adapter = new SkuAdapter(this, skus);
		lsSku.setAdapter(adapter);
	}

	private boolean isBranded(TtDKunjunganSurveyorRak rak2) {
	
		r.setKode(rak2.getKodeRak());
		try {
			r = rDao.getByExample(r);
			if(r.getBranded().equals("01")){
				return true;
			}else{
				
				return false;
			}
			
		} catch (Exception e) {
			return false;
		}
	}


/*		private void loadVarian() {
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
						//loadSku();
						//loadSubKatagori(kategori);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
					}
				});

	}*/
		
	private void loadSku() {
		sku = new TmSku();
		skus.clear();
		if(isBrand()){
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
	}
	
	private boolean isBrand() {
		try {		
			if(brandDao.listByExample(brand).size() > 0){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		
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