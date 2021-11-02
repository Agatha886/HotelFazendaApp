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
import br.com.unip.pimIV.hotelFazenda.util.MoedaUtil;
import br.com.unip.pimIV.hotelFazenda.util.PeriodoUtil;
import br.com.unip.pimIV.hotelFazenda.util.ResourcesUltil;

/**
 * Classe ListaComprasAdapter refere-se a um adapter personalido para lista de compras do aplicativo
 */
public class ListaComprasAdapter extends RecyclerView.Adapter<ListaComprasAdapter.QuartosViewHolder> {

    /**
     * A variável context é o contexto da activity ou fragment onde estará o RecyclerView que fará uso deste adapter
     */
    private final Context context;

    /**
     * O parâmetro quartos refere-se a lista de quartos comprados pelo o usuário no aplicativo
     */
    private final List<Quarto> quartos;

    /**
     * Construtor padrão da classe ListaComprasAdapter
     *
     * @param context
     * @param quartos
     */
    public ListaComprasAdapter(Context context, List<Quarto> quartos) {
        this.context = context;
        this.quartos = quartos;
    }


    /**
     * Sobrescrita  do método onCreateViewHolder, responsável por criar uma ViewGroup para ser
     * usada pela na criação da instância de QuartosViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public QuartosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.detalhes_da_compra, parent, false);
        return new QuartosViewHolder(viewCriada);
    }


    /**
     * Método responsável por realizar processo de Bind para cada item da lista
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ListaComprasAdapter.QuartosViewHolder holder, int position) {
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
     * Classe QuartosViewHolder refere-se a um ViewHolder personalido para lista de compras do aplicativo
     */
    public class QuartosViewHolder extends RecyclerView.ViewHolder {

        /**
         * Construtor padrão da classe QuartosViewHolder
         *
         * @param itemView
         */
        public QuartosViewHolder(@NonNull View itemView) {
            super(itemView);

        }

        /**
         * Responsável por criar cada componente de layout para cada item da lista de compras do adapter e vinculá-los
         *
         * @param quarto
         */
        public void vincula(Quarto quarto) {
            atribuiNomeDoQuarto(itemView, quarto);
            atribuiPreco(itemView, quarto);
            atribuiPeriodo(itemView, quarto);
            atribuiImagem(itemView, quarto);
        }

        /**
         * Vincula informação do preço total da hospedagem a um componente de layout para exibi-lo
         *
         * @param viewCriada
         * @param quarto
         */

        private void atribuiPreco(View viewCriada, Quarto quarto) {
            TextView preco = viewCriada.findViewById(R.id.detalhes_compra_textview_preco);
            String precoDoPacoteNaMoedaBrasileira = MoedaUtil.formataParaBrasileiro(quarto.getPrecoTotal());
            preco.setText(precoDoPacoteNaMoedaBrasileira);
        }

        /**
         * Vincula informação do período de hospedagem a um componente de layout para exibi-lo
         *
         * @param viewCriada
         * @param quarto
         */
        private void atribuiPeriodo(View viewCriada, Quarto quarto) {
            TextView campoPeriodo = viewCriada.findViewById(R.id.detalhes_compra_textview_periodo);
            String diasTexto = PeriodoUtil.periodoEmTexto(quarto.getDataDeIda(), quarto.getDataDeVolta());
            campoPeriodo.setText(diasTexto);
        }

        /**
         * Vincula imagem do quarto a um componente de layout para exibi-lo
         *
         * @param viewCriada
         * @param quarto
         */
        private void atribuiImagem(View viewCriada, Quarto quarto) {
            ImageView imagem = viewCriada.findViewById(R.id.detalhes_compra_imagem);
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
            TextView nomeDoQuarto = viewCriada.findViewById(R.id.detalhes_compra_textview_nome);
            nomeDoQuarto.setText(quarto.getQuarto());
        }
    }

}


