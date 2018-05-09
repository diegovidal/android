package br.com.vp.advancedandroid.details

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.vp.advancedandroid.R
import br.com.vp.advancedandroid.model.Contributor
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.util.ArrayList

/**
 * @author diegovidal on 07/05/2018.
 */
internal class ContributorAdapter: RecyclerView.Adapter<ContributorAdapter.ContributorViewHolder>() {

    private val data = ArrayList<Contributor>()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_user_list_item, parent, false)
        return ContributorViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return data.size
    }

    override fun onBindViewHolder(holder: ContributorViewHolder, position: Int) {

        holder.bind(data[position])
    }

    override fun getItemId(position: Int): Long {

        return data[position].id
    }

    fun setData(contributors: List<Contributor>?) {

        if (contributors != null){
            val diffResult = DiffUtil.calculateDiff(ContributorDiffCallback(data, contributors))
            data.clear()
            data.addAll(contributors)
            diffResult.dispatchUpdatesTo(this)
        } else {
            data.clear()
            notifyDataSetChanged()
        }
    }

    internal class ContributorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.tv_user_name)
        lateinit var usernameText: TextView

        @BindView(R.id.iv_avatar)
        lateinit var avatarImageView: ImageView

        init {
            ButterKnife.bind(this, itemView)
            itemView.setOnClickListener { v ->

            }
        }

        fun bind(contributor: Contributor) {

            usernameText.text = contributor.login
            Glide.with(avatarImageView.context)
                    .load(contributor.avatarUrl)
                    .into(avatarImageView)

        }
    }
}