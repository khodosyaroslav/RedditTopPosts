package edu.fpm.reddittopposts.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.fpm.reddittopposts.data.entities.ChildrenResponse
import edu.fpm.reddittopposts.databinding.FragmentMainBinding
import edu.fpm.reddittopposts.databinding.PostItemBinding
import edu.fpm.reddittopposts.utils.formatCreation

class TopPostsPagingAdapter(val mainBinding: FragmentMainBinding) :
    PagingDataAdapter<ChildrenResponse, TopPostsPagingAdapter.TopPostsViewHolder>(PostsDiffCallback) {

    inner class TopPostsViewHolder(private val binding: PostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val postItem = getItem(position)?.data

            binding.apply {
                itemPostTitle.text = postItem?.title
                itemPostComments.text = postItem?.num_comments.toString()
                itemPostUsername.text = postItem?.author
                itemPostCreated.text = formatCreation(postItem?.created!!)

                if(postItem.thumbnail.endsWith(".jpg") || postItem.thumbnail.endsWith(".png")){
                    Glide.with(mainBinding.root.context).load(postItem.thumbnail).into(itemPostImage)
                    //setOnClickListener
                } else {
                    itemPostImage.visibility = View.GONE
                }
            }
            mainBinding.postsProgressBar.visibility = ProgressBar.GONE
        }
    }

    override fun onBindViewHolder(holder: TopPostsViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopPostsViewHolder {
        val binding = PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopPostsViewHolder(binding)
    }

    object PostsDiffCallback : DiffUtil.ItemCallback<ChildrenResponse>() {
        override fun areItemsTheSame(
            oldItem: ChildrenResponse,
            newItem: ChildrenResponse
        ): Boolean {
            return oldItem.data.url == newItem.data.url
        }

        override fun areContentsTheSame(
            oldItem: ChildrenResponse,
            newItem: ChildrenResponse
        ): Boolean {
            return oldItem.data == newItem.data
        }
    }
}