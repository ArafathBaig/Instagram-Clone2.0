package arafath.myappcom.instagram_clone20;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.io.ByteArrayOutputStream;


/**
 * A simple {@link Fragment} subclass.
 */
public class SharePicTab extends Fragment implements View.OnClickListener {
        private ImageView imageView;
        private EditText editDesc;
        private Button btnshare;
        private Bitmap recievedImagebitmap;

    public SharePicTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_share_pic_tab, container, false);

        imageView = view.findViewById(R.id.imageView);
        editDesc = view.findViewById(R.id.editDescription);
        btnshare = view.findViewById(R.id.buttonSend);

        imageView.setOnClickListener(SharePicTab.this);
        btnshare.setOnClickListener(SharePicTab.this);

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.imageView:
                if(android.os.Build.VERSION.SDK_INT >= 23 &&
                        ActivityCompat.checkSelfPermission(getContext(),
                                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
                }else{
                    getChosenImage();
                }
                break;

            case R.id.buttonSend:
                if(recievedImagebitmap !=null) {

                    if (editDesc.getText().toString().equals("")) {
                        FancyToast.makeText(getContext(), "ERROR: Enter a description", FancyToast.ERROR, Toast.LENGTH_SHORT, true).show();

                    } else {

                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        recievedImagebitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                        byte[] bytes = byteArrayOutputStream.toByteArray();
                        ParseFile parseFile = new ParseFile("img.png",bytes);
                        ParseObject object = new ParseObject("Photo");
                        object.put("picture",parseFile);
                        object.put("image_dex",editDesc.getText().toString());
                        object.put("username", ParseUser.getCurrentUser().getUsername());
                        final ProgressDialog progressDialog = new ProgressDialog(getContext());
                        progressDialog.setMessage("Loading");
                        progressDialog.show();

                        object.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if(e ==null){
                                    FancyToast.makeText(getContext(), "Done!",FancyToast.SUCCESS,Toast.LENGTH_SHORT,true).show();
                                }else{
                                    FancyToast.makeText(getContext(), "Unknown Error "+e.getMessage(),FancyToast.ERROR,Toast.LENGTH_SHORT,true).show();
                                }
                                progressDialog.dismiss();
                            }
                        });

                    }
                }else{
                    FancyToast.makeText(getContext(),"ERROR: Enter an image",FancyToast.ERROR,Toast.LENGTH_SHORT,true).show();
                }
                break;
        }
    }

    private void getChosenImage() {


//        FancyToast.makeText(getContext(),"Now we can access the images ", Toast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();


        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,2000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1000){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getChosenImage();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2000){

            if(resultCode == Activity.RESULT_OK){


                try{
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getActivity().getContentResolver().query(selectedImage,filePathColumn,null,null,null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();

                    recievedImagebitmap = BitmapFactory.decodeFile(picturePath);
                    imageView.setImageBitmap(recievedImagebitmap);
                }catch(Exception e){
                        e.printStackTrace();
                }
            }

        }
    }
}
