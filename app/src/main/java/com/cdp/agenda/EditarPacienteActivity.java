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

public class EditarPacienteActivity extends AppCompatActivity {

    EditText  txtraza,txtNombre, txtedad, txttamano,txtdatosmedicos;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;
    boolean correcto = false;
    Paciente paciente;
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

        final DbPaciente dbPacientes = new DbPaciente(EditarPacienteActivity.this);
        paciente = dbPacientes.verPaciente(id);

        if (paciente != null) {
            txtNombre.setText(paciente.getNombre());
            txtraza.setText(paciente.getRaza());
            txtedad.setText(paciente.getEdad());
            txttamano.setText(paciente.getTamano());
            txtdatosmedicos.setText(paciente.getDatosmeditos());

        }

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtNombre.getText().toString().equals("") && !txtraza.getText().toString().equals("")) {
                    correcto = dbPacientes.editarpaciente(id, txtNombre.getText().toString(),Integer.valueOf(txtedad.getText().toString()),txtraza.getText().toString(),txttamano.getText().toString(),txtdatosmedicos.getText().toString() );

                    if(correcto){
                        Toast.makeText(EditarPacienteActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarPacienteActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarPacienteActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
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