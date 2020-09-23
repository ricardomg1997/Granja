package com.example.granja;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.granja.adapter.AnimalAdapter;
import com.example.granja.dto.Animal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.lvAnimales)
    public ListView lvAnimales;
    @BindView(R.id.txtBuscar)
    public EditText txtBuscar;
    private AnimalAdapter animalAdapter;
    List<Animal> listaAnimales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initComponents();
        loadInfo();
        buscarOnTextListener();
        setOnClickListener();
    }

    private void buscarOnTextListener() {
        txtBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                animalAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void initComponents() {
        ButterKnife.bind(this);
    }
    private void loadInfo(){
        listaAnimales = new ArrayList<>();
        listaAnimales.add(new Animal("Perro", R.raw.ladrido_perro, R.drawable.perro, "Un perro"));
        listaAnimales.add(new Animal("Gato", R.raw.maullido_gato, R.drawable.gato, "Un gato"));
        listaAnimales.add(new Animal("Gallo", R.raw.canto_gallo, R.drawable.gallo, "Un gallo"));

        animalAdapter = new AnimalAdapter(this, listaAnimales);
        lvAnimales.setAdapter(animalAdapter);
    }

    private void setOnClickListener () {
        lvAnimales.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Animal animal = listaAnimales.get(i);
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), animal.getSonido());
                mediaPlayer.start();
            }
        });
    }
}
