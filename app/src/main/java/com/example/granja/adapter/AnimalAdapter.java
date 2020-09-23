package com.example.granja.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import com.example.granja.R;
import com.example.granja.dto.Animal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimalAdapter extends BaseAdapter implements Filterable {
    private final Context context;
    private final LayoutInflater inflater;
    private List<Animal> animalesOut;
    private List<Animal> animalesIn;

    public AnimalAdapter(Context context, List<Animal> listaAnimales) {
        this.context = context;
        animalesOut = listaAnimales;
        animalesIn = listaAnimales;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {

        return animalesOut.size();
    }

    @Override
    public Object getItem(int i) {
        return animalesOut.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.animal_item_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.txtNombreAnimal.setText(animalesOut.get(i).getNombre());
        holder.ivAnimal.setImageResource(animalesOut.get(i).getImagen());
        holder.txtDescripcionAnimal.setText(animalesOut.get(i).getDescripcion());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                animalesOut = (List<Animal>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<Animal> FilteredArrList = new ArrayList<>();
                if (animalesIn == null) {
                    animalesIn = new ArrayList<>(animalesOut);
                }
                if (constraint == null || constraint.length() == 0) {
                    results.count = animalesIn.size();
                    results.values = animalesIn;
                } else {

                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < animalesIn.size(); i++) {
                        String data = animalesIn.get(i).getNombre();
                        if (data.toLowerCase().contains(constraint.toString())) {
                            FilteredArrList.add(animalesIn.get(i));
                        }
                    }
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }


    class ViewHolder{
        @BindView(R.id.txtNombreAnimal)
        TextView txtNombreAnimal;
        @BindView(R.id.ivAnimal)
        ImageView ivAnimal;
        @BindView(R.id.txtDescripcionAnimal)
        TextView txtDescripcionAnimal;
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
