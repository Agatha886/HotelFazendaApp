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

public class PagamentoActvity extends AppCompatActivity {
 private Quarto quarto;
 private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);
        TextView textViewPeriodo = findViewById(R.id.pagamento_textview_preco);
        Intent dadosRecebido = getIntent();


        if(dadosRecebido.hasExtra(CHAVE_QUARTO)){
            quarto = (Quarto) dadosRecebido.getSerializableExtra(CHAVE_QUARTO);
            if(quarto.getPrecoTotal() != null){
            String valorTotal = MoedaUtil.formataParaBrasileiro(quarto.getPrecoTotal());
            textViewPeriodo.setText(valorTotal);
            }
        }

        Button btnFinaliza = findViewById(R.id.pagamento_btn_finaliza_compra);
        btnFinaliza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuarioDAO.adicionaCompra(quarto);
                vaiParaCompraComcluidaActivity();
            }
        });
    }

    private void vaiParaCompraComcluidaActivity() {
        Intent intent = new Intent(PagamentoActvity.this, CompraComcluida.class);
        intent.putExtra(CHAVE_QUARTO, quarto);
        startActivity(intent);
    }
}