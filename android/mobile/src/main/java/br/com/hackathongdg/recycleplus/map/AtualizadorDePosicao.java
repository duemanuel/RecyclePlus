package br.com.hackathongdg.recycleplus.map;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.google.android.gms.maps.model.LatLng;


public class AtualizadorDePosicao implements LocationListener {

    private LocationManager locationManager;
    private MapaFragment mapa;

    public AtualizadorDePosicao(Activity activity, MapaFragment mapa) {
        this.mapa = mapa;

    }

    public void cancelar() {
    }

    @Override
    public void onLocationChanged(Location novaLocalizacao) {
        double latitude = -19.936267;
        double longitude = -43.936573;

        LatLng local = new LatLng(latitude, longitude);

        mapa.centralizaNo(local);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }
}