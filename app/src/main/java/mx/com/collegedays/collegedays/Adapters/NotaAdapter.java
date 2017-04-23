package mx.com.collegedays.collegedays.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import mx.com.collegedays.collegedays.Models.Nota;
import mx.com.collegedays.collegedays.R;

/**
 * Created by gerardo on 22/04/17.
 */

public class NotaAdapter extends BaseAdapter{

    private Context context;
    private List<Nota> notas;
    private int layout;

    public NotaAdapter(Context context, List<Nota> notas, int layout){
        this.context = context;
        this.notas = notas;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return notas.size();
    }

    @Override
    public Object getItem(int position) {
        return notas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_view_nota_item, null);
            vh = new ViewHolder();

            vh.txtTituloNota = (TextView) convertView.findViewById(R.id.textViewNotaTitulo);
            vh.txtContenidoNota = (TextView) convertView.findViewById(R.id.textViewNotaContenido);
            vh.txtHoraNota = (TextView) convertView.findViewById(R.id.textViewNotaHora);
            vh.txtFechaNota = (TextView) convertView.findViewById(R.id.textViewNotaFecha);

            convertView.setTag(vh);;
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        Nota nota = notas.get(position);

        vh.txtTituloNota.setText(nota.getTituloDeNota());
        String contenido = (nota.getContenido().length() > 53)? nota.getContenido().substring(0, 53) +" ...":nota.getContenido();
        vh.txtContenidoNota.setText(contenido);
        vh.txtHoraNota.setText(nota.getHoraCreacion());
        vh.txtFechaNota.setText(nota.getFechaCreacion());

        return convertView;
    }

    public class ViewHolder {
        TextView txtTituloNota;
        TextView txtContenidoNota;
        TextView txtHoraNota;
        TextView txtFechaNota;

    }
}
