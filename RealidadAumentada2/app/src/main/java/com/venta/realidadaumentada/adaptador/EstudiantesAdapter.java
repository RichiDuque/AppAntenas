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
import com.venta.realidadaumentada.Student;

import java.util.List;

public class EstudiantesAdapter extends ArrayAdapter<Student> {

    private Context mContext;
    private List<Student> mStudents;

    public EstudiantesAdapter(Context context, List<Student> students) {
        super(context, 0, students);
        mContext = context;
        mStudents = students;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.cardview_student, parent, false);
        }

        Student currentStudent = mStudents.get(position);

        TextView textViewNombre = listItem.findViewById(R.id.textViewNombre);
        textViewNombre.setText(currentStudent.getName());

        TextView textViewNotas = listItem.findViewById(R.id.textViewNotas);
        String notasFormatted = formatNotas(currentStudent.getNotas());
        textViewNotas.setText("Notas: \n" + notasFormatted);

        TextView textViewFinal = listItem.findViewById(R.id.textNotaFinal);
        textViewFinal.setText("Nota Final: "+calcularPromedio(currentStudent.getNotas()));

        return listItem;
    }
    private String formatNotas(List<String> notas) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < notas.size(); i++) {
            stringBuilder.append(notas.get(i));
            if (i < notas.size() - 1) {
                stringBuilder.append(",\n");
            }
        }
        return stringBuilder.toString();
    }

    private double calcularPromedio(List<String> notas) {
        if (notas.isEmpty()) {
            return 0.0;
        }

        double sum = 0;
        int count = 0;
        for (String nota : notas) {
            String[] parts = nota.split(":");
            if (parts.length == 2) {
                try {
                    sum += Double.parseDouble(parts[1].trim());
                    count++;
                } catch (NumberFormatException e) {
                    // Manejar la excepción si el formato no es correcto
                    e.printStackTrace();
                }
            }
        }
        if (count > 0) {
            return sum / count;
        } else {
            return 0.0;
        }
    }


}

