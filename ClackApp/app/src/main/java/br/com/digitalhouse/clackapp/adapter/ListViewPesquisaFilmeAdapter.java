package br.com.digitalhouse.clackapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.digitalhouse.clackapp.Elementos.Movie;
import br.com.digitalhouse.clackapp.R;

public class ListViewPesquisaFilmeAdapter extends BaseAdapter {

    // Declarando as variaveis
    Context mContext;
    LayoutInflater inflater;
    private List<Movie> movieList = null;
    private ArrayList<Movie> arraylist;

    public ListViewPesquisaFilmeAdapter (Context context, List<Movie> movieList){
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Movie>();
        this.arraylist.addAll(movieList);
        this.movieList = movieList;
    }

    public class ViewHolder{

        ImageView image;
        TextView nome;
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.pesquisa_list_view_itens,null);

            //Colocando o TextView e a Imagem no XML do ListView
            holder.image = (ImageView) view.findViewById(R.id.image_movie_label_id);
            holder.nome = (TextView) view.findViewById(R.id.name_movie_label_id);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        //Setando os resultados da Imagem e do TextView
        holder.nome.setText(movieList.get(position).getNome());
        Glide
                .with(mContext)
                .load(movieList.get(position).getPoster())
                .apply(new RequestOptions().override(400, 200))
                .into(holder.image);

        return view;
    }

    //Classe para filtrar
    public void filter (String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        movieList.clear();

        if (charText.length() == 0){
            movieList.addAll(arraylist);
        }else {
            for (Movie movie : arraylist) {
                if (movie.getNome().toLowerCase(Locale.getDefault()).contains(charText)){
                    movieList.add(movie);
                }

            }

        }
        notifyDataSetChanged();
    }
}
