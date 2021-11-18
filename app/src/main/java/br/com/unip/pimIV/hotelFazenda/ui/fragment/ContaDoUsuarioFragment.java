package br.com.unip.pimIV.hotelFazenda.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import br.com.unip.pimIV.hotelFazenda.R;
import br.com.unip.pimIV.hotelFazenda.model.Usuario;

import static br.com.unip.pimIV.hotelFazenda.dao.UsuarioDAO.usuario;

/**
 * Classe da ContaDoUsuarioFragment responsável pela tela de exibição das informações do usuário no menu principal
 */
public class ContaDoUsuarioFragment extends Fragment {

    /**
     * Cria componente View
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.conta_usuario, container, false);
    }

    /**
     * Realiza configurações dos campos com a View já criada
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCampoNome(usuario);
        setCampoCpf(usuario);
        setCampoEmail(usuario);
        setCampoTelefone(usuario);

    }

    /**
     * Configura campo para exibir o telefone do usuário
     *
     * @param usuario
     */
    private void setCampoTelefone(Usuario usuario) {
        TextView campoTelefone = getActivity().findViewById(R.id.conta_text_telefone);
        campoTelefone.setText(usuario.getTelefone());
    }

    /**
     * Configura campo para exibir o e-mail do usuário
     *
     * @param usuario
     */
    private void setCampoEmail(Usuario usuario) {
        TextView campoEmail = getActivity().findViewById(R.id.conta_text_email);
        campoEmail.setText(usuario.getEmail());
    }

    /**
     * Configura campo para exibir o CPF do usuário
     * @param usuario
     */
    private void setCampoCpf(Usuario usuario) {
        TextView campoCpf = getActivity().findViewById(R.id.conta_text_cpf);
        campoCpf.setText(usuario.getCpf());
    }

    /**
     * Configura campo para exibir o nome do usuário
     * @param usuario
     */
    private void setCampoNome(Usuario usuario) {
        TextView campoNome = getActivity().findViewById(R.id.conta_text_nome);
        campoNome.setText(usuario.getNome());
    }
}
