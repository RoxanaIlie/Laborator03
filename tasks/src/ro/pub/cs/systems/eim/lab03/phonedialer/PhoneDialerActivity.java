package ro.pub.cs.systems.eim.lab03.phonedialer;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import ro.pub.cs.systems.eim.lab03.phonedialer.general.*;

public class PhoneDialerActivity extends Activity {

	private EditText phoneNumber = null;
	private NumberButtonClickListener numberButtonClickListener = new NumberButtonClickListener();
	private BackspaceButtonClickListener backspaceButtonClickLisener = new BackspaceButtonClickListener();
	private CallButtonClickListener callButtonClickLisener = new CallButtonClickListener();
	private HangupButtonClickListener hangupButtonClickLisener = new HangupButtonClickListener();
	
	private class NumberButtonClickListener implements View.OnClickListener {
		
		@Override
		public void onClick(View view) {
			phoneNumber.setText(phoneNumber.getText().toString() + ((Button)view).getText().toString());
		}
	}
	
	private class BackspaceButtonClickListener implements View.OnClickListener {
		
		@Override
		public void onClick(View view) {
			if (phoneNumber.getText().toString().length() > 0) {
				phoneNumber.setText(phoneNumber.getText().toString().substring(0, phoneNumber.getText().toString().length() - 1));
			}
		}
	}
	
	private class CallButtonClickListener implements View.OnClickListener {
		
		@Override
		public void onClick(View view) {
			Intent intent = new Intent(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:" + phoneNumber));
			startActivity(intent);
		}
	}
	
	private class HangupButtonClickListener implements View.OnClickListener {
		
		@Override
		public void onClick(View view) {
			finish();
		}
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_phone_dialer);
		phoneNumber = (EditText)findViewById(R.id.editText1);
		
		for (int i = 0; i < ro.pub.cs.systems.eim.lab03.phonedialer.general.Constants.buttonIds.length; i++) {
			Button b = (Button)findViewById(ro.pub.cs.systems.eim.lab03.phonedialer.general.Constants.buttonIds[i]);
			b.setOnClickListener(numberButtonClickListener);
		}
		
		ImageButton backspaceImageButton = (ImageButton)findViewById(R.id.imageButton1);
		backspaceImageButton.setOnClickListener(backspaceButtonClickLisener);
		ImageButton callImageButton = (ImageButton)findViewById(R.id.imageButton2);
		callImageButton.setOnClickListener(callButtonClickLisener);
		ImageButton hangupImageButton = (ImageButton)findViewById(R.id.imageButton3);
		hangupImageButton.setOnClickListener(hangupButtonClickLisener);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.phone_dialer, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
