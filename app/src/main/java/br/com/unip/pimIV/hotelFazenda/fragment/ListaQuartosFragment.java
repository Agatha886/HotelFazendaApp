package br.com.unip.pimIV.hotelFazenda.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import br.com.unip.pimIV.hotelFazenda.R;
import br.com.unip.pimIV.hotelFazenda.dao.QuartosDAO;
import br.com.unip.pimIV.hotelFazenda.recyclerView.adapter.ListaQuartosAdapter;

public class ListaQuartosFragment extends Fragment {
    private QuandoItemClicado quandoItemClicado;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.lista_quartos, container, false);
    }

    public void setQuandoItemClicado(QuandoItemClicado quandoItemClicado) {
        this.quandoItemClicado = quandoItemClicado;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = getActivity().getApplicationContext();
        QuartosDAO dao = new QuartosDAO();
        ListaQuartosAdapter adapter = new ListaQuartosAdapter(context, dao.lista());
        RecyclerView recycler = view.findViewById(R.id.lista_quartos_recyclerView);
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(quandoItemClicado);
    }
}
