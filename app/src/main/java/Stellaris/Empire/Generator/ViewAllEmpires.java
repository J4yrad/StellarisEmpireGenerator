package Stellaris.Empire.Generator;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewAllEmpires extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_empires);
        EmpireViewModel mViewModel = ViewModelProviders.of(this).get(EmpireViewModel.class);
        RecyclerView rvEmpires = findViewById(R.id.rvContacts);
        List<EmpireObject> Empires = new ArrayList<>();
        EmpiresAdapter adapter = new EmpiresAdapter(Empires,mViewModel);
        // Attach the adapter to the recyclerview to populate items
        rvEmpires.setAdapter(adapter);
        // Set layout manager to position the items
        rvEmpires.setLayoutManager(new LinearLayoutManager(this));
        mViewModel.getAllEmpires().observeForever(new Observer<List<EmpireObject>>() {
            @Override
            public void onChanged(@Nullable List<EmpireObject> empires) {
                adapter.setEmpireList(empires);
                adapter.notifyDataSetChanged();
            }
        });
    }

}
