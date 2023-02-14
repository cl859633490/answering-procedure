package com.example.titi.ui.notifications;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.titi.R;
import com.example.titi.User.login;
import com.example.titi.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {
    private SharedPreferences sprfMain;
    SharedPreferences.Editor editorMain;
    TextView textView5;
    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button button=root.findViewById(R.id.button);
        button.setOnClickListener(e->{
            Intent intent=new Intent();
            intent.setClass(getActivity(), setting.class);
            startActivity(intent);
        });

        ImageButton imageButton4=root.findViewById(R.id.imageButton4);
        imageButton4.setOnClickListener(e->{
            Intent intent=new Intent();
            intent.setClass(getActivity(), login.class);
            startActivity(intent);
        });

        Button button1=root.findViewById(R.id.button8);
        button1.setOnClickListener(e->{
            Intent intent=new Intent();
            intent.setClass(getActivity(), exercise_record.class);
            startActivity(intent);
        });

        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}