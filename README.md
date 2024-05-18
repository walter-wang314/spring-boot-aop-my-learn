# Spring Boot Aspect Oriented Programming (AOP) Example

### Use postman to request for the Header: 

use-id: bc434e38-f377-4c64-9ee9-55975790ab02

All the todo is stored in one HashMap

This is a small TODO-list example application that shows how to create Aspect that handle cross-cutting concerns. It demonstrates 3 different Aspects:

* TimeLogAspect: an aspect applied through the @Timed annotation that logs the duration of a method call
* RequestLogAspect: an aspect applied to methods with the Spring @RequestMapping that logs request paths
* RestrictAspect: an aspect that restricts method access through a @Restrict annotation

A blog post explaining more can be found at https://niels.nu/blog/2017/spring-boot-aop.html


----------------------------------------------------------------------
Data Structure:

{
"todoLists": [
{
"name": "morning",
"todos": [
"wash face",
"brash teach"
]
},
{
"name": "afternoon",
"todos": [
"clothing",
"bathing"
]
}
]
}


- TodoRepository 初始化数据结构；
- 通过 Controller 进行访问，访问的时候配置 Customize HTTP Request Header （use-id: bc434e38-f377-4c64-9ee9-55975790ab02）；
- 自定义 Annotation and AOP 配合使用：
  - 可以用来记录方法的执行时间；
  - 记录请求日志；
  - @Before 可以用来判断权限；
