package id.qsolution.adapter;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

public abstract class QsolutionAdapter extends BaseAdapter implements Filterable{
	
	protected List<?> listData = null;
	protected List<?> listFilter = null;
	protected LayoutInflater lyInflater = null;
	
	public QsolutionAdapter(Activity activity){
		try {
			listData = new ArrayList<Object>();
			listFilter = new ArrayList<Object>();
			lyInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		} catch (Exception e) {
			Log.i("err ", e.getMessage());
		}
	}
	
	public QsolutionAdapter (Activity activity, List<?> data) {
		try {
			listData = data;
			listFilter = data;
			lyInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		} catch (Exception e) {
			Log.i("err ", e.getMessage());
		}
	}

	@Override
	public int getCount() {
		return listFilter.size();
	}

	@Override
	public Object getItem(int arg0) {
		return listFilter.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public abstract View getView(int index, View view, ViewGroup viewGroup);
	
	
	@Override
	public Filter getFilter() {
		return null;
	}
}
