package id.qsolution.services;


import id.qsolution.io.FlushedInputStream;
import id.qsolution.util.Utils;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class HttpRetriever {

	private DefaultHttpClient client = new DefaultHttpClient();	

	public String retrievePost(String url, List<NameValuePair> nameValuePairs ) {

		HttpPost postRequest = new HttpPost(url);
		try {
			postRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			
			HttpResponse postResponse = client.execute(postRequest);
			final int statusCode = postResponse.getStatusLine().getStatusCode();

			if (statusCode != HttpStatus.SC_OK) { 
				Log.w(getClass().getSimpleName(), "Error " + statusCode + " for URL " + url); 
				return null;
			}

			HttpEntity postResponseEntity = postResponse.getEntity();

			if (postResponseEntity != null) {
				return EntityUtils.toString(postResponseEntity);
			}

		} 
		catch (IOException e) {
			postRequest.abort();
			Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
		} 
		return null;

	}

	public String retrieve(String url) {

		HttpGet getRequest = new HttpGet(url);
		
		try {

			HttpResponse getResponse = client.execute(getRequest);
			final int statusCode = getResponse.getStatusLine().getStatusCode();

			if (statusCode != HttpStatus.SC_OK) { 
				Log.w(getClass().getSimpleName(), "Error " + statusCode + " for URL " + url); 
				return null;
			}

			HttpEntity getResponseEntity = getResponse.getEntity();

			if (getResponseEntity != null) {
				return EntityUtils.toString(getResponseEntity);
			}

		} 
		catch (IOException e) {
			getRequest.abort();
			Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
		}

		return null;

	}

	public InputStream retrieveStream(String url) {
		HttpGet getRequest = new HttpGet(url);
		try {
			HttpResponse getResponse = client.execute(getRequest);
			final int statusCode = getResponse.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) { 
				Log.w(getClass().getSimpleName(), "Error " + statusCode + " for URL " + url); 
				return null;
			}
			HttpEntity getResponseEntity = getResponse.getEntity();
			return getResponseEntity.getContent();

		} 
		catch (IOException e) {
			getRequest.abort();
			Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
		}

		return null;

	}

	public Bitmap retrieveBitmap(String url) throws Exception {

		InputStream inputStream = null;
		try {
			inputStream = this.retrieveStream(url);
			final Bitmap bitmap = BitmapFactory.decodeStream(new FlushedInputStream(inputStream));
			return bitmap;
		} 
		finally {
			Utils.closeStreamQuietly(inputStream);
		}

	}

}
