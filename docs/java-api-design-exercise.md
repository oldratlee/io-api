_转自 个人博客[通用Java I/O API设计练习总结](http://oldratlee.com/493/tech/java/java-api-design-exercise.html) 和 自己在公司Blog[通用Java I/O API设计练习总结](http://code.alibabatech.com/blog/architecture_1489/java-api-design-exercise.html)。_

通用Java I/O API设计练习总结
===============================

[Java的通用I/O API](https://github.com/oldratlee/translations/tree/master/generic-io-api-in-java-and-api-design)（by _Rickard Öberg_）中给出了一个通用Java IO API设计，并且有API的Demo代码。

博文中只给出设计的发展思路、关键接口、典型的使用方式。没有实现细节，看起来可能比较费力。  
\# 细致的分解后的设计往往比较抽象，不容易快速理解。

做为练习，实现这个API。本工程即是实现示例 :)

这里给出我的实现和说明，节省你一些理解的时间。   
\# 我在组内分享时的PPT：[API设计实例分析](ApiDesignSampleStudy.pptx)

一、实现展示
---------------------

### 关键接口的依赖关系图

![依赖关系]( interface-dependency.jpg )

### 接口的调用序列图

![调用序列](invocation-sequence.jpg)

图中一些约定：

* 红色的方框：创建对象、清理资源 操作
* 蓝色的方法：核心接口上的方法
* 红色的方法：IO的操作所在的方法

注： [UML图源文件](generic_io_uml.asta)，使用[Astah](http://astah.net/download#community)绘制。

### 包的功能

- package com.oldratlee.io.core  
核心接口
- package com.oldratlee.io.core.filter  
实现的Filter功能的类
- package com.oldratlee.io.utils  
工具类
- package com.oldratlee.io.demo  
Demo示例的Main类

二、实现分析
----------------------

下面列一下序列图中的一些关键信息：

1. 客户负责创建Input、Output、调用input.transferTo(output)方法。
2. Input、Output负责初始化、清理资源；   
新建Sender、Receiver对象；    
调用Sender、Receiver方法，驱动实例的IO操作。

再回顾一下原作者的分析：

1. 客户代码，初始化了传输，要知道输入和输出的源。
1. 从输入中读的代码。
1. 辅助代码，用于跟踪整个过程。这些代码我希望能够重用，而不管是何种传输的类型。
1. 最后这个部分是接收数据，写数据。这个代码，我要批量读写，可以在第2第4部分修改，改成一次处理多行。

三、总结
----------------------

### API的设计过程

1. 分析整理已有的典型程序代码   
注意这里不要涉及修饰性的功能，先使用最简单典型的例子。
1. 把代码按功能职责分类，描述出功能职责（以后把功能职责 简称化 功能）
1. 提取出客户的操作，这些操作不会受设计的影响，并就是API使用的入口功能。   
入口上是大粒度的描述。
1. 从入口功能开始，按照功能的依赖关系，逐个整理各个功能的
	- 接口方法
	- 要依赖功能，作为接口方法上的参数，关联2个功能。
	- 用户看到的部分粒度变细，分解内部功能。
1. 与最终操作连接后结束API整理    
这个例子中，即实际的IO操作。

### 这样设计结果

1. 分离的功能：  
	- 资源维护 和 操作分离（IO资源维护和IO操作分离）
	- 核心功能 和 修饰性功能
2. 分离功能之间功能方法参数关联起来，即外围功能持有依赖功能，组合方式（非继承）。
3. 各个功能有明确的接口，且是组合的，所以可以方便Wrap以拦截加入修饰性功能。
4. 同时整理出了API（外围接口）和SPI（内部接口）

虽然总结了一下，查要照着操练，难免显得过于很有难度的！随意给出指正，讨论 :)
