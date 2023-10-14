package com.venta.realidadaumentada.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.venta.realidadaumentada.R;
import com.venta.realidadaumentada.Sugerencia;

import java.util.List;

public class SugerenciaAdapter extends ArrayAdapter<Sugerencia> {

    private Context mContext;
    private List<Sugerencia> mSugerencias;

    public SugerenciaAdapter(Context context, List<Sugerencia> sugerencias) {
        super(context, 0, sugerencias);
        mContext = context;
        mSugerencias = sugerencias;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.cardview_sugerencia, parent, false);
        }

        Sugerencia currentSugerencia = mSugerencias.get(position);

        TextView textViewTitulo = listItem.findViewById(R.id.textViewTitulo);
        textViewTitulo.setText(currentSugerencia.getDescripcion());

        TextView textViewDescripcion = listItem.findViewById(R.id.textViewDescripcion);
        textViewDescripcion.setText(currentSugerencia.getNombre());

        return listItem;
    }
}
