# kbc-template-nf

## 功能
基于SprintBoot的java项目脚手架代码

## 维护者
wangyanan
zhanghao

## 使用说明
```shell
#环境要求：
#java 8或以上版本
#maven 3或以上版本
#git 1.8或以上版本

#初始化项目
mkdir project1
cd project1
git init
git remote add origin https://github.com/k2jf/kbc-demo.git

#或从已有项目repo里下载代码
#git clone git@github.com:k2jf/kbc-demo.git project1
#cd project1

#从组件库repo里下载框架代码（用--allow-unrelated-histories强制下载非当前repo里的代码到当前目录）
#由于框架代码在项目根目录下，因此我们让这些代码下载后关联到项目repo而不再是框架组件的repo
#下载后仍然可以pull框架代码，但不可以再push（会加入不必要的代码）
#注意：如果项目里已经包含了框架代码，则不要执行此步骤
git pull --allow-unrelated-histories git@github.com:k2jf/kbc-template-nf.git master

#从组件库里下载每个组件代码，-P参数指定组件在项目里所在的目录
#并按每个组件README文件集成到项目
#注意：如果项目里已经包含了所需组件代码，则不要执行此步骤
git remote add -f kbc-announce git@github.com:k2jf/kbc-announce.git
git subtree add -P src/main/java/com/k2data/kbc/announce kbc-announce master --squash

#检查一下当前已连接的各个远程库
git remote -vv

#验证代码是否能正常启动
#启动后可通过浏览器访问，默认地址http://localhost:9080
mvn clean package
java -jar target/kbc-template-nf-0.0.1-SNAPSHOT.jar

#开发代码，其中可能修改项目代码，也可能修改组件代码...（此处省略10000字）

#提交代码，组件代码也会进入项目repo
git add .
git commit -m "..."
git push origin branch1

#若项目需要使用新版本组件（假设新版本分支名为"v2.0"）
#若有冲突，需要手工解决
git subtree pull -P src/main/java/com/k2data/kbc/announce kbc-announce v2.0 --squash

#若需要将组件定制代码推送到组件库
#先执行下面的命令，然后提交一个PR给组件管理员审核，后者决定是否合并到master
git subtree push -P src/main/java/com/k2data/kbc/announce kbc-announce branch1
```
