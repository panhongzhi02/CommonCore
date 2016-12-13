# PDA扫描头集成说明
## 配置广播接收器
1、在AndroidManifest.xml文件中声明广播接收器，说明如下

 - name：广播接收器类名
 - action：获取广播名称
 - category: 广播类别，默认

```xml
<receiver android:name=".scan.receiver.ScanReceive">
    <intent-filter>
        <action android:name="com.hrhy.mc.util.scancode"></action>
        <category android:name="android.intent.category.DEFAULT"></category>
    </intent-filter>
</receiver>
```
2、创建ScanReceive广播接收器
```java
public class ScanReceive extends BroadcastReceiver{

    /**
     * 摩托罗拉扫描接口
     */
    public static final String MOTO_NAME = "com.motorolasolutions.emdk.datawedge.data_string";
    /**
     * 新联扫描接口
     */
    public static final String XINLIAN_NAME = "lachesis_barcode_value_notice_broadcast_data_string";
    /**
     * 医惠扫描接口
     */
    public static final String YIHUI_NAME = "scanresult";
    /**
     * 清远PDA扫描接口
     */
    public static final String YUANQING_NAME = "barcode_string";

    @Override
    public void onReceive(Context context, Intent intent) {
        //scanResult为扫描二维码获取到的 字符串
        String scanResult = intent.getStringExtra(MOTO_NAME);
        //ScanHandlerHelper.getInstance(context).doHandler(new ScanRequest(scanResult));
    }
}
```
3、将扫描到的内容scanResult发送到界面可以采用Activity内部注册广播接收器、handler发送消息、将scanResult设为全局变量、使用EventBus框架进行消息通信
## 设备扫描配置

 1. 启动DataWedge应用——>点击![home](./picture/home.png)——>点击选择New Profile——>输入CubeCare——>点击OK——>点击CubeCare——>进行如下设置。
 2. 选择Profile enabled；
 3. 点击Associated apps——>点击![home](./picture/home.png)——>选择New app/activity——>com.hrhy.cc.activity——>选择*；
 4. 将Enable Enable/disable keystroke output选中
 5. 将Enabled Enabled/disable keystroke output 取消选中；
 6. 将Enabled Enabled/disable output via intent 选中；
 7. 点击Intent Action 输入 com.hrhy.mc.util.scancode；
 8. 点击Intent category 输入 android.intent.category.DEFAULT；
 9. 点击Intent delivery 选择 Broadcast intent；
