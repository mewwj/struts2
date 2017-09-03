# struts2
Struts2框架发展

Apache Struts 在 2000年5月由Craig McClanahan 发起,并于2001年7月发布了1.0版。
Struts一出现便大受欢迎,更成为了以后几年内web 开发的实际标准。
Struts2是Struts的下一代产品，是在WebWork的技术基础上进行了合并。

Struts2是在WebWork2基础发展而来的。主要是因为struts2有以下优点：

1 > 在软件设计上Struts2没有像struts1那样跟Servlet API和struts API有着紧密的耦合，Struts2的应用可以不依赖于Servlet API和struts API。 Struts2的这种设计属于无侵入式设计，而Struts1却属于侵入式设计。

2> Struts2提供了拦截器，利用拦截器可以进行AOP编程，实现如权限拦截等功能。
3> Strut2提供了类型转换器，我们可以把特殊的请求参数转换成需要的类型。在Struts1中，如果我们要实现同样的功能，就必须向Struts1的底层实现BeanUtil注册类型转换器才行。

4> Struts2提供支持多种表现层技术，如：JSP、freeMarker、Velocity等
5> Struts2的输入校验可以对指定方法进行校验，解决了Struts1长久之痛。
6> 提供了全局范围、包范围和Action范围的国际化资源文件管理实现

搭建Struts2环境时，我们一般需要做以下几个步骤的工作：

1》找到开发Struts2应用需要使用到的jar文件.

2》编写Struts2的配置文件

3》在web.xml中加入Struts2 MVC框架启动配置

         <package name="itcast" namespace="/test" extends="struts-default">
         <action name="helloworld" class="cn.itcast.action.HelloWorldAction" method="execute" >
         <result name="success">/hello.jsp</result>
         </action>
         </package> 
         
在struts2框架中使用包来管理Action，包的作用和Java中的类包是非常类似的，它主要用于管理一组业务功能相关的action。在实际应用中，我们应该把一组业务功能相关的Action放在同一个包下。

    配置包时必须指定name属性，该name属性值可以任意取名，但必须唯一，他不对应java的类包，如果其他包要继承该包，必须通过该属性进行引用。包的namespace属性用于定义该包的命名空间，
    命名空间作为访问该包下的Action的路径的一部分，如访问上面例子的Action，访问路径为：/test/helloworld.action。 namespace属性可以不配置，
    对本例而言，如果不指定该属性，默认的命名空间为“”（空字符串）。

    通常每个包都应该继承struts-default包， 因为Struts2很多核心的功能都是拦截器来实现。如：从请求中把请求参数封装到action、文件上传和数据验证等等都是通过拦截器实现的。 
    struts-default定义了这些拦截器和Result类型。可以这么说：当包继承了struts-default才能使用struts2提供的核心功能。 struts-default包是在struts2-core-2.x.x.jar文件中的struts-default.xml中定义。 
    struts-default.xml也是Struts2默认配置文件。 Struts2每次都会自动加载 struts-default.xml文件。

包还可以通过abstract=“true”定义为抽象包，抽象包中不能包含action。

 

Action名称的搜索顺序：

 

1．获得请求路径的URI，例如url是：http://server/struts2/path1/path2/path3/test.action

 

2．首先寻找namespace为/path1/path2/path3的package，如果存在这个package，则在这个package中寻找名字为test的action，如果不存在这个package则转步骤3；

 

3．寻找namespace为/path1/path2的package，如果存在这个package，则在这个package中寻找名字为test的action，如果不存在这个package，则转步骤4；

 

4．寻找namespace为/path1的package，如果存在这个package，则在这个package中寻找名字为test的action，如果仍然不存在这个package，就去默认的namaspace的package下面去找名字为test的action（默认的命名空间为空字符串"" ），如果还是找不到，页面提示找不到action。

Action配置中的各项默认值：

 

        <package name="itcast" namespace="/test" extends="struts-default">
                <action name="helloworld" class="cn.itcast.action.HelloWorldAction"  method="execute" >
        <result name="success">/WEB-INF/page/hello.jsp</result>
                </action>
          </package> 

 

1>如果没有为action指定class，默认是ActionSupport。

2>如果没有为action指定method，默认执行action中的execute() 方法。

3>如果没有指定result的name属性，默认值为success。

用struts2完成登录案例：

