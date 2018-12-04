package com.example.elina.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.elina.weatherapp.pojoClasses.Cities;
import com.example.elina.weatherapp.pojoClasses.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private final Intent intent;
    private final Context context;
    private final LayoutInflater inflater;
    private final List<Cities> cities;

    Integer position;

    public CityAdapter(Context context, List<Cities> cities) {
        this.context = context;
        this.cities = cities;
        this.inflater = LayoutInflater.from(context);
        intent = new Intent(context, ActivityTwo.class);
        setHasStableIds(true);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Intent intent = new Intent();
        holder.cityName.setText();
        holder.cityTemp.setText();
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return cities.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView cityName, cityTemp;

        ViewHolder(View itemView) {
            super(itemView);
            cityName = itemView.findViewById(R.id.cityName);
            cityTemp = itemView.findViewById(R.id.cityTemp);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            position = getAdapterPosition();
            intent.putExtra("name", cities.get(position).getCity());
            intent.putExtra("name", cities.get(position);
        }
    }
}
