package id.qsolution.respons;


import id.qsolution.global.GenericRespons;
import id.qsolution.models.TmResult;
import java.util.List;
import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import com.google.gson.Gson;

public class UploadResponsService extends GenericRespons<String> {

	@Override
	public String find(String query) {
		//Gson gson = new Gson();
		return retrieveRespons(query);
		//TmResult respons = gson.fromJson(response, TmResult.class);
		//return respons;	
	}

	private String retrieveRespons(String query) {
		String url = constructSearchUrl(query);
		String response = "";
		try {
			response = httpRetriever.retrieve(url);
			if (response!=null)
			  Log.d(getClass().getSimpleName(), response);
			
		} catch (Exception e) {
			return response;
		}
		return response;
	}

	@Override
	public String find(List<NameValuePair> nameValuePairs) {
		//Gson gson = new Gson();
		//TmResult respons = new TmResult();
	
			return retrieveRespons(nameValuePairs);
			//JSONObject test = new JSONObject(response);
			//respons = gson.fromJson(test.getString("tmResult"), TmResult.class);
		
		
		//return respons;
	}

	private String retrieveRespons(List<NameValuePair> nameValuePairs) {
		String url = constructSearchUrl();
		String response = httpRetriever.retrievePost(url,nameValuePairs);
		if (response!=null)
			Log.d(getClass().getSimpleName(), response);
		return response;
	}

}
