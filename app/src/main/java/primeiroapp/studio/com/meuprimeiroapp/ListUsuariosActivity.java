package primeiroapp.studio.com.meuprimeiroapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import adapter.UsuarioAdapter;
import dao.UsuarioDAO;
import model.Usuario;
import util.Mensagem;

public class ListUsuariosActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener, DialogInterface.OnClickListener{
    private ListView lista;
    private List<Usuario> usuarioList;
    private UsuarioAdapter usuarioAdapter;
    private UsuarioDAO usuarioDAO;

    private int idposicao;

    private AlertDialog alertDialog, alertConfirmacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_usuarios);
        System.out.println();

        alertDialog = Mensagem.criarAlertDialog(this);
        alertConfirmacao = Mensagem.criarDialoConfirmacao(this);


        usuarioDAO = new UsuarioDAO(this);
        usuarioList = usuarioDAO.listarUsuarios();

        System.out.println("lista de usuarios");
        for(Usuario u: usuarioList) {
            System.out.println(u.getNome() + " - " + u.getLogin() + " - " + u.getSenha());
        }

        usuarioAdapter = new UsuarioAdapter(this, usuarioList);

        lista = (ListView) findViewById(R.id.lvUsuarios);
        lista.setAdapter(usuarioAdapter);

        lista.setOnItemClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_usuarios, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_cadastrar_usuarios){
            startActivity(new Intent(this, CadUsuarioActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        int id = usuarioList.get(idposicao).get_id();
        switch (which){
            case 0:
                Intent intent = new Intent(this, CadUsuarioActivity.class);
                intent.putExtra("USUARIO_ID", id);
                startActivity(intent);
                break;

            case 1:
                alertConfirmacao.show();
                usuarioList.remove(idposicao);
                usuarioDAO.removerUsuario(id);
                lista.invalidateViews();
                break;
            case DialogInterface.BUTTON_POSITIVE:
                break;
            case  DialogInterface.BUTTON_NEGATIVE:
                alertConfirmacao.dismiss();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        idposicao = position;
        alertDialog.show();

    }
}

