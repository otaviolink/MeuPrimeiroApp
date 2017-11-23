package primeiroapp.studio.com.meuprimeiroapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import util.Mensagem;

public class MainActivity extends AppCompatActivity {
    //Tela Treino
    private Button btn_trei;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Tela Treino
        btn_trei = (Button) findViewById(R.id.btn_trei);
        btn_trei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuActivityTreino.class);
                startActivity(intent);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_lista_usuarios:
                startActivity(new Intent(this, ListUsuariosActivity.class));
                break;
            case R.id.action_lista_sair:
                Mensagem.MsgConfirm(this, "Sair", "Deseja realmente sair?", R.drawable.ic_action_about, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

                break;
            case R.id.action_lista_logout:
                SharedPreferences preferences = getSharedPreferences("LoginActivityPreferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

