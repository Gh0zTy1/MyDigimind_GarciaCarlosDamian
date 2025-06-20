package garcia.carlosdamian.mydigimind_garciacarlosdamian.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import garcia.carlosdamian.mydigimind_garciacarlosdamian.R
import garcia.carlosdamian.mydigimind_garciacarlosdamian.Recordatorio

class HomeFragment : Fragment() {

    private lateinit var gridView: GridView
    private lateinit var adapter: GridViewAdapter
    private val listaRecordatorios = mutableListOf<Recordatorio>()
    private var inicializada = false

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        gridView = root.findViewById(R.id.gridViewRecordatorios)

        if (!inicializada) {
            listaRecordatorios.add(Recordatorio("Practice", "Everyday", "17:00"))
            listaRecordatorios.add(Recordatorio("Practice", "Everyday", "17:00"))
            listaRecordatorios.add(Recordatorio("Practice", "Everyday", "17:00"))
            listaRecordatorios.add(Recordatorio("Practice", "Everyday", "17:00"))
            listaRecordatorios.add(Recordatorio("Practice", "Everyday", "17:00"))
            listaRecordatorios.add(Recordatorio("Practice", "Everyday", "17:00"))
            listaRecordatorios.add(Recordatorio("Practice", "Everyday", "17:00"))
            listaRecordatorios.add(Recordatorio("Practice", "Everyday", "17:00"))
            listaRecordatorios.add(Recordatorio("Practice", "Everyday", "17:00"))
            inicializada = true
        }

        adapter = GridViewAdapter(requireContext(), listaRecordatorios)
        gridView.adapter = adapter

        return root
    }


    private class GridViewAdapter(private val context: Context, private val listaRecordatorios: List<Recordatorio>) : BaseAdapter() {

        override fun getCount(): Int{
            return listaRecordatorios.size
        }

        override fun getItem(position: Int): Any {
            return listaRecordatorios[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val recordatorio = listaRecordatorios[position]
            val inflater = LayoutInflater.from(context)
            val vista = convertView ?: inflater.inflate(R.layout.recordatorio, parent, false)

            val txtNombre: TextView = vista.findViewById(R.id.txtNombreRecordatorio)
            val txtDias: TextView = vista.findViewById(R.id.txtDiasRecordatorio)
            val txtTiempo: TextView = vista.findViewById(R.id.txtTiempoRecordatorio)

            txtNombre.text = recordatorio.nombre
            txtDias.text = recordatorio.dias
            txtTiempo.text = recordatorio.tiempo

            return vista
        }
    }
}