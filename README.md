# javava
抓娃娃

# repackage

    mvn clean package spring-boot:repackage

# 自定义打包

    mvn clean package dependency:copy-dependencies

# javava-zego

    mvn clean package -pl javava-zego -am dependency:copy-dependencies

创建流回调接口

http://localhost:8080/zego-api/v1/openStream

关闭流回调接口

http://localhost:8080/zego-api/v1/closeStream

# weixin-authc

登陆二维码(post)

http://localhost:8080/authc-api/authc

微信扫码回调

http://localhost:8080/authc-api/authz

扫码结果查询(get)

http://localhost:8080/authc-api/authc/{ticket}
