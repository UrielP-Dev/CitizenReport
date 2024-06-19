package com.si.citizenreport.BLogic;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import com.si.citizenreport.R;


public class FirstFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button userSettingsButton = view.findViewById(R.id.user_settings);
        Button reportHistoryButton = view.findViewById(R.id.report_history);
        Button notificationsButton = view.findViewById(R.id.notifications);
        Button supportButton = view.findViewById(R.id.support);
        Button logoutButton = view.findViewById(R.id.logout);

        userSettingsButton.setOnClickListener(v -> {

        });

        reportHistoryButton.setOnClickListener(v -> {
            // Maneja el clic en "Historial de reportes"
        });

        notificationsButton.setOnClickListener(v -> {
            // Maneja el clic en "Notificaciones"
            Intent intent = new Intent(getActivity(), NotificationSettingsActivity.class);
            startActivity(intent);
        });

        supportButton.setOnClickListener(v -> {
            // Maneja el clic en "Soporte"
            Intent intent = new Intent(getActivity(), UserDetailsActivity.class);
            startActivity(intent);
        });

        logoutButton.setOnClickListener(v -> {
            // Maneja el clic en "Cerrar Sesión"
            logout();
        });
    }
    private void logout() {
        // Borrar los datos de sesión
        SharedPreferences preferences = getActivity().getSharedPreferences("user_session", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();

        // Redirigir a la pantalla de inicio de sesión
        Intent intent = new Intent(getActivity(), LoginScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}