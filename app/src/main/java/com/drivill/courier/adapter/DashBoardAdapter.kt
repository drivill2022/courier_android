package com.drivill.courier.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.drivill.courier.R
import com.drivill.courier.activity.StartNewActivity
import com.drivill.courier.activity.onboarding.OnBoardItems
import com.drivill.courier.databinding.OnboarditemsBinding
import com.drivill.courier.model.model.SplashModelItem


class DashBoardAdapter(var mContext: Context, var mListScreen: ArrayList<SplashModelItem>) : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = OnboarditemsBinding.inflate(inflater, null)

        binding.tvTitle.text = mListScreen[position].title
        binding.onboardDesc.text = mListScreen[position].description
        Glide.with(mContext).load(mListScreen[position].image).into(binding.imgTitle)
       // binding.imgTitle.setImageResource(mListScreen[position].image)
        container.addView(binding.root)
        addBottomDots(position, binding.layoutDots)
        if(position==2){
            binding.btnNext.visibility=View.VISIBLE
            binding.layoutDots.visibility=View.INVISIBLE
        }
        else{

            binding.btnNext.visibility=View.GONE
            binding.layoutDots.visibility=View.VISIBLE

        }
        binding.btnNext.setOnClickListener {
            val intent=Intent(mContext,StartNewActivity::class.java)
            mContext.startActivity(intent)
        }
        return binding.root
    }

    override fun getCount(): Int {
        return mListScreen.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    private lateinit var dots: Array<TextView?>
    private fun addBottomDots(pageNumber: Int, layoutDots: LinearLayout) {
        dots = arrayOfNulls(3)
        layoutDots.removeAllViews()
        for (i in dots.indices) {
            dots[i] = TextView(mContext)
            dots[i]!!.text = Html.fromHtml("&#8226;")
            dots[i]!!.textSize = 5f
            dots[i]!!.compoundDrawablePadding=10
            dots[i]!!.setTextColor(Color.TRANSPARENT)
            dots[i]!!.setBackground(if (i != pageNumber) {mContext.getResources().getDrawable(R.drawable.ic_circle_grey) }
            else mContext.getResources().getDrawable(R.drawable.ic_circle_blue))





            val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(5, 5, 5, 5)
            dots[i]!!.layoutParams = params
            layoutDots.addView(dots[i])


        }
    }
}