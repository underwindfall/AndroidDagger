# Android Dagger
这个Repo就是简单的分析一下**dagger**是怎么实现依赖注入的过程的，原理的部分将在 [blog](https://qifanyang.com) 中介绍。

## Dagger

1. Component 是连接 **@Inject** 所需要的变量和变量所需要的构造器之间的链接桥梁
<!--![Component](https://raw.githubusercontent.com/underwindfall/blogAssets/master/blog/dagger/component.png)-->

2. Module
<!--![Module](https://raw.githubusercontent.com/underwindfall/blogAssets/master/blog/kotlin/Module.png)-->

可惜的是*@Inject *并非万能

- 接口没有构造函数

- 第三方库的类不能被标注

- 构造函数中的参数必须配置

这几种情况就不能被使用，所以就有了 **@Provides @Module**的诞生，用来补充Module的不足。

3. SubComponent vs Component dependencies
两者从功能行上来说都是想简化*Component*的使用，从而达到不用复写已有的注入来分享新的注入功能。
```
Component dependencies - Use this when you want to keep two components independent.

SubComponents - Use this when you want to keep two components coupled.
```

和针筒抽取module很像，使用dependencies相当于使用针筒来抽取component。使用dependencies相当于把两个component进行组合，但是有一个指导性的原则：

**被dependencies的component需要对外告知它能够提供的内容**

4. Scope

- @Qualifier @Named

就是简单区分同样的Qualifier（限定符）的作用相当于起了个区分的别名

- Provides vs Lazy

Lazy:只有在调用 Lazy<T> 的 get() 方法时才会初始化依赖实例注入依赖
```java
public class Man {
    @Inject
    Lazy<Car> lazyCar;

    public void goWork() {
        lazyCar.get().go(); // lazyCar.get() 返回 Car 实例
    }
}
```
Provides和lazy差不多但是会根据Module提供的方法所进行注入Provider<T>，每次调用它的 get() 方法都会调用到 @Inject 构造函数创建新实例或者 Module 的 provide 方法返回实例。

- @Binds

@Provides和@Binds这两个不能共存于一个module,
最初介绍dagger的几个变种中，有一种情况是不提供provides方法，而是在构造函数上添加@Inject的方法。其实这种方法有利于书写，方便快捷的优势，但是当遇上如上这种情况，我们需要注入的是一个接口的时候，我们无法使用@Inject注解构造函数的方法来提供一个接口（我们可以把@Inject添加到实现类中，但是在注入时，dagger并不知道你想要注入的是哪个实现。）
所以@Binds就这样诞生了，可以想象的是，@Binds的作用其实就是告诉dagger，当请求注入一个接口时，我们使用某个实现类何其绑定，用实现类来进行注入。

- @IntoSet

- @IntoMap

## Dagger Android


## DI Pattern
依赖注入，就是根据依赖关系解耦。本来的依赖关系被第三方Factory解除。
听上去不是白话，我们来举个例子

```kotlin
Class A{
    var b:B
}

Class B{}
```
转化成被第三方容器提供对象,这里dagger会通过寻找**@Inject**对象，然后发现起构造函数，如果也含有**@Inject**就会生成
相应Factory被注入
```kotlin
Class A{
    @Inject
    var b:B
}
```

