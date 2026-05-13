package com.example.prefi.screens.home


class HomePresenter(private val view: HomeContract.View, private val model: HomeModel): HomeContract.Presenter {
    override fun initializeUsername() {
        val username = model.getUsername()

        if(!username.isNullOrEmpty()){
            view.displayUsername("Good day, $username")
        }
        else{
            view.displayUsername("Good day, user")
        }
    }
}