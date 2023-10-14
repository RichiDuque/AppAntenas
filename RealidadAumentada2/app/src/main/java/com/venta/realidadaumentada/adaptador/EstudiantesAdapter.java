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
        textViewNotas.setText("Notas: " + currentStudent.getNotas().toString());

        return listItem;
    }
}

