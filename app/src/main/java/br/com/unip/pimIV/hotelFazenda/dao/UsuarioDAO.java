package br.com.unip.pimIV.hotelFazenda.dao;

import android.util.Log;

import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.unip.pimIV.hotelFazenda.model.Quarto;
import br.com.unip.pimIV.hotelFazenda.model.Usuario;
import br.com.unip.pimIV.hotelFazenda.util.MandaUsuario;

public class UsuarioDAO {

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    public static Usuario usuario;

    public void getUser(String id, MandaUsuario mandaUsuario) {
        DocumentReference usuarios = firebaseFirestore.collection("usuarios").document(id);
        usuarios.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                String nome = value.getString("nome");
                String cpf = value.getString("cpf");
                Usuario usuarioNovo = new Usuario("nome", "cpf", "12", "teste", "teste");
                mandaUsuario.sucesso(usuarioNovo);
            }
        });
    }

    public static List<Usuario> listaUsuarios = new ArrayList<Usuario>(Arrays.asList(new Usuario(
            "Agatha", "12588758895", "(12)995623589", "agatha@gmail.com", "123")));

    public void adicionaUsuario(Usuario user) {
        listaUsuarios.add(user);
    }

    public void adicionaCompra(Quarto quarto) {
        usuario.listaDeCompras.add(quarto);
    }

}
