package br.com.unip.pimIV.hotelFazenda.recyclerView.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.unip.pimIV.hotelFazenda.R;
import br.com.unip.pimIV.hotelFazenda.model.Quarto;
import br.com.unip.pimIV.hotelFazenda.ui.fragment.QuandoItemClicado;
import br.com.unip.pimIV.hotelFazenda.util.MoedaUtil;
import br.com.unip.pimIV.hotelFazenda.util.PeriodoUtil;
import br.com.unip.pimIV.hotelFazenda.util.ResourcesUltil;

public class ListaComprasAdapter extends RecyclerView.Adapter<ListaComprasAdapter.QuartosViewHolder> {

    private final Context context;
    private final List<Quarto> quartos;
    private QuandoItemClicado quandoItemClicado;

    public ListaComprasAdapter(Context context, List<Quarto> quartos) {
        this.context = context;
        this.quartos = quartos;
    }


    @NonNull
    @Override
    public QuartosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.detalhes_da_compra, parent, false);
        return new QuartosViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaComprasAdapter.QuartosViewHolder holder, int position) {
        Quarto quarto = quartos.get(position);
        holder.vincula(quarto);
    }

    @Override
    public int getItemCount() {
        return quartos.size();
    }

    public class QuartosViewHolder extends RecyclerView.ViewHolder {

        public QuartosViewHolder(@NonNull View itemView) {
            super(itemView);

        }

        public void vincula(Quarto quarto) {
            atribuiNomeDoQuarto(itemView, quarto);
            atribuiPreco(itemView, quarto);
            atribuiPeriodo(itemView, quarto);
            atribuiImagem(itemView, quarto);
        }

        private void atribuiPreco(View viewCriada, Quarto quarto) {
            TextView preco = viewCriada.findViewById(R.id.detalhes_compra_textview_preco);
            String precoDoPacoteNaMoedaBrasileira = MoedaUtil.formataParaBrasileiro(quarto.getPrecoTotal());
            preco.setText(precoDoPacoteNaMoedaBrasileira);
        }

        private void atribuiPeriodo(View viewCriada, Quarto quarto) {
            TextView campoPeriodo = viewCriada.findViewById(R.id.detalhes_compra_textview_periodo);
            String diasTexto = PeriodoUtil.periodoEmTexto(quarto.getDataDeIda(),quarto.getDataDeVolta());
            campoPeriodo.setText(diasTexto);
        }

        private void atribuiImagem(View viewCriada, Quarto quarto) {
            ImageView imagem = viewCriada.findViewById(R.id.detalhes_compra_imagem);
            Drawable drawable = ResourcesUltil.getDrawable(context, quarto.getImagem());
            imagem.setImageDrawable(drawable);
        }

        private void atribuiNomeDoQuarto(View viewCriada, Quarto quarto) {
            TextView nomeDoQuarto = viewCriada.findViewById(R.id.detalhes_compra_textview_nome);
            nomeDoQuarto.setText(quarto.getQuarto());
        }
    }

}


