package edu.buaa.automanager.view.fragment;

import android.graphics.Bitmap;
import android.widget.Toast;

import com.baidu.mapapi.map.MKMapViewListener;
import com.baidu.mapapi.map.MapPoi;

public class LocusFragment extends BaseFragment {
	// @Override
	// public View onCreateView(LayoutInflater inflater, ViewGroup container,
	// Bundle savedInstanceState) {
	// TextView textView = new TextView(getActivity());
	// textView.setText("Locus");
	// return textView;
	// }
	private MKMapViewListener mMapListener = new MKMapViewListener() {
		public void onMapMoveFinish() {

		}

		public void onClickMapPoi(MapPoi mapPoiInfo) {

			String title = "";
			if (mapPoiInfo != null) {
				title = mapPoiInfo.strText;
				Toast.makeText(getActivity(), title, Toast.LENGTH_SHORT).show();
			}
		}

		public void onGetCurrentMap(Bitmap b) {
		}

		@Override
		public void onMapAnimationFinish() {

		}

		@Override
		public void onMapLoadFinish() {
			// TODO Auto-generated method stub

		}
	};
}
