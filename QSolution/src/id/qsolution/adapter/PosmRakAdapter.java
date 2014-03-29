package id.qsolution.adapter;

import id.qsolution.main.R;
import id.qsolution.models.TmBrand;
import id.qsolution.models.TmPosm;
import id.qsolution.models.TmRak;
import id.qsolution.models.TtDKunjunganSurveyorOutletPosm;
import id.qsolution.models.TtDKunjunganSurveyorRak;
import id.qsolution.models.TtDKunjunganSurveyorRakPosm;
import id.qsolution.models.dao.TmBrandDao;
import id.qsolution.models.dao.TmPosmDao;
import id.qsolution.models.dao.TmRakDao;
import id.qsolution.models.dao.TtDKunjunganSurveyorRakDao;

import java.util.List;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PosmRakAdapter extends QsolutionAdapter {
	
	Activity act;
	
	public PosmRakAdapter(Activity activity) {
		super(activity);
		act = activity;
	}

	public PosmRakAdapter(Activity activity, List<?> data) {
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
		TtDKunjunganSurveyorRakPosm rakPosm = (TtDKunjunganSurveyorRakPosm) getItem(index);
		
		header.setText("Rak"+rakPosm.getNomorUrut()+" Posm "+rakPosm.getNamaPosm());
		detail.setText("Jumlah  : " + rakPosm.getJumlah());
		return v;
	}

	private String getPosm(String kodePosm) {
		String result = "";
		try {
			TmPosm p = new TmPosm();
			TmPosmDao pd = new TmPosmDao(act.getApplicationContext());
			p.setKode(kodePosm);
			p = pd.getByExample(p);
			result = p.getNama();
		} catch (Exception e) {
			result = "";
		}
		return result;
	}

	

}
