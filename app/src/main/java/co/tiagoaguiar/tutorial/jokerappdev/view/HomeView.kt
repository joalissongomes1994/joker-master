package co.tiagoaguiar.tutorial.jokerappdev.view

import co.tiagoaguiar.tutorial.jokerappdev.model.Category

interface HomeView {
    fun showProgress()
    fun showCategories(response: List<Category>)
    fun hideProgress()
    fun showFailure(message: String)
}