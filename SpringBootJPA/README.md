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
具体配置可以参考[`application.properties`](https://github.com/lhh2002/BigDataCodeWarehouse/blob/main/SpringBootJPA/src/main/resources/application.properties) 重点关注：<font size="4" color="red">spring.jpa.database-platform</font>
此参数用来使用JPA对数据操作的类 MySQL8+使用`org.hibernate.dialect.MySQL8Dialect`、MySQL5+使用`org.hibernate.dialect.MySQL5Dialect`

## 3.对表进行增删改查操作

**新增数据**
```java
  @Test
  void contestAdd() {
        System.out.println("Pet add");
        Pet pet = new Pet();
        pet.setColor("红色");
        pet.setName("小红");
        petDao.save(pet);
        System.out.println("Pet add  success");
    }
```
**删除数据**
```java
 @Test
    void contestDelete() {
        petDao.deleteById(1);
        System.out.println("delete 1 success");
    }
```
**修改数据**
```java
    @Test
    void contestUpdate() {
        Pet pet = new Pet();
        pet.setId(1);
        pet.setColor("白色");
        petDao.save(pet);
        System.out.println("Update 1 success");
    }
```
**查询数据**
```java
   @Test
        // 使用Jpa 查询操作
    void contestFind() {
        // 根据主键查询
        Optional<Pet> byId = petDao.findById(1);
        Pet pet = byId.get();
        System.out.println("根据主键查询 +++++ " + pet);
        List<Pet> petDaoAll = petDao.findAll();
        System.out.println("查询所有 +++++ " + petDaoAll);
    }

    @Test
        // 使用自定义查询(需要遵循JPA的语法规范)
    void contestFindByName() {
        List<Pet> byName = petDao.findByName("小红");
        System.out.println(byName);

    }

    @Test
        //使用自定义SQL 查询
    void contestFindQuery() {
        List<Pet> byName = petDao.getByName("小红");
        System.out.println(byName);
    }
```

## 一对多
需要注意点：查询数据的时候查询`student`表时需要关注`clazz`表是否设置了立刻加载默认模式是不立刻加载要是查看clazz的数据会出来`no session`导致的原因是不是在之前的会话结束了没有加载出来所以报了这个异常
解决方案：
1. 在OneToMany加上（fetch = FetchType.EAGER）
2. 在启动类上加上还需加上一个注解（`EntityScan`）
```java
   //解决no session
    @Bean
    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
        return new OpenEntityManagerInViewFilter();
    }
```
**对表进行增删改查**
具体查看测试类：`SpringbootApplicationOneToMany`

## 添加连接池

使用 application.yml 文件对JPA 配置连接池 方式 http://127.0.0.1:8080/druid 查看连接池相关的配置信息


