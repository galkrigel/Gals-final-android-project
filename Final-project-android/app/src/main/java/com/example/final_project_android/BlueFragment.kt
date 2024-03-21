package com.example.final_project_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation

class BlueFragment : Fragment() {
    private var textViewTitle: TextView? = null
    private var textViewPrice: TextView? = null
    private var title: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_blue, container, false)

        val blueTitle = arguments?.let {BlueFragmentArgs.fromBundle(it).TITLE  }
        val bluePrice = arguments?.let {BlueFragmentArgs.fromBundle(it).PRICE  }


        textViewTitle = view.findViewById(R.id.tvBlueFragmentTitle)
        textViewTitle?.text = blueTitle ?: "Please assign a title"

        textViewPrice = view.findViewById(R.id.tvBlueFragmentPrice)
        textViewPrice?.text = bluePrice ?: "Please assign a price"

        val backButton: Button = view.findViewById(R.id.btnBlueFragmentBack)
        backButton.setOnClickListener{
            Navigation.findNavController(view).popBackStack()
        }

        return view
    }

}


//companion object {
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment BlueFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    @JvmStatic
//    fun newInstance(param1: String, param2: String) =
//        BlueFragment().apply {
//            arguments = Bundle().apply {
//                putString(ARG_PARAM1, param1)
//                putString(ARG_PARAM2, param2)
//            }
//        }
//}