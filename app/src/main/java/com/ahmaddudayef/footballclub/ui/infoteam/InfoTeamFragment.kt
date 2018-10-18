package com.ahmaddudayef.footballclub.ui.infoteam


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ahmaddudayef.footballclub.R


/**
 * A simple [Fragment] subclass.
 */
class InfoTeamFragment : Fragment() {

    companion object {
        fun newInstance() = InfoTeamFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_team, container, false)
    }

}// Required empty public constructor
