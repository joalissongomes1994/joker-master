package co.tiagoaguiar.tutorial.jokerappdev.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import co.tiagoaguiar.tutorial.jokerappdev.R
import co.tiagoaguiar.tutorial.jokerappdev.model.Joke
import co.tiagoaguiar.tutorial.jokerappdev.presentation.JokePresenter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class JokeFragment : Fragment(), JokeView {

    private lateinit var presenter: JokePresenter
    private lateinit var txtJoke: TextView
    private lateinit var imageJoke: ImageView
    private lateinit var progressBar: ProgressBar

    companion object {
        const val CATEGORY_KEY = "category"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = JokePresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_joke, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtJoke = view.findViewById(R.id.txt_joke)
        imageJoke = view.findViewById(R.id.image_joke)
        progressBar = view.findViewById(R.id.progress_joke)

        val categoryName = arguments?.getString(CATEGORY_KEY)!!
        presenter.findJokeByCategory(categoryName)

        val toolbar = activity?.findViewById<Toolbar>(R.id.toolbar)
        toolbar?.title = categoryName

        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            presenter.findJokeByCategory(categoryName)
        }
    }

    override fun showJokeByCategory(response: Joke) {
        txtJoke.text = response.text
        Picasso.get().load(response.iconUrl).into(imageJoke)
    }

    override fun showFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
        txtJoke.visibility =  View.GONE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
        txtJoke.visibility =  View.VISIBLE
    }
}