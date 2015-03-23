package au.com.commbank.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * A fragment that launches other parts of the demo application.
 */
public class MapFragment extends CbaFragment {

    @InjectView(R.id.mapView)
    MapView mMapView;

    private GoogleMap googleMap;
    private Double latitude;
    private Double longitude;
    private String atmTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // inflate and return the layout
        View view = inflater.inflate(R.layout.fragment_map, container,
                false);

        ButterKnife.inject(this, view);

        mMapView.onCreate(savedInstanceState);

        latitude = getArguments().getDouble("lat");

        longitude = getArguments().getDouble("lng");

        atmTitle = getArguments().getString("atmTitle");

        mMapView.onResume();// needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        googleMap = mMapView.getMap();


        // create marker
        MarkerOptions marker = new MarkerOptions().position(
                new LatLng(latitude, longitude)).title(atmTitle);

        try {
            // Changing marker icon
            marker.icon(BitmapDescriptorFactory
                    .fromResource(R.mipmap.icon_welcome_logo));
        }
        catch(Exception ex){}
        // adding marker
        googleMap.addMarker(marker);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(latitude, longitude)).zoom(15).build();
        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));

        // Perform any camera updates here
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public String getTitle() {
        return "Find us";
    }
}