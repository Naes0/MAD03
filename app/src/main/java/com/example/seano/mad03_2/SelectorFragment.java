package com.example.seano.mad03_2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class SelectorFragment extends Fragment
{
    private RecyclerView selectorRecyclerView;
    private SelectAdapter selectAdapter;

    private class SelectViewHolder extends RecyclerView.ViewHolder
    {
        public SelectViewHolder(LayoutInflater li, ViewGroup parent)
        {
            super(li.inflate(R.layout.list_selection, parent, false));
        }
    }

    private class SelectAdapter extends RecyclerView.Adapter<SelectViewHolder>
    {
        private List<Structure> selectorList;

        public SelectAdapter(List<Structure> inSelectorList)
        {
            selectorList = inSelectorList;
        }


        @Override
        public SelectViewHolder onCreateViewHolder( ViewGroup parent, int viewType)
        {
            LayoutInflater li = LayoutInflater.from(getActivity());
            return new SelectViewHolder(li, parent);
        }

        @Override
        public void onBindViewHolder( SelectViewHolder holder, int position)
        {
            //holder.bind(selectorList.get(position));
        }

        @Override
        public int getItemCount()
        {
            return selectorList.size();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle)
    {
        View view = inflater.inflate(R.layout.fragment_selector, ui, false);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.selectorRecyclerView);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}
