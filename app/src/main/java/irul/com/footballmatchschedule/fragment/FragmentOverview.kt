package irul.com.footballmatchschedule.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatTextView
import android.view.*
import irul.com.footballmatchschedule.R
import android.view.LayoutInflater
import android.view.ViewGroup

class FragmentOverview : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_overview, container, false)

        val description = rootView.findViewById<AppCompatTextView>(R.id.description)
        description.text = arguments?.getString("description").toString()
        return rootView
    }
}