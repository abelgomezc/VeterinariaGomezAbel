package com.cdp.agenda.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cdp.agenda.R;
import com.cdp.agenda.VerActivity;
import com.cdp.agenda.entidades.Paciente;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaPacientesAdapter extends RecyclerView.Adapter<ListaPacientesAdapter.ContactoViewHolder> {

    ArrayList<Paciente> listaPacientess;
    ArrayList<Paciente> listaOriginal;




    public ListaPacientesAdapter(ArrayList<Paciente> listaPacientess) {
        this.listaPacientess = listaPacientess;
        listaOriginal = new ArrayList<>();
       listaOriginal.addAll(listaPacientess);
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_contacto, null, false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {


      holder.viewNraza.setText(listaPacientess.get(position).getRaza());

      holder.viewedad.setText(String.valueOf(listaPacientess.get(position).getEdad()));
      holder.viewNombre.setText(listaPacientess.get(position).getNombre());
      holder.viewtamano.setText(listaPacientess.get(position).getTamano());
      holder.viewdatosmedicos.setText(listaPacientess.get(position).getDatosmeditos());


    }

    public void filtrado(final String txtBuscar) {

        //fitrado
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaPacientess.clear();
            listaPacientess.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Paciente> collecion = listaPacientess.stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaPacientess.clear();
                listaPacientess.addAll(collecion);
            } else {
                for (Paciente c : listaOriginal) {
                    if (c.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaPacientess.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaPacientess.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {

        TextView  viewNraza, viewNombre, viewedad, viewtamano,viewdatosmedicos;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewNraza = itemView.findViewById(R.id.viewEdad);
            viewedad = itemView.findViewById(R.id.viewEdad);
            viewtamano = itemView.findViewById(R.id.viewtamano);
            viewdatosmedicos=itemView.findViewById(R.id.viewDatosmedicos);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaPacientess.get(getAdapterPosition()).getCondigo());
                    context.startActivity(intent);
                }
            });
        }
    }
}
