package io.muhsin.taskapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.muhsin.taskapp.App
import io.muhsin.taskapp.R
import io.muhsin.taskapp.databinding.FragmentHomeBinding
import io.muhsin.taskapp.model.Task
import io.muhsin.taskapp.ui.home.adapter.TaskAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val adapter = TaskAdapter(onLongClick = this::onLongClick, onClick = this::onClick)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding?.root ?: binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        val list = App.db.taskDao().getAll()
        adapter.addTasks(list)
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }
    private fun onClick(task: Task) {
        val bundle = Bundle()/*
        bundle.putString("title", task.title)
        bundle.putString("desc", task.desc)*/
        bundle.putSerializable("task", task)
        findNavController().navigate(R.id.taskFragment, bundle)
    }


    private fun onLongClick(task: Task) {
        Log.e("ololo", "onLongClick: ", )
    }
}