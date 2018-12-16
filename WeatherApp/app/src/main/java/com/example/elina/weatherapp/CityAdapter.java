package com.example.elina.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.elina.weatherapp.pojoClasses.Info;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private final Intent intent;
    private final Context context;
    private final LayoutInflater inflater;
    private final java.util.List<Info> cities;

    public CityAdapter(Context context, java.util.List<Info> cities) {
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
        Info list = cities.get(position);
        holder.cityName.setText(list.getName().toString());
        holder.cityTemp.setText(list.getMain().getTemp().toString());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return cities.size();
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
            intent.putExtra("name", cities.get(getAdapterPosition()).getName());
            intent.putExtra("temp", cities.get(getAdapterPosition()).getMain().getTemp());
            intent.putExtra("pressure", cities.get(getAdapterPosition()).getMain().getPressure());
            intent.putExtra("humidity", cities.get(getAdapterPosition()).getMain().getHumidity());
            intent.putExtra("wind", cities.get(getAdapterPosition()).getWind());
        }
    }
}
