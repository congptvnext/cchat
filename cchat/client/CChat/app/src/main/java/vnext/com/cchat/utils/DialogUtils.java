package vnext.com.cchat.utils;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

public class DialogUtils {
    ProgressDialog dialog;
    Context context;

    public DialogUtils(Context context){
        this.context = context;
        dialog = new ProgressDialog(context);

    }
    public void showDialog(String message){
        dialog.setMessage(message);
        dialog.show();
    }
    public void showDiaWithTitle(String title,String message){
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.show();
    }
    public void dismissDialog(){
        dialog.dismiss();
    }
    public static void showToast(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
    public static void showToastRunOnThread(final Activity activity, final String message){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity,message,Toast.LENGTH_SHORT).show();
            }
        });
    }


}
