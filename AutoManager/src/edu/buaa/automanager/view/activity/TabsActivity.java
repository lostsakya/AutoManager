package edu.buaa.automanager.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import net.tsz.afinal.annotation.view.ViewInject;
import edu.buaa.automanager.R;
import edu.buaa.automanager.view.fragment.LocationFragment;
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

	@ViewInject(id = R.id.rg)
	private RadioGroup mRadioGroup;

	private Fragment locationFragment;
	private Fragment locusFragment;
	private Fragment statusFragment;
	private Fragment moreFragment;
	private Fragment currentFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tabs);
		locationFragment = new LocationFragment();
		locusFragment = new LocusFragment();
		statusFragment = new StatusFragment();
		moreFragment = new MoreFragment();

		tabLocation = (RadioButton) findViewById(R.id.tab_location);
		tabLocus = (RadioButton) findViewById(R.id.tab_locus);
		tabStatus = (RadioButton) findViewById(R.id.tab_status);
		tabMore = (RadioButton) findViewById(R.id.tab_more);

		tabLocation.setOnCheckedChangeListener(this);
		tabLocus.setOnCheckedChangeListener(this);
		tabStatus.setOnCheckedChangeListener(this);
		tabMore.setOnCheckedChangeListener(this);
		currentFragment = locationFragment;
		showFragment(currentFragment);
	}

	private void showFragment(Fragment fragment) {
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		fragmentTransaction.replace(R.id.fragment_container, fragment);
		fragmentTransaction.addToBackStack(null);
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
