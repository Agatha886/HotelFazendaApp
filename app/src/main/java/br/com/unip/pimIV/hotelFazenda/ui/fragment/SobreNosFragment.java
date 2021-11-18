package br.com.unip.pimIV.hotelFazenda.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import br.com.unip.pimIV.hotelFazenda.R;

/**
 * Classe da SobreNosFragment responsável pela tela de exibição das informações sobre o Hotel Fazenda no menu principal
 */
public class SobreNosFragment extends Fragment {
    /**
     * Cria componente View
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable  Bundle savedInstanceState) {

        return inflater.inflate(R.layout.sobre_nos, container, false);
    }

}
