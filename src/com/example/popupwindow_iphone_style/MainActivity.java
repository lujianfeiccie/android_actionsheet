package com.example.popupwindow_iphone_style;

import com.example.popupwindow_iphone_style.IPhoneStylePopupWindow.OnItemClickListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	Button bt_bottom = null;
	
	IPhoneStylePopupWindow popup_window = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		bt_bottom = (Button)findViewById(R.id.bt_bottom);
		bt_bottom.setOnClickListener(this);
		
		popup_window = new IPhoneStylePopupWindow(this);
		
		popup_window.addButton("Item1",new OnItemClickListener() {
			@Override
			public void itemClick(View v) {
				Toast.makeText(MainActivity.this, "Item1", Toast.LENGTH_SHORT).show();
			}
		});
		
		popup_window.addButton("Item2",new OnItemClickListener() {
			@Override
			public void itemClick(View v) {
				Toast.makeText(MainActivity.this, "Item2", Toast.LENGTH_SHORT).show();
			}
		},R.drawable.popup_window_btn2_style,
		R.color.popup_window_text2_style,30);

		popup_window.addButton("Item3",new OnItemClickListener() {
			@Override
			public void itemClick(View v) {
				Toast.makeText(MainActivity.this, "Item3", Toast.LENGTH_SHORT).show();
			}
		},R.drawable.popup_window_btn2_style,
		R.color.popup_window_text2_style);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		popup_window.showAtLocation(v, Gravity.BOTTOM, 0, 0);
	}
	

}
