package mx.unam.ingenieria.tienda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import mx.unam.ingenieria.tienda.fragments.BuscarFragment;
import mx.unam.ingenieria.tienda.fragments.ComprarFragment;
import mx.unam.ingenieria.tienda.fragments.InicioFragment;
import mx.unam.ingenieria.tienda.fragments.MemoramaFragment;
import mx.unam.ingenieria.tienda.fragments.OperacionLargaFragment;
import mx.unam.ingenieria.tienda.fragments.SesionFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView btmNavigationPrincipal; //Para asociar al archivo

    //Declaracionde nuestros fragment
    private InicioFragment inicioFragment;
    private ComprarFragment comprarFragment;
    private BuscarFragment buscarFragment;
    private SesionFragment sesionFragment;
    private OperacionLargaFragment operacionLargaFragment;
    private MemoramaFragment memoramaFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Relacionar los botones

        btmNavigationPrincipal=findViewById(R.id.btmNavigationPrincipal);
        inicioFragment=new InicioFragment(); //Instanciamos los fragments
        buscarFragment=new BuscarFragment();
        sesionFragment= new SesionFragment();
        comprarFragment= new ComprarFragment();
        operacionLargaFragment = new OperacionLargaFragment();
        memoramaFragment = new MemoramaFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContenedor, inicioFragment).commit();  //Para SUSTIRUIR LA VISTA Y QUE EMPIECE CON INICIO


        btmNavigationPrincipal.setOnNavigationItemSelectedListener(navListener);

    }

    //Crearemos un menu para el juego
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_opciones_principal, menu);
        return true;
        //Asi se asociara el menu de opciones a nuestro activity
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuOpOperaciones:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContenedor, operacionLargaFragment).commit();
                break;

            case R.id.menuOpJuego:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContenedor, memoramaFragment).commit();
                break;

        }
        return true;
    }

    //Crearemos unos eventos, lammados listenes
    BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) { //Se pueso observar que se asociara un menu a traves de la expresion OnNavigationItemSelectedListener
            switch (item.getItemId()) { //Como ya esta cargado el menu solito se idendifica u lo asocia
                case R.id.nav_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContenedor, inicioFragment).commit();
                    break;

                case R.id.nav_buscar:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContenedor, buscarFragment).commit();
                    break;

                case R.id.nav_comprar:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContenedor, comprarFragment).commit();
                    break;

                case R.id.nav_inicia:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContenedor, sesionFragment).commit();
                    break;
            }
            return true;
        }
    };
}