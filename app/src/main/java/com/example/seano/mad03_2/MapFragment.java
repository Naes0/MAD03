package com.example.seano.mad03_2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.List;

public class MapFragment extends Fragment
{
    private MapData map;

    @Override
    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        map = MapData.get();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle)
    {
        View view = inflater.inflate(R.layout.fragment_map, ui,false);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.mapRecyclerView);
        rv.setLayoutManager(new GridLayoutManager(getActivity(), MapData.HEIGHT, GridLayoutManager.HORIZONTAL, false));
        GridAdapter adapter = new GridAdapter();
        rv.setAdapter(adapter);
        return view;
    }

    private class GridViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imageView1;
        private ImageView imageView2;
        private ImageView imageView3;
        private ImageView imageView4;
        private ImageView imageView5;
        
        public GridViewHolder(LayoutInflater li, ViewGroup parent)
        {
            super(li.inflate(R.layout.grid_cell, parent, false));

            int size = parent.getMeasuredHeight() / MapData.HEIGHT+2;
            ViewGroup.LayoutParams lp = itemView.getLayoutParams();
            lp.width = size;
            lp.height = size;

            imageView1 = (ImageView) itemView.findViewById(R.id.imageView1);
            imageView2 = (ImageView) itemView.findViewById(R.id.imageView2);
            imageView3 = (ImageView) itemView.findViewById(R.id.imageView3);
            imageView4 = (ImageView) itemView.findViewById(R.id.imageView4);
            imageView5 = (ImageView) itemView.findViewById(R.id.imageView5);

        }

        public void bind(MapElement mapEle)
        {
            imageView1.setImageResource(mapEle.getNorthWest());
            imageView2.setImageResource(mapEle.getNorthEast());
            imageView3.setImageResource(mapEle.getSouthWest());
            imageView4.setImageResource(mapEle.getSouthEast());
            if (mapEle.getStructure() != null)
            {
                imageView5.setImageResource(mapEle.getStructure().getDrawableId());
            }
        }
    }

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
}
