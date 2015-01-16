package com.ixy.colorfilterdemo;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class PorterDuffColorFilterActivity extends Activity implements
		ColorPickerDialog.OnColorChangedListener {

	ImageView mIvResult;
	View mVSrcColor;
	TextView mTvMode;
	Spinner mSpiMode;

	private int mSrcColor = Color.RED;
	static PorterDuff.Mode[] sModes = PorterDuff.Mode.values();
	static String[] sModeNames = new String[sModes.length];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_porter_duff_color_filter);
		mIvResult = (ImageView) findViewById(R.id.iv_result);
		mVSrcColor = findViewById(R.id.v_src_color);
		mTvMode = (TextView) findViewById(R.id.tv_mode);
		mSpiMode = (Spinner) findViewById(R.id.spinner_mode);

		for (int i = 0; i < sModes.length; i++) {
			sModeNames[i] = sModes[i].name().toString();
		}

		ArrayAdapter<String> modeArrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, sModeNames);

		mSpiMode.setAdapter(modeArrayAdapter);
		mSpiMode.setSelection(0);
		mSpiMode.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				mTvMode.setText(sModeNames[position]);
				setPorterDuffColorFilter();

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		mVSrcColor.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new ColorPickerDialog(PorterDuffColorFilterActivity.this,
						PorterDuffColorFilterActivity.this, mSrcColor).show();

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.porter_duff_color_filter, menu);
		return true;
	}

	private void setPorterDuffColorFilter() {
		PorterDuff.Mode mode = sModes[mSpiMode.getSelectedItemPosition()];
		PorterDuffColorFilter filter = new PorterDuffColorFilter(mSrcColor, mode);
		mIvResult.setColorFilter(filter);
	}

	@Override
	public void colorChanged(int color) {
		mVSrcColor.setBackgroundColor(color);
		mSrcColor = Color.argb(100, Color.red(color), Color.green(color), Color.blue(color));
		setPorterDuffColorFilter();
	}
}
