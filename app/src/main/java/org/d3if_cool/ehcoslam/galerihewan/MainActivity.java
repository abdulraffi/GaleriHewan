package org.d3if_cool.ehcoslam.galerihewan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebase;
    private DatabaseReference mDatabase;
    private AdapterHewan mAdapterHewan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapterHewan = new AdapterHewan(this,mDatabase.child("daftar"));
        mDatabase = mFirebase.getInstance().getReference();
        ListView listView = (ListView)findViewById(R.id.myList);
        listView.setAdapter(mAdapterHewan);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mDatabase.child("daftar").child(mAdapterHewan.getIdkey().get(i)).setValue(new Hewan("mmmmmm"));
            }
        });
    }

    public void simpanData(View view) {
        EditText nama = (EditText)findViewById(R.id.nama);
        String hewan = nama.getText().toString();

        String key = mDatabase.child("daftar").push().getKey();
        mDatabase.child("daftar").child(key).setValue(new Hewan(hewan));
    }
}
