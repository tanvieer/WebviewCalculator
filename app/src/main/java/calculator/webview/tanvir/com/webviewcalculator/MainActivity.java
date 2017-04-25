package calculator.webview.tanvir.com.webviewcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText port,ip,adrs;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        port = (EditText) findViewById(R.id.editTextPort);
        ip = (EditText) findViewById(R.id.editTextIP);
        adrs = (EditText) findViewById(R.id.editTextUrl);

        button = (Button) findViewById(R.id.buttonUrl);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(MainActivity.this, WebviewCalculator.class);
                Log.d("myip","button clicked");

                String intnturl ="http://"+ip.getText()+":"+port.getText()+"/"+adrs.getText();


                Log.d("MyWebviewCalculator",intnturl);


                if(URLUtil.isValidUrl(intnturl)){
                    intent.putExtra("intentUrl",intnturl);
                    startActivity(intent);
                }
                else {
                    intnturl = "http://192.168.1.100:8080/Planteen/adminDashboard.jsp";
                    intent.putExtra("intentUrl",intnturl);
                    startActivity(intent);
                }

            }
        });

    }
}
