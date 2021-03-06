package com.example.tgraydas.lab2;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tgraydas.lab2.dummy.DummyContent;
import com.example.tgraydas.lab2.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ListFragment extends Fragment {

    public ListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        String DATABASE_NAME = "movies_db";
        final Database_PJ the_DB;
        the_DB = Room.databaseBuilder(getActivity().getApplicationContext(),
                Database_PJ.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
        ArrayList<String> elements = new ArrayList<String>() {};
        LoadForms(the_DB, elements);
        ListView listView = view.findViewById(R.id.list);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                elements
        );
        listView.setAdapter(listViewAdapter);
        // Set the adapter
        return view;
    }
    public void LoadForms(Database_PJ db, ArrayList<String> Forms){
        final Database_PJ the_db = db;
        List<Formulario_DB> rows = the_db.daoAccess().fetchAllFormularios();
        for (Formulario_DB row: rows) {
            String data = "";
            data += row.Nombre + " | ";
            data += row.Comentario + " | ";
            data += row.Fecha + " | ";
            data += row.Categoria + " | ";
            Forms.add(data);
        }
    }
}
