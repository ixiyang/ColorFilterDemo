package com.ixy.colorfilterdemo;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.LightingColorFilter;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LightColorFilterActivity extends Activity {

	private ImageView mIvResult;
	private View mVMul;
	private View mVAdd;

	private TextView mTvMul;
	private TextView mTvAdd;
	private int mMulColor;
	private int mAddColor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_light_color_filter);

		mIvResult = (ImageView) findViewById(R.id.iv_result);
		mVMul = findViewById(R.id.v_mul_color);
		mVAdd = findViewById(R.id.v_add_color);
		mTvMul = (TextView) findViewById(R.id.tv_mul);
		mTvAdd = (TextView) findViewById(R.id.tv_add);
		mMulColor = getResources().getColor(android.R.color.black);
		mAddColor = getResources().getColor(android.R.color.holo_green_light);
		mVMul.setBackgroundColor(mMulColor);
		mVAdd.setBackgroundColor(mAddColor);
		mVMul.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new ColorPickerDialog(LightColorFilterActivity.this,
						new ColorPickerDialog.OnColorChangedListener() {

							@Override
							public void colorChanged(int color) {
								mMulColor = color;
								mVMul.setBackgroundColor(color);
								printColor(mMulColor, mTvMul);
								setLightColorFilter();
							}
						}, mMulColor).show();

			}
		});

		mVAdd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new ColorPickerDialog(LightColorFilterActivity.this,
						new ColorPickerDialog.OnColorChangedListener() {

							@Override
							public void colorChanged(int color) {
								mAddColor = color;
								mVAdd.setBackgroundColor(color);
								printColor(mAddColor, mTvAdd);
								setLightColorFilter();
							}
						}, mAddColor).show();

			}
		});

		printColor(mMulColor, mTvMul);
		printColor(mAddColor, mTvAdd);
		setLightColorFilter();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.light_color_filter, menu);
		return true;
	}

	private void setLightColorFilter() {
		LightingColorFilter filter = new LightingColorFilter(mMulColor, mAddColor);
		mIvResult.setColorFilter(filter);
	}

	private void printColor(int color, TextView tv) {
		/*
		 * String r = Integer.toHexString(Color.red(color)); String g =
		 * Integer.toHexString(Color.green(color)); String b =
		 * Integer.toHexString(Color.blue(color));
		 * 
		 * StringBuilder sb = new StringBuilder();
		 * sb.append("r:").append(r).append
		 * ("  g:").append(g).append("  b:").append(b);
		 * 
		 * tv.append("(" + sb.toString() + ")");
		 */
	}
}
