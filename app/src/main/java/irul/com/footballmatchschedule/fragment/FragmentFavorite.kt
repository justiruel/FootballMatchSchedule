package irul.com.footballmatchschedule.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import irul.com.footballmatchschedule.R
import irul.com.footballmatchschedule.adapter.MainFragmentPagerAdapter

class FragmentFavorite : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_favorite, container, false)

        val toolbar = rootView.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)

        val tabLayout = rootView.findViewById(R.id.tabLayout) as TabLayout
        val viewPager = rootView.findViewById(R.id.viewPager) as ViewPager
        val mLayoutManager = LinearLayoutManager(activity)
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        val mainFragmentPagerAdapter = MainFragmentPagerAdapter(fragmentManager,tabLayout.tabCount)
        mainFragmentPagerAdapter.addFragment(FragmentFavoriteMatches(), getString(R.string.Matches))
        mainFragmentPagerAdapter.addFragment(FragmentFavoriteTeams(), getString(R.string.Teams))
        viewPager.adapter = mainFragmentPagerAdapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.tabMode = TabLayout.MODE_FIXED //TabLayout.MODE_SCROLLABLE

        return rootView
    }
}