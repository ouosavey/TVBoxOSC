# TVBox 竖屏应用 - 本地构建指南

## 📋 环境要求

### Android 应用构建环境

1. **JDK 1.8+**
   - 推荐使用 JDK 8 或 JDK 11
   - 下载地址：https://adoptopenjdk.net/

2. **Android Studio**
   - 版本：Android Studio 4.0+
   - 下载地址：https://developer.android.com/studio

3. **Android SDK**
   - 编译 SDK：API 30
   - 目标 SDK：API 26
   - 最低 SDK：API 16
   - 组件：
     - Android SDK Platform 30
     - Android Build-Tools
     - Android SDK Command-line Tools

4. **Gradle**
   - 项目已配置 Gradle 6.7.1（通过 gradle wrapper）

### HarmonyOS 应用构建环境

1. **DevEco Studio**
   - 版本：3.0+
   - 下载地址：https://developer.huawei.com/consumer/cn/deveco-studio/

2. **HarmonyOS SDK**
   - API 版本：4.0 (API 11)
   - 需要安装"Phone"和"Tablet"设备类型

---

## 🔨 Android 应用构建步骤

### 方式一：使用 Android Studio

1. **打开项目**
   ```
   启动 Android Studio
   File → Open
   选择 /workspace 目录
   ```

2. **配置 SDK**
   ```
   File → Project Structure
   选择 SDK Location
   设置 Android SDK 路径
   ```

3. **同步项目**
   ```
   点击 "Sync Project with Gradle Files" 按钮
   等待同步完成
   ```

4. **构建 Debug 版本**
   ```
   Build → Build Bundle(s) / APK(s) → Build APK(s)
   或点击工具栏的绿色三角形按钮
   ```

5. **构建 Release 版本**
   ```
   Build → Generate Signed Bundle / APK
   选择 APK → Next
   配置签名信息或创建新签名
   选择 release 构建类型
   点击 Create
   ```

### 方式二：使用命令行

1. **配置环境变量**
   ```bash
   export ANDROID_HOME=/path/to/android-sdk
   export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools
   ```

2. **构建 Debug 版本**
   ```bash
   cd /workspace
   chmod +x gradlew
   ./gradlew :phone-android:assembleDebug
   ```

3. **构建 Release 版本**
   ```bash
   # 首先生成签名文件
   cd phone-android
   keytool -genkey -v -keystore tvbox-release.keystore \
     -alias tvbox -keyalg RSA -keysize 2048 -validity 10000

   # 配置签名后执行构建
   cd /workspace
   ./gradlew :phone-android:assembleRelease
   ```

### APK 文件位置

构建完成后，APK 文件位于：
- **Debug 版本**：`phone-android/build/outputs/apk/debug/phone-android-debug.apk`
- **Release 版本**：`phone-android/build/outputs/apk/release/phone-android-release-unsigned.apk`

---

## 📱 HarmonyOS 应用构建步骤

### 方式一：使用 DevEco Studio

1. **导入项目**
   ```
   启动 DevEco Studio
   File → Open
   选择 /workspace/phone-harmony 目录
   ```

2. **配置 SDK**
   ```
   Tools → SDK Manager
   选择 HarmonyOS
   勾选需要的 SDK 版本
   点击 Apply 安装
   ```

3. **同步项目**
   ```
   等待 Sync 完成
   ```

4. **构建 Debug 版本**
   ```
   Build → Build Haps(s) → Debug
   或使用快捷键 Ctrl + Shift + F10
   ```

5. **构建 Release 版本**
   ```
   Build → Build Haps(s) → Release
   ```

### HAP 文件位置

构建完成后，HAP 文件位于：
- **Debug 版本**：`phone-harmony/entry/build/default/outputs/hap/debug/entry-debug.hap`
- **Release 版本**：`phone-harmony/entry/build/default/outputs/hap/release/entry-release.hap`

### 安装 HAP 到设备

1. **使用 DevEco Studio**
   ```
   Run → Run 'entry' (或点击绿色三角形)
   选择连接的设备
   等待安装完成
   ```

2. **使用命令行工具**
   ```bash
   # 连接到设备
   hdc shell

   # 安装 HAP
   hdc install /path/to/entry-debug.hap
   ```

---

## 🛠️ 常见问题解决

### Android 构建问题

**问题 1：Gradle 下载失败**
```
解决方案：检查网络连接，或使用代理
export JAVA_OPTS="-Dproxy.host=127.0.0.1 -Dproxy.port=7890"
```

**问题 2：SDK 版本不匹配**
```
解决方案：在 File → Project Structure 中调整 SDK 版本
或在 build.gradle 中修改 compileSdkVersion
```

**问题 3：NDK 构建失败**
```
解决方案：确保 NDK 已安装
Android Studio → Preferences → Appearance & Behavior → System Settings → Android SDK → SDK Tools
勾选 NDK
```

**问题 4：依赖库下载慢**
```
解决方案：配置阿里云镜像
在 build.gradle 中添加：
allprojects {
    repositories {
        maven { url 'https://maven.aliyun.com/repository/public' }
        maven { url 'https://maven.aliyun.com/repository/google' }
    }
}
```

### HarmonyOS 构建问题

**问题 1：SDK 配置失败**
```
解决方案：检查 DevEco Studio 版本，确保支持当前 API 版本
```

**问题 2：签名验证失败**
```
解决方案：在 File → Project Structure → Signing Configs 中配置签名
```

**问题 3：设备连接失败**
```
解决方案：
1. 启用设备的开发者模式
2. 允许 USB 调试
3. 重新连接设备
```

---

## 📦 构建输出文件

### Android APK
```
phone-android/build/outputs/apk/
├── debug/
│   └── phone-android-debug.apk
└── release/
    └── phone-android-release-unsigned.apk
```

### HarmonyOS HAP
```
phone-harmony/entry/build/default/outputs/hap/
├── debug/
│   └── entry-debug.hap
└── release/
    └── entry-release.hap
```

---

## 🎯 后续步骤

1. **测试安装包**
   - 将 APK 传输到 Android 设备
   - 启用"安装未知来源应用"
   - 安装并测试

2. **应用签名**
   - 为 Release 版本签名
   - 生成 SHA256 指纹
   - 在各平台配置应用签名

3. **发布应用**
   - Android：发布到应用商店或提供 APK 下载
   - HarmonyOS：发布到华为应用市场

---

## 📞 获取帮助

如果在构建过程中遇到问题：
1. 查看构建日志中的具体错误信息
2. 检查环境配置是否正确
3. 搜索相关错误信息
4. 提交 Issue 到项目仓库
