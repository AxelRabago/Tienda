package mx.unam.ingenieria.tienda.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import mx.unam.ingenieria.tienda.R;

public class MemoramaFragment extends Fragment {

    private ImageView [][] matImagenes=new ImageView[4][4]; //Para declarar una matriz inicializada de 4x4
    private boolean activa=true; //Para validar sis e puede dar click
    private int nclick; //Contador de click en una carta
    private int primerax, primeray; //Coordenada de la carta seleccionada
    private int[][] tablero ={ //Matriz de indices
            {1,1,2,2},
            {3,3,4,4},
            {5,5,6,6},
            {7,7,8,8}
    };

    private Handler manejador = new Handler(Looper.myLooper()); //Un metodo que se encarga de verificar quien es el hiloo principal

    //Validar todas las cartas destapadas
    private boolean destapada[][] =new boolean[4][4]; //Por default todas son false y debemos poner la libreria OS que funciona con el sistema opertivo

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_memorama, container, false);

        matImagenes[0][0]=v.findViewById(R.id.imageView1);
        matImagenes[0][1]=v.findViewById(R.id.imageView2);
        matImagenes[0][2]=v.findViewById(R.id.imageView3);
        matImagenes[0][3]=v.findViewById(R.id.imageView4);

        matImagenes[1][0]=v.findViewById(R.id.imageView5);
        matImagenes[1][1]=v.findViewById(R.id.imageView6);
        matImagenes[1][2]=v.findViewById(R.id.imageView7);
        matImagenes[1][3]=v.findViewById(R.id.imageView8);

        matImagenes[2][0]=v.findViewById(R.id.imageView9);
        matImagenes[2][1]=v.findViewById(R.id.imageView10);
        matImagenes[2][2]=v.findViewById(R.id.imageView11);
        matImagenes[2][3]=v.findViewById(R.id.imageView12);

        matImagenes[3][0]=v.findViewById(R.id.imageView13);
        matImagenes[3][1]=v.findViewById(R.id.imageView14);
        matImagenes[3][2]=v.findViewById(R.id.imageView15);
        matImagenes[3][3]=v.findViewById(R.id.imageView16);

        oculto();

        matImagenes[0][0].setOnClickListener(onClick);
        matImagenes[0][1].setOnClickListener(onClick);
        matImagenes[0][2].setOnClickListener(onClick);
        matImagenes[0][3].setOnClickListener(onClick);
        matImagenes[1][0].setOnClickListener(onClick);
        matImagenes[1][1].setOnClickListener(onClick);
        matImagenes[1][2].setOnClickListener(onClick);
        matImagenes[1][3].setOnClickListener(onClick);
        matImagenes[2][0].setOnClickListener(onClick);
        matImagenes[2][1].setOnClickListener(onClick);
        matImagenes[2][2].setOnClickListener(onClick);
        matImagenes[2][3].setOnClickListener(onClick);
        matImagenes[3][0].setOnClickListener(onClick);
        matImagenes[3][1].setOnClickListener(onClick);
        matImagenes[3][2].setOnClickListener(onClick);
        matImagenes[3][3].setOnClickListener(onClick);

        revolverCartas();
        return v;
    }


    public void  oculto(){
        for(int i=0; i<4; i++){
            for ( int j=0; j<4; j++){
                matImagenes[i][j].setImageResource(R.drawable.oculto);
            }
        }


    }

    private void revolverCartas() {
        for (int i=0; i<100; i++){
                int x1=(int)(Math.random()*4); //Como nos da un valor entre 0 y 1 al multiplicarlo x4 nos ayudara
                int y1=(int)(Math.random()*4);
                int x2=(int)(Math.random()*4);
                int y2=(int)(Math.random()*4);
                int aux = tablero[x1][y1];
                tablero[x1][y1]=tablero[x2][y2];
                tablero[x2][y2]=aux;

        }
    }

    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) { //V es la carta que seleccionamos

            if (activa){
                nclick++;
                if (nclick==2){

                    if (v==matImagenes[primerax][primeray]){
                        nclick--;
                        return;

                    }
                }
                verificarCartaSeleccionada(v);
                if(nclick==2){
                    congelar3Seg();
                }

            }
        }
    };

    private void congelar3Seg() {
        nclick=0;
        activa=false;
        primeray=-1;
        primerax=-1;

        manejador.postDelayed(new Runnable() {
            @Override
            public void run() {
                activa=true;
                for(int i=0; i<4; i++){
                    for (int j=0; j<4; j++) {
                    if(destapada[i][j]) {
                        destapar(matImagenes[i][j], tablero[i][j]);
                        }
                    else{
                        matImagenes[i][j].setImageResource(R.drawable.oculto);
                    }
                    }
                }



            }
        },2000); //Para que espere 3 seg

    }

    private void verificarCartaSeleccionada(View v) {
        for(int i=0; i<4; i++){
            for (int j=0; j<4; j++){
                if (matImagenes[i][j]==v){ //Para recorrer las imagenes y ver si a carta corresponde
                    destapar(matImagenes[i][j], tablero[i][j]); //Se destapa la carta seleccionada
                    if (nclick==1){ //Si es la primera vez que se selecciona la carta
                        primerax=i;
                        primeray=j;
                        return;

                    } else {
                        if (tablero[i][j] == tablero [primerax][primeray]) { //i es que las 2 cartas son iguales
                            destapada[i][j]=true; //Para destapar la primera carta
                            destapada[primerax][primeray]=true; //La segunda carta
                            verificarSiGana();
                            nclick=0;
                            return;
                        }
                    }

                }
            }
        }
    }

    private void verificarSiGana() {
        int destapadas=0;
        for(int i=0; i<4; i++){
            for (int j=0; j<4; j++) {
                if(destapada[i][j]){
                    destapadas++; //Para contar las cartas destapadas
                }
            }
        }
        if (destapadas==16){
            congelar3Seg();
            revolverCartas();
            oculto();
        }

    }

    private void destapar(ImageView imageView, int tablero) {
        switch (tablero){
            case 1:
                imageView.setImageResource(R.drawable.imagen1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.imagen2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.imagen3);
                break;
            case 4:
                imageView.setImageResource(R.drawable.imagen4);
                break;
            case 5:
                imageView.setImageResource(R.drawable.imagen5);
                break;
            case 6:
                imageView.setImageResource(R.drawable.imagen6);
                break;
            case 7:
                imageView.setImageResource(R.drawable.imagen7);
                break;
            case 8:
                imageView.setImageResource(R.drawable.imagen8);
                break;

        }

    }


}
