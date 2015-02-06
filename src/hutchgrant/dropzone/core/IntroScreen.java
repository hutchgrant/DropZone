package hutchgrant.dropzone.core;

import com.example.dropzone.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class IntroScreen extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainscreen);


		Button startButton = (Button) findViewById(R.id.startButton);

		startButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				Intent intent = new Intent();
				intent.setClass(IntroScreen.this, game.class);
				startActivity(intent);
			}
		});

	
	}
}