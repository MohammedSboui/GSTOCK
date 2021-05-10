package com.example.gstock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Admin extends AppCompatActivity {

    Button _btnComposant,_btnMembre,_btnSuivi,_btnEmprunt,_btnFamille;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        _btnComposant = (Button) findViewById(R.id.btnComposant);
        _btnMembre = (Button) findViewById(R.id.btnMembre);
        _btnSuivi = (Button) findViewById(R.id.btnSuivi);
        _btnEmprunt= (Button) findViewById(R.id.btnEmprunt);
        _btnFamille = (Button) findViewById(R.id.btnFamille);
        _btnComposant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), Composants.class);
                startActivity(i);
            }

    });
        _btnMembre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), AjoutMembre.class);
                startActivity(i);
            }

        });
        _btnSuivi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), SuiviEmprunt.class);
                startActivity(i);
            }

        });
        _btnEmprunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), Emprunt.class);
                startActivity(i);
            }

        });
        _btnFamille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), FamilleComposant.class);
                startActivity(i);
            }

        });
}
}