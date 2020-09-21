package com.example.granja;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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
    private AnimalAdapter animalAdapter;
    @BindView(R.id.txtBuscar)
    public EditText txtBuscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        loadInfo();
        txtBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                animalAdapter.getTextFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void initComponents() {
        ButterKnife.bind(this);
    }
    private void loadInfo(){
        List<Animal> listaAnimales = new ArrayList<>();
        listaAnimales.add(new Animal("Perro", R.raw.ladrido_perro, R.drawable.perro, "Un perro"));
        listaAnimales.add(new Animal("Gato", R.raw.maullido_gato, R.drawable.gato, "Un gato"));
        listaAnimales.add(new Animal("Gallo", R.raw.canto_gallo, R.drawable.gallo, "Un gallo"));

        animalAdapter = new AnimalAdapter(this, listaAnimales);
        lvAnimales.setAdapter(animalAdapter);
    }

    public void onClickConstrain(View view) {
                if(){
            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(R.raw.ladrido_perro));
            mediaPlayer.start();
        }
    }
}