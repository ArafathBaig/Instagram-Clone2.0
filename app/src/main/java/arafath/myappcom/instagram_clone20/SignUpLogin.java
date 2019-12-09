package arafath.myappcom.instagram_clone20;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLogin extends AppCompatActivity{
    EditText editText1,editText2,editText3,editText4;
    Button button1,button2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

        editText1 = findViewById(R.id.userSU);
        editText2 = findViewById(R.id.passSU);
        editText3 = findViewById(R.id.userLI);
        editText4 = findViewById(R.id.passLI);

        button1 = findViewById(R.id.signin);
        button2 = findViewById(R.id.logIn);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ParseUser user = new ParseUser();
                user.setUsername(editText1.getText().toString());
                user.setPassword(editText2.getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            FancyToast.makeText(SignUpLogin.this, user.get("username") + " signed in!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                        }else{
                            FancyToast.makeText(SignUpLogin.this, "Error signing in!", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                        }
                    }
                });
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(editText3.getText().toString(), editText4.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user != null && e == null){
                            FancyToast.makeText(SignUpLogin.this, user.get("username") + " Logged in!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                        }else{
                            FancyToast.makeText(SignUpLogin.this, "Wrong Information", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                        }
                    }
                });

            }
        });

    }

}
