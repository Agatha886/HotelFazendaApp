package br.com.unip.pimIV.hotelFazenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import br.com.unip.pimIV.hotelFazenda.R;
import br.com.unip.pimIV.hotelFazenda.model.Quarto;
import br.com.unip.pimIV.hotelFazenda.ui.fragment.ContaDoUsuarioFragment;
import br.com.unip.pimIV.hotelFazenda.ui.fragment.ListaDeComprasFragment;
import br.com.unip.pimIV.hotelFazenda.ui.fragment.ListaQuartosFragment;
import br.com.unip.pimIV.hotelFazenda.ui.fragment.QuandoItemClicado;
import br.com.unip.pimIV.hotelFazenda.ui.fragment.SobreNosFragment;

import static br.com.unip.pimIV.hotelFazenda.dao.UsuarioDAO.usuario;
import static br.com.unip.pimIV.hotelFazenda.ui.activity.Contantes.CHAVE_POSICAO;
import static br.com.unip.pimIV.hotelFazenda.ui.activity.Contantes.CHAVE_QUARTO;
import static br.com.unip.pimIV.hotelFazenda.ui.activity.Contantes.CHAVE_USER;


/**
 * Classe MainActivity, que representa a tela do menu principal do aplicativo.
 *
 * @version 1.0.0
 * @author Agatha Monfredini de Paula Faria
 */

public class MainActivity extends AppCompatActivity {

    /**
     * Método responsável por criar/inicializar a MainActivity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setMensagemDeOla();
        setNavegacao();
    }

    /**
     * Sobrescrita do método onBackPressed para quando o botão de voltar for pressionado, o aplicativo volte para tela de Login apagando pilha de activitys
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }


    /**
     * Preenche mensagem de boas vindas do menu principal com o nome do usuário
     */
    private void setMensagemDeOla() {
        String mensagemDeOla = "Olá," + " " + usuario.getNome();
        TextView campoMensagem = findViewById(R.id.main_textView_nome);
        campoMensagem.setText(mensagemDeOla);
    }

    /**
     * Realiza toda a configuração de navegação dos Fragments da tela de menu,para que quando um usuário clicar em um item específico do menu aparece cada Fragement correspondente
     */

    private void setNavegacao() {
        BottomNavigationView bN = findViewById(R.id.bottom_navigation);
        bN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));

        bN.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            Fragment fragmentSelecionado = null;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.sobre_nos) {
                    fragmentSelecionado = new SobreNosFragment();
                    setTitle("Sobre Nós");
                }
                if (itemId == R.id.lista_quartos) {
                    fragmentSelecionado = new ListaQuartosFragment();
                    setTitle("Lista De Quartos");
                    setClickItemQuarto();
                }
                if (itemId == R.id.conta_login) {
                    fragmentSelecionado = new ContaDoUsuarioFragment();
                    setTitle("Minha Conta");
                }

                if (itemId == R.id.minhas_compras) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(CHAVE_USER, usuario);
                    fragmentSelecionado = new ListaDeComprasFragment();
                    fragmentSelecionado.setArguments(bundle);
                }

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.activity_main_container, fragmentSelecionado)
                        .commit();
                return true;
            }

            private void setClickItemQuarto() {
                ListaQuartosFragment fragment = (ListaQuartosFragment) fragmentSelecionado;
                fragment.setQuandoItemClicado(new QuandoItemClicado() {
                    @Override
                    public void quandoClicado(Quarto quarto, int posicao) {
                        vaiParaFormularioNotaActivityModifica(quarto, posicao);
                    }
                });
            }
        });
    }

    /**
     * Realiza a configuração do evento de click de cada item da lista de quartos do fragment ListaQuartosFragment.
     * Para quando o item for da lista for clicado o usuário seja direcionado para tela de CadastroHospedagemActivity
     * @param quarto
     * @param posicao
     */

    private void vaiParaFormularioNotaActivityModifica(Quarto quarto, int posicao) {
        Intent abreFormularioComNota = new Intent(MainActivity.this, CadastroHospedagemActivity.class);
        abreFormularioComNota.putExtra(CHAVE_QUARTO, quarto);
        abreFormularioComNota.putExtra(CHAVE_POSICAO, posicao);
        startActivity(abreFormularioComNota);
    }
}