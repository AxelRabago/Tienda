package mx.unam.ingenieria.tienda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {


    EditText edtUsuario;
    EditText edtNombreCompleto;
    EditText edtPassword;
    EditText edtPasswordConfirmar;
    Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edtUsuario= findViewById(R.id.edtNombreUsuarioRegistrar);
        edtNombreCompleto= findViewById(R.id.edtNombreCompletoRegistrar);
        edtPassword= findViewById(R.id.edtPasswordRegistrar);
        edtPasswordConfirmar= findViewById(R.id.edtPasswordConfirmaRegistrar);
        btnSalvar= findViewById(R.id.btnSalvarRegistro);
        btnSalvar.setOnClickListener(onClickListenerSalvar);
    }


    View.OnClickListener onClickListenerSalvar= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(edtUsuario.getText().toString().equals(""))
            {
                Toast.makeText(getApplicationContext(),"Debes ingresar un usuario",Toast.LENGTH_SHORT).show();
            }
            else if(edtPassword.getText().toString().equals(edtPasswordConfirmar.getText().toString())  )
            {
                Intent intLogin= new Intent(RegistroActivity.this,LoginActivity.class  );
                //intLogin.putExtra("usuario",edtUsuario.getText().toString());
                //intLogin.putExtra("password",edtPassword.getText().toString());
                //startActivity(intLogin);

                SharedPreferences preferences =getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                String usuario=edtUsuario.getText().toString();
                String pasword=edtPassword.getText().toString();
                editor.putString("user",usuario);
                editor.putString("pass",pasword);

                editor.commit();
                startActivity(intLogin);
                finish();
            }


        }
    };


}