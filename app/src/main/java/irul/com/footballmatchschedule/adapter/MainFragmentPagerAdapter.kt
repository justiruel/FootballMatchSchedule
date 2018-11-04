package irul.com.footballmatchschedule.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class MainFragmentPagerAdapter(fm: FragmentManager?, internal var mNumOfTabs: Int) : FragmentStatePagerAdapter(fm) {

    private val mFragments:ArrayList<Fragment> = arrayListOf()
    private val mTitleFragments:ArrayList<String> = arrayListOf()

    override fun getItem(position: Int): Fragment {
        return mFragments[position]
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFragments.add(fragment)
        mTitleFragments.add(title)
    }

    override fun getCount(): Int {
        return mFragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitleFragments[position]
    }
}