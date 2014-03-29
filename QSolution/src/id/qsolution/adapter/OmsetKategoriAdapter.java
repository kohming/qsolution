package id.qsolution.adapter;

import id.qsolution.main.R;
import id.qsolution.models.TtDKunjunganSurveyorOmsetKatagori;
import java.util.List;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OmsetKategoriAdapter extends QsolutionAdapter {

	Activity act;
	
	public OmsetKategoriAdapter(Activity activity, List<?> data) {
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
		TtDKunjunganSurveyorOmsetKatagori outletPosm = (TtDKunjunganSurveyorOmsetKatagori) getItem(index);
		header.setText((index+1) +". "+outletPosm.getNamaKategori());
		detail.setText("Omset "+outletPosm.getOmset());
		return v;
	}

	
}
