package id.qsolution.global;



import id.qsolution.services.HttpRetriever;
import java.util.List;
import org.apache.http.NameValuePair;

public abstract class GenericRespons<T> {

	protected HttpRetriever httpRetriever = new HttpRetriever();
	public abstract T find(List<NameValuePair> nameValuePairs);
	public abstract T find(String query);
	

	protected String constructSearchUrl(String params) {
		StringBuffer sb = new StringBuffer();
		sb.append(GlobalVar.LOGIN_URL);
		sb.append("?");
		sb.append(params);
		return sb.toString();
	}
	
	protected String constructSearchUrl() {
		StringBuffer sb = new StringBuffer();
		sb.append(GlobalVar.LOGIN_URL);
		return sb.toString();
	}
	
	
}