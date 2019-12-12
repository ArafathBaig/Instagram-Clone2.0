package arafath.myappcom.instagram_clone20;


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

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.put("profileName",editName.getText().toString());
                user.put("profileBio",editBio.getText().toString());
                user.put("profileProfession",editPro.getText().toString());
                user.put("profileHobbies",editHob.getText().toString());
                user.put("profileSports",editSpo.getText().toString());

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


            }
        });
        return view;
    }

}
