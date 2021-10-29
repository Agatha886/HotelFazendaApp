package br.com.unip.pimIV.hotelFazenda.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import br.com.unip.pimIV.hotelFazenda.dao.UsuarioDAO;
import br.com.unip.pimIV.hotelFazenda.model.Quarto;
import br.com.unip.pimIV.hotelFazenda.model.QuartoDocumento;

public class ListaDeComprasViewModel extends ViewModel {

    private final FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public LiveData<List<Quarto>> getCompras(OnFailureListener onFailureListener){
        Task<QuerySnapshot> compras = firestore.collection("compras").get();
        compras.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot document: documents) {
                   try {
                       String idUser = (String) document.get("idUser");
                       if(idUser.equals("jzk475iy0UYTbXWnBsBMqncW0M72")){
                           String quarto = (String) document.get("quarto");
                           Log.i("TAG", "onSuccess:" + quarto);
                       }
                   }catch (Exception e){
                       Log.e("TAG", "onSuccess: " , e);
                   }
                }
            }
        });
        compras.addOnFailureListener(onFailureListener);
        return usuarioDAO.getListaCompra(onFailureListener);
    }


}
