package com.example.myapplication

import android.content.Context
import com.bumptech.glide.Glide
import com.example.mvvm_simple.baseadapter.BaseAdapter
import com.example.mvvm_simple.baseadapter.CommonViewHolder
import com.example.myapplication.bean.Result

class MainAdapter(context: Context,data:MutableList<Result>?) :BaseAdapter<Result>(context,R.layout.item_main,data){


    override fun convert(holder: CommonViewHolder, data: Result, position: Int) {
        holder.setImageUrl(R.id.image,data.url,{ image, url -> Glide.with(mContext).load(url).into(image) })
            .setText(R.id.tv,data.desc)
    }

}
