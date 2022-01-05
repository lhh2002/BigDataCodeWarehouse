# SpringBootJPA

使用JPA对MySQL进行增删改查 `一张表`、`一对多`、`多对多`

## 1.配置pom文件

```xml
<!--MySQL驱动包配置-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.22</version>
</dependency>
<!--jpa配置-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

```

## 2.配置application文件（重点）
具体配置可以参考`application.properties` 重点关注：<font size="4" color="red">spring.jpa.database-platform</font>
此参数用来使用JPA对数据操作的类 MySQL8+使用`org.hibernate.dialect.MySQL8Dialect`、MySQL5+使用`org.hibernate.dialect.MySQL5Dialect`

## 3.对表进行增删改查操作

