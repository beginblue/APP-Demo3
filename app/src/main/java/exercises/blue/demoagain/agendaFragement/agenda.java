package exercises.blue.demoagain.agendaFragement;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import exercises.blue.demoagain.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link agenda.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link agenda#newInstance} factory method to
 * create an instance of this fragment.
 */
public class agenda extends Fragment {


    private static final String TAG = "agenda";
    agendaRecyclerAdapter mAdapter = new agendaRecyclerAdapter(new agendaRecyclerAdapter.myOnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            Toast.makeText(getContext(), "Hello world" + position, Toast.LENGTH_SHORT).show();
        }
    }, new longClickAdapter());

    public class longClickAdapter implements agendaRecyclerAdapter.myOnItemLongClickListener {

        @Override
        public void onItemLongClick(View v, final int position) {
            PopupMenu popupMenu = new PopupMenu(getContext(), v);
            popupMenu.inflate(R.menu.on_angenda_item_click_menu);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.agenda_menu_add:
                            mAdapter.addItem(position);
                            return true;
                        case R.id.agenda_menu_del:
                            mAdapter.removeItem(position);
                            return true;
                        default:
                            return true;
                    }
                }
            });
            popupMenu.show();
        }
    }

    private OnFragmentInteractionListener mListener;

    public agendaRecyclerAdapter getAdapter() {
        return mAdapter;
    }

    public agenda() {
        // Required empty public constructor
    }


    public static agenda newInstance() {
        agenda fragment = new agenda();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agenda, container, false);
        RecyclerView rec = (RecyclerView) view.findViewById(R.id.agenda_list);
        rec.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        rec.setAdapter(mAdapter);
        rec.setItemAnimator(new DefaultItemAnimator());
        rec.addItemDecoration(new aDividerItemDecoration());
        return view;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
