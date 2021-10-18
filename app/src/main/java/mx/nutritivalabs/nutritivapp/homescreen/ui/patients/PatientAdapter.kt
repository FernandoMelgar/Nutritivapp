package mx.nutritivalabs.nutritivapp.homescreen.ui.patients

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import mx.nutritivalabs.nutritivapp.R
import mx.nutritivalabs.nutritivapp.patient.Patient

class PatientAdapter(var patientArray: ArrayList<Patient>) : RecyclerView.Adapter<PatientAdapter.PatientViewHolder>() {

    private lateinit var rowView: View

    var listener: RowListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PatientAdapter.PatientViewHolder {
        rowView =LayoutInflater.from(parent.context).inflate(R.layout.patient_row, parent, false)
        return PatientViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: PatientAdapter.PatientViewHolder, position: Int) {
        holder.set(patientArray[position])

        val row = holder.itemView.findViewById<LinearLayout>(R.id.rowLayout)
        row.setOnClickListener {
            //TODO show the patient fragment with more info
        }
    }

    override fun getItemCount(): Int {
        return patientArray.size
    }

    class PatientViewHolder(view: View): RecyclerView.ViewHolder(view) {
        //TODO implement
        fun set(patient: Patient){

        }
    }

}