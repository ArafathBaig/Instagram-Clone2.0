package arafath.myappcom.instagram_clone20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

    Button btn;
    EditText editText, editText2, editText3, editText4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        btn.setOnClickListener(MainActivity.this);


        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);


    }

    @Override
    public void onClick(View v) {
        final ParseObject kickBoxer = new ParseObject("KickBoxer");
        kickBoxer.put("kickPower",Integer.parseInt(editText4.getText().toString()));
        kickBoxer.put("punch_speed",Integer.parseInt(editText.getText().toString()));
        kickBoxer.put("punch_power",Integer.parseInt(editText2.getText().toString()));
        kickBoxer.put("kick_speed",Integer.parseInt(editText3.getText().toString()));
        kickBoxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    FancyToast.makeText(MainActivity.this,kickBoxer.get("name") + " saved Succesfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                }else{
                    Toast.makeText(MainActivity.this, e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

