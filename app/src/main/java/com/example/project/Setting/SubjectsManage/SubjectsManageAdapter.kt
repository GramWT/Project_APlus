package com.example.project.Setting.SubjectsManage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.project.DataBase.model.Subject
import com.example.project.databinding.CustomSubjectManageBinding
import kotlinx.android.synthetic.main.custom_subject_manage.view.*

class SubjectsManageAdapter : RecyclerView.Adapter<SubjectsManageAdapter.MyViewHolder>() {

    private var subjectList = emptyList<Subject>()

    private lateinit var binding: CustomSubjectManageBinding

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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
            val action = SubjectsManageDirections.actionSubjectsManageNavToSubjectsManageUpdate(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return subjectList.size
    }

    fun setData(subject: List<Subject>) {
        this.subjectList = subject
        notifyDataSetChanged()
    }


}
