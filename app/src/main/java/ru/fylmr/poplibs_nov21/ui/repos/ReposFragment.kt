package ru.fylmr.poplibs_nov21.ui.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.fylmr.poplibs_nov21.databinding.FragmentReposBinding
import ru.fylmr.poplibs_nov21.model.GithubUserModel
import ru.fylmr.poplibs_nov21.ui.repos.adapter.ReposAdapter

class ReposFragment : MvpAppCompatFragment(), ReposView {

    private val userModel by lazy {
        requireArguments().getParcelable<GithubUserModel>(KEY_USER_MODEL)!!
    }

    private var _binding: FragmentReposBinding? = null
    private val binding: FragmentReposBinding
        get() = _binding!!

    private val presenter by moxyPresenter {
        ReposPresenter(userModel)
    }

    private val adapter by lazy {
        ReposAdapter(presenter::onItemClicked)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentReposBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        private const val KEY_USER_MODEL = "KEY_USER_MODEL"

        fun newInstance(user: GithubUserModel): ReposFragment {
            return ReposFragment().apply {
                arguments = bundleOf(KEY_USER_MODEL to user)
            }
        }
    }
}