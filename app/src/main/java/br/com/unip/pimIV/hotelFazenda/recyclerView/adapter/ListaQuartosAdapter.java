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
import br.com.unip.pimIV.hotelFazenda.util.PessoasUltil;
import br.com.unip.pimIV.hotelFazenda.util.MoedaUtil;
import br.com.unip.pimIV.hotelFazenda.util.ResourcesUltil;

public class ListaQuartosAdapter extends RecyclerView.Adapter<ListaQuartosAdapter.QuartosViewHolder> {

    private final Context context;
    private final List<Quarto> quartos;
    private QuandoItemClicado quandoItemClicado;

    public ListaQuartosAdapter(Context context, List<Quarto> quartos) {
        this.context = context;
        this.quartos = quartos;
    }

    public void setOnItemClickListener(QuandoItemClicado quandoItemClicado) {
        this.quandoItemClicado = quandoItemClicado;
    }


    @NonNull
    @Override
    public QuartosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_quarto, parent, false);
        return new QuartosViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaQuartosAdapter.QuartosViewHolder holder, int position) {
        Quarto quarto = quartos.get(position);
        holder.vincula(quarto);
    }

    @Override
    public int getItemCount() {
        return quartos.size();
    }

    public class QuartosViewHolder extends RecyclerView.ViewHolder {
        private Quarto quarto;

        public QuartosViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(v -> quandoItemClicado.quandoClicado(quarto, getAdapterPosition()));
        }

        public void vincula(Quarto quarto) {
            this.quarto = quarto;
            atribuiNomeDoQuarto(itemView, quarto);
            atribuiPreco(itemView, quarto);
            atribuiQuantidadeDePessoas(itemView, quarto);
            atribuiImagem(itemView, quarto);
        }

        private void atribuiPreco(View viewCriada, Quarto quarto) {
            TextView preco = viewCriada.findViewById(R.id.item_pacote_precos);
            String precoDoPacoteNaMoedaBrasileira = MoedaUtil.formataParaBrasileiro(quarto.getPrecoDaDiaria());
            preco.setText(precoDoPacoteNaMoedaBrasileira);
        }

        private void atribuiQuantidadeDePessoas(View viewCriada, Quarto quarto) {
            TextView pessoas = viewCriada.findViewById(R.id.item_pacote_dias);
            String pessoasTexto = PessoasUltil.formataTextoPessoas(quarto.getPessoas());
            pessoas.setText(pessoasTexto);
        }

        private void atribuiImagem(View viewCriada, Quarto quarto) {
            ImageView imagem = viewCriada.findViewById(R.id.item_pacote_imagem);
            Drawable drawable = ResourcesUltil.getDrawable(context, quarto.getImagem());
            imagem.setImageDrawable(drawable);
        }

        private void atribuiNomeDoQuarto(View viewCriada, Quarto quarto) {
            TextView nomeDoQuarto = viewCriada.findViewById(R.id.item_pacote_local);
            nomeDoQuarto.setText(quarto.getQuarto());
        }
    }

}


