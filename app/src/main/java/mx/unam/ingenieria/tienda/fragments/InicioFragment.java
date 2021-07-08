package mx.unam.ingenieria.tienda.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import mx.unam.ingenieria.tienda.R;

public class InicioFragment extends Fragment {
    private static final String FILE_NAME = "archivo.txt";
    private TextView txtvProducto;

    //Para dar de alra la lista que creamos

    private ListView lstProductos;
    private String [] productos = { //Una arreglo de strings
            "Zapatos",
            "WebCams",
            "Mochila",
            "SmartWatch",
            "Zapatos",
            "WebCams",
            "Mochila",
            "SmartWatch",
            "Zapatos",
            "WebCams",
            "Mochila",
            "SmartWatch",
            "Zapatos",
            "WebCams",
            "Mochila",
            "SmartWatch",
            "Zapatos",
            "WebCams",
            "Mochila",
            "SmartWatch"

    };

    private String productoSelect;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_inicio, container, false); //Para que infle la vista

        lstProductos= v.findViewById(R.id.lstProductos);
        txtvProducto=v.findViewById(R.id.txtbArchivo);
        RellenarLista();
        lstProductos.setOnItemClickListener(onClickItem);
        registerForContextMenu(lstProductos);
        return v;
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_contextual_archivos,menu); //Para manipular el menu
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        productoSelect=lstProductos.getItemAtPosition(info.position).toString();

        switch (item.getItemId()){
            case R.id.menContSalvar:
                //Toast.makeText(getContext(), "Salvando archivo", Toast.LENGTH_LONG).show();
                Salvar();
                break;

            case R.id.menConCargar:
                //Toast.makeText(getContext(), "Cargar archivo", Toast.LENGTH_LONG).show();
                Cargar();
                break;

        }



        return super.onContextItemSelected(item);
    }

    private void Cargar() {
        FileInputStream fis = null;
        try {
            fis=getActivity().openFileInput(FILE_NAME);
            InputStreamReader isr=new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr); //Para que guarde la info en el cache
            StringBuilder sb= new StringBuilder(); //Para tranformar los bytes  a cadena
            String texto;
            while ((texto=br.readLine())!=null){
                sb.append(texto).append("\n");
            }
            txtvProducto.setText(sb.toString());






        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Salvar() {
        FileOutputStream fos = null; //Para tener un mejor control en el flujo de datos
        //Recordar que siempre debemos poner excepciones cuando manejemos archivos
        try {
            fos=getActivity().openFileOutput(FILE_NAME, Context.MODE_PRIVATE); //Para abrir un archivo de esta activity en modo privado para que solo la app lo use
            try {
                fos.write(productoSelect.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(getContext(),"Salvando el archivo en " +
                    getActivity().getFilesDir() + "/" + FILE_NAME,Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Metodo para rellenar la lista
    private void RellenarLista(){


        ArrayAdapter miAdapter = new ArrayAdapter(getContext(), //Es muy forzozo crear uno de estos
                android.R.layout.simple_list_item_1, productos //Para determinar la manera en como se puede comportar nuestra lista
        ); //Es un adaptador el cual nos ayudara a asociar los elementos del arreglo a nuestro arrayList

        lstProductos.setAdapter(miAdapter); //Para asociar a nuestra ListView un arreglo

    }

    AdapterView.OnItemClickListener onClickItem = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getContext(),"Mostrando "+productos[position],Toast.LENGTH_LONG).show();
        }
    };


}
