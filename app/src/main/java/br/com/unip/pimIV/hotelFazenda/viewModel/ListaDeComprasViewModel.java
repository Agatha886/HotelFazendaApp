package br.com.unip.pimIV.hotelFazenda.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import br.com.unip.pimIV.hotelFazenda.dao.UsuarioDAO;
import br.com.unip.pimIV.hotelFazenda.model.Quarto;

public class ListaDeComprasViewModel extends ViewModel {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public LiveData<List<Quarto>> getCompras(OnFailureListener onFailureListener){
        return usuarioDAO.getListaCompra(onFailureListener);
    }


}
