# MRecyclerView

Android懒人专用RecyclerView+懒人专用Adapter+懒人专用animator+下拉刷新上拉加载更多（可DIY）
特别声明:开源框架并非本人亲手敲出来的,本人只是站在巨人的肩膀上,整合了一下别人优秀的代码,并加以使用,希望方便到各位.

1.LoadingIndicator的用法: 

类别:

    <span class="hljs-variable">AVLoadingIndicatorView</span>.<span class="hljs-variable">BallBeat</span>,
        <span class="hljs-variable">AVLoadingIndicatorView</span>.<span class="hljs-variable">BallClipRotate</span>,
        <span class="hljs-variable">AVLoadingIndicatorView</span>.<span class="hljs-variable">BallGridBeat</span>,
        <span class="hljs-variable">AVLoadingIndicatorView</span>.<span class="hljs-variable">BallPulse</span>,
        <span class="hljs-variable">AVLoadingIndicatorView</span>.<span class="hljs-variable">BallRotate</span>,
        <span class="hljs-variable">AVLoadingIndicatorView</span>.<span class="hljs-variable">BallSpinFadeLoader</span>,
        <span class="hljs-variable">AVLoadingIndicatorView</span>.<span class="hljs-variable">BallTrianglePath</span>,
        <span class="hljs-variable">AVLoadingIndicatorView</span>.<span class="hljs-variable">BallZigZagDeflect</span>,
        <span class="hljs-variable">AVLoadingIndicatorView</span>.<span class="hljs-variable">CubeTransition</span>,
        <span class="hljs-variable">AVLoadingIndicatorView</span>.<span class="hljs-variable">LineScaleParty</span>,
        <span class="hljs-variable">AVLoadingIndicatorView</span>.<span class="hljs-variable">Pacman</span>,
        <span class="hljs-variable">AVLoadingIndicatorView</span>.<span class="hljs-variable">SemiCircleSpin</span>,
        <span class="hljs-variable">AVLoadingIndicatorView</span>.<span class="hljs-variable">SquareSpin</span>,
        <span class="hljs-variable">AVLoadingIndicatorView</span>.<span class="hljs-variable">TriangleSkewSpin</span>

    用法:
        m<span class="hljs-variable">RecyclerView</span>.set<span class="hljs-variable">LoadingMoreProgressStyle</span>(<span class="hljs-variable">AVLoadingIndicatorView</span>.<span class="hljs-variable">BallBeat</span>);
            m<span class="hljs-variable">RecyclerView</span>.set<span class="hljs-variable">RefreshProgressStyle</span>(<span class="hljs-variable">AVLoadingIndicatorView</span>.<span class="hljs-variable">BallClipRotate</span>);

    此外还可以自定义,只需要修改listview_header.xml的样式即可,可以添加各种喜欢的动画效果.这里难度不大,在次略过.

    <span class="hljs-number">2</span>.下面重点介绍下<span class="hljs-variable">XReyclerView</span>.
    `</pre>

       这个控件可以说是非常让我兴奋的,因为之前使用过XlistView,在其可以实现下拉刷新,上拉加载更多功能上深得喜爱,可定制型也非常强,可以自己实现成类似于京东,淘宝等下拉刷新动画效果.令人遗憾的是,support包中的RecyclerView是越来越火了,并且以其一妓之长击败了ListView和GridView拿到双杀.

    <pre>`本人感觉原因大概有以下三点:

    <span class="hljs-number">1.</span>具有更加优秀的RecycleBin机制,比AbsListView更好的处理<span class="hljs-keyword">new</span>和回收过程.

    <span class="hljs-number">2.</span>爸爸级展示效果,可以一列,双列,还有decoration等等,可以很自由的旋转.

    <span class="hljs-number">3.</span>ItemAnimation, 现在app在性能上已不是主题, 用户体验才是目标了, 而各种酷炫的动画效果则是提高用户体验的重要因素.

    =============分割线===================================

    一不小心又B了那么多,其实XRecyclerView用法没多大难度,跟RecyclerView用法一样,哎,就是这么简单粗暴咯.

    <span class="hljs-number">3.</span>ItemAnimator 

    承上启下,ItemAnimator确实让人兴奋,虽然比不上<span class="hljs-number">5.0</span>以上的transition动画,但是有了这些动画, 用起来也能够挺爽的不要不要的了.不信你看↓..

    AnimationAdapter: 
    这是一个装饰了RecyclerView.Adapter的类.其主要改造的地方在这里:

      <span class="hljs-annotation">@Override</span> <span class="hljs-function"><span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">onBindViewHolder</span><span class="hljs-params">(RecyclerView.ViewHolder holder, <span class="hljs-keyword">int</span> position)</span> </span>{
    mAdapter.onBindViewHolder(holder, position);

    <span class="hljs-keyword">int</span> adapterPosition = holder.getAdapterPosition();
    <span class="hljs-keyword">if</span> (!isFirstOnly || adapterPosition > mLastPosition) {
      <span class="hljs-keyword">for</span> (Animator anim : getAnimators(holder.itemView)) {
        anim.setDuration(mDuration).start();
        anim.setInterpolator(mInterpolator);
      }
      mLastPosition = adapterPosition;
    } <span class="hljs-keyword">else</span> {
      ViewHelper.clear(holder.itemView);
    }
    `</pre>

      }
        由于上面可以看出,在Item展示的时候是逐个读取动画来执行,并且提供了isFirstOnly 来配置是否只是第一次展示的时候才执行动画.通过 AnimationAdapter.setFirstOnly(true);来设置.
        但是 AnimationAdapter是一个抽象类, 需要重载一个类来实现里面的getAnimators方法.详细写法可参照demo里的ChooseAnimatorsAdapter类.也可以查看Xrecyclerview包下的animators包,里面提供了很多已经写好的AnimatorAdapters类,拿来即用,懒嘛..哈哈  另外提一下, 在下拉刷新和动画并不和侧滑冲突,爽爽哒

    <pre>`<span class="hljs-number">4</span>.重头戏:<span class="hljs-variable">BaseAdapter</span>, 这个叼,这个叼. 
    我用过两种baseadapter ,一个是基于<span class="hljs-variable">JoanZapata</span>/base-adapter-helper 主要用于abs<span class="hljs-variable">ListView</span>,而另外一个是基于张鸿杨大神的base<span class="hljs-variable">Adapter</span>,两个用法基本一样.

    以国产为例,recyclerview的<span class="hljs-variable">Adapter</span>只要声明一个<span class="hljs-variable">CommonAdapter</span>实现convert方法.在做数据处理.其他的丢给框架去封装就行了,看代码.
    `</pre>

       mRecyclerView.setAdapter(
       new CommonAdapter<String>(this, R.layout.item_list, mDatas)
    {

    <pre>`<span class="hljs-annotation">@Override</span>
    <span class="hljs-function"><span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">convert</span><span class="hljs-params">(ViewHolder holder, String s)</span>
    </span>{
        holder.setText(R.id.id_item_list_title, s);
    }
    `</pre>

    });

    <pre>`只要对holder进行各种操作即可,holder里面的功能有限,不过可以根据自身需求添加.
    各种方法如:

    holder.setImageUrl(<span class="hljs-property">id</span>,url);

    可以省去很多load img方法重写.
    值得开心的是:产品狗一般不让一个recyclerview里面只放一种<span class="hljs-property">item</span>. 这个框架作者也考虑到了这点, 写法稍微复杂点,不过有兴趣的去看下张大神的github哈.<span class="hljs-comment"># MRecyclerView</span>

    Android懒人专用RecyclerView+懒人专用Adapter+懒人专用animator+下拉刷新上拉加载更多（可DIY）

    特别声明:开源框架并非本人亲手敲出来的,本人只是站在巨人的肩膀上,整合了一下别人优秀的代码,并加以使用,希望方便到各位.

