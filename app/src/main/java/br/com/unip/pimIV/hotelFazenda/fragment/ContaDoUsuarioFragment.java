package br.com.unip.pimIV.hotelFazenda.fragment;

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

import static br.com.unip.pimIV.hotelFazenda.ui.activity.Contantes.CHAVE_USER;

public class ContaDoUsuarioFragment extends Fragment {

    private Usuario usuario;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.conta_usuario, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        usuario = (Usuario) getArguments().getSerializable(CHAVE_USER);
        if(usuario != null){
            setCampoNome(usuario);
            setCampoCpf(usuario);
            setCampoEmail(usuario);
            setCampoTelefone(usuario);
        }
    }

    private void setCampoTelefone(Usuario usuario) {
        TextView campoTelefone = getActivity().findViewById(R.id.conta_text_telefone);
        campoTelefone.setText(usuario.getTelefone());
    }

    private void setCampoEmail(Usuario usuario) {
        TextView campoEmail = getActivity().findViewById(R.id.conta_text_email);
        campoEmail.setText(usuario.getEmail());
    }

    private void setCampoCpf(Usuario usuario) {
        TextView campoCpf = getActivity().findViewById(R.id.conta_text_cpf);
        campoCpf.setText(usuario.getCpf());
    }

    private void setCampoNome(Usuario usuario) {
        TextView campoNome = getActivity().findViewById(R.id.conta_text_nome);
        campoNome.setText(usuario.getNome());
    }
}
