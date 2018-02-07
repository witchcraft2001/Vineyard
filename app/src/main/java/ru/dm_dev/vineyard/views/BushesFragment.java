package ru.dm_dev.vineyard.views;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import ru.dm_dev.vineyard.R;
import ru.dm_dev.vineyard.common.BushesListAdapter;
import ru.dm_dev.vineyard.common.IAdapterClickListener;
import ru.dm_dev.vineyard.models.Bushe;
import ru.dm_dev.vineyard.presenters.BushesPresenter;
import ru.dm_dev.vineyard.presenters.IBushesPresenter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BushesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BushesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BushesFragment extends Fragment implements IBushesFragmentView, View.OnClickListener, IAdapterClickListener, SearchView.OnQueryTextListener {

    View rootView;
    RecyclerView rv;
    ProgressBar progressBar;
    BushesListAdapter bushesListAdapter;
    IBushesPresenter presenter;

    private OnFragmentInteractionListener mListener;
    private final String LOG_TAG = "BushesFragmentView";

    public BushesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BushesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BushesFragment newInstance(String param1, String param2) {
        BushesFragment fragment = new BushesFragment();
        return fragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_menu, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(this);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_bushes, container, false);
        progressBar = (ProgressBar)rootView.findViewById(R.id.progressBar);
        rv = (RecyclerView)rootView.findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);

        bushesListAdapter = new BushesListAdapter(getActivity(), null, this);
        rv.setAdapter(bushesListAdapter);
        rv.setHasFixedSize(true);
        presenter = new BushesPresenter();
        presenter.init(this);
        FloatingActionButton fab = (FloatingActionButton)rootView.findViewById(R.id.fab);
        fab.setOnClickListener(this);
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showLoader() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setBushesListAdapter(List<Bushe> list) {
        bushesListAdapter.swapList(list);
        Log.d(LOG_TAG, "Bushes count " + list.size());
    }

    @Override
    public void onClick(long id) {
        Intent intent = new Intent(getContext(), EditBusheActivity.class);
        intent.putExtra("Id", id);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        Intent intent = new Intent(getContext(), EditBusheActivity.class);
        intent.putExtra("Id", 0L);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        presenter.setSearchQuery(newText);
        return false;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
