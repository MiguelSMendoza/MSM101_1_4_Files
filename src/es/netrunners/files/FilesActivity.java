package es.netrunners.files;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FilesActivity extends Activity {
	EditText value;
	TextView value2;

	String FILENAME = "filename.ext";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		value = (EditText) findViewById(R.id.value);
		value2 = (TextView) findViewById(R.id.txtValue);
	}

	public void retrieveData(View v) {

		try {
			String str = new String();
			FileInputStream fis = openFileInput(FILENAME);
			int byteChar;
			while ((byteChar = fis.read()) != -1) {
				str += (char) byteChar;
			}
			fis.close();
			value2.setText(str);
		} catch (FileNotFoundException e) {
			Toast.makeText(getApplicationContext(),
					"File " + FILENAME + " Not Found", Toast.LENGTH_LONG)
					.show();
		} catch (IOException e) {
			Toast.makeText(getApplicationContext(), "IO Exception",
					Toast.LENGTH_LONG).show();
		}

	}

	public void storeData(View v) {
		try {
			FileOutputStream fos = openFileOutput(FILENAME, MODE_PRIVATE);
			fos.write(value.getText().toString().getBytes());
			fos.write("Hello World!!".getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			Toast.makeText(getApplicationContext(),
					"File " + FILENAME + " Not Found", Toast.LENGTH_LONG)
					.show();
		} catch (IOException e) {
			Toast.makeText(getApplicationContext(), "IO Exception",
					Toast.LENGTH_LONG).show();
		}

		Toast.makeText(getApplicationContext(), "Save Successfull !!",
				Toast.LENGTH_LONG).show();

	}
}