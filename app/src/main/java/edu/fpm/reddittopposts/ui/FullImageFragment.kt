package edu.fpm.reddittopposts.ui

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import edu.fpm.reddittopposts.R
import edu.fpm.reddittopposts.databinding.FragmentFullImageBinding
import edu.fpm.reddittopposts.utils.Constants.IMG_URL

@AndroidEntryPoint
class FullImageFragment : Fragment() {

    private var _binding: FragmentFullImageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFullImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageUrl = requireArguments().getString(IMG_URL)
        Glide.with(requireContext()).load(imageUrl).into(binding.fullImage)

        binding.fullImageDownload.setOnClickListener {
            downloadImage(imageUrl!!)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun downloadImage(url: String) {
        val downloadManager = requireContext().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManager.Request(Uri.parse(url))

        val fileName = "redditImage.jpg"
        val directory = Environment.DIRECTORY_PICTURES
        request.setDestinationInExternalPublicDir(directory, fileName)

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setTitle(fileName)
        request.setDescription("Downloading image...")

        downloadManager.enqueue(request)
    }

}