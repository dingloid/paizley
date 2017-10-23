package grayteam.paizley_customerapp.fragments;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import grayteam.paizley_customerapp.MenuItemDetailActivity;
import grayteam.paizley_customerapp.R;
import grayteam.paizley_customerapp.models.MenuItem;
import grayteam.paizley_customerapp.viewHolder.MenuItemViewHolder;

public abstract class MenuItemListFragment extends Fragment {

    protected String menuSection;

    // [START define_database_reference]
    private DatabaseReference database;
    // [END define_database_reference]

    private FirebaseRecyclerAdapter<MenuItem, MenuItemViewHolder> adapter;
    private RecyclerView recycler;
    private LinearLayoutManager manager;

    public MenuItemListFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstances){
        super.onCreateView(inflater, container, savedInstances);
        View rootView = inflater.inflate(R.layout.menu_all_items, container, false);

        database = FirebaseDatabase.getInstance().getReference();

        recycler = (RecyclerView) rootView.findViewById(R.id.menu_item_list);
        recycler.setHasFixedSize(true);

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (adapter != null) {
            adapter.cleanup();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set up Layout Manager, reverse layout
        manager = new LinearLayoutManager(getActivity());
        manager.setReverseLayout(true);
        manager.setStackFromEnd(true);
        recycler.setLayoutManager(manager);

        // Set up FirebaseRecyclerAdapter with the Query
        Query postsQuery = getQuery(database);
        adapter = new FirebaseRecyclerAdapter<MenuItem, MenuItemViewHolder>(MenuItem.class, R.layout.menu_item,
                MenuItemViewHolder.class, postsQuery) {
            @Override
            protected void populateViewHolder(final MenuItemViewHolder viewHolder, final MenuItem model, final int position) {
                final DatabaseReference postRef = getRef(position);

                // Set click listener for the whole post view
                final String postKey = postRef.getKey();
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Launch PostDetailActivity
                        Intent intent = new Intent(getActivity(), MenuItemDetailActivity.class);
                        intent.putExtra(MenuItemDetailActivity.EXTRA_POST_KEY, postKey);
                        intent.putExtra("sectionName", menuSection);
                        startActivity(intent);
                    }
                });

//                // Bind Post to ViewHolder, setting OnClickListener for the star button
//                viewHolder.bindToPost(model, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View starView) {
//                        // Need to write to both places the post is stored
////                        DatabaseReference globalPostRef = mDatabase.child(mClassName + "/posts").child(postRef.getKey());
////                        DatabaseReference userPostRef = mDatabase.child(mClassName + "/user-posts").child(model.uid).child(postRef.getKey());
//                    }
//                });
            }
        };
        recycler.setAdapter(adapter);
    }


    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public abstract Query getQuery(DatabaseReference databaseReference);

    public void setSectionName(String section){
        menuSection = section;
    }

}
