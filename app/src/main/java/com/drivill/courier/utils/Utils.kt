package com.drivill.courier.utils

import com.drivill.courier.R
import com.drivill.courier.activity.onboarding.OnBoardItems

object Utils {

    public fun getPagerData(): MutableList<OnBoardItems> {
        val mlist: MutableList<OnBoardItems> = ArrayList()
        mlist.add(
            OnBoardItems(
                "Pickup Request anywhere", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor \nincididunt ut labore et dolore", R.drawable.onboarding1
            )
        )
        mlist.add(
            OnBoardItems(
                "Rapid Pickup & Delivery", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor \nincididunt ut labore et dolore", R.drawable.onboarding2
            )
        )
        mlist.add(
            OnBoardItems(
                "Lorem ipsum dolor", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor \nincididunt ut labore et dolore",
                R.drawable.onboarding3
            )
        )
        return mlist
    }

}