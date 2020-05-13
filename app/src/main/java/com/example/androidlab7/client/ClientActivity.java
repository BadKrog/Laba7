package com.example.androidlab7.client;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidlab7.myinterf.OnClick;
import com.example.androidlab7.R;
import com.example.androidlab7.admin.AdminActivity;
import com.example.androidlab7.cart.CartActivity;

public class ClientActivity extends FragmentActivity {
    ClientRecyclerFragment clientRecyclerFragment;
    ClientViewPagerFragment clientViewPagerFragment;
    final static String TAG_1 = "RECYCLER_FRAGMENT";
    final static String TAG_2 = "VIEW_PAGER_FRAGMENT";
    OnClick click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        click = new OnClick() {
            @Override
            public void click(int position) {
                clientViewPagerFragment = new ClientViewPagerFragment(position);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_frame, clientViewPagerFragment, TAG_2);
                transaction.commit();
            }
        };
        clientRecyclerFragment = new ClientRecyclerFragment(click);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_frame, clientRecyclerFragment, TAG_1);
        transaction.commit();
        Button button = findViewById(R.id.cart_btn_main);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        Button adminButton = findViewById(R.id.admin_btn_main);
        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().findFragmentByTag(TAG_2) != null){
            clientRecyclerFragment = new ClientRecyclerFragment(click);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_frame, clientRecyclerFragment, TAG_1)
                    .commit();
        } else {
            finish();
        }
    }
}
