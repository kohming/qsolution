package id.qsolution.adapter;


import id.qsolution.main.R;
import id.qsolution.models.TtDKunjunganSurveyorPhoto;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PhotoAdapter extends QsolutionAdapter {

	int IMAGE_MAX_SIZE = 100;
	private static Activity act = null;
	private static final int MAX_HEIGHT = 100;
	private static final int MAX_WIDTH = 100;

	public PhotoAdapter(Activity activity) {
		super(activity);
		act = activity;
	}

	public PhotoAdapter(Activity activity, List<?> data) {
		super(activity, data);
		act = activity;
	}

	@Override
	public View getView(int index, View view, ViewGroup viewGroup) {
		
		View v = view;
		if (v == null) v = lyInflater.inflate(R.layout.image_row, null);
		ImageView image = (ImageView) v.findViewById(R.id.imgOutlet);
		TextView header = (TextView) v.findViewById(R.id.header);
		TextView detail = (TextView) v.findViewById(R.id.detail);
		TtDKunjunganSurveyorPhoto foto = (TtDKunjunganSurveyorPhoto) getItem(index);

		// image.setImageDrawable(Drawable.createFromPath(foto.getNamaFile()));

		// File imgFile = new File();

		try {
			image.setImageBitmap(decodeSampledBitmap(act,
					Uri.fromFile(new File(foto.getNamaFile()))));
		} catch (IOException e) {
			Log.i("list image ", e.getMessage());
		}

		header.setText(gatName(foto.getNamaFile()));
		detail.setText(foto.getDeskripsi());
		return v;
	}

	/**
	 * Rotate an image if required.
	 * 
	 * @param img
	 * @param selectedImage
	 * @return
	 */
	private static Bitmap rotateImageIfRequired(Context context, Bitmap img,
			Uri selectedImage) {

		// Detect rotation
		int rotation = getRotation(context, selectedImage);
		if (rotation != 0) {
			Matrix matrix = new Matrix();
			matrix.postRotate(rotation);
			Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(),
					img.getHeight(), matrix, true);
			img.recycle();
			return rotatedImg;
		} else {
			return img;
		}
	}

	/**
	 * Get the rotation of the last image added.
	 * 
	 * @param context
	 * @param selectedImage
	 * @return
	 */
	private static int getRotation(Context context, Uri selectedImage) {
		int rotation = 0;
		ContentResolver content = context.getContentResolver();

		Cursor mediaCursor = content.query(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[] {
						"orientation", "date_added" }, null, null,
				"date_added desc");

		if (mediaCursor != null && mediaCursor.getCount() != 0) {
			while (mediaCursor.moveToNext()) {
				rotation = mediaCursor.getInt(0);
				break;
			}
		}
		mediaCursor.close();
		return rotation;
	}

	public static Bitmap decodeSampledBitmap(Context context, Uri selectedImage)
			throws IOException {

		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		InputStream imageStream = context.getContentResolver().openInputStream(
				selectedImage);
		BitmapFactory.decodeStream(imageStream, null, options);
		imageStream.close();

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, MAX_WIDTH,
				MAX_HEIGHT);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		imageStream = context.getContentResolver().openInputStream(
				selectedImage);
		Bitmap img = BitmapFactory.decodeStream(imageStream, null, options);

		img = rotateImageIfRequired(context, img, selectedImage);
		return img;
	}

	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			final int halfHeight = height / 2;
			final int halfWidth = width / 2;

			// Calculate the largest inSampleSize value that is a power of 2 and
			// keeps both
			// height and width larger than the requested height and width.
			while ((halfHeight / inSampleSize) > reqHeight
					&& (halfWidth / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}
		}

		return inSampleSize;
	}

	private String gatName(String nama) {
		String[] x = new String[nama.split("/").length];
		String result = "";
		x = nama.split("/");
		result = x[nama.split("/").length - 1];
		return result;
	}

}