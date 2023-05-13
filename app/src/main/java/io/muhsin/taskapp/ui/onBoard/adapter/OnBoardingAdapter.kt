package io.muhsin.taskapp.ui.onBoard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import io.muhsin.taskapp.databinding.ItemOnboardingBinding
import io.muhsin.taskapp.model.OnBoard
import io.muhsin.taskapp.utils.loadImage

class OnBoardingAdapter(private val onClick: (OnBoard) -> Unit) : Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val data = arrayListOf(
        OnBoard(
            title = "Title 1",
            desc = "Desc 1",
            "https://w7.pngwing.com/pngs/185/850/png-transparent-task-computer-icons-tasks-s-angle-text-microsoft-office-thumbnail.png"
        ),
        OnBoard(
            title = "Title 2",
            desc = "Desc 2",
            "https://w7.pngwing.com/pngs/185/850/png-transparent-task-computer-icons-tasks-s-angle-text-microsoft-office-thumbnail.png"
        ),
        OnBoard(
            title = "Title 3",
            desc = "Desc 3",
            "https://w7.pngwing.com/pngs/185/850/png-transparent-task-computer-icons-tasks-s-angle-text-microsoft-office-thumbnail.png"
        ),
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnboardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(data.get(position))
    }

    override fun getItemCount(): Int {
        return data.size
    }


    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding) :
        ViewHolder(binding.root) {
        fun bind(onBoard: OnBoard) {
            binding.btnStart.setOnClickListener {
                onClick(onBoard)
            }

            binding.tvTitle.text = onBoard.title
            binding.tvDesc.text = onBoard.desc
            binding.ivBoard.loadImage(onBoard.image)
        }
    }
}