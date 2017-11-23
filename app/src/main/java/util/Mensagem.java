package util;


import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class Mensagem {
    public static void Msg(Activity activity, String msg){
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
    }

    public static void addMsgOK(Activity activity, String titulo, String msg, int icone){
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle(titulo);
        alert.setMessage(msg);
        alert.setNeutralButton("OK", null);
        alert.setIcon(icone);
        alert.show();
    }

    public static void MsgConfirm(Activity activity, String titulo, String msg, int icone, DialogInterface.OnClickListener listener){
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle(titulo);
        alert.setMessage(msg);
        alert.setPositiveButton("SIM", listener);
        alert.setNegativeButton("NÃO", null);
        alert.setIcon(icone);
        alert.show();
    }

    public static AlertDialog criarAlertDialog(Activity activity){
        final  CharSequence[] items = {
                "Editar",
                "Excluir"
        };
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle("Opções");
        alert.setItems(items, (DialogInterface.OnClickListener) activity);
        return alert.create();
    }
    public static AlertDialog criarDialoConfirmacao(Activity activity){
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
       // alert.setMessage("Deseja realmente Excluir?");
      //  alert.setPositiveButton("Sim", (DialogInterface.OnClickListener)activity);
      //  alert.setNegativeButton("Não", (DialogInterface.OnClickListener)activity);

        return alert.create();

    }
}
