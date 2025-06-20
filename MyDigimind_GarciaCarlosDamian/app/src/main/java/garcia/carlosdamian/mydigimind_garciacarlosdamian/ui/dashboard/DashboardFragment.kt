package garcia.carlosdamian.mydigimind_garciacarlosdamian.ui.dashboard

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import garcia.carlosdamian.mydigimind_garciacarlosdamian.R
import garcia.carlosdamian.mydigimind_garciacarlosdamian.Recordatorio
import garcia.carlosdamian.mydigimind_garciacarlosdamian.databinding.FragmentDashboardBinding
import garcia.carlosdamian.mydigimind_garciacarlosdamian.ui.home.HomeFragment
import java.text.SimpleDateFormat
import java.util.Calendar


class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    private lateinit var btnSave: Button
    private lateinit var etTitulo: EditText

    private lateinit var checkMonday: CheckBox
    private lateinit var checkTuesday: CheckBox
    private lateinit var checkWednesday: CheckBox
    private lateinit var checkThursday: CheckBox
    private lateinit var checkFriday: CheckBox
    private lateinit var checkSaturday: CheckBox
    private lateinit var checkSunday: CheckBox
    private lateinit var btnTime: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel =
            ViewModelProvider(owner = this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        btnSave = root.findViewById(R.id.btn_save)
        etTitulo = root.findViewById(R.id.etTitulo)
        checkMonday = root.findViewById(R.id.checkboxMonday)
        checkTuesday = root.findViewById(R.id.checkboxTuesday)
        checkWednesday = root.findViewById(R.id.checkboxWednesday)
        checkThursday = root.findViewById(R.id.checkboxThursday)
        checkFriday = root.findViewById(R.id.checkboxFriday)
        checkSaturday = root.findViewById(R.id.checkboxSaturday)
        checkSunday = root.findViewById(R.id.checkboxSunday)
        btnTime = root.findViewById(R.id.btn_time)

        btnTime.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

                btnTime.text = SimpleDateFormat("HH:mm", java.util.Locale.getDefault()).format(cal.time)
            }
            TimePickerDialog(
                root.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE), true
            ).show()

        }

        btnSave.setOnClickListener {
            if (checkFields()) {
                val titulo = etTitulo.text.toString()
                val tiempo = btnTime.text.toString()
                val dias = ArrayList<String>()

                if (checkMonday.isChecked) dias.add("mon")
                if (checkTuesday.isChecked) dias.add("tue")
                if (checkWednesday.isChecked) dias.add("wed")
                if (checkThursday.isChecked) dias.add("thu")
                if (checkFriday.isChecked) dias.add("fri")
                if (checkSaturday.isChecked) dias.add("sat")
                if (checkSunday.isChecked) dias.add("sun")

                val tarea = Recordatorio(titulo, dias.toString(), tiempo)

                HomeFragment.listaRecordatorios.add(tarea)
                Toast.makeText(root.context, "Nueva tarea añadida", Toast.LENGTH_SHORT).show()
                cleanFields() // Limpia los campos después de guardar exitosamente
            }
        }

        return root
    }


    private fun checkFields(): Boolean {

        if (etTitulo.length() >= 20){
            Toast.makeText(context, "El titulo es muy largo.", Toast.LENGTH_SHORT).show()
            return false
        }

        if (etTitulo.text.isNullOrBlank()) {
            Toast.makeText(context, "Por favor, ingresa un título.", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!checkMonday.isChecked &&
            !checkTuesday.isChecked &&
            !checkWednesday.isChecked &&
            !checkThursday.isChecked &&
            !checkFriday.isChecked &&
            !checkSaturday.isChecked &&
            !checkSunday.isChecked
        ) {
            Toast.makeText(context, "Por favor, selecciona al menos un día.", Toast.LENGTH_SHORT).show()
            return false
        }

        if (btnTime.text == "Set Time") {
            Toast.makeText(context, "Por favor, selecciona una hora.", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }


    private fun cleanFields() {
        etTitulo.text.clear()

        checkMonday.isChecked = false
        checkTuesday.isChecked = false
        checkWednesday.isChecked = false
        checkThursday.isChecked = false
        checkFriday.isChecked = false
        checkSaturday.isChecked = false
        checkSunday.isChecked = false

        btnTime.text = getString(R.string.set_time)
    }

}