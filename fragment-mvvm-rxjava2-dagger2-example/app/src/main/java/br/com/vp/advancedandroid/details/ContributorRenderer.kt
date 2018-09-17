package br.com.vp.advancedandroid.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.vp.advancedandroid.R
import br.com.vp.advancedandroid.model.Contributor
import br.com.vp.advancedandroid.poweradapter.item.ItemRenderer
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import javax.inject.Inject

/**
 * @author diegovidal on 15/05/2018.
 */
class ContributorRenderer @Inject
        constructor()
    : ItemRenderer<Contributor> {

    override fun layoutRes(): Int {

        return R.layout.view_user_list_item
    }

    override fun createView(parent: ViewGroup): View {
        val view = LayoutInflater.from(parent.context).inflate(layoutRes(), parent, false)
        view.tag = ViewBinder(view)
        return view
    }

    override fun render(itemView: View, item: Contributor) {
        (itemView.tag as? ViewBinder)?.bind(item)
    }

    internal class ViewBinder(itemView: View) {

        @BindView(R.id.tv_user_name) lateinit var usernameText: TextView
        @BindView(R.id.iv_avatar) lateinit var avatarImageView: ImageView

        init {
            ButterKnife.bind(this, itemView)
        }

        fun bind(contributor: Contributor){

            usernameText.text = contributor.login
            Glide.with(avatarImageView.context)
                    .load(contributor.avatarUrl)
                    .into(avatarImageView)
        }
    }
}