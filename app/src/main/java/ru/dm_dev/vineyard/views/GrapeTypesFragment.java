package ru.dm_dev.vineyard.views;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

import java.util.List;

import ru.dm_dev.vineyard.R;
import ru.dm_dev.vineyard.models.GrapeType;
import ru.dm_dev.vineyard.presenters.BushesPresenter;
import ru.dm_dev.vineyard.presenters.IGrapeTypesPresenter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GrapeTypesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GrapeTypesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GrapeTypesFragment extends Fragment implements SearchView.OnQueryTextListener {

    private static final String LOG_TAG = "GrapeTypesFragment";
    private OnFragmentInteractionListener mListener;
    private View rootView;
    private RecyclerView rv;
    private ProgressBar progressBar;
    private GrapeTypeListAdapter adapter;
    private IGrapeTypesPresenter presenter;

    public GrapeTypesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GrapeTypesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GrapeTypesFragment newInstance(String param1, String param2) {
        GrapeTypesFragment fragment = new GrapeTypesFragment();
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

        adapter = new GrapeTypeListAdapter(getActivity(), null, this);
        rv.setAdapter(adapter);
        rv.addItemDecoration(new ItemDivider(getContext()));
        rv.setHasFixedSize(true);
        presenter = new GrapeTypesPresenter();
        presenter.init(this);
        FloatingActionButton fab = (FloatingActionButton)rootView.findViewById(R.id.fab);
        fab.setOnClickListener(this);
        return rootView;    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
    public void setListAdapter(List<GrapeType> list) {
        adapter.swapList(list);
        Log.d(LOG_TAG, "GrapeTypes count " + list.size());
    }

    @Override
    public void onClickItem(long id) {
        Intent intent = new Intent(getContext(), EditBusheActivity.class);
        intent.putExtra("Id", id);
        startActivity(intent);
    }

    @Override
    public void onClickEditButton(long id) {
        Intent intent = new Intent(getContext(), EditBusheActivity.class);
        intent.putExtra("Id", id);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
