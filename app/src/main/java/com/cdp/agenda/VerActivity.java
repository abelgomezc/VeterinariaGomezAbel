package com.cdp.agenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cdp.agenda.db.DbPaciente;
import com.cdp.agenda.entidades.Paciente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerActivity extends AppCompatActivity {

    EditText  txtraza,txtNombre, txtedad, txttamano,txtdatosmedicos;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;

  Paciente contacto;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre = findViewById(R.id.txtNombre);
        txtraza = findViewById(R.id.txtRazaed);
        txtedad= findViewById(R.id.txtedad1);
        txttamano= findViewById(R.id.txttamano);
        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);
        btnGuarda = findViewById(R.id.btnGuarda);
        btnGuarda.setVisibility(View.INVISIBLE);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DbPaciente dbContactos = new DbPaciente(VerActivity.this);
        contacto = dbContactos.verPaciente(id);

        if(contacto != null){


            txtraza.setText(contacto.getRaza());
            txtedad.setText(contacto.getEdad());
            txtNombre.setText(contacto.getNombre());
            txttamano.setText(contacto.getTamano());
            txtdatosmedicos.setText(contacto.getDatosmeditos());


            txtraza.setInputType(InputType.TYPE_NULL);
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtedad.setInputType(InputType.TYPE_NULL);
            txttamano.setInputType(InputType.TYPE_NULL);
            txtdatosmedicos.setInputType(InputType.TYPE_NULL);
        }

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerActivity.this, EditarActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);
                builder.setMessage("¿Desea eliminar este contacto?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbContactos.eliminarPaciente(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }

    private void lista(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}