package mx.unam.ingenieria.tienda.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

import mx.unam.ingenieria.tienda.R;
import mx.unam.ingenieria.tienda.recyclerview.AdaptadorMuestraProducto;
import mx.unam.ingenieria.tienda.recyclerview.MuestraProducto;

public class BuscarFragment extends Fragment {
    RecyclerView rcvProductos;
    private  AdaptadorMuestraProducto mAdapter;
    private FirebaseFirestore mFirestore; //Es el objeto o instancia que manipula la base de datos


    //private ArrayList<MuestraProducto> productos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_buscar, container, false);

        rcvProductos=v.findViewById(R.id.rcvProductos);
        mFirestore = FirebaseFirestore.getInstance(); //Abre la conexion a la base de datos


        LinearLayoutManager llm= new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rcvProductos.setLayoutManager(llm);
        //InicializarMuestraProductos();
        InicializarAdaptador();

        return v;
    }


    private void InicializarAdaptador() {
        mAdapter = new AdaptadorMuestraProducto(Consulta());
        rcvProductos.setAdapter(mAdapter);
        //AdaptadorMuestraProducto adaptador = new AdaptadorMuestraProducto(productos);
        //rcvProductos.setAdapter(adaptador);

    }

    private FirestoreRecyclerOptions Consulta() {
        Query query = mFirestore.collection("Producto");
        FirestoreRecyclerOptions<MuestraProducto> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<MuestraProducto>()
                .setQuery(query,MuestraProducto.class).build();
        return firestoreRecyclerOptions;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    /*
    private void InicializarMuestraProductos() {
        productos=new ArrayList<>();
        productos.add(new MuestraProducto("Estrena",R.drawable.gamerpc, "Juega con la mejor potecia"));
        productos.add(new MuestraProducto("Comodidad",R.drawable.silla, "Juega con la mejor comodida"));
        productos.add(new MuestraProducto("Observa",R.drawable.monitor, "Juega en 4k"));
    }

     */


}
