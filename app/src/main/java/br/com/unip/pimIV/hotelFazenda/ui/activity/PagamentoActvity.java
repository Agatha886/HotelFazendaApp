package br.com.unip.pimIV.hotelFazenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.com.unip.pimIV.hotelFazenda.dao.UsuarioDAO;
import br.com.unip.pimIV.hotelFazenda.model.Quarto;
import br.com.unip.pimIV.hotelFazenda.util.MoedaUtil;

import static br.com.unip.pimIV.hotelFazenda.ui.activity.Contantes.CHAVE_QUARTO;

/**
 * Classe da PagamentoActvity responsável pela tela de pagamento do pacote de hospedagem selecionado pelo usuário
 *
 * @author Agatha Monfredini de Paula Faria
 * @version 1.0.0
 */
public class PagamentoActvity extends AppCompatActivity {

    /**
     * Objeto quarto com as informações do quarto escolhido no pacote
     */
    private Quarto quarto;

    /**
     * Obejeto de UsuarioDao responsável pelo banco de dados dos usuários no aplicativo
     */
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    /**
     * Método responsável por criar/inicializar a PagamentoActvity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);
        setInformacoesDoQuarto();
        setClickBotaoFinaliza();
    }

    /**
     * Set click do botão para adiconar a compra no pacote de compras do usuário e realizar a função vaiParaCompraComcluidaActivity
     */
    private void setClickBotaoFinaliza() {
        Button btnFinaliza = findViewById(R.id.pagamento_btn_finaliza_compra);
        btnFinaliza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuarioDAO.adicionaCompra(quarto);
                vaiParaCompraComcluidaActivity();
            }
        });
    }

    /**
     * Exibe detalhes da compra ao usuário para realização do pagamento
     */

    private void setInformacoesDoQuarto() {
        TextView textViewPeriodo = findViewById(R.id.pagamento_textview_preco);
        Intent dadosRecebido = getIntent();
        if (dadosRecebido.hasExtra(CHAVE_QUARTO)) {
            quarto = (Quarto) dadosRecebido.getSerializableExtra(CHAVE_QUARTO);
            if (quarto.getPrecoTotal() != null) {
                String valorTotal = MoedaUtil.formataParaBrasileiro(quarto.getPrecoTotal());
                textViewPeriodo.setText(valorTotal);
            }
        }
    }

    /**
     * Vai para tela de CompraComcluidaActivity
     */
    private void vaiParaCompraComcluidaActivity() {
        Intent intent = new Intent(PagamentoActvity.this, CompraComcluida.class);
        intent.putExtra(CHAVE_QUARTO, quarto);
        startActivity(intent);
    }
}