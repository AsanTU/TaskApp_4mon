package io.muhsin.taskapp.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.muhsin.taskapp.App
import io.muhsin.taskapp.R
import io.muhsin.taskapp.databinding.FragmentTaskBinding
import io.muhsin.taskapp.model.Task

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private var task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            task = arguments?.getSerializable(TASK_KEY) as Task
            binding.etTitle.setText(task?.title)
            binding.etDesc.setText(task?.desc)
            binding.btnSave.text = getString(R.string.update)
        }

        binding.btnSave.setOnClickListener {
            if (arguments != null) {
                update()
            } else save()
        }
    }

    private fun update() {
        task?.title = binding.etTitle.text.toString()
        task?.desc = binding.etDesc.text.toString()
        task?.let { App.db.taskDao().update(it) }
        findNavController().navigateUp()
    }

    private fun save() {
        val data = Task(
            title = binding.etTitle.text.toString(),
            desc = binding.etDesc.text.toString(),
        )
        App.db.taskDao().insert(data)
        findNavController().navigateUp()
    }

    companion object {
        //const val TASK_REQUEST = "task.requestKey"
        const val TASK_KEY = "task"

    }
}