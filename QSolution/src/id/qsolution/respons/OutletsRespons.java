package id.qsolution.respons;

import java.util.List;
import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import com.google.gson.Gson;
import id.qsolution.global.GenericRespons;
import id.qsolution.models.TmResult;

public class OutletsRespons extends GenericRespons<TmResult> {


	@Override
	public TmResult find(String query) {
		Gson gson = new Gson();
		TmResult respons = new TmResult();
		String response = retrieveUsers(query);
		try {
			JSONObject file = new JSONObject(response);
			respons = gson.fromJson(file.getString("tmResult"), TmResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respons;	
	}

	private String retrieveUsers(String query) {
		String url = constructSearchUrl(query);
		Log.i("url ",url);
		String response = httpRetriever.retrieve(url);
		if (response!=null)
		  Log.d(getClass().getSimpleName(), response);
		return response;
	}

	@Override
	public TmResult find(List<NameValuePair> nameValuePairs) {
		Gson gson = new Gson();
		TmResult respons = new TmResult();
		String response = retrieveUsers(nameValuePairs);
		try {
			JSONObject test = new JSONObject(response);
			respons = gson.fromJson(test.getString("tmResult"), TmResult.class);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return respons;
	}

	private String retrieveUsers(List<NameValuePair> nameValuePairs) {
		String url = constructSearchUrl();
		String response = httpRetriever.retrievePost(url,nameValuePairs);
		if (response!=null)
			Log.d(getClass().getSimpleName(), response);
		return response;
	}
}
