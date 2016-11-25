# CommonCore
android项目快速开发公共依赖
## UI部分
### 基础Activity
#### toolbar封装

- 新建Activity继承BaseActivity
- 在activity的onCreate方法中使用如下方法设置标题栏名称
```java
setTitle("名称")
```
- 在activity的onCreate方法中使用mToolbar来进行其他操作，如在右侧添加按钮：
首先调用
``` java
setSupportActionBar(mToolbar);
```
在res/menu文件夹下创建menu_main.xml,内容如下
```xml
<menu xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:tools="http://schemas.android.com/tools"
      xmlns:app="http://schemas.android.com/apk/res-auto"
      tools:context=".activity.HomeActivity">
    <item android:id="@+id/action_scan"
          android:title="扫一扫"
          android:orderInCategory="80"
          android:icon="@drawable/ic_center_focus_weak"
          app:showAsAction="ifRoom">
    </item>
</menu>
```
在activity中重写onCreateOptionsMenu方法，加入menu_main
```java
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
```
设置监听事件
```java
 mToolbar.setOnMenuItemClickListener(this);
 //=========================================
     @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_scan:
                Intent intent = new Intent(this, CaptureActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
                break;
        }
        return false;
    }
```


#### 网络请求加载框

### TabLayout简单封装
### RecyclerView下拉刷新，滑动分页，分页组件
## 网络请求部分
### Retrofit使用封装


### 文件下载
