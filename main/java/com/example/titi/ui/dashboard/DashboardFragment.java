package com.example.titi.ui.dashboard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.titi.R;
import com.example.titi.databinding.FragmentDashboardBinding;
import com.example.titi.ui.ques_makesi;

public class DashboardFragment extends Fragment {
    Button button4 ,button5 ,button6 ,button7;
    private FragmentDashboardBinding binding;

    @SuppressLint("RestrictedApi")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
       button4 =root.findViewById(R.id.button4);
         button5 =root.findViewById(R.id.button5);
         button6 =root.findViewById(R.id.button6);
        button7 =root.findViewById(R.id.button7);
        setlisten();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void setlisten() {
      button4.setOnClickListener(e->{
           Intent intent=new Intent();
           intent.setClass(getActivity(), ques_makesi.class);
           startActivity(intent);
       });
        button5.setOnClickListener(e->{
            Intent intent=new Intent();
            intent.setClass(getActivity(), ques_makesi.class);
            startActivity(intent);
        });
        button6.setOnClickListener(e->{
            Intent intent=new Intent();
            intent.setClass(getActivity(), ques_makesi.class);
            startActivity(intent);
        });
        button7.setOnClickListener(e->{
            Intent intent=new Intent();
            intent.setClass(getActivity(), ques_makesi.class);
            startActivity(intent);
        });
        }


}