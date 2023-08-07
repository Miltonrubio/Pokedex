package com.example.pruebacrud2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;


import com.example.pruebacrud2.Models.Pokemon;
import com.example.pruebacrud2.pokeapi.PokeApiService;

import java.util.ArrayList;

import retrofit2.Call;

public class ListaPokemonAdapter extends RecyclerView.Adapter<ListaPokemonAdapter.ViewHolder> {


private ArrayList<Pokemon> dataset;

private Context context;

public ListaPokemonAdapter(Context context){
this.context =context;
    dataset = new ArrayList<>();
}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
     View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon,parent, false);

        return  new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pokemon p = dataset.get(position);

        holder.Titulo.setText(p.getName());

        holder.Numero.setText("No." + p.getNumber());

        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + p.getNumber() + ".png")
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.Imagen);




    }



    @Override
    public int getItemCount(){
        return dataset.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView Imagen;
        private TextView Titulo, Numero, Tipo;



        public ViewHolder(View itemView){
            super(itemView);
                    Imagen= itemView.findViewById(R.id.imageView1);
                    Titulo=itemView.findViewById(R.id.textView1);

            Numero=itemView.findViewById(R.id.textView);

        }

    }


 public void adicionarListaPokemon(ArrayList<Pokemon> listaPokemon){
dataset.addAll(listaPokemon);
notifyDataSetChanged();
 }

}
