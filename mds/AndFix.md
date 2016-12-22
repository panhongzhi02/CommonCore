# AndFix热更新框架使用
## 工具下载
### 下载地址
[下载apkpatch](https://raw.githubusercontent.com/alibaba/AndFix/master/tools/apkpatch-1.0.3.zip)
### 命令说明
示例：apkpatch.bat -f F:\developer\AndFix\new\AndFix.apk -t F:\developer\AndFix\old\AndFix.apk -o F:\developer\AndFix\patch -k F:\developer\key -p 123456 -a test -e 123456

| 命令  | 说明    |
| --- | ------------ |
| -f  | 新apk路径    |
| -t  | 旧apk路径    |
| -o  | 补丁输出目录 |
| -k  | 签名文件目录 |
| -p  | 签名文件密码 |
| -a  | 签名文件别名 |
| -e  | 别名文件密码 |
![命令示例](./picture/AndFix命令示例.PNG)
### 在代码中使用
path为apatch文件在sd卡中的存储位置，可以通过从服务下载补丁之后保存到SD卡的方式来添加补丁。
```java
AndFix.addPatch(context, path);
```
### AndFix存在的问题
无法添加新的文件和类，只能修补已经存在的方法内容，无法创建新的方法。所以一般情况只适用于修改BUG，不适用于添加新的功能。

| 修补内容                   | 是否可修补 |
| -------------------------- | ---------- |
| 修补Activity生命周期的方法 | 可以       |
| 修补Fragment生命周期的方法 | 可以       |
| Application的OnCreate方法  | 不可以     |
| xml绑定的方法              | 不可以     |
| 静态方法                   | 可以       |
| 构造方法                   | 不可以     |
| public方法                 | 可以       |
| private方法                | 可以       |
| pritected方法              | 可以       |
| 重写接口的方法             | 可以           |

