package id.qsolution.adapter;

import id.qsolution.main.R;
import id.qsolution.models.TmBrand;
import id.qsolution.models.TmPosm;
import id.qsolution.models.TtDKunjunganSurveyorOutletPosm;
import id.qsolution.models.dao.TmBrandDao;
import id.qsolution.models.dao.TmPosmDao;

import java.util.List;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PosmOutletAdapter extends QsolutionAdapter {

	Activity act;
	
	public PosmOutletAdapter(Activity activity, List<?> data) {
		super(activity, data);
		act = activity;
	}

	@Override
	public View getView(int index, View view, ViewGroup viewGroup) {
		View v = view;
		if (v == null)
			v = lyInflater.inflate(R.layout.outlet_row, null);
		TextView header = (TextView) v.findViewById(R.id.header);
		TextView detail = (TextView) v.findViewById(R.id.detail);
		TtDKunjunganSurveyorOutletPosm outletPosm = (TtDKunjunganSurveyorOutletPosm) getItem(index);
		header.setText((index+1) +" "+outletPosm.getNamaPosm());
		detail.setText("Brand "+outletPosm.getNamaBrand() +" Jumlah "+outletPosm.getJumlah());
		return v;
	}

	private String getBrand(String kodeBrand) {
		String result = "";
		try {
			TmBrand brand = new TmBrand();
			TmBrandDao brandDao = new TmBrandDao(act.getApplicationContext());
			brand.setKode(kodeBrand);
			brand = brandDao.getByExample(brand);
			result = brand.getNama();
		} catch (Exception e) {
			result = "";
		}
		return result;
	}

	private String getPosm(String kode) {
		String result = "";
		try {
			TmPosm posm = new TmPosm();
			TmPosmDao posmDao = new TmPosmDao(act.getApplicationContext());
			posm.setKode(kode);
			posm = posmDao.getByExample(posm);
			result = posm.getNama();
		} catch (Exception e) {
			result = "";
		}
		return result;
	}

}
