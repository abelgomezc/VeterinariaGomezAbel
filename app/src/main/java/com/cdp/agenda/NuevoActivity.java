package com.cdp.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cdp.agenda.db.DbPaciente;

public class NuevoActivity extends AppCompatActivity {

    EditText  txtraza,txtNombre, txtedad, txttamano,txtdatosmedicos;
    Button btnGuarda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtNombre = findViewById(R.id.txtNombre);
        txtraza = findViewById(R.id.txtRazaed);
        txtedad= findViewById(R.id.txtedad1);
        txttamano= findViewById(R.id.txttamano);
        txtdatosmedicos = findViewById(R.id.txtDatosMedicos);
        btnGuarda = findViewById(R.id.btnGuarda);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!txtNombre.getText().toString().equals("") && !txttamano.getText().toString().equals("")) {

                    DbPaciente dbPaciente = new DbPaciente(NuevoActivity.this);
                    long id = dbPaciente.insertarContacto( txtNombre.getText().toString(),Integer.valueOf(txtedad.getText().toString()),txtraza.getText().toString(),txttamano.getText().toString(),txtdatosmedicos.getText().toString());

                    if (id > 0) {
                        Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(NuevoActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void limpiar() {
        txtNombre.setText("");
        txtraza.setText("");
        txtedad.setText("");
        txttamano.setText("");
        txtdatosmedicos.setText("");

    }
}