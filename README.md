# Task Manager
一个简单的 Android 应用，为一些没有后台管理页功能的设备提供清理后台的能力，如`WearOS 2`

![shot](https://img.picui.cn/free/2024/08/29/66d08fbdc75f8.png)![shot](https://img.picui.cn/free/2024/08/29/66d08fbd99f04.png)![shot](https://img.picui.cn/free/2024/08/29/66d08fbdc8d08.png)

### 使用
`TaskManager`需要[`Shizuku`](https://github.com/RikkaApps/Shizuku)激活。对于安卓手表等小屏设备，我推荐使用[`Shizuku-wear`](https://github.com/java30433/Shizuku-wear)。激活方式不再赘述

在最近应用列表中，**单击**可以打开当前应用，**长按**可以查看应用信息，**从右往左滑**可以强行停止APP

由于方法的限制，最近应用列表中显示的并非打开过的APP，而是所有在后台中运行的APP进程，划卡强停后，所有于此APP相关的进程都会被杀死（类似于在设置中使用“强行停止”）。因此代码中预先隐藏了一部分重要的系统进程、Shizuku 和 TaskManager 本身。某些系统进程在强行停止后会自动重启，这是不可避免的，您可以长按后将其从任务列表中隐藏。

### 技术细节
使用了`Jetpack Compose`作为UI框架

`lib-navigator`是我编写的 compose 导航库

`lib-protostore`是我编写的基于`kotlinx-serialization-protobuf`实现的数据持久化库

所有代码均以 GPLv3 协议开源。

### 更新日志
- 2024-08-29 v1.1.0
  - 添加了在列表中隐藏应用的功能
  - 在 Github 开源
- 2024-08-28 v1.0.0
  - 第一个版本