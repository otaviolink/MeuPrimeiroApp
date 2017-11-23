package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.Tarefa;
import model.Usuario;

public class TarefaDAO {

    private  DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public TarefaDAO(Context context){
        databaseHelper = new DatabaseHelper(context);
    }
    private SQLiteDatabase getDatabase(){
        if (database == null){
            database = databaseHelper.getWritableDatabase();
        }
        return database;
    }
    private Tarefa criarTarefa(Cursor cursor){
        Tarefa model = new Tarefa(
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Tarefas._ID)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Tarefas.TABELA)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Tarefas.DT_CRIACAO)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Tarefas.DT_COMPLETADO))
        );

        return model;
    }

    //Listar Tarefas
    public List<Tarefa> listarTarefa(){
        Cursor cursor = getDatabase().query(DatabaseHelper.Tarefas.TABELA,
                DatabaseHelper.Tarefas.COLUNAS, null,null, null, null, null);

        List<Tarefa> tarefas = new ArrayList<Tarefa>();
        while (cursor.moveToNext()){
            Tarefa model = criarTarefa(cursor);
            tarefas.add(model);
        }
        cursor.close();
        return tarefas;
    }

    //Salvar Tarefas
    public long salvarTarefa (Tarefa model ){
        ContentValues valores = new ContentValues();
        valores.put(DatabaseHelper.Tarefas.TAREFA, model.getTarefa());


        if (model.get_id()!=null){
            return getDatabase().update(DatabaseHelper.Tarefas.TABELA, valores,
                    "_id = ?", new String[]{model.get_id().toString()});
        }

        return getDatabase().insert(DatabaseHelper.Tarefas.TABELA, null, valores);
    }

    //Remover Tarefa
    public boolean removerTarefa (int id){
        return getDatabase().delete(DatabaseHelper.Tarefas.TABELA,
                "_id = ?", new String[]{Integer.toString(id)}) > 0 ;
    }

    //Buscar por Tarefaz
    public Tarefa buscarTarefaPorid(int id){
        Cursor cursor = getDatabase().query(DatabaseHelper.Tarefas.TABELA,
                DatabaseHelper.Tarefas.COLUNAS, "_id = ?", new String[]{Integer.toString(id)}, null, null,null);
        if (cursor.moveToNext()){
            Tarefa model = criarTarefa(cursor);
            cursor.close();
            return model;
        }
        return null;

    }

    public void fechar(){
        databaseHelper.close();
        database = null;
    }

}
