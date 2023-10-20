package com.venta.realidadaumentada.adaptador;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.venta.realidadaumentada.CodigoClase;
import com.venta.realidadaumentada.EstudiantesActivity;
import com.venta.realidadaumentada.R;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CodigoAdapter extends ArrayAdapter<CodigoClase> {

    private Context mContext;
    private List<CodigoClase> mCodigo;
    private OkHttpClient client;
    private OnCodigoDeletedListener onCodigoDeletedListener;

    public CodigoAdapter(Context context, List<CodigoClase> codigoClases) {
        super(context, 0, codigoClases);
        mContext = context;
        mCodigo = codigoClases;
        client = new OkHttpClient();
    }

    public interface OnCodigoDeletedListener {
        void onCodigoDeleted();
    }

    public void setOnCodigoDeletedListener(OnCodigoDeletedListener listener) {
        this.onCodigoDeletedListener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.cardview_codigo, parent, false);
        }

        CodigoClase currentCodigo = mCodigo.get(position);

        TextView textViewCodigo = listItem.findViewById(R.id.textViewNumber);
        textViewCodigo.setText(String.valueOf(currentCodigo.getCodigoClase()));

        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigoSeleccionado = String.valueOf(currentCodigo.getCodigoClase());
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Opciones")
                        .setItems(new CharSequence[]{"Visualizar","Eliminar"}, (dialog, which) -> {
                            if (which == 0) {
                                // Lógica para eliminar el código seleccionado
                                visualizarCodigo(codigoSeleccionado);
                            } else if (which == 1) {
                                // Lógica para visualizar el código seleccionado
                                mostrarConfirmacionEliminacion(codigoSeleccionado);
                            }
                        });
                builder.create().show();
            }
        });

        return listItem;
    }

    private void visualizarCodigo(String codigo) {
        Intent intent = new Intent(getContext(), EstudiantesActivity.class);
        intent.putExtra("codigo_clase", codigo);
        getContext().startActivity(intent);
    }

    private void mostrarConfirmacionEliminacion(String codigo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Estás seguro de que quieres eliminar este código?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                eliminarCodigo(codigo);
            }
        });
        builder.setNegativeButton("No", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void eliminarCodigo(String codigo) {
        // Lógica para eliminar los estudiantes asociados al código seleccionado
        Request requestDeleteStudents = new Request.Builder()
                .url("https://apiantennascrud-production.up.railway.app/deleteStudents/" + codigo)
                .delete()
                .build();

        client.newCall(requestDeleteStudents).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    // Lógica para eliminar el código seleccionado después de eliminar los estudiantes
                    Log.d("RESPONSE_DATA_estudiante", response.body().string());
                    eliminarCodigoClase(codigo);
                } else {
                    // Manejo de error
                    Log.d("RESPONSE_DATA_estudiante", response.body().string());
                }
            }
        });
    }

    private void eliminarCodigoClase(String codigo) {
        // Lógica para eliminar el código seleccionado
        Request requestDeleteCode = new Request.Builder()
                .url("https://apiantennascrud-production.up.railway.app/deleteCode/" + codigo)
                .delete()
                .build();

        client.newCall(requestDeleteCode).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.d("RESPONSE_DATA", response.body().string());
                    if (onCodigoDeletedListener != null) {
                        if (mContext != null) {
                            ((Activity) mContext).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(mContext, "Código eliminado exitosamente", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        onCodigoDeletedListener.onCodigoDeleted();
                    }
                } else {
                    // Manejo de error
                    Log.d("RESPONSE_DATA", response.body().string());
                }
            }
        });
    }

}
