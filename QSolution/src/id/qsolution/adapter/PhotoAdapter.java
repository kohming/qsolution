package id.qsolution.adapter;

import id.qsolution.main.R;
import id.qsolution.models.TtDKunjunganSurveyorPhoto;
import id.qsolution.models.TtPhoto;
import java.util.List;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PhotoAdapter extends QsolutionAdapter{
	
	public PhotoAdapter(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	public PhotoAdapter(Activity activity, List<?> data) {
		super(activity, data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int index, View view, ViewGroup viewGroup) {
		View v = view;
		if (v == null) v = lyInflater.inflate(R.layout.image_row, null);
		ImageView image =(ImageView) v.findViewById(R.id.imgOutlet);
		TextView header = (TextView) v.findViewById(R.id.header);
		TextView detail = (TextView) v.findViewById(R.id.detail);
		TtDKunjunganSurveyorPhoto foto = (TtDKunjunganSurveyorPhoto) getItem(index);
		
		image.setImageDrawable(Drawable.createFromPath(foto.getNamaFile()));
		header.setText(gatName(foto.getNamaFile()));
		detail.setText(foto.getDeskripsi());
		return v;
	}

	private String gatName(String nama) {
		String[] x = new String[ nama.split("/").length]   ;
		String result = "";
		x = nama.split("/");
		result = x[nama.split("/").length-1];
		return result;
	}

}
