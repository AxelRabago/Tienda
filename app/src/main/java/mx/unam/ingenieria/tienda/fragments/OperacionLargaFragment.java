package mx.unam.ingenieria.tienda.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ThemedSpinnerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import mx.unam.ingenieria.tienda.MainActivity;
import mx.unam.ingenieria.tienda.R;

public class OperacionLargaFragment extends Fragment { //Siempre es imporante importar

    //Declaramos items
    Button btnContar;
    TextView txtvContador;
    long sum=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_operacion_larga,container,false);
        btnContar = v.findViewById(R.id.brnContar);
        txtvContador = v.findViewById(R.id.txtvContador);
        btnContar.setOnClickListener(onClick);

        return v;
    }


    //Asignamos la duncion on click al botton
    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            txtvContador.setText("Espere un momento...");
            //Thread hilo = new Thread(new Runnable);
            //hilo.start();

            OperacionLarga();
        }
    };



    //Metodo para contar
    private void OperacionLarga(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (long i=0; i<= 1_000_000_000; i++) { //Contador a mil millones
                    sum=sum+1;

                }
                //Log.d("Resultado", "Total: " +sum);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txtvContador.setText(String.valueOf(sum));

                    }
                });

            }
        }).start();


    }

/*
    @Override
    public void run() {
        for (long i=0; i<= 1_000_000_000; i++) { //Contador a mil millones
            sum=sum+1;
        }
        //Log.d("Resultado", "Total: " +sum);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtvContador.setText(String.valueOf(sum));
            }
        });
    }

 */
}
