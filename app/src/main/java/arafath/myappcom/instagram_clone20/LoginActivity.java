package arafath.myappcom.instagram_clone20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtName, edtPass;
    private Button btnlogIn;
    private TextView textSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtName = findViewById(R.id.edtTextUsername);
        edtPass = findViewById(R.id.edtTextPassword);
        btnlogIn = findViewById(R.id.buttonLogIn);
        textSignup = findViewById(R.id.textSignUp);

        btnlogIn.setOnClickListener(this);
        textSignup.setOnClickListener(this);

        if(ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.buttonLogIn:
                ParseUser.logInInBackground(edtName.getText().toString(), edtPass.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if(user !=null && e==null){
                            FancyToast.makeText(LoginActivity.this, user.getUsername() + " is Logged In.", FancyToast.SUCCESS, Toast.LENGTH_SHORT,true).show();

                        }else{
                            FancyToast.makeText(LoginActivity.this, e.getMessage(), FancyToast.ERROR, Toast.LENGTH_SHORT,true).show();

                        }
                    }
                });
                break;

            case R.id.textSignUp:
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
