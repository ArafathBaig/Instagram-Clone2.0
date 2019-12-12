package arafath.myappcom.instagram_clone20;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

    EditText editName , editBio, editPro, editHob, editSpo;
    Button btnupdate;

    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile_tab, container, false);

        editName = view.findViewById(R.id.editName);
        editBio = view.findViewById(R.id.editBio);
        editPro = view.findViewById(R.id.editProfession);
        editHob = view.findViewById(R.id.editHob);
        editSpo = view.findViewById(R.id.editSports);

        btnupdate = view.findViewById(R.id.buttonSave);


        final ParseUser user = ParseUser.getCurrentUser();

        if(user.get("profileName") == null){
            editName.setText("");
        }else{
            editName.setText(user.get("profileName").toString());
        }

        if(user.get("profileBio") == null){
            editBio.setText("");
        }else{
            editBio.setText(user.get("profileBio").toString());
        }


        if(user.get("profileProfession") == null){
            editPro.setText("");
        }else{
            editPro.setText(user.get("profileProfession").toString());

        }


        if(user.get("profileHobbies") == null){
            editHob.setText("");
        }else{
            editHob.setText(user.get("profileHobbies").toString());
        }


        if(user.get("profileSports") == null){
            editSpo.setText("");
        }else{
            editSpo.setText(user.get("profileSports").toString());
        }



//        final ProgressDialog progressDialog = new ProgressDialog(getContext());
//        progressDialog.setMessage("Updating Info");
//        progressDialog.show();

        btnupdate.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                user.put("profileName",editName.getText()+"");
                user.put("profileBio",editBio.getText()+"");
                user.put("profileProfession",editPro.getText()+"");
                user.put("profileHobbies",editHob.getText()+"");
                user.put("profileSports",editSpo.getText()+"");

                user.saveInBackground(new SaveCallback() {


                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            FancyToast.makeText(getContext(),"Info Updated",FancyToast.INFO, Toast.LENGTH_SHORT,true).show();

                        }else{
                            FancyToast.makeText(getContext(),e.getMessage(),FancyToast.ERROR, Toast.LENGTH_SHORT,true).show();
                        }
                    }

                });
//                progressDialog.dismiss();

            }
        });
        return view;
    }

}
