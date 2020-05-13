package com.example.androidlab7.client;

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

public class ClientRecyclerFragment extends Fragment {
    OnClick click;
    public ClientRecyclerFragment(OnClick click) {
        super();
        this.click = click;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recycler_fragment_main, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.main_recycler);
        final ClientRecyclerAdapter adapter = new ClientRecyclerAdapter(click);
        ChangedListenerData listener = new ChangedListenerData() {
            @Override
            public void notifyDataChanged() {
                adapter.notifyDataSetChanged();
            }
        };
        ItemData.getInstance().addDataChangedListener(listener);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return v;
    }
}
