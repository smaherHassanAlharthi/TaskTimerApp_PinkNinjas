package com.smaher.tasktimerapp_pinkninjas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.smaher.tasktimerapp_pinkninjas.adapters.RVAdapter
import com.smaher.tasktimerapp_pinkninjas.adapters.StatAdapter
import com.smaher.tasktimerapp_pinkninjas.databinding.FragmentHomeBinding
import com.smaher.tasktimerapp_pinkninjas.databinding.FragmentStatisticsBinding

class StatisticsFragment : Fragment() {
    private lateinit var rvAdapter:StatAdapter
    private var _binding: FragmentStatisticsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    //declare tasks view model
    lateinit var myViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view: View
        //for binding view
        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        view = binding.root

        /*when the fragment get the ViewModelProvider, it received the same
        SharedViewModel instance,*/
        myViewModel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)
        setRV()
        //we get the list of tasks from the database
        myViewModel.tasks.observe(viewLifecycleOwner, {list->
            list?.let { rvAdapter.update(it) }
        })
        binding.btBackStat.setOnClickListener{
            findNavController().navigate(R.id.action_statisticsFragment_to_homeFragment)
        }


        return view
    }

    fun setRV(){
        rvAdapter = StatAdapter( requireContext() as MainActivity,this)
        binding.rvList.adapter = rvAdapter
        binding.rvList.layoutManager = GridLayoutManager(context,3)
    }


}