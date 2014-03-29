package id.qsolution.adapter;

import id.qsolution.main.R;
import id.qsolution.models.TmBrand;
import id.qsolution.models.TmSku;
import id.qsolution.models.dao.TmBrandDao;

import java.util.List;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SkuAdapter extends QsolutionAdapter {
	
	Activity act;
	
	public SkuAdapter(Activity activity) {
		super(activity);

	}

	public SkuAdapter(Activity activity, List<?> data) {
		super(activity, data);
		act = activity;
	}

	@Override
	public View getView(int index, View view, ViewGroup viewGroup) {
		View v = view;
		if (v == null) v = lyInflater.inflate(R.layout.outlet_row, null);
		TextView skuNo = (TextView) v.findViewById(R.id.header);
		TextView skuName = (TextView) v.findViewById(R.id.detail);
		TmSku sku = (TmSku) getItem(index);
		TmBrand brand = new TmBrand();
		TmBrandDao brandDao = new TmBrandDao(act.getApplicationContext());
		brand.setKode(sku.getKodeBrand());
		brand = brandDao.getByExample(brand);
		skuNo.setText(sku.getDeskripsi());
		skuName.setText(brand.getNama());
		return v;
	}

}
