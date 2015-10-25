package br.com.hackathongdg.recycleplus.map;

import android.os.Bundle;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import br.com.hackathongdg.recycleplus.R;

public class MostraPontosProximos extends FragmentActivity{

    private AtualizadorDePosicao atualizador;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.map_layout);

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();

        MapaFragment mapaFragment = new MapaFragment();
        transaction.replace(R.id.mapa, new MapaFragment());
        transaction.commit();

        atualizador = new AtualizadorDePosicao(this, mapaFragment);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        atualizador.cancelar();
    }
}
