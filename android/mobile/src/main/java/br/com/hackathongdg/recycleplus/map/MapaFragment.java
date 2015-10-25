package br.com.hackathongdg.recycleplus.map;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.List;

public class MapaFragment extends SupportMapFragment {
    private Context context;
    private GoogleMap map;
    private br.com.hackathongdg.recycleplus.map.Localizador localizador;

    @Override
    public void onResume() {
        super.onResume();
        context = getActivity();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                addMarkers(map);
            }
        });
    }

    public void centralizaNo(LatLng local) {
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(local, 17);
        if (update != null) {
            map.animateCamera(update);
        }
    }

    private void addMarkers(GoogleMap map) {

        localizador = new Localizador(getActivity());

        LatLng local = localizador.getCoordenada("Av Cristovão Colombo, 454 - Belo Horizonte");

        if (local != null) {
            MarkerOptions options = new MarkerOptions().title("Ponto de Reciclagem").position(local);
            map.addMarker(options);
        }
    }
}
