package br.com.unip.pimIV.hotelFazenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import br.com.unip.pimIV.hotelFazenda.model.Quarto;
import br.com.unip.pimIV.hotelFazenda.ui.fragment.DetalhesDaCompraFragment;

import static br.com.unip.pimIV.hotelFazenda.ui.activity.Contantes.CHAVE_QUARTO;

/**
 * Classe CompraComcluida responsável pela tela de sucesso após o usuário concluir a compra
 *
 * @author Agatha Monfredini de Paula Faria
 * @version 1.0.0
 */

public class CompraComcluida extends AppCompatActivity {

    /**
     * Método responsável por criar/inicializar a CompraComcluida
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra_comcluida);

        Intent dadosRecebido = getIntent();

        if (dadosRecebido.hasExtra(CHAVE_QUARTO)) {
            Quarto quarto = (Quarto) dadosRecebido.getSerializableExtra(CHAVE_QUARTO);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //Manda dados da Intent
            DetalhesDaCompraFragment fragement = new DetalhesDaCompraFragment();
            Bundle argumentos = new Bundle();
            argumentos.putSerializable(CHAVE_QUARTO, quarto);
            fragement.setArguments(argumentos);

            fragmentTransaction.add(R.id.compra_comcluida_fragemrnt, fragement);
            fragmentTransaction.commit();
        }
    }

    /**
     * Sobrescrita do método onBackPressed para quando o botão de voltar for pressionado, o aplicativo volte para MainActivity apagando pilha de activitys
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}