package ru.dm_dev.vineyard.views;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import ru.dm_dev.vineyard.R;
import ru.dm_dev.vineyard.common.AreasListAdapter;
import ru.dm_dev.vineyard.common.BushesListAdapter;
import ru.dm_dev.vineyard.models.Area;
import ru.dm_dev.vineyard.presenters.AreasPresenter;
import ru.dm_dev.vineyard.presenters.BushesPresenter;
import ru.dm_dev.vineyard.presenters.IAreasPresenter;
import ru.dm_dev.vineyard.presenters.IBushesPresenter;

public class AreasFragment extends Fragment implements IAreasFragmentView {

    View rootView;
    RecyclerView rv;
    ProgressBar progressBar;
    AreasListAdapter areasListAdapter;
    IAreasPresenter presenter;

    private OnFragmentInteractionListener mListener;

    public AreasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BushesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AreasFragment newInstance(String param1, String param2) {
        AreasFragment fragment = new AreasFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        areasListAdapter = new AreasListAdapter(getActivity(), null);
        rv.setAdapter(areasListAdapter);
        rv.setHasFixedSize(true);
        presenter = new AreasPresenter();
        presenter.init(this);
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
    public void setAreasListAdapter(List<Area> list) {
        areasListAdapter.swapList(list);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
