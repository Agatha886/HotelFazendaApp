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

import br.com.unip.pimIV.hotelFazenda.model.Quarto;
import br.com.unip.pimIV.hotelFazenda.ui.fragment.QuandoItemClicado;
import br.com.unip.pimIV.hotelFazenda.util.MoedaUtil;
import br.com.unip.pimIV.hotelFazenda.util.PessoasUltil;
import br.com.unip.pimIV.hotelFazenda.util.ResourcesUltil;

/**
 * Classe ListaQuartosAdapter refere-se a um adapter personalido para quartos do aplicativo
 *
 * @author Agatha Monfredini de Paula Faria
 * @version 1.0.0
 */
public class ListaQuartosAdapter extends RecyclerView.Adapter<ListaQuartosAdapter.QuartosViewHolder> {

    /**
     * A variável context é o contexto da activity ou fragment onde estará o RecyclerView que fará uso deste adapter
     */
    private final Context context;

    /**
     * O atributo quartos refere-se a lista de quartos do aplicativo
     */
    private final List<Quarto> quartos;

    /**
     * O atributo quartos refere-se é uma instância de uma interface responsável pelo evento de click de cada item desse adapter
     */
    private QuandoItemClicado quandoItemClicado;

    /**
     * Construtor padrão da classe ListaQuartosAdapter
     *
     * @param context
     * @param quartos
     */
    public ListaQuartosAdapter(Context context, List<Quarto> quartos) {
        this.context = context;
        this.quartos = quartos;
    }

    /**
     * Set evento de click de cada item da lista
     *
     * @param quandoItemClicado
     */
    public void setOnItemClickListener(QuandoItemClicado quandoItemClicado) {
        this.quandoItemClicado = quandoItemClicado;
    }

    /**
     * Sobrescrita  do método onCreateViewHolder, responsável por criar uma ViewGroup para ser
     * usada na criação de uma instância de QuartosViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public QuartosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_quarto, parent, false);
        return new QuartosViewHolder(viewCriada);
    }

    /**
     * Método responsável por realizar processo de Bind para cada item da lista
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ListaQuartosAdapter.QuartosViewHolder holder, int position) {
        Quarto quarto = quartos.get(position);
        holder.vincula(quarto);
    }

    /**
     * Retorna tamanho da lista utilizada pelo adapter
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return quartos.size();
    }


    /**
     * Classe QuartosViewHolder refere-se a um ViewHolder personalido para lista de quartos do aplicativo
     */
    public class QuartosViewHolder extends RecyclerView.ViewHolder {
        private Quarto quarto;

        /**
         * Construtor padrão da classe QuartosViewHolder
         *
         * @param itemView
         */
        public QuartosViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(v -> quandoItemClicado.quandoClicado(quarto, getAdapterPosition()));
        }

        /**
         * Responsável por pegar cada componente de layout e cada item da lista de quartos do adapter e vinculá-los
         *
         * @param quarto
         */
        public void vincula(Quarto quarto) {
            this.quarto = quarto;
            atribuiNomeDoQuarto(itemView, quarto);
            atribuiPreco(itemView, quarto);
            atribuiQuantidadeDePessoas(itemView, quarto);
            atribuiImagem(itemView, quarto);
        }

        /**
         * Vincula informação do preço da diária a um componente de layout para exibi-lo
         *
         * @param viewCriada
         * @param quarto
         */
        private void atribuiPreco(View viewCriada, Quarto quarto) {
            TextView preco = viewCriada.findViewById(R.id.item_pacote_precos);
            String precoDoPacoteNaMoedaBrasileira = MoedaUtil.formataParaBrasileiro(quarto.getPrecoDaDiaria());
            preco.setText(precoDoPacoteNaMoedaBrasileira);
        }

        /**
         * Vincula informação do limite de pessoas do quarto a um componente de layout para exibi-lo
         *
         * @param viewCriada
         * @param quarto
         */
        private void atribuiQuantidadeDePessoas(View viewCriada, Quarto quarto) {
            TextView pessoas = viewCriada.findViewById(R.id.item_pacote_dias);
            String pessoasTexto = PessoasUltil.formataTextoPessoas(quarto.getPessoas());
            pessoas.setText(pessoasTexto);
        }

        /**
         * Vincula imagem do quarto a um componente de layout para exibi-lo
         *
         * @param viewCriada
         * @param quarto
         */
        private void atribuiImagem(View viewCriada, Quarto quarto) {
            ImageView imagem = viewCriada.findViewById(R.id.item_pacote_imagem);
            Drawable drawable = ResourcesUltil.getDrawable(context, quarto.getImagem());
            imagem.setImageDrawable(drawable);
        }

        /**
         * Vincula informação do nome do quarto a um componente de layout para exibi-lo
         *
         * @param viewCriada
         * @param quarto
         */
        private void atribuiNomeDoQuarto(View viewCriada, Quarto quarto) {
            TextView nomeDoQuarto = viewCriada.findViewById(R.id.item_pacote_local);
            nomeDoQuarto.setText(quarto.getQuarto());
        }
    }

}


