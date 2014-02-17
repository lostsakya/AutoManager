package edu.buaa.automanager.view.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.SupportMapFragment;
import com.baidu.platform.comapi.basestruct.GeoPoint;

import edu.buaa.automanager.R;
import edu.buaa.automanager.utils.LogUtil;
import edu.buaa.automanager.view.fragment.LocusFragment;
import edu.buaa.automanager.view.fragment.MoreFragment;
import edu.buaa.automanager.view.fragment.StatusFragment;

public class TabsActivity extends FragmentActivity implements OnClickListener, OnCheckedChangeListener {
	private static final String TAG = "TabsActivity";
	// private FragmentTabHost mTabHost;
	private RadioButton tabLocation;
	private RadioButton tabLocus;
	private RadioButton tabStatus;
	private RadioButton tabMore;

	private RadioGroup mRadioGroup;

	private SupportMapFragment locationFragment;
	private Fragment locusFragment;
	private Fragment statusFragment;
	private Fragment moreFragment;
	private Fragment currentFragment;

	private Fragment[] fragments;

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		Log.d(TAG, "onRestoreInstanceState");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d(TAG, "onRestart");
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.d(TAG, "onStart");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
		MapController controller = locationFragment.getMapView().getController();
		controller.setCenter(new GeoPoint((int) (39.945 * 1E6), (int) (116.404 * 1E6)));
		controller.setZoom(13);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.d(TAG, "onSaveInstanceState");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.d(TAG, "onPause");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.d(TAG, "onStop");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestory");
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		Log.d(TAG, "onConfigurationChanged");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tabs);

		// locationFragment = new LocationFragment();
		locationFragment = SupportMapFragment.newInstance();
		locusFragment = new LocusFragment();
		statusFragment = new StatusFragment();
		moreFragment = new MoreFragment();

		fragments = new Fragment[] { locationFragment, locusFragment, statusFragment, moreFragment };

		tabLocation = (RadioButton) findViewById(R.id.tab_location);
		tabLocus = (RadioButton) findViewById(R.id.tab_locus);
		tabStatus = (RadioButton) findViewById(R.id.tab_status);
		tabMore = (RadioButton) findViewById(R.id.tab_more);

		mRadioGroup = (RadioGroup) findViewById(R.id.rg);
		int childCount = mRadioGroup.getChildCount();
		for (int i = 0; i < childCount; i++) {
			CompoundButton child = (CompoundButton) mRadioGroup.getChildAt(i);
			child.setOnCheckedChangeListener(this);
		}

		// tabLocation.setOnCheckedChangeListener(this);
		// tabLocus.setOnCheckedChangeListener(this);
		// tabStatus.setOnCheckedChangeListener(this);
		// tabMore.setOnCheckedChangeListener(this);

		currentFragment = locationFragment;
		addFragments();
		showFragment(currentFragment);
	}

	private void addFragments() {
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		for (Fragment fragment : fragments) {
			fragmentTransaction.add(R.id.fragment_container, fragment);
			String tag2 = fragment.getTag();
			LogUtil.log(TAG, tag2);
		}
		fragmentTransaction.commit();
	}

	private void showFragment(Fragment fragment) {
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		for (Fragment frag : fragments) {
			fragmentTransaction.hide(frag);
		}
		fragmentTransaction.show(fragment);
		fragmentTransaction.commit();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tab_location:
			currentFragment = locationFragment;
			break;
		case R.id.tab_locus:
			currentFragment = locusFragment;
			break;
		case R.id.tab_status:
			currentFragment = statusFragment;
			break;
		case R.id.tab_more:
			currentFragment = moreFragment;
			break;
		default:
			currentFragment = locationFragment;
			break;
		}
		LogUtil.log(TAG, currentFragment.getTag());
		showFragment(currentFragment);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			switch (buttonView.getId()) {
			case R.id.tab_location:
				currentFragment = locationFragment;
				break;
			case R.id.tab_locus:
				currentFragment = locusFragment;
				break;
			case R.id.tab_status:
				currentFragment = statusFragment;
				break;
			case R.id.tab_more:
				currentFragment = moreFragment;
				break;
			default:
				currentFragment = locationFragment;
				break;
			}
			showFragment(currentFragment);
		}

	}
}
