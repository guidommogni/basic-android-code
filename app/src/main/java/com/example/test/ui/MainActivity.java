package com.example.test.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.di.ComponentsLocator;
import com.example.test.model.ModelVotes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private MyListPresenter presenter;
    private Toolbar toolbar;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);

        recyclerView = findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);

        presenter = ComponentsLocator.getPresenter();

        FloatingActionButton fab = findViewById(R.id.fab);
        setOnScrollEvent();
        try {
            final InputStream inputStream = getResources().getAssets().open("grpc.crt");
            fab.setOnClickListener(view -> presenter.testService(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setOnScrollEvent() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy != 0) {
                    //presenter.onScrollEvent(layoutManager.findLastVisibleItemPosition());
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onAttachView(this);
    }

    public void setToolbarTitle(final String title) {
        toolbar.setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void shwError() {
        Toast.makeText(this, "algo salio mal", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onDetachView();
    }

    public void showMessage(final String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    public void addMoreVotes(final List<ModelVotes> votes) {
        adapter.addVotes(votes);
    }
}
