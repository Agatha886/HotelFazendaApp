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

import br.com.unip.pimIV.hotelFazenda.R;
import br.com.unip.pimIV.hotelFazenda.model.Quarto;
import br.com.unip.pimIV.hotelFazenda.util.DataUtil;
import br.com.unip.pimIV.hotelFazenda.util.MoedaUtil;
import br.com.unip.pimIV.hotelFazenda.util.PessoasUltil;
import br.com.unip.pimIV.hotelFazenda.util.ResourcesUltil;

import static br.com.unip.pimIV.hotelFazenda.ui.activity.Contantes.CHAVE_QUARTO;

public class CadastroHospedagemActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Detalhes do Quarto";
    private TextView dataDeIda;
    private TextView dataDeVolta;
    private TextView textViewPeriodo;
    private Quarto quarto;
    private int dias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_hospedagem);
        setTitle(TITULO_APPBAR);
        carregaQuartoRecebido();
        adicionaCalendario();

    }

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
                    setValores(calendar, data);
                }
            }
        };
    }

    private void setValores(Calendar calendar, TextView data) {
        if (data == dataDeIda) {
            quarto.setDataDeIda(calendar);
            calculaTotal();
        } else {
            quarto.setDataDeVolta(calendar);
            calculaTotal();
        }
    }

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

    private void chamaDatePicker(Calendar calendar, TextView data) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(this, configuraDatePicker(data), year, month, day).show();
    }

    private void carregaQuartoRecebido() {
        Intent intent = getIntent();
        if (intent.hasExtra(CHAVE_QUARTO)) {
            quarto = (Quarto) intent.getSerializableExtra(CHAVE_QUARTO);
            inicializaCampos();
        }
    }

    private void inicializaCampos() {
        mostraDataDeVolta();
        mostraDataDeIda();
        mostraNomeDoQuarto();
        mostraImagem();
        mostraNumeroDePessoas();
        mostraPreco();
        mostraPeriodo();
        configuraButton();
    }

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

    private void irParaPagamento() {
        Intent intent = new Intent(CadastroHospedagemActivity.this, PagamentoActvity.class);
        intent.putExtra(CHAVE_QUARTO, quarto);
        startActivity(intent);
    }

    private void mostraDataDeVolta() {
        dataDeVolta = findViewById(R.id.cadastro_hospedagem_textview_data_volta);
        String dataFormatada = DataUtil.formataParaBrasileiro(quarto.getDataDeVolta());
        dataDeVolta.setText(dataFormatada);
    }

    private void mostraDataDeIda() {
        dataDeIda = findViewById(R.id.cadastro_hospedagem_textview_data_ida);
        String dataFormatada = DataUtil.formataParaBrasileiro(quarto.getDataDeIda());
        dataDeIda.setText(dataFormatada);
    }
    private void mostraPeriodo() {
        textViewPeriodo = findViewById(R.id.cadastro_hospedagem_textview_total);
        String precoDaDiaria = MoedaUtil.formataParaBrasileiro(quarto.getPrecoDaDiaria());
        textViewPeriodo.setText(precoDaDiaria);
    }

    private void mostraPreco() {
        TextView textViewPreco = findViewById(R.id.cadastro_hospedagem_textview_preco);
        String precoDoPacote = MoedaUtil.formataParaBrasileiro(quarto.getPrecoDaDiaria());
        textViewPreco.setText(precoDoPacote);
    }

    private void mostraNumeroDePessoas() {
        TextView textViewDias = findViewById(R.id.cadastro_hospedagem_textview_dias);
        String formataDiasEmTexto = PessoasUltil.formataTextoPessoas(quarto.getPessoas());
        textViewDias.setText(formataDiasEmTexto);
    }

    private void mostraImagem() {
        ImageView imageViewLocal = (findViewById(R.id.cadastro_hospedagem_img));
        Drawable drawableDoPacote = ResourcesUltil.getDrawable(this, quarto.getImagem());
        imageViewLocal.setImageDrawable(drawableDoPacote);
    }

    private void mostraNomeDoQuarto() {
        TextView textViewLocal = (findViewById(R.id.cadastro_hospedagem_textview_local));
        textViewLocal.setText(quarto.getQuarto());
    }

    private void calculaTotal() {
        dias = daysBetween();
        if (dias > 0) {
            quarto.setPrecoTotal(BigDecimal.ZERO);
            BigDecimal total = quarto.getPrecoDaDiaria().multiply(new BigDecimal(this.dias));
            quarto.setPrecoTotal(total);
            String valorTotal = MoedaUtil.formataParaBrasileiro(total);
            textViewPeriodo.setText(valorTotal);
        } else if( dias == 0){
            quarto.setPrecoTotal(quarto.getPrecoDaDiaria());
            textViewPeriodo.setText(MoedaUtil.formataParaBrasileiro(quarto.getPrecoTotal()));
        }
    }

    public int daysBetween() {
        return dias = quarto.getDataDeVolta().get(Calendar.DAY_OF_YEAR) -
                quarto.getDataDeIda().get(Calendar.DAY_OF_YEAR);
    }

    private Boolean validaPeriodo() {
        if (dias < 0) {
            Toast.makeText(CadastroHospedagemActivity.this,
                    "Período Inválido", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}