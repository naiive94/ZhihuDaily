#ZhihuDaily RxJava MVP
知乎日报的非官方版本，架构借鉴了google samples的mvp architecture.


##Architecture
###Packaging
<img src="screenshots/packaging.png" width="50%" />

###Code
根据功能模块分为不同的包，在每个功能模块中定义Contract，约定View以及Presenter的方法。

```java
public interface MainContract {

    interface View extends BaseView<Presenter>{

        void showDrawerItems(List<DrawerItem> list);

        void showEmpty();

        void hide();

    }

    interface Presenter extends BasePresenter{

        void getDrawerList();

        void updateDrawerList();
    }
}
```
MainPresenter中的RxJava的运用，这里暂时还没有对subscriber进行封装。


```java
private void loadDrawerList(boolean forceUpdate) {
        if (forceUpdate)
            mRepository.refreshThemes();

        Subscription subscription = mRepository.convertDrawerItems()
                .compose(Relper.<List<DrawerItem>>applySchedulersIoMain())
                .subscribe(new Action1<List<DrawerItem>>() {
                    @Override
                    public void call(List<DrawerItem> drawerItems) {
                        mView.showDrawerItems(drawerItems);
                        mView.hide();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showEmpty();
                    }
                });

        addSubscription(subscription);
    }
```
通过代理类来将Repository作为单例进行管理，目的是保存数据状态。

```java
public static TopicRepository provideTopicRepository(){
        return DataSource.Proxy.create().get(TopicRepository.class);
    }
```

###Libraries
- RxJava 
- RxAndroid
- Retrofit2
- OkHttp3
- Glide
- DiskLruCache
- BaseRecyclerViewAdapterHelper
- Banner




