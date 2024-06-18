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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_second);

        recyclerView = recyclerView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        notificationList = new ArrayList<>();
        // Añadir datos de ejemplo
        notificationList.add(new Notification("Estado de reporte:", "Su reporte ha sido revisado con éxito", "hace 5 minutos"));
        notificationList.add(new Notification("Estado de reporte:", "Su reporte ha sido enviado con éxito", "hace 10 minutos"));
        notificationList.add(new Notification("Estado de reporte:", "Su reporte está siendo revisado", "hace 15 minutos"));

        adapter = new NotificationAdapter(notificationList);
        recyclerView.setAdapter(adapter);
    }
}