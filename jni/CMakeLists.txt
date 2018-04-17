
# 指定CMake的最小版本

cmake_minimum_required(VERSION 3.4.1)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

# 创建并命名一个库，把它设置为静态库或者共享库类型，并且提供它的源码的相对路径
# 你可以顶一个多个库，CMAKE会为你构建它们。
# Gradle会自动将共享库打包到你的apk中

add_library( # 设置库的名字
             hello-lib

             # 设置库为共享类型
             SHARED

             # 提供一个到你源文件的相对路径（可包含多个文件）.
             src/main/cpp/hello-lib.cpp )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

# 搜索一个预编译库并且将其路径存储为一个变量
# 因为CMAKE 在搜索路径中默认包括系统库，因此你仅仅需要指定你想要添加的公共NDK库的名字即可
# CMAKE会在完成编译之前来验证库是否存在

find_library( # Sets the name of the path variable.
              # 设置路径变量的名字
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              # 指定 你想要添加的 NDK库 的名字
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.


# 指定CMAKE可以将那些库链接到你的目标库。
# 你可以链接多个库，比如你在构建脚本中定义的库，预编译的第三方库，或者系统库

target_link_libraries( # Specifies the target library.
                       # 指定目标库
                       hello-lib

                       # Links the target library to the log library
                       # included in the NDK.
                       # 链接 目标库 到 NDK中包含的 log库
                       ${log-lib} )