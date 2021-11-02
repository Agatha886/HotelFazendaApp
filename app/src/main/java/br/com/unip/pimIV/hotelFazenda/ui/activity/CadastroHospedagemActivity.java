
package br.com.unip.pimIV.hotelFazenda.ui.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.util.Calendar;

import br.com.unip.pimIV.hotelFazenda.model.Quarto;
import br.com.unip.pimIV.hotelFazenda.util.DataUtil;
import br.com.unip.pimIV.hotelFazenda.util.MoedaUtil;
import br.com.unip.pimIV.hotelFazenda.util.PessoasUltil;
import br.com.unip.pimIV.hotelFazenda.util.ResourcesUltil;

import static br.com.unip.pimIV.hotelFazenda.ui.activity.Contantes.CHAVE_QUARTO;

/**
 * Classe da CadastroHospedagemActivity responsável pela tela de cadastro de hóspedagem
 *
 * @author Agatha Monfredini de Paula Faria
 * @version 1.0.0
 */
public class CadastroHospedagemActivity extends AppCompatActivity {

    /**
     * Atributo responsável pelo título do APPBAR da tela de cadastro de hóspedagem
     */
    public static final String TITULO_APPBAR = "Detalhes do Quarto";

    /**
     * Componente de layout que irá exibir a data de ida (check-in) do usuário
     */
    private TextView dataDeIda;

    /**
     * Componente de layout que irá exibir a data de volta (check-out) do usuário
     */
    private TextView dataDeVolta;

    /**
     * Componente de layout que irá exibir o período de hospedagem do usuário do usuário
     */
    private TextView textViewPrecoTotal;

    /**
     * Atributo com as informações do quarto selecionado pelo usuário
     */
    private Quarto quarto;

    /**
     * Atributo com a informação dos do período de hospedagem em dias
     */
    private int dias;