在index.jsp中：

 
复制代码

            <body>
              <div align="center"> 
                 <h1>用户登录界面</h1>
                 <div>${msg}</div>
                 <div>
                  <form action="${pageContext.request.contextPath}/csdn/loginAction" method="post">
                    <table>
                       <tr>
                         <td> 用户名</td>
                         <td><input type="text" name="username"/>
                         </td>
                       </tr>
                       <tr>
                        <td>密码</td>
                        <td>
                          <input type="password" name="userpass"/>
                        </td> 
                       </tr>
                       <tr>
                         <td colspan="2">
                          <input type="submit" value="登录">

                          <input type="reset">
                         </td>
                       </tr>
                    </table>
                  </form>
                  </div>
                  </div>
              </body>
            在LoginAction类中
            public class LoginAction implements Action{
              private String username;
              private String userpass;
              private String msg;
            注意：此处省略了get,set方法
            public String login(){
            System.out.println("你传递的参数值是："+username+"----------"+userpass);
            if("feng".equals(username)){
            return SUCCESS;
            }
            this.setMsg("重新登录");
            return INPUT;
            }
            }
            Struts2的配置文件中：
            <?xml version="1.0" encoding="UTF-8"?>
            <!DOCTYPE struts PUBLIC 
            "-//Apache Software Foundation//DTD Struts Configuration 2.1//EN" 
            "http://struts.apache.org/dtds/struts-2.1.dtd">
            <struts>
              <package name="redarmys" extends="struts-default" namespace="/csdn">
                <action name="loginAction" class="cn.csdn.action.LoginAction"  method="login">
                   <result name="success">/sc.jsp</result>
                   <result name="input">/index.jsp</result>
                </action>
              </package>
            </struts>

 

注意：解决在断网环境下,配置文件无提示的问题

    我们可以看到Struts.xml在断网的情况下,前面有一个叹号,这时,我们按alt+/ 没有提示,这是因为” 
    http://struts.apache.org/dtds/struts-2.0.dtd”是一个网络地址,如果上网的话,IDE会自动帮我们下载此文件,如果断网就没有办法了,
    但是我们还是有解决方法的.
   首先在源码包里找到struts-2.0.dtd这个文件
在MyEclipse中菜单栏中选择:windowPreferencesMyEclipseFiles and Editors
Xmlxml Catalog在右边的下拉框中选择User Specified Entries点击Add按钮左边选中Catalog Entry右边的
location中点击File System在弹出的对话框中将搜索到的文件全路径    复制上去,并选中struts-2.0.dtd在key type下拉框中选择URIkey文本框中填写:
http://struts.apache.org/dtds/struts-2.0.dtd ,点击OK即可. 这时我们可以看到Struts.xml文件中还有叹号,我们可以在Struts标签与package标签中打入
一个回车后保存即可.

问题一:大家可以看到这里用到了EL表达式,我们知道EL表达式只能取出page,request,session,application四个范围之一里面的数据,
但是我这里并没有将任何数据放入到四个范围中,为什么这里能够取出数据来呢?
问题二: Chapter1Action是一个非常普通的类,它不是Servlet,为什么能够处理用户的请求呢?

是因为：

Struts2用于处理用户请求的Action,没有与Servlet API耦合,显示无法处理用户请求,而Struts2提供了系列拦截器,该系列拦截器负责将HttpServletRequest请求中的请求参数解析出来,传入到Action中,并调用Action的execute方法来处理用户的请求.

Struts.xml配置中包与action的介绍:

        <package name="chapter1" namespace="/chapter1" extends="struts-default">
        <action name="HelloWorld" class="chapter1.action.Chapter1Action" method="execute">
           <result name="success">/WEB-INF/JspPage/chapter1/HelloWorld.jsp</result>
        </action>
        </package>

Struts2框架中使用包来管理action,避免了Servlet在web.xml中难以管理的与维护的局面.包的作用和java中的类包是非常类似的,
它主要用于管理一组业务功能相关的action,在实际应用中,我们应该把一组业务功能相关的action 放在同一个包下.

配置包时必须指定name属性,该name属性值可以任意取名,但必须唯一,如果其他包要继承该包,必须通过该属性进行引用,
包的namespace属性用于定义该包的命名空间,命名空间作用为访问该包下的action路径的一部分,见示例.namespace属性可以不配置,
如果不指定该属性,默认的命名空间为””

通常每个包都应该继承struts-default包,因为struts2很多核心功能都是拦截来实现的,如,从请求中把请求参数封闭到action,
文件上传和数据验证等都是通过拦截器实现的,struts-default定义了这些拦截器和Result类型,可以这么说,
当包继承了struts-default才能使用struts2提供的核心功能,struts-default包是在struts2-core-2.xx.jar文件中的struts-defalut.xml中定义,
struts-default.xml也是struts2默认配置文件,struts2每次都会自动加载struts-default.xml文件.

package还有一个abstract=”true”属性,指定此包为抽象包,和抽象类的概念差不多,说明此包只能被其他包继承,则它里面不允许包含action元素.

        <action name="HelloWorld" class="chapter1.action.Chapter1Action" method="execute">
        <result name="success">/WEB-INF/JspPage/chapter1/HelloWorld.jsp</result>
        </action> 


