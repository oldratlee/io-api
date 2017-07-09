# `Java`的通用`IO API`设计

[:book: English Documentation](README-EN.md) | :book: 中文文档

------------------------------

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->


- [包的功能](#%E5%8C%85%E7%9A%84%E5%8A%9F%E8%83%BD)
- [更多信息](#%E6%9B%B4%E5%A4%9A%E4%BF%A1%E6%81%AF)
- [API设计的进一步学习资料](#api%E8%AE%BE%E8%AE%A1%E7%9A%84%E8%BF%9B%E4%B8%80%E6%AD%A5%E5%AD%A6%E4%B9%A0%E8%B5%84%E6%96%99)
    - [简单资料](#%E7%AE%80%E5%8D%95%E8%B5%84%E6%96%99)
    - [系统书籍](#%E7%B3%BB%E7%BB%9F%E4%B9%A6%E7%B1%8D)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

------------------------------

博文[A generic input/output API in Java](http://www.jroller.com/rickard/entry/a_generic_input_output_api)（by Rickard Öberg，[【译】Java的通用I/O API](https://github.com/oldratlee/translations/blob/master/generic-io-api-in-java-and-api-design/README.md)）中给出了一个通用`Java` `IO API`设计，并且有`API`的`Demo`代码。

更重要的一点是，这篇文章给出实现这个`API`设计本身的步骤和过程，这让`API`设计实现过程有了条理。文中示范了从 普通简单实现 整理成 正确分解、可以复用、可扩展的`API`设计 的过程。
这个很值得理解和学习！设计偏向是艺术，一个赏心悦目的设计，尤其是`API`设计，旁人看来多是妙手偶得的感觉，如果能有些章可循真是一件好事。

给出 _**减少艺术的艺术工作量**_ 的方法的人是大师。

原文中只给出设计的

- 发展思路
- 关键接口
- 典型的使用方式

没有给出实现细节和可运行的实现，看起来可能比较费力。（细致的分解后的设计往往比较抽象而不容易快速理解）

为了大家和自己更深入有效地学习，需要：  

1. 给出这个通用`IO API`的可运行的`Demo`实现。  
    这个工程即是本人的可运行的`Demo`实现。  
    当然个人力荐你先自己实现练习一下，这样比直接看我的实现，在学习上会有效得多！
1. 写了一篇分析总结。  
    本人的分析总结：[用Java I/O API设计练习的分析和总结](docs/java-api-design-exercise.md)。这个你可以直接看，以更高效方便地理解这个`API`的设计。

> PS：
>
> 上面2件事其实是份自学的家庭作业哦～ :laughing:  
> 在阿里中间件团队的时候，[@_ShawnQianx_ 大大](http://weibo.com/shawnqianx)看到这篇文章时，给组里的人布置家庭作业～ :bowtie:
>
> @_ShawnQianx_ 对这篇文章及作者的评论：
>
> 设计时，一要分解好系统，二是多个组件拼回来还是系统预期的样子，二步都做好是难度所在。这个人分析和把控的功力很好！

## 包的功能

```java
package com.oldratlee.io.core
	核心接口
package com.oldratlee.io.core.filter
	filter功能
package com.oldratlee.io.utils
	工具类
package com.oldratlee.io.demo
	demo类
```

## 更多信息

- 个人在组内分享时的PPT：[API设计实例分析](docs/ApiDesignSampleStudy.pptx)
- 本人对这篇博文的译文：[【译】Java的通用I/O API](https://github.com/oldratlee/translations/tree/master/generic-io-api-in-java-and-api-design/README.md)
- 问题交流： https://github.com/oldratlee/io-api/issues

## API设计的进一步学习资料

### 简单资料

- How to Design a Good API and Why it Matters(by Joshua Bloch) 【[本地下载](docs/How-to-Design-a-Good-API-and-Why-it-Matters-by-Joshua-Bloch.pdf)】  
    <http://lcsd05.cs.tamu.edu/slides/keynote.pdf>
- Google Search  
    <http://www.google.com.hk/search?&q=api+design>

### 系统书籍

- The Little Manual of API Design 【[本地下载](docs/The-Little-Manual-of-API-Design.pdf)】  
    <http://chaos.troll.no/~shausman/api-design/api-design.pdf>
- [Practical API Design: Confessions of a Java Framework Architect](http://www.amazon.com/Practical-API-Design-Confessions-Framework/dp/1430243171)  
    中文版：[《软件框架设计的艺术》](http://book.douban.com/subject/6003832/)
- [Framework Design Guidelines: Conventions, Idioms, and Patterns for Reusable .NET Libraries (2nd Edition)](http://www.amazon.com/Framework-Design-Guidelines-Conventions-Libraries/dp/0321545613)  
    中文版：[.NET设计规范](http://book.douban.com/subject/4805165/)
