package com.studiogh.randomuser.view.fragment

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.studiogh.randomuser.R
import com.studiogh.randomuser.model.Name
import com.studiogh.randomuser.model.Picture
import com.studiogh.randomuser.model.Results
import com.studiogh.randomuser.viewmodels.UserSearchViewModel


class UserSearchFragment : Fragment() {
    private lateinit var viewModel: UserSearchViewModel
    private lateinit var searchButton: Button
    private lateinit var userProfileImage: ImageView
    private lateinit var userFullname: TextView
    private lateinit var userEmail: TextView
    private lateinit var userCellphone: TextView
    private lateinit var userInfoContainer: RelativeLayout


    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserSearchViewModel::class.java)
        viewModel.init()
        viewModel.getUserResponseLiveData()?.observe(this,
            { results ->
                if (results != null) {
                    results.items?.let {
                        movingView(userInfoContainer, it);
                    }
                }
                updateSearchEnable()
            })
    }

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_user_search, container, false)

        searchButton = view.findViewById(R.id.fragment_user_search_btn_search)
        userProfileImage = view.findViewById(R.id.fragment_user_search_image_profile)
        userFullname = view.findViewById(R.id.fragment_user_search_txt_fullname)
        userEmail = view.findViewById(R.id.fragment_user_search_txt_email)
        userCellphone = view.findViewById(R.id.fragment_user_search_txt_cellphone)
        userInfoContainer = view.findViewById(R.id.fragment_user_search_rl_profile)

        searchButton.setOnClickListener{
            performSearch()
            updateSearchEnable()
        }

        return view
    }

    private fun performSearch() {
        viewModel?.searchUser()
    }

    private fun getScreenWidth(): Int {
        return Resources.getSystem().getDisplayMetrics().widthPixels
    }

    private fun updateSearchEnable(){
        searchButton.isEnabled = !searchButton.isEnabled
    }

    private fun updateUserInfo(it: List<Results>){
        val name: Name? = it[0].name
        val fullName = name?.title + ". "+name?.first+ " "+name?.last
        userFullname.text = fullName
        userEmail.text = it[0].email
        userCellphone.text = it[0].cell

        if (it[0].picture != null) {
            val imageUrl: Picture = it[0].picture!!
            Glide.with(this)
                .load(imageUrl.large)
                .circleCrop()
                .thumbnail(Glide.with(this).load(R.drawable.gif_loading))
                .into(userProfileImage)
        }
    }

    private fun movingView(v: View, data: List<Results>){
        movingAnimation(
            v, getScreenWidth(),
            AccelerateInterpolator(),
            DecelerateInterpolator(), 400, data
        )
    }

    private fun movingAnimation(
        v: View,
        initialPositionX: Int,
        initialEffect: Interpolator,
        finalEffect: Interpolator,
        duration: Long,
        data: List<Results>
    ){
        ViewCompat.animate(v).x(0F)
            .translationX(initialPositionX.toFloat())
            .setDuration(duration)
            .setInterpolator(initialEffect)
            .withEndAction{

                updateUserInfo(data)

                ViewCompat.setTranslationX(v,-initialPositionX.toFloat())
                ViewCompat.animate(v)
                    .translationX(0F)
                    .setDuration(duration)
                    .setInterpolator(finalEffect)
            }
    }
}