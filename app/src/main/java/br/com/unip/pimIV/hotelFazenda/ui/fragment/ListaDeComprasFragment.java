package br.com.unip.pimIV.hotelFazenda.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;



import br.com.unip.pimIV.hotelFazenda.dao.UsuarioDAO;
import br.com.unip.pimIV.hotelFazenda.model.Quarto;
import br.com.unip.pimIV.hotelFazenda.recyclerView.adapter.ListaComprasAdapter;

/**
 * Classe da ListaDeComprasFragment responsável pela tela de exibição da lista de compras do usuário no menu principal
 */
public class ListaDeComprasFragment extends Fragment {

    /**
     * Cria componente View
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.lista_compras_realizadas, container, false);
    }

    /**
     *  Realiza configurações dos campos com a View já criada
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Minhas Compras");
        Context context = getActivity().getApplicationContext();
        List<Quarto> listaDeCompras = UsuarioDAO.usuario.listaDeCompras;

        if (listaDeCompras.size() > 0) {
            TextView semCompras = getActivity().findViewById(R.id.lista_compras_text_view_sem_compras);
            semCompras.setVisibility(View.INVISIBLE);
            semCompras.setText("");
        }

        ListaComprasAdapter adapter = new ListaComprasAdapter(context, listaDeCompras);
        RecyclerView recycler = view.findViewById(R.id.lista_compras_recyclerView);
        recycler.setAdapter(adapter);

    }
}
