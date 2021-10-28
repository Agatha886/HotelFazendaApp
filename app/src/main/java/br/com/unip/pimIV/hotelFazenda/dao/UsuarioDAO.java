
package br.com.unip.pimIV.hotelFazenda.dao;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.unip.pimIV.hotelFazenda.model.Quarto;
import br.com.unip.pimIV.hotelFazenda.model.Usuario;

public class UsuarioDAO {

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private static MutableLiveData<Usuario> liveDataUsuario = new MutableLiveData();
    public static LiveData<Usuario> usuarioLiveDate = liveDataUsuario;

    public void getUser(String id) {
        DocumentReference usuarios = firebaseFirestore.collection("usuarios").document(id);
        usuarios.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                Usuario usuarioFirebase = value.toObject(Usuario.class);
                Log.i("TAG", "onEvent No Event:" + usuarioFirebase.getNome());
                liveDataUsuario.setValue(usuarioFirebase);
            }
        });
    }

    public static List<Usuario> listaUsuarios = new ArrayList<Usuario>(Arrays.asList(new Usuario(
            "Agatha", "12588758895", "(12)995623589", "agatha@gmail.com", "123")));

    public void adicionaUsuario(String id, String cpf, String email, String nome, String telefone, OnFailureListener onFailureListener, OnSuccessListener onSuccessListener) {
        DocumentReference usuarios = firebaseFirestore.collection("usuarios").document(id);
        Map<String, Object> user = new HashMap<>();
        user.put("nome",nome );
        user.put("cpf", cpf);
        user.put("email", email);
        user.put("telefone", telefone);
        Task<Void> set = usuarios.set(user);

        set.addOnSuccessListener(onSuccessListener);
        set.addOnFailureListener(onFailureListener);

    }

    public LiveData<List<Quarto>> getListaCompra(OnFailureListener onFailureListener){

        MutableLiveData<List<Quarto>> quartos = new MutableLiveData();

        DocumentReference document = firebaseFirestore.collection("usuarios").document("listaCompras");
        Task<DocumentSnapshot> documentSnapshotTask = document.get();
        documentSnapshotTask.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.i("TAG", "onSuccess:" + documentSnapshot);
            }
        });

        documentSnapshotTask.addOnFailureListener(onFailureListener);
        return quartos;
    }

    public void adicionaCompra(Quarto quarto) {
//        usuario.listaDeCompras.add(quarto);
    }

}
