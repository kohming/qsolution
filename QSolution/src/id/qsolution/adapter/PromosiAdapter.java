package id.qsolution.adapter;

import id.qsolution.main.R;
import id.qsolution.models.TtDKunjunganSurveyorRakSkuPromosi;
import java.util.List;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PromosiAdapter extends QsolutionAdapter{
	
	Activity act;
	
	public PromosiAdapter(Activity activity, List<?> data) {
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
		TtDKunjunganSurveyorRakSkuPromosi rakPosm = (TtDKunjunganSurveyorRakSkuPromosi) getItem(index);
		header.setText(rakPosm.getNamaPromosi());
		detail.setText(rakPosm.getNamaSku());
		return v;
	}

}
