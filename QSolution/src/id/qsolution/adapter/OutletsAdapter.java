package id.qsolution.adapter;

import id.qsolution.main.R;
import id.qsolution.models.TmKlasifikasiOutlet;
import id.qsolution.models.TmOutlet;
import id.qsolution.models.TmTipeOutlet;
import id.qsolution.models.dao.TmKlasifikasiOutletDao;
import id.qsolution.models.dao.TmTipeOutletDao;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;


public class OutletsAdapter extends QsolutionAdapter{
	
	Activity act;

	public OutletsAdapter(Activity activity) {
		super(activity);
		act = activity;
	}
	
	public OutletsAdapter(Activity activity, List<?> data) {
		super(activity, data);
		act = activity;
	}

	@Override
	public View getView(int index, View view, ViewGroup viewGroup) {
		View v = view;
		if (v == null) v = lyInflater.inflate(R.layout.outlet_row, null);
		TextView custno = (TextView) v.findViewById(R.id.header);
		TextView custName = (TextView) v.findViewById(R.id.detail);
		
		TmOutlet outlet = (TmOutlet) getItem(index);
	
		custno.setText(outlet.getKode());
		custName.setText(validateString((outlet.getNama()))+", "+validateString(outlet.getAlamat()));
		return v;
	}
	
	private String validateString(String string) {
		String result = "";
		try {
			if(string.equals("") || string == null){
				result ="";
			}else{
				result = string;
			}
		} catch (Exception e) {
			result ="";
		}
		
		return result;
	}

	@Override
	public Filter getFilter() {
		Filter filter = new Filter(){

			@Override
			protected FilterResults performFiltering(CharSequence constrain) {
				constrain = constrain.toString().toLowerCase();
				FilterResults result = new FilterResults();
				if(constrain ==  null || constrain.length() == 0){
					result.values = listData;
					result.count = listData.size();
					return result;
				} else {
					List<TmOutlet> filteredItem = new ArrayList<TmOutlet>();
					for(int i = 0; i < listData.size() ; i++){
						TmOutlet outlet =  (TmOutlet) listData.get(i);
						if(outlet.getKode().contains(constrain)){
							filteredItem.add(outlet);
							continue;
						} else if (outlet.getNama().toLowerCase().contains(constrain)){
							filteredItem.add(outlet);
							continue;
						}
					}
					result.count = filteredItem.size();
					result.values = filteredItem;
				}
				return result;
			}

			@Override
			protected void publishResults(CharSequence constrain, FilterResults results) {
				listFilter = (ArrayList<TmOutlet>) results.values;
				if(results.count == 0){
					notifyDataSetInvalidated();
				}else {
					notifyDataSetChanged();
				}
			}
			
		};
		return filter;
	}

}
