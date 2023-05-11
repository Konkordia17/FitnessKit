package com.example.fitnesskit.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnesskit.R
import com.example.fitnesskit.app.App
import com.example.fitnesskit.databinding.FragmentTrainingScheduleBinding
import com.example.fitnesskit.di.AppComponent
import com.example.fitnesskit.presentation.adapter.TrainListAdapter
import javax.inject.Inject

class TrainingScheduleFragment : Fragment(R.layout.fragment_training_schedule) {
    private var _binding: FragmentTrainingScheduleBinding? = null
    private val binding get() = _binding!!

    private lateinit var trainsAdapter: TrainListAdapter
    private lateinit var appComponent: AppComponent

    @Inject
    lateinit var vmFactory: TrainingScheduleViewModelFactory
    lateinit var vm: TrainingScheduleViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent = (activity?.applicationContext as App).appComponent
        appComponent.injectTrainingScheduleFragment(this)
        vmFactory = appComponent.getViewModelFactory()
        vm = ViewModelProvider(this, vmFactory)[TrainingScheduleViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrainingScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        observeLaveData()
    }

    private fun observeLaveData() {
        vm.trainingList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.sportAnimation.pauseAnimation()
                binding.sportAnimation.visibility = View.GONE
            }
            trainsAdapter.submitList(it)
        }
    }

    private fun initList() {
        trainsAdapter = TrainListAdapter()
        with(binding.trainingList) {
            adapter = trainsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val dividerItemDecoration =
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
            setHasFixedSize(true)
        }
    }
}