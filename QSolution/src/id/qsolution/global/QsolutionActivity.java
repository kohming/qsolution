package id.qsolution.global;


import id.qsolution.util.FontUtils;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class QsolutionActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setNoTitle();
		
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		FontUtils.setRobotoFont(QsolutionActivity.this, (ViewGroup) QsolutionActivity.this.getWindow().getDecorView());
	}
	
	

	private void setNoTitle() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
	


	/*@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_CAMERA) {
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_CALL) {
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_BACK) {
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_HOME) {
			return true;
		}
		return false;
	}*/
}
