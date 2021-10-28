package br.com.unip.pimIV.hotelFazenda.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;

import java.util.List;

import br.com.unip.pimIV.hotelFazenda.R;
import br.com.unip.pimIV.hotelFazenda.model.Quarto;
import br.com.unip.pimIV.hotelFazenda.model.Usuario;
import br.com.unip.pimIV.hotelFazenda.recyclerView.adapter.ListaComprasAdapter;
import br.com.unip.pimIV.hotelFazenda.viewModel.ListaDeComprasViewModel;

import static br.com.unip.pimIV.hotelFazenda.ui.activity.Contantes.CHAVE_USER;

public class ListaDeComprasFragment extends Fragment {
    private Usuario usuario;
    private ListaDeComprasViewModel viewModel = new ListaDeComprasViewModel();
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.lista_compras_realizadas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Minhas Compras");
        context = getActivity().getApplicationContext();

        usuario = (Usuario) getArguments().getSerializable(CHAVE_USER);
        viewModel.getCompras(getOnFailureListener()).observe(getViewLifecycleOwner(), new Observer<List<Quarto>>() {
            @Override
            public void onChanged(List<Quarto> listaDeCompras) {
                if (listaDeCompras.size() > 0) {
                    TextView semCompras = getActivity().findViewById(R.id.lista_compras_text_view_sem_compras);
                    semCompras.setVisibility(View.INVISIBLE);
                    semCompras.setText("");
                    ListaComprasAdapter adapter = new ListaComprasAdapter(context, listaDeCompras);
                    RecyclerView recycler = view.findViewById(R.id.lista_compras_recyclerView);
                    recycler.setAdapter(adapter);
                }
            }
        });


    }

    private OnFailureListener getOnFailureListener() {
        return e -> Toast.makeText(context, "Deu errado mas entrou na Falha", Toast.LENGTH_LONG).show();
    }
}
