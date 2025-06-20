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

    private var adapter: GridViewAdapter? = null

    companion object{
        val listaRecordatorios = ArrayList<Recordatorio>()
        var first = true
    }


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val gridView: GridView = root.findViewById(R.id.gridViewRecordatorios)

        if (first){
            filltareas()
            first = false
        }

        adapter = GridViewAdapter(requireContext(), listaRecordatorios)
        gridView.adapter = adapter

        return root
    }

    fun filltareas(){
        listaRecordatorios.add(Recordatorio("Practice 1", arrayListOf("Tus").toString(), "17:30"))
        listaRecordatorios.add(Recordatorio("Practice 2", arrayListOf("Mon", "Sun").toString(), "17:40"))
        listaRecordatorios.add(Recordatorio("Practice 3", arrayListOf("Wed").toString(), "14:00"))
        listaRecordatorios.add(Recordatorio("Practice 4", arrayListOf("Sat").toString(), "11:00"))
        listaRecordatorios.add(Recordatorio("Practice 5", arrayListOf("Fri").toString(), "13:00"))
    }

    private class GridViewAdapter() : BaseAdapter() {
        var listaTareas = ArrayList<Recordatorio>()
        var context: Context? = null

        constructor(contexto: Context, tareas: ArrayList<Recordatorio>) : this() {
            this.context = contexto
            this.listaTareas = tareas
        }


        override fun getCount(): Int {
            return listaTareas.size
        }

        override fun getItem(p0: Int): Any {
            return listaTareas[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var tarea = listaTareas[p0]
            var inflador = LayoutInflater.from(this.context)
            var vista = inflador.inflate(R.layout.recordatorio, null)

            val nombre: TextView = vista.findViewById(R.id.tvNombreRecordatorio)
            val tiempo: TextView = vista.findViewById(R.id.tvTiempoRecordatorio)
            val dias: TextView = vista.findViewById(R.id.tvDiasRecordatorio)

            nombre.setText(tarea.nombre)
            tiempo.setText(tarea.tiempo)
            dias.setText(tarea.dias.toString())

            return vista
        }
    }
}