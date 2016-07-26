# MRecyclerView
项目CSDN地址:
http://blog.csdn.net/u014665060/article/details/51241526
Android RecyclerView+item动画+下拉刷新,上拉加载更多,侧滑删除（易用,可定制）（可DIY）
特别声明:开源框架并非全部本人亲手敲出来的,本人只是站在巨人的肩膀上,整合了一下别人优秀的代码,并加修改融合,希望方便到各位盆友并Get到盆友们的支持,喜欢的star或者fork下.
###下拉刷新,上拉加载更多(可DIY):
![](http://img.blog.csdn.net/20160426203038364?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)

###支持多种动画:

![](http://img.blog.csdn.net/20160426203050349?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)

###多个ItemType(只需30行代码):

![](http://img.blog.csdn.net/20160426203100780?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)


#1.介绍下MReyclerView
    

       因为之前使用过XlistView,在其可以实现下拉刷新,上拉加载更多功能上深得喜爱,可定制型也非常强,可以自己实现成类似于京东,淘宝等下拉刷新动画效果.原理差不多,加个HeaderView和FooterView作为下拉刷新和上拉加载更多.通过改变HeaderView的height来实现滑动的效果.加上属性动画让用户体验更佳.

  

###=============分割线=============================================================

    一不小心又B了那么多,其实MRecyclerView用法没多大难度,跟RecyclerView用法一样,哎.

#2.ItemAnimator 

    承上启下,ItemAnimator确实让人兴奋,虽然比不上5.0以上的transition动画,但是有了这些动画, 用起来也能够挺爽的不要不要的了.不信你看↓..

    AnimationAdapter: 
    这是一个装饰了RecyclerView.Adapter的类.其主要改造的地方在这里:

	       @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
	    mAdapter.onBindViewHolder(holder, position);
	
	    int adapterPosition = holder.getAdapterPosition();
	    if (!isFirstOnly || adapterPosition > mLastPosition) {
	      for (Animator anim : getAnimators(holder.itemView)) {
	        anim.setDuration(mDuration).start();
	        anim.setInterpolator(mInterpolator);
	      }
	      mLastPosition = adapterPosition;
	    } else {
	      ViewHelper.clear(holder.itemView);
	    }
	  }

        由于上面可以看出,在Item展示的时候是逐个读取动画来执行,并且提供了 isFirstOnly  来配置是否只是第一次展示的时候才执行动画.通过 AnimationAdapter.setFirstOnly(true);来设置.
        但是 AnimationAdapter是一个抽象类, 需要重载一个类来实现里面的getAnimators方法.详细写法可参照demo里的ChooseAnimatorsAdapter类.也可以查看Xrecyclerview包下的animators包,里面提供了很多已经写好的AnimatorAdapters类,拿来即用,懒嘛..哈哈  另外提一下, 在下拉刷新和动画并不和侧滑冲突,爽爽哒

#3.侧滑菜单栏
详情键SwipeWraper,只要在listietm最外层套上他,并且当做FrameLayout来使用就可以实现侧滑菜单功能.哎

    
     View deleteView = holder.itemView.findViewById(R.id.tv_delete);
                final SwipeWraper swipelayout = (SwipeWraper) holder.itemView.findViewById(R.id.swipelayout);
                deleteView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        swipelayout.close(true);
                        datas.remove(holder.getLayoutPosition()-1);
                        animAdapter.notifyItemRemoved(holder.getLayoutPosition());
                    }
                });

可以省去很多load img方法重写.
    值得开心的是:产品狗一般不让一个recyclerview里面只放一种item. 这个框架作者也考虑到了这点, 写法稍微复杂点,不过有兴趣的去看下张大神的github哈.# MRecyclerView

    

可以省去很多load img方法重写.
产品狗一般不让一个recyclerview里面只放一种item. 这个框架作者也考虑到了这点, 写法稍微复杂点,在此安利本人喜欢的张大神的github.里面有更详细的baseadapter用法的介绍:
链接:[https://github.com/hongyangAndroid/baseAdapter](https://github.com/hongyangAndroid/baseAdapter)
还有很多功能没提,大家细细探索哈..

##THANKS PROVIDE:

ItemAnimators:
[https://github.com/wasabeef/recyclerview-animators](https://github.com/wasabeef/recyclerview-animators)



如以上内容造成侵权 敬请告知删除,谢谢! 

联系QQ: 371166028 邮箱:itmarshon@163.com
