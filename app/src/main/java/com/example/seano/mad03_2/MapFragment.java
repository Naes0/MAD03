package com.example.seano.mad03_2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class MapFragment extends Fragment
{
    private MapData map;
    private SelectorFragment selector;
    private GridAdapter adapter;

    @Override
    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        map = MapData.get();
        adapter = new GridAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle)
    {
        View view = inflater.inflate(R.layout.fragment_map, ui,false);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.mapRecyclerView);
        rv.setLayoutManager(new GridLayoutManager(getActivity(), MapData.HEIGHT, GridLayoutManager.HORIZONTAL, false));
        adapter = new GridAdapter();
        rv.setAdapter(adapter);
        return view;
    }

    private class GridViewHolder extends RecyclerView.ViewHolder
    {
        boolean buildable;
        private ImageView imageView1;
        private ImageView imageView2;
        private ImageView imageView3;
        private ImageView imageView4;
        private ImageView imageView5;

        //Viewholder
        public GridViewHolder(LayoutInflater li, ViewGroup parent)
        {
            super(li.inflate(R.layout.grid_cell, parent, false));

            int size = parent.getMeasuredHeight() / MapData.HEIGHT+1;
            ViewGroup.LayoutParams lp = itemView.getLayoutParams();
            lp.width = size;
            lp.height = size;

            imageView1 = (ImageView) itemView.findViewById(R.id.imageView1);
            imageView2 = (ImageView) itemView.findViewById(R.id.imageView2);
            imageView3 = (ImageView) itemView.findViewById(R.id.imageView3);
            imageView4 = (ImageView) itemView.findViewById(R.id.imageView4);
            imageView5 = (ImageView) itemView.findViewById(R.id.imageView5);
            buildable = false;

            imageView5.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Structure selection = selector.getStructure();
                    if( selection != null && buildable)
                    {
                        imageView5.setImageResource(selection.getDrawableId());
                        adapter.notifyItemChanged(getAdapterPosition());
                    }
                }
            });
        }

        public void bind(MapElement mapEle)
        {
            imageView1.setImageResource(mapEle.getNorthWest());
            imageView2.setImageResource(mapEle.getNorthEast());
            imageView3.setImageResource(mapEle.getSouthWest());
            imageView4.setImageResource(mapEle.getSouthEast());
            buildable = mapEle.isBuildable();
        }
    }

    //adapter
    public class GridAdapter extends RecyclerView.Adapter<GridViewHolder>
    {
        @Override
        public GridViewHolder onCreateViewHolder( ViewGroup parent, int viewType)
        {
            LayoutInflater li = LayoutInflater.from(getActivity());
            return new GridViewHolder(li, parent);
        }

        @Override
        public void onBindViewHolder(GridViewHolder holder, int position)
        {
            int row = position % MapData.HEIGHT;
            int col = position / MapData.HEIGHT;
            holder.bind(map.get(row, col));
        }

        @Override
        public int getItemCount()
        {
            return MapData.HEIGHT*MapData.WIDTH;
        }
    }

    public void setSelector(Fragment selFrag)
    {
        selector = (SelectorFragment) selFrag;
    }
}
