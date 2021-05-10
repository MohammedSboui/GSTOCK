package com.example.gstock;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Emprunt extends AppCompatActivity {
    EditText _txtDateE, _txtDateR, _txtEtat, _txtId;
    Button _btnAdd, _btnUpdate, _btnDelete, _btnSave, _btnCancel;
    Spinner _spnComposant,_spnMembre;
    SQLiteDatabase db;
    int op = 1;
    Cursor cur,cur1;
    String x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emprunt);
        _txtDateE = (EditText) findViewById(R.id.txtDateE);
        _txtDateR = (EditText) findViewById(R.id.txtNomF);
        _txtEtat = (EditText) findViewById(R.id.txtTel1);
        _txtId = (EditText) findViewById(R.id.txtId);
        _btnAdd = (Button) findViewById(R.id.btnAdd);
        _btnUpdate = (Button) findViewById(R.id.btnUpdate);
        _btnDelete = (Button) findViewById(R.id.btnDelete);
        _spnComposant.getSelectedItem().toString();
        _spnMembre.getSelectedItem().toString();
        _btnCancel = (Button) findViewById(R.id.btnCancel);
        _btnSave = (Button) findViewById(R.id.btnSave);
        db = openOrCreateDatabase("GestionStock", MODE_PRIVATE, null);
        // Création de la table "users"
        db.execSQL("CREATE TABLE IF NOT EXISTS Emprunt (id int primary key,DateE DATE, DateR DATE,Etat varchar,id_Membre int, id_Composant);");
        _btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op = 1;
                _txtId.setText("");
                _txtDateE.setText("");
                _txtDateR.setText("");
                _txtEtat.setText("");
                _btnSave.setVisibility(View.VISIBLE);
                _btnUpdate.setVisibility(View.INVISIBLE);
                _btnDelete.setVisibility(View.INVISIBLE);
                _btnAdd.setEnabled(false);
                _btnCancel = (Button) findViewById(R.id.btnCancel);

            }
        });
        _btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // tester si les champs ne sont pas vides
                try {
                    x = cur.getString(0);
                    op = 2;

                    _btnSave.setVisibility(View.VISIBLE);
                    _btnCancel.setVisibility(View.VISIBLE);

                    _btnDelete.setVisibility(View.INVISIBLE);
                    _btnUpdate.setEnabled(false);
                    _btnAdd.setVisibility(View.INVISIBLE);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Sélectionnez une emprunt puis appyuer sur le bouton de modification", Toast.LENGTH_SHORT).show();
                }

            }
        });
        _btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (op == 1) {
                    // insertion
                    db.execSQL("insert into Emprunt (id,DateE,DateR,Etat,id_Membre,id_Composant) values (?,?,?,?,?,?);", new String[]{_txtId.getText().toString(), _txtDateE.getText().toString(), _txtDateR.getText().toString(), _txtEtat.getText().toString(),_spnComposant.getSelectedItem().toString(),_spnMembre.getSelectedItem().toString()});
                } else if (op == 2) {
                    // Mise à jour
                    db.execSQL("update Emprunt set  DateE=?, DateR=?,Etat=? where id=?;", new String[]{_txtDateE.getText().toString(), _txtDateR.getText().toString(), _txtEtat.getText().toString(), x});
                }

                _btnSave.setVisibility(View.INVISIBLE);
                _btnCancel.setVisibility(View.INVISIBLE);
                _btnUpdate.setVisibility(View.VISIBLE);
                _btnDelete.setVisibility(View.VISIBLE);

                _btnAdd.setVisibility(View.VISIBLE);
                _btnAdd.setEnabled(true);
                _btnUpdate.setEnabled(true);

            }
        });
        _btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op = 0;

                _btnSave.setVisibility(View.INVISIBLE);
                _btnCancel.setVisibility(View.INVISIBLE);
                _btnUpdate.setVisibility(View.VISIBLE);
                _btnDelete.setVisibility(View.VISIBLE);

                _btnAdd.setVisibility(View.VISIBLE);
                _btnAdd.setEnabled(true);
                _btnUpdate.setEnabled(true);

            }
        });
        _btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    x = cur.getString(0);
                    db.execSQL("delete from Emprunt where id=?;",new String[] {cur.getString(0)});
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Sélectionner une Emprunt puis appyuer sur le bouton de suppresssion", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

    }
}