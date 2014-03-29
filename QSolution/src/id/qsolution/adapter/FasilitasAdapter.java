package id.qsolution.adapter;

import id.qsolution.main.R;
import id.qsolution.models.TmFasilitas;
import id.qsolution.models.TmOutletFasilitas;
import id.qsolution.models.dao.TmFasilitasDao;
import id.qsolution.models.dao.TmOutletFasilitasDao;

import java.util.List;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FasilitasAdapter extends QsolutionAdapter{
	
	Activity act;

	public FasilitasAdapter(Activity activity) {
		super(activity);
		act = activity;
	}
	
	public FasilitasAdapter(Activity activity, List<?> data) {
		super(activity, data);
		act = activity;
	}

	@Override
	public View getView(int index, View view, ViewGroup viewGroup) {
		View v = view;
		if (v == null) v = lyInflater.inflate(R.layout.fasilitas_row, null);
		TextView no = (TextView) v.findViewById(R.id.txtNo);
		TextView header = (TextView) v.findViewById(R.id.header);
		TextView detail = (TextView) v.findViewById(R.id.detail);
		TmFasilitas fasilitas = new TmFasilitas();
		TmFasilitasDao fasilitasDao = new TmFasilitasDao(act.getApplicationContext());
		
		TmOutletFasilitas outletFasilitas = (TmOutletFasilitas) getItem(index);
		
		fasilitas.setKode(outletFasilitas.getKodeFasilitas());
		fasilitas = fasilitasDao.getByExample(fasilitas);
		
		no.setText(String.valueOf(index+1));
		header.setText(fasilitas.getNama());
		detail.setText("Jumlah "+String.valueOf(outletFasilitas.getJumlah()));
		/*fasilitas.setKode(outletFasilitas.getKodeFasilitas());
		fasilitas = fasilitasDao.getByExample(fasilitas);
		
		header.setText(fasilitas.getNama());
		detail.setText("Jumlah "+String.valueOf(outletFasilitas.getJumlah()));*/
		return v;
	}

}