Action 元素method属性,默认值为method=”execute”,也就是当action接收到请求后,交给哪个方法去处理,默认的是交给execute方法去处理,当然,也可以交给其他方法,后面会讲解到.

<result name="success">/WEB-INF/JspPage/chapter1/HelloWorld.jsp</result> 


result元素主要定义视图的跳转和返回的行为及类型,后面会详细介绍.

struts.xml文件的分离
从上一个项目实践中可以看到,我们的web.xml文件非常之大,到后来是越来越难的查找与维护,看得头都是大的,Struts2配置文件可以分离,很好的解决了此问题.
通过主次配置文件的分离,可以加强团队间的合作,并且互不打扰彼此的配置文件,出了问题也知道责任在哪里.
在实例开发中也是这样做的,通过一个主文件中,打开全局开关,引入其他子配置文件,如:
复制代码

        <?xml version="1.0" encoding="UTF-8" ?>
        <!DOCTYPE struts PUBLIC
            "-//Apache Software Foundation//DTD Struts Configuration 2.0//EN"
            "http://struts.apache.org/dtds/struts-2.0.dtd">
        <struts>
        <constant name="struts.enable.DynamicMethodInvocation" value="false"/>
        <constant name="struts.devMode" value="false"/>
        <constant name="struts.i18n.encoding" value="UTF-8"/>
        <constant name="struts.serve.static.browserCache " value="false"/>
        <include file="chapter1.xml"/>
        </struts> 



        常用开关的介绍
        <constant name="struts.i18n.encoding" value="UTF-8"/>
        指定Web应用的默认编码集，相当于调用HttpServletRequest的setCharacterEncoding方法
        <constant name="struts.action.extension" value="do"/>
        该属性指定需要Struts 2处理的请求后缀，该属性的默认值是action，即所有匹配*.action的请求都由Struts 2处理。    
        如果用户需要指定多个请求后缀，则多个后缀之间以英文逗号（，）隔开。   
        <constant name="struts.serve.static.browserCache " value="false"/>
        设置浏览器是否缓存静态内容，默认值为true，开发阶段最好false
        <constant name="struts.configuration.xml.reload" value="true"/>
        当struts的配置文件修改后，系统是否自动重新加载该文件，默认值为false，开发阶段最好true
        <constant name="struts.devMode" value="true"/>
        开发模式下设为true，这样可以打印出更详细的错误信息
        <constant name="struts.enable.DynamicMethodInvocation" value="false"/>
        动态方法调用,可以解决多个请求对应一个Servlet的问题,后面详细讲解,默认为true,关闭则设为false.

        这里只是列举了一些常用的开关,当然还有许多其他的开关,后面的学习中会逐渐介绍,大家在这里先了解一下.



            struts.serve.static.browserCache 该属性设置浏览器是否缓存静态内容。当应用处于开发阶段时，我们希望每次请求都获得服务器的最新响应，则可设置该属性为false。

            struts.enable.DynamicMethodInvocation 该属性设置Struts 2是否支持动态方法调用，该属性的默认值是true。如果需要关闭动态方法调用，则可设置该属性为false。

            struts.enable.SlashesInActionNames 该属性设置Struts 2是否允许在Action名中使用斜线，该属性的默认值是false。如果开发者希望允许在Action名中使用斜线，则可设置该属性为true。

            struts.tag.altSyntax 该属性指定是否允许在Struts 2标签中使用表达式语法，因为通常都需要在标签中使用表达式语法，故此属性应该设置为true，该属性的默认值是true。

            struts.devMode该属性设置Struts 2应用是否使用开发模式。如果设置该属性为true，则可以在应用出错时显示更多、更友好的出错提示。该属性只接受true和flase两个值，
            该属性的默认值是false。通常，应用在开发阶段，将该属性设置为true，当进入产品发布阶段后，则该属性设置为false。

            struts.i18n.reload该属性设置是否每次HTTP请求到达时，系统都重新加载资源文件。该属性默认值是false。在开发阶段将该属性设置为true会更有利于开发，
            但在产品发布阶段应将该属性设置为false。

            提示 开发阶段将该属性设置了true，将可以在每次请求时都重新加载国际化资源文件，从而可以让开发者看到实时开发效果；产品发布阶段应该将该属性设置为false，是为了提供响应性能，每次请求都需要重新加载资源文件会大大降低应用的性能。

            struts.ui.theme该属性指定视图标签默认的视图主题，该属性的默认值是xhtml。
            struts.ui.templateDir该属性指定视图主题所需要模板文件的位置，该属性的默认值是template，即默认加载template路径下的模板文件。
            struts.ui.templateSuffix该属性指定模板文件的后缀，该属性的默认属性值是ftl。该属性还允许使用ftl、vm或jsp，分别对应FreeMarker、Velocity和JSP模板。
            struts.configuration.xml.reload该属性设置当struts.xml文件改变后，系统是否自动重新加载该文件。该属性的默认值是false。
            struts.velocity.configfile该属性指定Velocity框架所需的velocity.properties文件的位置。该属性的默认值为velocity.properties。
            struts.velocity.contexts该属性指定Velocity框架的Context位置，如果该框架有多个Context，则多个Context之间以英文逗号（,）隔开。
            struts.velocity.toolboxlocation该属性指定Velocity框架的toolbox的位置。
            struts.url.http.port该属性指定Web应用所在的监听端口。该属性通常没有太大的用户，只是当Struts 2需要生成URL时（例如Url标签），
            该属性才提供Web应用的默认端口。
            struts.url.https.port该属性类似于struts.url.http.port属性的作用，区别是该属性指定的是Web应用的加密服务端口。
            struts.url.includeParams该属性指定Struts 2生成URL时是否包含请求参数。该属性接受none、get和all三个属性值，分别对应于不包含、
            仅包含GET类型请求参数和包含全部请求参数。
            struts.custom.i18n.resources该属性指定Struts 2应用所需要的国际化资源文件，如果有多份国际化资源文件，则多个资源文件的文件名以英文逗号（,）
            隔开。
            struts.dispatcher.parametersWorkaround 对于某些Java EE服务器，不支持HttpServlet Request调用getParameterMap()方法，
            此时可以设置该属性值为true来解决该问题。该属性的默认值是false。对于WebLogic、Orion和OC4J服务器，通常应该设置该属性为true。
            struts.freemarker.manager.classname 该属性指定Struts 2使用的FreeMarker管理器。
            该属性的默认值是org.apache.struts2.views.freemarker.FreemarkerManager，这是Struts 2内建的FreeMarker管理器。
            struts.freemarker.wrapper.altMap该属性只支持true和false两个属性值，默认值是true。通常无需修改该属性值。
            struts.xslt.nocache 该属性指定XSLT Result是否使用样式表缓存。当应用处于开发阶段时，该属性通常被设置为true；当应用处于产品使用
            阶段时，该属性通常被设置为false。
            struts.configuration.files 该属性指定Struts 2框架默认加载的配置文件，如果需要指定默认加载多个配置文件，则多个配置文件的文件名之间以英文逗号（,）隔开。
            该属性的默认值为struts-default.xml,struts-plugin.xml,struts.xml，看到该属性值，读者应该明白为什么Struts 2框架默认加载struts.xml文件了。 
            在请求时,路径后的后缀action可要可不要,即下面的两种请求都是可以的
            http://localhost:8080/Struts2/chapter1/HelloWorld
            http://localhost:8080/Struts2/chapter1/HelloWorld.action

            Action配置中的各项默认值
            请看下面的代码
            <action name="Login">
            <result>/WEB-INF/JspPage/chapter1/Login.jsp</result>
            </action> 
            我们发现,当我们请求的路径为http://localhost:8080/Struts2/chapter1/Login时,同样可以实现页面的跳转,这是怎么回事呢?
            如果没有为action指定class,默认是ActionSupport类
            <action name="Login"> 
            相当于 
            <action name="Login" class="com.opensymphony.xwork2.ActionSupport">
            如果没有为action指定method,默认执行action中的execute()方法
            <action name="Login">
            相当于
            <action name="Login" class="com.opensymphony.xwork2.ActionSupport"
            method="execute">
            如果没有指定result的name属性,默认值为success.
            <result>相当于<result name="success">


            提出一个问题ActionSupport这个类到底是个什么类呢?
            首先要肯定的一点是他是一个具有execute方法的类,并且execute方法返回”success”字符串,因为只有这样,前面的运行结果才能说的通. 
            ActionSupport还实现了很多其他的结果,提供了许多定制的功能.


            ActionSupport类的作用 
            struts2不要求我们自己设计的action类继承任何的struts基类或struts接口，但是我们为了方便实现我们自己的action，
            大多数情况下都会继承com.opensymphony.xwork2.ActionSupport类，并重写此类里的public String execute() throws Exception方法。
            因为此类中实现了很多的实用借口，提供了很多默认方法，这些默认方法包括国际化信息的方法、默认的处理用户请求的方法等，
            这样可以大大的简化Acion的开发。 
            Struts2中通常直接使用Action来封装HTTP请求参数，因此，Action类里还应该包含与请求参数对应的属性，并且为属性提供对应的getter
            和setter方法。
            课堂笔记
            默认值
            class="" ActionSupport
            method="" execute
            name="" “success”
            Action接口里提供了一些常量及execute方法,通常我们自己写的Action可以实现这个接口, ActionSupport已经实现了这个接口,
            并且还实现了验证,国际化等功能的接口,所以我们自己写的Action类通常会继承ActionSupport这个类来达到启用验证框架,国际化,
            自动转换等功能的目的.
