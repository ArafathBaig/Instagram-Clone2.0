package arafath.myappcom.instagram_clone20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    //UI components
    private EditText edtEmail, edtUsername, edtPassword;
    private Button btnSignup;
    private TextView textLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setTitle("Instagram-Clone");

        edtEmail = findViewById(R.id.edtTextSignUpEmail);
        edtUsername = findViewById(R.id.edtTextSignUpUsername);
        edtPassword = findViewById(R.id.edtTextSignUpPassword);

        btnSignup = findViewById(R.id.buttonSignUp);

        textLogin = findViewById(R.id.textLogin);


    }

    @Override
    public void onClick(View v) {

    }
}

