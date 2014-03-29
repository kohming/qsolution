package id.qsolution.services;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;

public abstract class GenericSeeker<E> {
	
		
	protected static final String BASE_URL = "http://10.0.2.2:8080/IctMobileServer/Servlet";	
	protected HttpRetriever httpRetriever = new HttpRetriever();
	protected XmlParser xmlParser = new XmlParser();
	public abstract ArrayList<E> find(List<NameValuePair> nameValuePairs);
	public abstract ArrayList<E> find(List<NameValuePair> nameValuePairs, int maxResults);
	public abstract ArrayList<E> find(String query);
	public abstract ArrayList<E> find(String query, int maxResults);
	
	protected String constructSearchUrl(String params) {
		StringBuffer sb = new StringBuffer();
		sb.append(BASE_URL);
		sb.append("?");
//		sb.append(URLEncoder.encode(params));
		sb.append(params);
		return sb.toString();
	}
	
	protected String constructSearchUrl() {
		StringBuffer sb = new StringBuffer();
		sb.append(BASE_URL);
		return sb.toString();
	}
	
	public ArrayList<E> retrieveFirstResults(ArrayList<E> list, int maxResults) {
		ArrayList<E> newList = new ArrayList<E>();
		int count = Math.min(list.size(), maxResults);
		for (int i=0; i<count; i++) {
			newList.add(list.get(i));
		}
		return newList;
	}

}