只要对holder进行各种操作即可,holder里面的功能有限,不过可以根据自身需求添加.
各种方法如:

holder.setImageUrl(id,url);

可以省去很多load img方法重写.
值得开心的是:产品狗一般不让一个recyclerview里面只放一种item. 这个框架作者也考虑到了这点, 写法稍微复杂点,不过有兴趣的去看下张大神的github哈.

里面有更详细的baseadapter用法的介绍:
链接:[https://github.com/hongyangAndroid/baseAdapter](https://github.com/hongyangAndroid/baseAdapter)
还有很多功能没提,大家细细探索哈..

THANKS PROVIDE:

ItemAnimators:
[https://github.com/wasabeef/recyclerview-animators](https://github.com/wasabeef/recyclerview-animators)

BaseAdapter: [https://github.com/hongyangAndroid/baseAdapter](https://github.com/hongyangAndroid/baseAdapter)
LoadingIndicator: [https://github.com/81813780/AVLoadingIndicatorView](https://github.com/81813780/AVLoadingIndicatorView)
SwipeItem:

[https://github.com/daimajia/AndroidSwipeLayout](https://github.com/daimajia/AndroidSwipeLayout)

如以上内容造成侵权 敬请告知删除,谢谢! 

联系QQ: 371166028 邮箱:itmarshon@163.com

里面有更详细的baseadapter用法的介绍:
链接:[https://github.com/hongyangAndroid/baseAdapter](https://github.com/hongyangAndroid/baseAdapter)
还有很多功能没提,大家细细探索哈..

THANKS PROVIDE:

ItemAnimators:
[https://github.com/wasabeef/recyclerview-animators](https://github.com/wasabeef/recyclerview-animators)

BaseAdapter: [https://github.com/hongyangAndroid/baseAdapter](https://github.com/hongyangAndroid/baseAdapter)
LoadingIndicator: [https://github.com/81813780/AVLoadingIndicatorView](https://github.com/81813780/AVLoadingIndicatorView)
SwipeItem:

[https://github.com/daimajia/AndroidSwipeLayout](https://github.com/daimajia/AndroidSwipeLayout)

如以上内容造成侵权 敬请告知删除,谢谢! 

联系QQ: 371166028 邮箱:itmarshon@163.com