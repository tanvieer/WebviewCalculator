package calculator.webview.tanvir.com.webviewcalculator;

/**
 * Created by Tanvir on 27-Apr-17.
 */

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class WebAppInterface {
    WebviewCalculator wa=null;

    String equation  = "";
    String operator = "";
    double num = 0;
    int firstTime = 0;

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        wa=(WebviewCalculator)c;
    }

    @android.webkit.JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(wa, toast, Toast.LENGTH_LONG).show();

    }

    @android.webkit.JavascriptInterface
    public void lastSign(String val){

        operator = val;

    }

    @android.webkit.JavascriptInterface
    public void setNum(String val){

        if(firstTime == 0){
            num = Double.parseDouble(val);
            firstTime++;
        }else {
            if(operator.equals("+")) {
                num += Double.parseDouble(val);
            }else if(operator.equals("-")) {
                num -= Double.parseDouble(val);
            }else if(operator.equals("*")) {
                num *= Double.parseDouble(val);
            }else if(operator.equals("/")) {
                if(Double.parseDouble(val) == 0){
                    Toast.makeText(wa,"Cant Divide By Zero",Toast.LENGTH_SHORT).show();
                    num = 0;
                }else {
                    num /= Double.parseDouble(val);
                }
            }
        }

    }

    @android.webkit.JavascriptInterface
    public void Cal(String val){

        Log.e("calculate","here1");
        if(operator.equals("+")){
            num += Double.parseDouble(val);
        }else if(operator.equals("-")){
            num -= Double.parseDouble(val);
        }else if(operator.equals("*")) {
            num *= Double.parseDouble(val);
        }else if(operator.equals("/")) {
            if(Double.parseDouble(val) == 0){
                Toast.makeText(wa,"Cant Divide By Zero",Toast.LENGTH_SHORT).show();
                num = 0;
            }else {
                num /= Double.parseDouble(val);
            }
        }

    }

    @android.webkit.JavascriptInterface
    public String getResult(){

        return Double.toString(num);
    }

    @android.webkit.JavascriptInterface
    public void setEquation(String val){

        equation = val;
    }

    @android.webkit.JavascriptInterface
    public void reset(){

        equation = "";
        operator = "";
        num = 0;
        firstTime = 0;
    }

    @android.webkit.JavascriptInterface
    public void setValue(String val){


    }

    public void reload(){
        wa.mHandler.post(new Runnable() {
            public void run() {
                WebAppInterface.this.wa.webView.reload();
                Log.d("try", "reload");
            }
        });
    }


}