    /**
     * Método responsável por criar/inicializar a CadastroHospedagemActivity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_hospedagem);
        setTitle(TITULO_APPBAR);
        carregaQuartoRecebido();
        adicionaCalendario();

    }

    /**
     * Método responsável por chamar função setValoresDataDeIdaOuDataDeVolta conforme o usuário selcionar as datas de ida e volta no componenete de calendário (DatePicker)
     *
     * @param data
     * @return
     */
    @NonNull
    private DatePickerDialog.OnDateSetListener configuraDatePicker(TextView data) {
        return new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(ano, mes, dia);
                if (data != null) {
                    String dataFormatada = DataUtil.formataParaBrasileiro(calendar);
                    data.setText(dataFormatada);
                    setValoresDataDeIdaOuDataDeVolta(calendar, data);
                }
            }
        };
    }

    /**
     * Método responsável por atribuir o valor da data de ida ou de volta selecionada pelo usuário ao quarto selecionao por este
     *
     * @param calendar
     * @param data
     */
    private void setValoresDataDeIdaOuDataDeVolta(Calendar calendar, TextView data) {
        if (data == dataDeIda) {
            quarto.setDataDeIda(calendar);
            calculaTotal();
        } else {
            quarto.setDataDeVolta(calendar);
            calculaTotal();
        }
    }

    /**
     * Set clique do campo dataDeIda e de volta , do layout , para chamar o componente de calendário (DatePicker)
     */
    private void adicionaCalendario() {
        dataDeIda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamaDatePicker(Calendar.getInstance(), dataDeIda);
            }
        });

        dataDeVolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamaDatePicker(Calendar.getInstance(), dataDeVolta);
            }
        });
    }

    /**
     * Método responsável por criar um compoente de calendário com valores iniciais da data atual
     *
     * @param calendar
     * @param data
     */
    private void chamaDatePicker(Calendar calendar, TextView data) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(this, configuraDatePicker(data), year, month, day).show();
    }

    /**
     * Método responsável por atribuir os valores do quarto recebido da MainActivity a variável quarto
     */
    private void carregaQuartoRecebido() {
        Intent intent = getIntent();
        if (intent.hasExtra(CHAVE_QUARTO)) {
            quarto = (Quarto) intent.getSerializableExtra(CHAVE_QUARTO);
            inicializaCampos();
        }
    }

    /**
     * Método responsável por chamar as funções de configurações de cada componente de layout
     */
    private void inicializaCampos() {
        mostraDataDeVolta();
        mostraDataDeIda();
        mostraNomeDoQuarto();
        mostraImagem();
        mostraNumeroDePessoas();
        mostraPreco();
        mostraPrecoTotal();
        configuraButton();
    }

    /**
     * Set clique do botão realiar compra
     *
     * <b>Procedimentos:</b>
     * Caso período escolhido pelo usuário se valido, este será direcionado para PagamentoActvity
     * Caso <b>não:</b> aparecerá a mensagem "Período Inválido" na tela
     */
    private void configuraButton() {
        Button btnRealizarCompra = findViewById(R.id.cadastro_hospedagem_btn_realizar_compra);
        btnRealizarCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validaPeriodo())
                    irParaPagamento();
            }
        });
    }

    /**
     * Vai para PagamentoActvity
     */
    private void irParaPagamento() {
        Intent intent = new Intent(CadastroHospedagemActivity.this, PagamentoActvity.class);
        intent.putExtra(CHAVE_QUARTO, quarto);
        startActivity(intent);
    }


    /**
     * Configura componente de layout dataDeVolta com o valor do atributo dataDeVolta do quarto
     */
    private void mostraDataDeVolta() {
        dataDeVolta = findViewById(R.id.cadastro_hospedagem_textview_data_volta);
        String dataFormatada = DataUtil.formataParaBrasileiro(quarto.getDataDeVolta());
        dataDeVolta.setText(dataFormatada);
    }

    /**
     * Configura componente de layout dataDeIda com o valor do atributo dataDeIda do quarto
     */
    private void mostraDataDeIda() {
        dataDeIda = findViewById(R.id.cadastro_hospedagem_textview_data_ida);
        String dataFormatada = DataUtil.formataParaBrasileiro(quarto.getDataDeIda());
        dataDeIda.setText(dataFormatada);
    }

    /**
     * Configura componente de layout textViewPrecoTotal com o valor inicial do atributo precoDaDiaria do quarto
     */
    private void mostraPrecoTotal() {
        textViewPrecoTotal = findViewById(R.id.cadastro_hospedagem_textview_total);
        String precoDaDiaria = MoedaUtil.formataParaBrasileiro(quarto.getPrecoDaDiaria());
        textViewPrecoTotal.setText(precoDaDiaria);
    }

    /**
     * Configura componente de layout textViewPreco com o valor do atributo precoDaDiaria do quarto
     */
    private void mostraPreco() {
        TextView textViewPreco = findViewById(R.id.cadastro_hospedagem_textview_preco);
        String precoDoPacote = MoedaUtil.formataParaBrasileiro(quarto.getPrecoDaDiaria());
        textViewPreco.setText(precoDoPacote);
    }

    /**
     * Configura componente de layout textViewDias com o valor do atributo pessoas do quarto
     */
    private void mostraNumeroDePessoas() {
        TextView textViewDias = findViewById(R.id.cadastro_hospedagem_textview_dias);
        String formataDiasEmTexto = PessoasUltil.formataTextoPessoas(quarto.getPessoas());
        textViewDias.setText(formataDiasEmTexto);
    }

    /**
     * Configura componente de layout imageViewLocal com a imagem do quarto
     */

    private void mostraImagem() {
        ImageView imageViewLocal = (findViewById(R.id.cadastro_hospedagem_img));
        Drawable drawableDoPacote = ResourcesUltil.getDrawable(this, quarto.getImagem());
        imageViewLocal.setImageDrawable(drawableDoPacote);
    }


    /**
     * Configura componente de layout textViewNomeQuarto com o valor do atributo nome do quarto
     */
    private void mostraNomeDoQuarto() {
        TextView textViewNomeQuarto = (findViewById(R.id.cadastro_hospedagem_textview_local));
        textViewNomeQuarto.setText(quarto.getQuarto());
    }

    /**
     * Método responsável por  calcular o valor total das hospedagem confome o período selecionado pelo usuário
     */
    private void calculaTotal() {
        dias = daysBetween();
        if (dias > 0) {
            quarto.setPrecoTotal(BigDecimal.ZERO);
            BigDecimal total = quarto.getPrecoDaDiaria().multiply(new BigDecimal(this.dias));
            quarto.setPrecoTotal(total);
            String valorTotal = MoedaUtil.formataParaBrasileiro(total);
            textViewPrecoTotal.setText(valorTotal);
        } else if (dias == 0) {
            quarto.setPrecoTotal(quarto.getPrecoDaDiaria());
            textViewPrecoTotal.setText(MoedaUtil.formataParaBrasileiro(quarto.getPrecoTotal()));
        }
    }

    /**
     * Método responsável por retornar os dias conforme o período selecionado pelo usuário
     * @return
     */
    public int daysBetween() {
        return dias = quarto.getDataDeVolta().get(Calendar.DAY_OF_YEAR) -
                quarto.getDataDeIda().get(Calendar.DAY_OF_YEAR);
    }

    /**
     * Método responsável por validar o período selecionado pelo usuário
     * @return
     */
    private Boolean validaPeriodo() {
        if (dias < 0) {
            Toast.makeText(CadastroHospedagemActivity.this,
                    "Período Inválido", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}