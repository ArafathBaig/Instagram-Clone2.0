package arafath.myappcom.instagram_clone20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
            transitionToMediaActivity();
        }
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.buttonLogIn:
                if( edtName.getText().toString().equals("") || edtPass.getText().toString().equals("")){
                    FancyToast.makeText(LoginActivity.this, "Username, Password required.", FancyToast.INFO, Toast.LENGTH_SHORT, true).show();

                }else {
                    ParseUser.logInInBackground(edtName.getText().toString(), edtPass.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {

                            if (user != null && e == null) {
                                FancyToast.makeText(LoginActivity.this, user.getUsername() + " is Logged In.", FancyToast.SUCCESS, Toast.LENGTH_SHORT, true).show();
                                    transitionToMediaActivity();
                            } else {
                                FancyToast.makeText(LoginActivity.this, e.getMessage(), FancyToast.ERROR, Toast.LENGTH_SHORT, true).show();

                            }
                        }
                    });
                }
                break;

            case R.id.textSignUp:
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }
    public void rootLayoutTappedd(View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void transitionToMediaActivity(){
        Intent intent = new Intent(LoginActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}
