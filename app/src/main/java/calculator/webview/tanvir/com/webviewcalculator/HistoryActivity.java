package calculator.webview.tanvir.com.webviewcalculator;



import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * Created by Tanvir on 09-Mar-17.
 */

public class HistoryActivity extends AppCompatActivity {

    private TextView _ScrollScreen;
    private DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        _ScrollScreen = (TextView)findViewById(R.id.historytext);
        myDb = new DatabaseHelper(this);
        Log.d("HistoryClass","on create history activity " );


    }




    @Override
    protected void onResume(){
        // TODO Auto-generated method stub
        super.onResume();


        String data="";

        List<String> dataList =  myDb.getHistory();
        Log.d("HistoryClass","on Resume history activity 2" );

        for(String s : dataList) {
            data += s;
        }
        Log.d("HistoryClass","on Resume history activity 3" );
        if(data!="")
            _ScrollScreen.setText(data);

    }

    public void onClickBack(View v){
        //Toast.makeText(this, "BackClicked", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(HistoryActivity.this, OfflineCalculator.class));
    }

    public void onClickClearHistory(View v){



        AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this);

        builder.setMessage("Are you sure want to clear all history?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        myDb.deleteHistory();
                        _ScrollScreen.setText("History Deleted!!");
                        Toast.makeText(HistoryActivity.this, "History Deleted", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Cancel",null);

        AlertDialog alert = builder.create();
        alert.show();

        Toast.makeText(HistoryActivity.this, "History test", Toast.LENGTH_SHORT).show();
    }
}
