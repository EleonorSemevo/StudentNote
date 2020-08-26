package com.memoire.studentnote;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.memoire.studentnote.classes.Enseignant;
import com.memoire.studentnote.classes.Parent;
import com.memoire.studentnote.database.DataManager;
import com.memoire.studentnote.database.DatabaseUtil;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {
    private Enseignant mEnseignant;
    private Parent mParent;
    private EditText mProfileNom;
    private EditText mProfileTelephone;
    private EditText mProfileMail;


    public ProfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        //View rootView = inflater.inflate(R.layout.fragment_profil, container, false);

        return inflater.inflate(R.layout.fragment_profil, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();

        DataManager dataManager = DataManager.getInstance();
        if(DatabaseUtil.mFirebaseAuth==null)
        {
            DatabaseUtil.mFirebaseAuth = FirebaseAuth.getInstance();
        }
        Object obj =dataManager.getProfil(DatabaseUtil.mFirebaseAuth.getCurrentUser().getEmail());
        String ml = DatabaseUtil.mFirebaseAuth.getCurrentUser().getEmail();
        Log.d("mail",DatabaseUtil.mFirebaseAuth.getCurrentUser().getEmail());
        dataManager.typeCurrentProfil();

        if(obj.getClass().equals(Enseignant.class))
        {
            mEnseignant = (Enseignant) obj;
            ((TextView) view.findViewById(R.id.profile_nom))
                    .setText(mEnseignant.getNom());
            ((TextView) view.findViewById(R.id.profile_telephone))
                    .setText(mEnseignant.getTelephone());
            ((TextView) view.findViewById(R.id.profile_mail))
                    .setText(mEnseignant.getMail());


        }
        //else if (DatabaseUtil.isParent)
        else if(obj.getClass().equals(Parent.class))
        {
            mParent = (Parent) obj;
            ((TextView) view.findViewById(R.id.profile_nom))
                    .setText(mParent.getNom());
            ((TextView) view.findViewById(R.id.profile_telephone))
                    .setText(mParent.getTelephone());
            ((TextView) view.findViewById(R.id.profile_mail))
                    .setText(mParent.getMail());

        }

        ((TextView) view.findViewById(R.id.profile_nom))
                .setEnabled(false);
        ((TextView) view.findViewById(R.id.profile_telephone))
                .setEnabled(false);
        ((TextView) view.findViewById(R.id.profile_mail))
                .setEnabled(false);
    }
}
