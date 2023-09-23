package com.example.newapp.presentation.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.newapp.R
import com.example.newapp.common.CustomRecyclerViewAdapter
import com.example.newapp.common.convertDate
import com.example.newapp.common.loadImage
import com.example.newapp.data.remote.news.ArticleModel
import com.example.newapp.databinding.ArticleListItemBinding

class CountriesAdapter(
    private val itemClick: (String) -> Unit,
) : CustomRecyclerViewAdapter<ArticleModel>() {
    override fun createBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ArticleListItemBinding.bind(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.article_list_item, parent, false)
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun bind(binding: ViewBinding, position: Int) {
        val articles = getItem(position)
        val articleListItemBinding = binding as ArticleListItemBinding

        with(articleListItemBinding) {
            articles?.let {
                it.urlToImage?.let { it1 -> this.articleIv.loadImage(it1) }
                binding.articleTitleTv.text = it.title
                binding.articleDescriptionTv.text = it.description
                binding.articleAuthor.text =
                    StringBuilder().append(it.author).append(", ").append(it.source)

                binding.articleDateTv.text =
                    it.publishedAt?.let { it1 -> convertDate(it1) }.toString()

//


            }
        }


    }

    fun setData(newData: List<ArticleModel>) {
        submitList(newData)
    }
}
//
//class ArticlesAdapter(
//    val listener: ArticleListener
//): RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder>() {
//
//    private var articles: List<Articles> = emptyList()
//
//    inner class ArticlesViewHolder(
//        private val view: View
//    ): RecyclerView.ViewHolder(view) , View.OnClickListener{
//        private val glide = Glide.with(view.context)
//        private val title = view.findViewById<TextView>(R.id.article_title_tv)
//        private val description = view.findViewById<TextView>(R.id.article_description_tv)
//        private val imgView = view.findViewById<ImageView>(R.id.article_iv)
//        private val author = view.findViewById<TextView>(R.id.article_author)
//        private val category = view.findViewById<TextView>(R.id.category_title_tv)
//        private val bookmark = view.findViewById<ImageView>(R.id.bookmark_iv)
//        private val dateTv = view.findViewById<TextView>(R.id.article_date_tv)
//
//        init {
//            view.setOnClickListener(this)
//            bookmark.setOnClickListener(this)
//        }
//
//        fun onBind(article: Articles){
//            glide.load(article.article.img).error(R.drawable.ic_photo).into(imgView)
//            title.text = article.article.title
//            description.text = article.article.description
//            author.text = article.article.author+", " +article.article.source
//            val lang = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                itemView.resources.configuration.locales[0].language
//            } else {
//                itemView.resources.configuration.locale.language
//            }
//            if (lang == "ar"){
//                category.text = article.category?.name_ar
//            }else{
//                category.text = article.category?.name_en
//            }
//            dateTv.text = format(article.article.publishedAt)
//            if (article.article.isBooked){
//                bookmark.setImageResource(R.drawable.ic_bookmark)
//            }else{
//                bookmark.setImageResource(R.drawable.ic_add_bookmark)
//            }
//        }
//
//        override fun onClick(v: View?) {
//            val article = articles[adapterPosition]
//            if (v != null && v.id == R.id.bookmark_iv){
//                listener.bookmarkArticle(
//                    articleUrl = article.article.url,
//                    isBooked = !article.article.isBooked
//                )
//            }else{
//                listener.onArticleClick(article.article.url)
//            }
//        }
//    }
//
//    interface ArticleListener{
//        fun onArticleClick(articleUrl: String)
//        fun bookmarkArticle(articleUrl: String,isBooked: Boolean)
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    fun setArticles(articles: List<ArticleRelation>){
//        this.articles = articles
//        notifyDataSetChanged()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder = ArticlesViewHolder(
//        view = LayoutInflater.from(parent.context).inflate(R.layout.article_list_item,parent,false)
//    )
//
//    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
//        holder.onBind(articles[position])
//    }
//
//    override fun getItemCount(): Int =  articles.size
//}
//
