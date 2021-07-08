package mx.unam.ingenieria.tienda.recyclerview;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.ArrayList;
import java.util.zip.Inflater;


import mx.unam.ingenieria.tienda.R;

public class AdaptadorMuestraProducto extends FirestoreRecyclerAdapter<MuestraProducto, AdaptadorMuestraProducto.ViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public AdaptadorMuestraProducto(@NonNull FirestoreRecyclerOptions<MuestraProducto> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull MuestraProducto producto) {
        holder.txtvCVTitulo.setText(producto.getTitulo());
        holder.txtvCVdescribe.setText(producto.getDescripcion());


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_producto,parent, false);
        return new ViewHolder(v);
    }

        /*
    ArrayList<MuestraProducto> productos;
    public AdaptadorMuestraProducto(ArrayList<MuestraProducto> productos){
        this.productos=productos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_producto,parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { //Para asociar todos los elmentos
        MuestraProducto producto = productos.get(position);
        holder.imgbCVproducto.setImageResource(producto.getImagen());
        holder.txtvCVTitulo.setText(producto.getTitulo());
        holder.txtvCVdescribe.setText(producto.getDescripcion());


    }

    @Override
    public int getItemCount() {
        return productos.size(); //Nos devolvera el total de lementos
    }

         */

    //Afuerzas debe estar anidad
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtvCVTitulo;
        TextView txtvCVdescribe;
        ImageButton imgbCVproducto;
        Button btnCVcoleccion;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            txtvCVTitulo = itemView.findViewById(R.id.txtvCVTitulo);
            txtvCVdescribe = itemView.findViewById(R.id.txtvCVdescribe);
            imgbCVproducto = itemView.findViewById(R.id.imgbCVproducto);
            btnCVcoleccion = itemView.findViewById(R.id.btnCVcoleccion);





        }
    }
}
