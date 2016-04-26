# MRecyclerView

Android懒人专用RecyclerView+懒人专用Adapter+懒人专用animator+下拉刷新上拉加载更多（可DIY）
特别声明:开源框架并非本人亲手敲出来的,本人只是站在巨人的肩膀上,整合了一下别人优秀的代码,并加以使用,希望方便到各位.
###下啦刷新,上拉加载更多(可DIY):
![](http://img.blog.csdn.net/20160426200038532)

###支持多种动画:

![](http://img.blog.csdn.net/20160426200105039)

###多个ItemType(只需30行代码):

![](http://img.blog.csdn.net/20160426200338712?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)

#1.LoadingIndicator的用法: 

类别:
			


AVLoadingIndicatorView.BallBeat,

            AVLoadingIndicatorView.BallClipRotate,
            AVLoadingIndicatorView.BallGridBeat,
            AVLoadingIndicatorView.BallPulse,
            AVLoadingIndicatorView.BallRotate,
            AVLoadingIndicatorView.BallSpinFadeLoader,
            AVLoadingIndicatorView.BallTrianglePath,
            AVLoadingIndicatorView.BallZigZagDeflect,
            AVLoadingIndicatorView.CubeTransition,
            AVLoadingIndicatorView.LineScaleParty,
            AVLoadingIndicatorView.Pacman,
            AVLoadingIndicatorView.SemiCircleSpin,
            AVLoadingIndicatorView.SquareSpin,
            AVLoadingIndicatorView.TriangleSkewSpin
    

    用法:
       mRecyclerView.setLoadingMoreProgressStyle(progressstyles[position]);
        mRecyclerView.setRefreshProgressStyle(progressstyles[position]);

    此外还可以自定义,只需要修改listview_header.xml的样式即可,可以添加各种喜欢的动画效果.这里难度不大,在次略过.

#2.下面重点介绍下XReyclerView
    

       这个控件可以说是非常让我兴奋的,因为之前使用过XlistView,在其可以实现下拉刷新,上拉加载更多功能上深得喜爱,可定制型也非常强,可以自己实现成类似于京东,淘宝等下拉刷新动画效果.令人遗憾的是,support包中的RecyclerView是越来越火了,并且以其一妓之长击败了ListView和GridView拿到双杀.

    本人感觉原因大概有以下三点:

    1.具有更加优秀的RecycleBin机制,比AbsListView更好的处理创建和回收过程.

    2.爸爸级展示效果,可以一列,双列,还有decoration等等,可以很自由的旋转.

    3.ItemAnimation, 现在app在性能上已不是主题, 用户体验才是目标了, 而各种酷炫的动画效果则是提高用户体验的重要因素.

###=============分割线=============================================================

    一不小心又B了那么多,其实XRecyclerView用法没多大难度,跟RecyclerView用法一样,哎,就是这么简单粗暴咯.

#3.ItemAnimator 

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

#4.BaseAdapter, (这个叼,这个叼). 
    我用过两种baseadapter ,一个是基于JoanZapata/base-adapter-helper 主要用于absListView,而另外一个是基于张鸿杨大神的baseAdapter,两个用法基本一样.

    以国产为例,recyclerview的Adapter只要声明一个CommonAdapter实现convert方法.在做数据处理.其他的丢给框架去封装就行了,看代码.
    

       adapter = new CommonAdapter<String>(this, R.layout.listitem, datas) {
            @Override
            public void convert(final ViewHolder holder, String s) {
                //1.1do somethings;
                final SwipeLayout swipeLayout= (SwipeLayout) holder.getConvertView().findViewById(R.id.swipelayout);
                TextView tv_delete= (TextView) swipeLayout.findViewById(R.id.tv_delete);

                swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
                swipeLayout.addDrag(SwipeLayout.DragEdge.Right, tv_delete);

                tv_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        swipeLayout.close(true);
                        adapter.notifyItemRemoved(holder.getLayoutPosition());
                    }
                });

            }
        };
    
只要对holder进行各种操作即可,holder里面的功能有限,不过可以根据自身需求添加.
    各种方法如:

    holder.setImageUrl(id,url);

可以省去很多load img方法重写.
    值得开心的是:产品狗一般不让一个recyclerview里面只放一种item. 这个框架作者也考虑到了这点, 写法稍微复杂点,不过有兴趣的去看下张大神的github哈.# MRecyclerView

    

可以省去很多load img方法重写.
值得开心的是:产品狗一般不让一个recyclerview里面只放一种item. 这个框架作者也考虑到了这点, 写法稍微复杂点,不过有兴趣的去看下张大神的github.里面有更详细的baseadapter用法的介绍:
链接:[https://github.com/hongyangAndroid/baseAdapter](https://github.com/hongyangAndroid/baseAdapter)
还有很多功能没提,大家细细探索哈..

##THANKS PROVIDE:

ItemAnimators:
[https://github.com/wasabeef/recyclerview-animators](https://github.com/wasabeef/recyclerview-animators)

BaseAdapter: [https://github.com/hongyangAndroid/baseAdapter](https://github.com/hongyangAndroid/baseAdapter)

LoadingIndicator: [https://github.com/81813780/AVLoadingIndicatorView](https://github.com/81813780/AVLoadingIndicatorView)

SwipeItem:
[https://github.com/daimajia/AndroidSwipeLayout](https://github.com/daimajia/AndroidSwipeLayout)

如以上内容造成侵权 敬请告知删除,谢谢! 

联系QQ: 371166028 邮箱:itmarshon@163.com
