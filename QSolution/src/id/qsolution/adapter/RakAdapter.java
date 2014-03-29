package id.qsolution.adapter;

import id.qsolution.main.R;
import id.qsolution.models.TmBrand;
import id.qsolution.models.TmRak;
import id.qsolution.models.TtDKunjunganSurveyorRak;
import id.qsolution.models.dao.TmBrandDao;
import id.qsolution.models.dao.TmRakDao;
import java.util.List;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RakAdapter extends QsolutionAdapter {

	Activity act;
	public RakAdapter(Activity activity, List<?> data) {
		super(activity, data);
		act = activity;
	}

	@Override
	public View getView(int index, View view, ViewGroup viewGroup) {
		View v = view;
		if (v == null)
			v = lyInflater.inflate(R.layout.rak_row, null);
		TextView header = (TextView) v.findViewById(R.id.header);
		TextView detail = (TextView) v.findViewById(R.id.detail);
		TtDKunjunganSurveyorRak rak = (TtDKunjunganSurveyorRak) getItem(index);
		
		TmRak masterRak = new TmRak();
		TmRakDao masterRakDao = new TmRakDao(act.getApplicationContext());
		
		
		masterRak.setKode(rak.getKodeRak());
		masterRak = masterRakDao.getByExample(masterRak);
		
		header.setText((index+1) + "  R." +rak.getNomorUrut()+"  "+rak.getNamaRak());
		detail.setText(getBrand(rak.getKodeBrand()));
		if(isSelesai(rak.getStatus())){
			header.setTextColor(Color.RED);
			detail.setTextColor(Color.RED);
		}
		/*try {
			
			
			TmBrand brand = new TmBrand();
			brand.setKode(rak.getKodeBrand());
			TmBrandDao brandDao = new TmBrandDao(act.getApplicationContext());
			brand = brandDao.getByExample(brand);
			
			header.setText(rak.getNomorUrut()+"/"+masterRak.getNama());
			detail.setText(brand.getNama());
		} catch (Exception e) {
			header.setText("Rak."+rak.getNomorUrut()+"/"+"Tidak Ada Brand");
			detail.setText("Tidak Ada Brand");
		}*/
		
		
		return v;
	}

	private boolean isSelesai(String status) {
		try {
			if(status.equals("selesai")) return true;
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	private String getBrand(String kodeBrand) {
		String result = "";
		try {
			TmBrand brand = new TmBrand();
			brand.setKode(kodeBrand);
			TmBrandDao brandDao = new TmBrandDao(act.getApplicationContext());
			brand = brandDao.getByExample(brand);
			result = brand.getNama();
			
		} catch (Exception e) {
			result = "";
		}
		return result;
	}

	private String isNull(String nama) {
		String result = "";
		try {
			result = nama;
		} catch (Exception e) {
			 result = "";
		}
		return result;
	}

}
