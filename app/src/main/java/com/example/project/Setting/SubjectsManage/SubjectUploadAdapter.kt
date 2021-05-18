package com.example.project.Setting.SubjectsManage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.project.DataBase.model.Subject
import com.example.project.DataBase.model.SubjectUpload
import com.example.project.databinding.CustomSubjectManageBinding
import kotlinx.android.synthetic.main.custom_subject_manage.view.*

class SubjectUploadAdapter : RecyclerView.Adapter<SubjectUploadAdapter.MyViewHolder>() {

    private var subjectList = emptyList<SubjectUpload>()

    private lateinit var binding: CustomSubjectManageBinding

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }


    override fun getItemCount(): Int {
        return subjectList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = CustomSubjectManageBinding.inflate(LayoutInflater.from(parent.context))

        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = subjectList[position]

        holder.itemView.code_sjm.text = currentItem.sid
        holder.itemView.name_sjm.text = currentItem.Name

        holder.itemView.row_manage_subject.setOnClickListener {
            val action = SubjectsManageUploadPDFDirections.actionSubjectsManageUploadPDFToSubjectUploadAdd(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    fun setData(subject: List<SubjectUpload>) {
        this.subjectList = subject
        notifyDataSetChanged()
    }
}