package com.cdp.agenda;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cdp.agenda.db.DbPaciente;
import com.cdp.agenda.entidades.Paciente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarActivity extends AppCompatActivity {

//    EditText txtNombre, txtTelefono, txtCorreo;
EditText  txtraza,txtNombre, txtedad, txttamano,txtdatosmedicos;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;
    boolean correcto = false;
    Paciente contacto;
    int id = 0;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre = findViewById(R.id.txtNombre);
        txtraza = findViewById(R.id.txtRazaed);
        txtedad= findViewById(R.id.txtedad1);
        txttamano= findViewById(R.id.txttamano);
        txtdatosmedicos = findViewById(R.id.txtDatosMedicos);

        btnGuarda = findViewById(R.id.btnGuarda);
        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DbPaciente dbContactos = new DbPaciente(EditarActivity.this);
        contacto = dbContactos.verPaciente(id);

        if (contacto != null) {
//            txtNombre.setText(contacto.getNombre());
//            txtTelefono.setText(contacto.getTelefono());
//            txtCorreo.setText(contacto.getCorreo_electornico());
            txtraza.setText(contacto.getRaza());
            txtedad.setText(contacto.getEdad());
            txtNombre.setText(contacto.getNombre());
            txttamano.setText(contacto.getTamano());
            txtdatosmedicos.setText(contacto.getDatosmeditos());
        }

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtNombre.getText().toString().equals("") && !txtraza.getText().toString().equals("")) {
                    correcto = dbContactos.editarpaciente(id, txtNombre.getText().toString(),Integer.valueOf(txtedad.getText().toString()),txtraza.getText().toString(),txttamano.getText().toString(),txtdatosmedicos.getText().toString());

                    if(correcto){
                        Toast.makeText(EditarActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void verRegistro(){
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}