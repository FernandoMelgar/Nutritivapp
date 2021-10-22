package mx.nutritivalabs.nutritivapp.homescreen.ui.patients

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.nutritivalabs.nutritivapp.R
import mx.nutritivalabs.nutritivapp.patient.Patient

class PatientsListAdapter(var patientsArray: ArrayList<Patient>) : RecyclerView.Adapter<PatientsListAdapter.PatientViewHolder>() {

    private lateinit var rowView: View

    var listener: RowListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PatientViewHolder {
        rowView = LayoutInflater.from(parent.context).inflate(R.layout.patient_row, parent, false)
        return PatientViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        holder.set(patientsArray[position])
        // Listener
        val row = holder.itemView.findViewById<LinearLayout>(R.id.rowLayout)
        row.setOnClickListener {
            listener?.rowClick(position)
            // TODO show the patient fragment with more info
        }
    }

    override fun getItemCount(): Int {
        return patientsArray.size
    }

    // Each box (row) of the RecyclerView
    class PatientViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val tvPatientName = view.findViewById<TextView>(R.id.tvPatientName)
        private val tvMeetingInfo = view.findViewById<TextView>(R.id.tvMeetingInfo)
        // TODO give more patient info if necessary
        @SuppressLint("SetTextI18n")
        fun set(patient: Patient){
            tvPatientName.text = "${patient.firstName}  ${patient.paternalLastName}"
            tvMeetingInfo.text = "12:00 pm | Zoom"    // TODO dynamic info
        }
    }

}