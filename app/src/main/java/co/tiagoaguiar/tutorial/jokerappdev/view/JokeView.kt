package co.tiagoaguiar.tutorial.jokerappdev.view

import co.tiagoaguiar.tutorial.jokerappdev.model.Joke

interface JokeView {
    fun showJokeByCategory(response: Joke)
    fun showFailure(message: String)
    fun showProgress()
    fun hideProgress()
}