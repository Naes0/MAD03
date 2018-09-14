package com.example.seano.mad03_2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class SelectorFragment extends Fragment
{
    StructureData structData;

    @Override
    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        structData = StructureData.get();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup ui, Bundle bundle)
    {
        View view = inflater.inflate(R.layout.fragment_selector, ui, false);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.selectorRecyclerView);
        rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        SelectAdapter adapter = new SelectAdapter();
        rv.setAdapter(adapter);
        return view;
    }

    private class SelectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        Structure currStruct;
        ImageView image;
        TextView text;

        public SelectViewHolder(LayoutInflater li, ViewGroup parent)
        {
            super(li.inflate(R.layout.list_selection, parent, false));
            image = (ImageView) itemView.findViewById(R.id.imageView);
            text = (TextView) itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        public void bind(Structure data)
        {
            image.setImageResource(data.getDrawableId());
            text.setText(data.getLabel());
        }

        @Override
        public void onClick(View view)
        {
            Toast.makeText(getActivity(),  )
        }
    }

    public class SelectAdapter extends RecyclerView.Adapter<SelectViewHolder>
    {
        @Override
        public SelectViewHolder onCreateViewHolder( ViewGroup parent, int viewType)
        {
            LayoutInflater li = LayoutInflater.from(getActivity());
            return new SelectViewHolder(li, parent);
        }

        @Override
        public void onBindViewHolder( SelectViewHolder holder, int position)
        {
            holder.bind(structData.get(position));
        }

        @Override
        public int getItemCount()
        {
            return structData.size();
        }
}


}
