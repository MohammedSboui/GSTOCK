package com.example.gstock;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AjoutMembre extends AppCompatActivity {
    EditText _txtNom, _txtPrenom, _txtAdress, _txtTel1,_txtTel2,_txtId;
    Button _btnAdd, _btnUpdate, _btnDelete, _btnSave, _btnCancel;
    SQLiteDatabase db;
    int op = 1;
    Cursor cur;
    String x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_membre);
        _txtId= (EditText) findViewById(R.id.txtId);
        _txtNom = (EditText) findViewById(R.id.txtNom);
        _txtPrenom = (EditText) findViewById(R.id.txtPrenom);
        _txtAdress = (EditText) findViewById(R.id.txtAdress);
        _txtTel1 = (EditText) findViewById(R.id.txtTel1);
        _txtTel2 = (EditText) findViewById(R.id.txtTel2);

        _btnAdd = (Button) findViewById(R.id.btnAdd);
        _btnUpdate = (Button) findViewById(R.id.btnUpdate);
        _btnDelete = (Button) findViewById(R.id.btnDelete);
        _btnCancel = (Button) findViewById(R.id.btnCancel);
        _btnSave = (Button) findViewById(R.id.btnSave);
        db = openOrCreateDatabase("Stock", MODE_PRIVATE, null);
        // Création de la table "users"
        db.execSQL("CREATE TABLE IF NOT EXISTS Membre (id int primary key,nom varchar ,prenom varchar ,address varchar,tel1 int, tel2 int);");

        _btnSave.setVisibility(View.INVISIBLE);
        _btnCancel.setVisibility(View.INVISIBLE);
        _btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op = 1;
                _txtId.setText("");
                _txtNom.setText("");
                _txtPrenom.setText("");
                _txtTel1.setText("");
                _txtTel2.setText("");
                _txtAdress.setText("");
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
                    Toast.makeText(getApplicationContext(), "Sélectionnez un membre puis appyuer sur le bouton de modification", Toast.LENGTH_SHORT).show();
                }

            }
        });

        _btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (op == 1) {
                    // insertion
                    db.execSQL("insert into Membre (id,nom,prenom,address,tel1,tel2) values (?,?,?,?);", new String[]{_txtId.getText().toString(), _txtNom.getText().toString(), _txtPrenom.getText().toString(), _txtAdress.getText().toString(),_txtTel1.getText().toString(),_txtTel2.getText().toString()});
                } else if (op == 2) {
                    // Mise à jour
                    db.execSQL("update Membre set  nom=?, prenom=?, address=?, tel1=?, tel2=? where id=?;", new String[]{_txtNom.getText().toString(), _txtPrenom.getText().toString(), _txtAdress.getText().toString(),_txtTel1.getText().toString(),_txtTel2.getText().toString(), x});
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
                    db.execSQL("delete from Membre where id=?;",new String[] {cur.getString(0)});
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Sélectionner un membre puis appyuer sur le bouton de suppresssion", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }
}