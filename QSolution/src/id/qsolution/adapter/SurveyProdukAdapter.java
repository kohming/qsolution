package id.qsolution.adapter;

import id.qsolution.main.R;
import id.qsolution.models.TtDKunjunganSurveyorRakSku;
import java.util.List;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SurveyProdukAdapter extends QsolutionAdapter {
	
	public SurveyProdukAdapter(Activity activity, List<?> data) {
		super(activity, data);
	}

	@Override
	public View getView(int index, View view, ViewGroup viewGroup) {
		View v = view;
		if (v == null) v = lyInflater.inflate(R.layout.rak_row, null);
		TextView skuNo = (TextView) v.findViewById(R.id.header);
		TextView skuName = (TextView) v.findViewById(R.id.detail);
		TtDKunjunganSurveyorRakSku skuRak = (TtDKunjunganSurveyorRakSku) getItem(index);
		skuNo.setText((index+1) + ". "+skuRak.getNamaSku());
		skuName.setText("Qty: "+skuRak.getJumlahUnit() +" Facing: " +skuRak.getJumlahFacing()+" Harga (Rp): "+ skuRak.getHargaJual() );
		return v;
	}

}
