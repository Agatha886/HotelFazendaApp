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
import br.com.unip.pimIV.hotelFazenda.dao.UsuarioDAO;
import br.com.unip.pimIV.hotelFazenda.fragment.ContaDoUsuarioFragment;
import br.com.unip.pimIV.hotelFazenda.fragment.ListaDeComprasFragment;
import br.com.unip.pimIV.hotelFazenda.fragment.ListaQuartosFragment;
import br.com.unip.pimIV.hotelFazenda.fragment.QuandoItemClicado;
import br.com.unip.pimIV.hotelFazenda.fragment.SobreNosFragment;
import br.com.unip.pimIV.hotelFazenda.model.Quarto;
import br.com.unip.pimIV.hotelFazenda.model.Usuario;

import static br.com.unip.pimIV.hotelFazenda.dao.UsuarioDAO.usuario;
import static br.com.unip.pimIV.hotelFazenda.ui.activity.Contantes.CHAVE_POSICAO;
import static br.com.unip.pimIV.hotelFazenda.ui.activity.Contantes.CHAVE_QUARTO;
import static br.com.unip.pimIV.hotelFazenda.ui.activity.Contantes.CHAVE_USER;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setMensagemDeOla();
        setNavegacao();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    private void setMensagemDeOla() {
        String mensagemDeOla = "Olá," + " " + usuario.getNome();
        TextView campoMensagem = findViewById(R.id.main_textView_nome);
        campoMensagem.setText(mensagemDeOla);
    }

    private void setNavegacao() {
        BottomNavigationView bN = findViewById(R.id.bottom_navigation);
        bN.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));

        if (bN != null) {
            bN.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                Fragment fragmentSelecionado = null;

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int itemId = item.getItemId();
                    if (itemId == R.id.sobre_nos) {
                        fragmentSelecionado = new SobreNosFragment();

                    }
                    if (itemId == R.id.lista_quartos) {
                        fragmentSelecionado = new ListaQuartosFragment();
                        setCliqueItemQuarto();
                    }
                    if (itemId == R.id.conta_login) {
                        fragmentSelecionado = new ContaDoUsuarioFragment();
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

                private void setCliqueItemQuarto() {
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
    }

    private void vaiParaFormularioNotaActivityModifica(Quarto quarto, int posicao) {
        Intent abreFormularioComNota = new Intent(MainActivity.this, CadastroHospedagemActivity.class);
        abreFormularioComNota.putExtra(CHAVE_QUARTO, quarto);
        abreFormularioComNota.putExtra(CHAVE_POSICAO, posicao);
        startActivity(abreFormularioComNota);
    }
}