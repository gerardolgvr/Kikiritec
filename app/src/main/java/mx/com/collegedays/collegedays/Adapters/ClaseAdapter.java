package mx.com.collegedays.collegedays.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import io.realm.RealmResults;
import mx.com.collegedays.collegedays.Models.Clase;
import mx.com.collegedays.collegedays.R;

/**
 * Created by gerardo on 24/04/17.
 */

public class ClaseAdapter extends BaseAdapter{

    private Context context;
    private List<Clase> clases;
    private int layout;

    public ClaseAdapter(Context context, List<Clase> clases, int layout){
        this.context = context;
        this.clases = clases;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return clases.size();
    }

    @Override
    public Clase getItem(int position) {
        return clases.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_view_clase_item, null);

            vh = new ViewHolder();

            vh.nombreClase = (TextView) convertView.findViewById(R.id.textViewClaseNombre);
            vh.horaClase = (TextView) convertView.findViewById(R.id.textViewClaseHora);
            vh.duracion = (TextView) convertView.findViewById(R.id.textViewClaseDuracion) ;


            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        Clase clase = clases.get(position);

        vh.nombreClase.setText(clase.getNombreDeClase());
        vh.horaClase.setText(clase.getHoraClase());
        vh.duracion.setText(clase.getDuracion());

        return convertView;
    }

    public class ViewHolder{
        TextView nombreClase;
        TextView horaClase;
        TextView duracion;

    }

    public void setData(RealmResults<Clase> clases) {
        this.clases = clases;
        notifyDataSetChanged();
    }
}
