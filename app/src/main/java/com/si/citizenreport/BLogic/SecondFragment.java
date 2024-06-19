package com.si.citizenreport.BLogic;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import com.si.citizenreport.R;



public class SecondFragment extends Fragment {


    private RecyclerView recyclerView;
    private NotificationAdapter adapter;
    private List<Notification> notificationList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        notificationList = new ArrayList<>();
        // Añadir datos de ejemplo
        notificationList.add(new Notification("Estado de reporte:", "Su reporte ha sido revisado con éxito", "hace 5 minutos"));
        notificationList.add(new Notification("Estado de reporte:", "Su reporte ha sido enviado con éxito", "hace 10 minutos"));
        notificationList.add(new Notification("Estado de reporte:", "Su reporte está siendo revisado", "hace 15 minutos"));

        adapter = new NotificationAdapter(notificationList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}