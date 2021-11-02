package br.com.unip.pimIV.hotelFazenda.ui.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import br.com.unip.pimIV.hotelFazenda.model.Quarto;
import br.com.unip.pimIV.hotelFazenda.util.MoedaUtil;
import br.com.unip.pimIV.hotelFazenda.util.PeriodoUtil;
import br.com.unip.pimIV.hotelFazenda.util.ResourcesUltil;

import static br.com.unip.pimIV.hotelFazenda.ui.activity.Contantes.CHAVE_QUARTO;

public class DetalhesDaCompraFragment extends Fragment {
    private Quarto quarto;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detalhes_da_compra, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        quarto = (Quarto) arguments.getSerializable(CHAVE_QUARTO);
        if(quarto != null){
            mostraPreco();
            mostraNome();
            mostraPeriodo();
            mostraImagem();
        }

    }

    private void mostraImagem() {
        ImageView imageViewLocal = (getActivity().findViewById(R.id.detalhes_compra_imagem));
        Drawable drawableDoPacote = ResourcesUltil.getDrawable(getContext(), quarto.getImagem());
        imageViewLocal.setImageDrawable(drawableDoPacote);
    }

    private void mostraPeriodo() {
        TextView textperiodo = getActivity().findViewById(R.id.detalhes_compra_textview_periodo);
        String periodo = PeriodoUtil.periodoEmTexto(quarto.getDataDeIda(), quarto.getDataDeVolta());
        textperiodo.setText(periodo);
    }

    private void mostraNome() {
        TextView textNome = getActivity().findViewById(R.id.detalhes_compra_textview_nome);
        textNome.setText(quarto.getQuarto());
    }

    private void mostraPreco() {
        TextView textpreco = getActivity().findViewById(R.id.detalhes_compra_textview_preco);
        String preco = MoedaUtil.formataParaBrasileiro(quarto.getPrecoTotal());
        textpreco.setText(preco);
    }
}
