package irul.com.footballmatchschedule.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.LinearLayoutManager
import android.widget.ImageView
import irul.com.footballmatchschedule.R
import irul.com.footballmatchschedule.adapter.MainFragmentPagerAdapter
import kotlinx.android.synthetic.main.fragment_matches.*
import android.widget.Toast
import android.annotation.SuppressLint
import android.support.v4.view.MenuItemCompat.getActionView
import android.view.*
import android.view.MenuInflater
import android.support.v4.view.MenuItemCompat.getActionView
import android.support.v7.widget.SearchView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import org.jetbrains.anko.support.v4.toast
import android.support.v4.view.MenuItemCompat.getActionView

class FragmentMatches : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_matches, container, false)

        val toolbar = rootView.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)


        val tabLayout = rootView.findViewById(R.id.tabLayout) as TabLayout
        val viewPager = rootView.findViewById(R.id.viewPager) as ViewPager
        val mLayoutManager = LinearLayoutManager(activity)
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        val mainFragmentPagerAdapter = MainFragmentPagerAdapter(fragmentManager,tabLayout.tabCount)
        mainFragmentPagerAdapter.addFragment(FragmentEventPast(), getString(R.string.PrevMatch))
        mainFragmentPagerAdapter.addFragment(FragmentEventNext(), getString(R.string.NextMatch))
        viewPager.adapter = mainFragmentPagerAdapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.tabMode = TabLayout.MODE_FIXED //TabLayout.MODE_SCROLLABLE
        return rootView
    }
}