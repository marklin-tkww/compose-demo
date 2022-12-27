package com.example.myapplication.composeAndview.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapplication.composeAndview.viewmodel.ComposeUiViewModelFactory
import com.example.myapplication.composeAndview.viewmodel.ComposeUiViewModel
import com.example.myapplication.R


class ComposeUiFragment : Fragment() {
    private val viewModel: ComposeUiViewModel by viewModels {
        ComposeUiViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(
            R.layout.fragment_compose_ui, container, false
        )

        val composeView = view.findViewById<ComposeView>(R.id.compose_view)
        composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ComposeUiPage(viewModel)
            }
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.run {
            responseMessage.observe(viewLifecycleOwner) {
                Toast.makeText(context, "API返回的数据结果是 $it", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        fun getInstance() = ComposeUiFragment()
    }
}