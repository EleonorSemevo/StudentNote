package com.memoire.studentnote;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.google.firebase.auth.FirebaseAuth;
import com.memoire.studentnote.classes.Enseignant;
import com.memoire.studentnote.classes.Parent;
import com.memoire.studentnote.database.DataManager;
import com.memoire.studentnote.database.DatabaseUtil;

import static com.memoire.studentnote.database.DatabaseUtil.isEnseignant;
import static com.memoire.studentnote.database.DatabaseUtil.isParent;
import static com.memoire.studentnote.database.DatabaseUtil.mDataManager;
import static com.memoire.studentnote.database.DatabaseUtil.mDataWorker;
import static com.memoire.studentnote.database.DatabaseUtil.mEnseignant;
import static com.memoire.studentnote.database.DatabaseUtil.mListeEcoles;
import static com.memoire.studentnote.database.DatabaseUtil.mUtilisateurActuel;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {
   // private Enseignant mEnseignant;
    private Parent mParent;
    private EditText mProfileNom;
    private EditText mProfilePrenom;
    private EditText mProfileTelephone;
    private EditText mProfileMail;
    private EditText mProfileMdp;
    private Button   mButtonModifier;
    private Button mButtonEnregistrer;

    private TextView mTextViewUserName;
    private TextView mTextViewUserType;
    private int etatButton= 0;



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
       // Bundle args = getArguments();
        mProfileNom      =view.findViewById(R.id.profil_nom);
        mProfilePrenom   =view.findViewById(R.id.profil_prenom);
        mProfileTelephone=view.findViewById(R.id.profile_telephone);
        mProfileMail     =view.findViewById(R.id.profile_mail);
        mProfileMdp      =view.findViewById(R.id.profil_mot_de_passe);

        mButtonModifier  =view.findViewById(R.id.button_profile_modifier);
        mButtonEnregistrer = view.findViewById(R.id.button_profile_enregistrer);
        mTextViewUserType = view.findViewById(R.id.user_profile_type);
        mTextViewUserName = view.findViewById(R.id.user_profile_name);

        mButtonEnregistrer.setVisibility(View.GONE);




        initialisation(view);

        mButtonModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mButtonModifier.setVisibility(View.GONE);
                mButtonEnregistrer.setVisibility(View.VISIBLE);

                mProfileTelephone.setEnabled(true);
                mProfileNom.setEnabled(true);
                mProfilePrenom.setEnabled(true);





            }
        });

        mButtonEnregistrer.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int idListUser = mDataManager.getIdListUser(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                        int id;
                        if(isParent)
                            id=mParent.getId();
                        else if(isEnseignant)
                            id = mEnseignant.getId();
                        else
                            id =-1;


                        if(mProfileNom.getText().toString()==null || mProfileNom.getText().toString().length()<=0)
                            message("Nom de plus de 2 caractères", v);
                        else if(mProfilePrenom.getText().toString()==null || mProfileNom.getText().toString().length()<=0)
                            message("Prénom de plus de 2 caractères", v);
                        else if(mProfileTelephone.getText().toString().length()!=8)
                            message("Saisir un numéro valide", v);
                        else
                        {
                            mDataWorker.updateProfil(mProfileNom.getText().toString(), mProfilePrenom.getText().toString(),
                                    mProfileTelephone.getText().toString(), mUtilisateurActuel.getId(), idListUser,id);
                            mButtonModifier.setText("Modifier mon profile");

                            mProfileTelephone.setEnabled(false);
                            mProfileNom.setEnabled(false);
                            mProfilePrenom.setEnabled(false);

                            //
                            mTextViewUserName.setText(mProfileNom.getText().toString()+" "+ mProfilePrenom.getText().toString());

                            mButtonModifier.setVisibility(View.VISIBLE);
                            mButtonEnregistrer.setVisibility(View.GONE);

                        }



                    }
                }
        );



}


public void initialisation(View view)
{

        mTextViewUserName.setText(mUtilisateurActuel.getNom()+" "+mUtilisateurActuel.getPrenom());
        mTextViewUserType.setText(mUtilisateurActuel.getType());

        mProfileNom.setText(mUtilisateurActuel.getNom())  ;
        mProfilePrenom.setText(mUtilisateurActuel.getPrenom());
        mProfileMail.setText(mUtilisateurActuel.getMail());
        mProfileTelephone.setText(mUtilisateurActuel.getTelephone());
        mProfileMdp.setText(mUtilisateurActuel.getMdp());

        mProfileNom.setEnabled(false);
        mProfilePrenom.setEnabled(false);
        mProfileTelephone.setEnabled(false);
        mProfileMail.setEnabled(false);
        mProfileMdp.setEnabled(false);






}

public void message(String msg, View view)
{
    AlertDialog alertDialog= new AlertDialog.Builder(view.getContext()).create();
    alertDialog.setTitle("Erreur");
    //alertDialog.setIcon(R.drawable.);
    alertDialog.setMessage(msg);
    alertDialog.setInverseBackgroundForced(true);
    alertDialog.setButton(alertDialog.BUTTON_NEUTRAL, "OK",
            new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

}
}
