package vazk.app.examenandroid.colaboladores.mapa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;
import vazk.app.examenandroid.R;
import vazk.app.examenandroid.colaboladores.mapa.model.ListLocation;
import vazk.app.examenandroid.colaboladores.mapa.model.LocationModel;
import vazk.app.examenandroid.colaboladores.mapa.network.ApiClientMap;
import vazk.app.examenandroid.colaboladores.mapa.network.ApiServiceMap;
import vazk.app.examenandroid.databinding.ActivityMaps2Binding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMaps2Binding binding;
String UrlString;
    private List<LocationModel> mListMarker =  new ArrayList<>();

    //informacion de la ventana


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMaps2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        getrAlldataLocation();

    }

    private void getrAlldataLocation() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Esperando datos...");
        progressDialog.show();


        ApiServiceMap apiService= ApiClientMap.getRetrofit().create(ApiServiceMap.class);
        Call<ListLocation> call =  apiService.getallLocations();


        call.enqueue(new Callback<ListLocation>() {
            @Override
            public void onResponse(Call<ListLocation> call, Response<ListLocation> response) {
                progressDialog.dismiss();
                mListMarker = response.body().getmData();
                initMarker(mListMarker);
            }

            @Override
            public void onFailure(Call<ListLocation> call, Throwable t) {
progressDialog.dismiss();
                Toast.makeText(MapsActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initMarker(List<LocationModel> mListMarker) {
        for (int i = 0; i < mListMarker.size(); i++) {

            LatLng location = new LatLng(Double.parseDouble(mListMarker.get(i).getLatitude()),
                    Double.parseDouble(mListMarker.get(i).getLongitude()));


            Marker marker = mMap.addMarker(new MarkerOptions().position(location)
            .title(mListMarker.get(i).getCity())
            .snippet(mListMarker.get(i).getUrl()));

            LocationModel info = new LocationModel();
            info.setUrl(mListMarker.get(i).getUrl());

            marker.setTag(info);

            LatLng latLng = new LatLng(Double.parseDouble(mListMarker.get(0).getLatitude()),
                    Double.parseDouble(mListMarker.get(0).getLongitude()));

            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLng.latitude, latLng.longitude),11.0f ));

            if(mListMarker.size() != 0){
                TestInfoWindowAdapter testInfoWindowAdapter = new TestInfoWindowAdapter(MapsActivity.this);
                mMap.setInfoWindowAdapter(testInfoWindowAdapter);
            }



        }
    }

    private class TestInfoWindowAdapter implements GoogleMap.InfoWindowAdapter{

        private Context context;
        private TestInfoWindowAdapter(Context context)
        {
            this.context = context;
        }

        @Nullable
        @Override
        public View getInfoContents(@NonNull Marker marker) {
            return null;
        }

        @Nullable
        @Override
        public View getInfoWindow(@NonNull Marker marker) {

            View view = ((Activity)context).getLayoutInflater().inflate(R.layout.info_popup,null );

            TextView city = view.findViewById(R.id.mCityname);
            ImageView imageView = view.findViewById(R.id.image);

            city.setText(marker.getTitle());
            LocationModel infomodel = (LocationModel)  marker.getTag();

            UrlString = infomodel.getUrl();

            Picasso.get()
                    .load(UrlString)
                    .error(R.mipmap.ic_launcher)
                    .into(imageView, new MarkerCallBack(marker));
            return null;
        }
    }

    private class MarkerCallBack implements com.squareup.picasso.Callback {
        Marker marker = null;

        public MarkerCallBack(Marker marker) {
        this.marker = marker;
        }

        @Override
        public void onSuccess() {


            if(marker != null && marker.isInfoWindowShown())
            {
                marker.hideInfoWindow();
                marker.showInfoWindow();
            }
        }

        @Override
        public void onError(Exception e) {
            Toast.makeText(MapsActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}