package com.example.androidlab7.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidlab7.myinterf.OnClick;
import com.example.androidlab7.myinterf.ChangedListenerData;
import com.example.androidlab7.data.ItemData;
import com.example.androidlab7.R;

public class AdminRecyclerFragment extends Fragment {
    OnClick click;
    ChangedListenerData listener;
    public AdminRecyclerFragment(OnClick click) {
        this.click = click;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.recycler_fragment_admin, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.recycler_admin);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final AdminRecyclerAdapter adminRecyclerAdapter = new AdminRecyclerAdapter(click);
        listener = new ChangedListenerData() {
            @Override
            public void notifyDataChanged() {
                adminRecyclerAdapter.notifyDataSetChanged();
            }
        };
        ItemData.getInstance().addDataChangedListener(listener);
        recyclerView.setAdapter(adminRecyclerAdapter);
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ItemData.getInstance().removeListener(listener);
    }
}
