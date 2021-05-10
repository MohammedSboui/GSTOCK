package com.example.gstock;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
public class Composants extends AppCompatActivity {
    EditText _txtCode, _txtNomC, _txtEmprunt, _txtQuantite,_txtRechercheComposant;
    Button _btnAdd, _btnUpdate, _btnDelete, _btnSave, _btnCancel;
    ImageButton _btnRecherche;
    LinearLayout layNaviguer,layRecherche;
    SQLiteDatabase db;
    int op = 1;
    Cursor cur;
    String x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composants);
        layNaviguer = (LinearLayout) findViewById(R.id.layNaviguer);
        layRecherche = (LinearLayout) findViewById(R.id.layRecherche);

        _txtCode = (EditText) findViewById(R.id.txtCode);
        _txtNomC = (EditText) findViewById(R.id.txtNomC);
        _txtEmprunt = (EditText) findViewById(R.id.txtEmprunt);
        _txtQuantite = (EditText) findViewById(R.id.txtQuantite);
        _txtRechercheComposant= (EditText) findViewById(R.id.txtRechercheComposant);

        _btnAdd = (Button) findViewById(R.id.btnAdd);
        _btnUpdate = (Button) findViewById(R.id.btnUpdate);
        _btnDelete = (Button) findViewById(R.id.btnDelete);
        _btnCancel = (Button) findViewById(R.id.btnCancel);
        _btnSave = (Button) findViewById(R.id.btnSave);
        _btnRecherche = (ImageButton) findViewById(R.id.btnRecherche);
        db = openOrCreateDatabase("GestionStock", MODE_PRIVATE, null);
        // Création de la table "users"
        db.execSQL("CREATE TABLE IF NOT EXISTS Composant (Code int primary key,NomC varchar ,quantite int ,empruntable int);");
        _btnRecherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cur = db.rawQuery("select * from Composant where NomC like ?", new String[]{"%" + _txtRechercheComposant.getText().toString() + "%"});
                try {
                    cur.moveToFirst();
                    _txtCode.setText(cur.getString(1));
                    _txtNomC.setText(cur.getString(2));
                    _txtEmprunt.setText(cur.getString(3));
                    _txtQuantite.setText(cur.getString(4));
                    if (cur.getCount() == 1){
                        layNaviguer.setVisibility(View.INVISIBLE);
                    } else {
                        layNaviguer.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"aucun résultat.",Toast.LENGTH_SHORT).show();
                    _txtCode.setText("");
                    _txtNomC.setText("");
                    _txtEmprunt.setText("");
                    _txtQuantite.setText("");
                    layNaviguer.setVisibility(View.INVISIBLE);
                }
            }
        });

        _btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                op = 1;
                _txtCode.setText("");
                _txtEmprunt.setText("");
                _txtNomC.setText("");
                _txtQuantite.setText("");
                _btnSave.setVisibility(View.VISIBLE);
                _btnUpdate.setVisibility(View.INVISIBLE);
                _btnDelete.setVisibility(View.INVISIBLE);
                _btnAdd.setEnabled(false);
                _btnCancel = (Button) findViewById(R.id.btnCancel);
                layNaviguer.setVisibility(View.INVISIBLE);
                layRecherche.setVisibility(View.INVISIBLE);
            }
        });


        _btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (op == 1) {
                    // insertion
                    db.execSQL("insert into Composant (Code,NomC,Empruntable,Quantite) values (?,?,?,?);", new String[]{_txtCode.getText().toString(), _txtNomC.getText().toString(), _txtEmprunt.getText().toString(), _txtQuantite.getText().toString()});
                }

                _btnSave.setVisibility(View.INVISIBLE);
                _btnCancel.setVisibility(View.INVISIBLE);
                _btnUpdate.setVisibility(View.VISIBLE);
                _btnDelete.setVisibility(View.VISIBLE);

                _btnAdd.setVisibility(View.VISIBLE);
                _btnAdd.setEnabled(true);
                _btnUpdate.setEnabled(true);
                _btnRecherche.performClick();
                layRecherche.setVisibility(View.VISIBLE);
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

                layRecherche.setVisibility(View.VISIBLE);
            }
        });
        _btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    x = cur.getString(0);
                    db.execSQL("delete from Composant where Code=?;",new String[] {cur.getString(0)});
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Sélectionner un composant puis appyuer sur le bouton de suppresssion", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });




    }
}