package arafath.myappcom.instagram_clone20;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class UserPosts extends AppCompatActivity {

    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_posts);

        layout = findViewById(R.id.linearLayout);

        Intent recievedObject = getIntent();
        final String recievedUser = recievedObject.getStringExtra("username");
        FancyToast.makeText(
                this,recievedUser,FancyToast.SUCCESS, Toast.LENGTH_SHORT,true).show();

        setTitle(recievedUser+"'s Posts");

        ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("Photo");
        parseQuery.whereEqualTo("username",recievedUser);
        parseQuery.orderByDescending("createdAt");

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.show();

        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                 if(objects.size() > 0 && e==null){

                    for(ParseObject post : objects){

                        final TextView imageDes = new TextView(UserPosts.this);
                        imageDes.setText(post.get("image_des")+"");
                        final ParseFile postPicture = (ParseFile) post.get("picture");
                        postPicture.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {

                                if(data != null && e == null){

                                    Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
                                    ImageView imageView = new ImageView(UserPosts.this);
                                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                                    params.setMargins(5,5,5,5);
                                    imageView.setLayoutParams(params);
                                    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                    imageView.setImageBitmap(bitmap);

                                    LinearLayout.LayoutParams des_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT);
                                    des_params.setMargins(5,5,5,5);
                                    imageDes.setLayoutParams(des_params);
                                    imageDes.setGravity(Gravity.CENTER);
                                    imageDes.setBackgroundColor(Color.WHITE);
                                    imageDes.setTextColor(Color.BLACK);
                                    imageDes.setTextSize(30f);


                                    layout.addView(imageView);
                                    layout.addView(imageDes);

                                }
                            }
                        });
                    }
                }else{
                     FancyToast.makeText(UserPosts.this,recievedUser + " has no post's.",FancyToast.INFO,Toast.LENGTH_SHORT,true).show();
                    finish();
                 }
                dialog.dismiss();
            }
        });

    }
}
