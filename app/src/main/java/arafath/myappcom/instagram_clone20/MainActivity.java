package arafath.myappcom.instagram_clone20;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
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

        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
               if(keyCode == KeyEvent.KEYCODE_ENTER &&
                       event.getAction() == KeyEvent.ACTION_DOWN){

                   onClick(btnSignup);
                                  }
                return false;
            }
        });

        btnSignup = findViewById(R.id.buttonSignUp);

        textLogin = findViewById(R.id.textLogin);

        btnSignup.setOnClickListener(this);
        textLogin.setOnClickListener(this);

        if(ParseUser.getCurrentUser() != null) {
            transitionToMediaActivity();
        }



    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.buttonSignUp:

                if(edtEmail.getText().toString().equals("") || edtUsername.getText().toString().equals("") || edtPassword.getText().toString().equals("")){
                    FancyToast.makeText(MainActivity.this, "Email, Username, Password required.", FancyToast.INFO, Toast.LENGTH_SHORT, true).show();

                }else {
                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtEmail.getText().toString());
                    appUser.setUsername(edtUsername.getText().toString());
                    appUser.setPassword(edtPassword.getText().toString());

                    final ProgressDialog dialog = new ProgressDialog(this);
                    dialog.setMessage("Signing up " + edtUsername.getText().toString());
                    dialog.show();

                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(MainActivity.this, appUser.getUsername() + " is Signed up.", FancyToast.SUCCESS, Toast.LENGTH_SHORT, true).show();
                                transitionToMediaActivity();
                            } else {
                                FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.ERROR, Toast.LENGTH_SHORT, true).show();

                            }
                            dialog.dismiss();
                        }
                    });
                }
                break;

            case R.id.textLogin:
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                break;

        }
    }
    public void rootLayoutTapped(View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void transitionToMediaActivity(){
        Intent intent = new Intent(MainActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}

