package com.memoire.studentnote;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.memoire.studentnote.chat.Sender;
import com.memoire.studentnote.chat.UserDetails;
import com.memoire.studentnote.classes.ListeUser;
import com.memoire.studentnote.database.DataManager;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {

    RecyclerView usersList;
    TextView noUsersText;
    //ArrayList<String> al = new ArrayList<>();
    int totalUsers = 0;
    ProgressDialog pd;

    List<ListeUser> listeUser = new ArrayList<>();
    private MessageRecyclerAdapter mMessageRecyclerAdapter;

    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initialisationList();

        usersList = view.findViewById(R.id.msg_recycler);
        noUsersText = view.findViewById(R.id.noUsersText);

        noUsersText.setVisibility(View.GONE);
        usersList.setVisibility(View.VISIBLE);
        initialiseDisplayContent(view);



    }



//    public void initialisationList()
//    {
//        al = new ArrayList<>();
//        listeUser = DataManager.getInstance().getListeUsers();
//        //al.add("Loren");
//        UserDetails.user_uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        for(int i=0;i<listeUser.size();i++)
//        {
//            al.add(listeUser.get(i).getNom()+" "+listeUser.get(i).getPrenom());
//        }
//
//    }


    private void initialiseDisplayContent(View view)
    {
        final RecyclerView recyclerView= ((RecyclerView)view.findViewById(R.id.msg_recycler));
        final LinearLayoutManager msgLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(msgLayoutManager);


        DataManager dataManager = DataManager.getInstance();
        listeUser.clear();
        listeUser = dataManager.getListeUsers();


        mMessageRecyclerAdapter = new MessageRecyclerAdapter(view.getContext(),listeUser);
        recyclerView.setAdapter(mMessageRecyclerAdapter);


    }

}
