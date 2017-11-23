package primeiroapp.studio.com.meuprimeiroapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MenuActivityTreino extends AppCompatActivity {

    private Button btn_inic;
    private Button btn_cadt;
    private Button btn_treino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_treino);

        //Tela Iniciar Treino
        btn_inic = (Button) findViewById(R.id.btn_inic);
        btn_inic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivityTreino.this, DiaTreinoActivity.class);
                startActivity(intent);
            }
        });

        //Tela Cadastrar Treino
        btn_cadt = (Button) findViewById(R.id.btn_cadt);
        btn_cadt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivityTreino.this, CadTreinoActivity.class);
                startActivity(intent);
            }
        });

        //Tela Montar Treino
        btn_treino = (Button) findViewById(R.id.btn_treino);
        btn_treino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivityTreino.this, CadMontActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_treino, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
