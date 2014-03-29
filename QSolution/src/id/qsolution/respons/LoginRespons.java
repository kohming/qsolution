package id.qsolution.respons;


import id.qsolution.global.GenericRespons;
import id.qsolution.models.TmSurveyor;
import java.util.List;
import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import com.google.gson.Gson;


public class LoginRespons extends GenericRespons<TmSurveyor>{
	
	

	@Override
	public TmSurveyor find(String query) {
		
		Gson gson = new Gson();
		TmSurveyor respons = new TmSurveyor();
		String response = retrieveUsers(query);
		try {
			JSONObject file = new JSONObject(response);
			respons = gson.fromJson(file.getString("tmSurveyor"), TmSurveyor.class);
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
	public TmSurveyor find(List<NameValuePair> nameValuePairs) {
		Gson gson = new Gson();
		TmSurveyor respons = new TmSurveyor();
		String response = retrieveUsers(nameValuePairs);
		try {
			JSONObject test = new JSONObject(response);
			respons = gson.fromJson(test.getString("tmSurveyor"), TmSurveyor.class);
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
