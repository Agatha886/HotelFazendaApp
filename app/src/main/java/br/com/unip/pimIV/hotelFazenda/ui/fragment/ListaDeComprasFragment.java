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

import br.com.unip.pimIV.hotelFazenda.R;
import br.com.unip.pimIV.hotelFazenda.dao.UsuarioDAO;
import br.com.unip.pimIV.hotelFazenda.model.Quarto;
import br.com.unip.pimIV.hotelFazenda.recyclerView.adapter.ListaComprasAdapter;

public class ListaDeComprasFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.lista_compras_realizadas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = getActivity().getApplicationContext();
        List<Quarto> listaDeCompras = UsuarioDAO.usuario.listaDeCompras;
        getActivity().setTitle("Minhas Compras");

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
