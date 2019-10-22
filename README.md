## 码楠笔记

## 资料网站
[github仓库](https://github.com/PhoebeNan/community)
[spring web](https://spring.io/guides)
[github登录接入](https://developer.github.com/apps/)
[bootstrap文档](https://v3.bootcss.com/)

zyn

## 工具

## SSL异常，GitHub认证失败javax.net.ssl.SSLHandshakeException: sun.security.validator.ValidatorException: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException
先删除r.cer文件，然后再导入    输入密钥库口令:changeit
keytool -delete -alias w -keystore cacerts
keytool -import -alias w -keystore cacerts -file D://authori/w.cer

## User表的SQL语句
```sql
create table USER
(
	ID INT auto_increment,
	ACCOUNT_ID VARCHAR(100),
	NAME VARCHAR(50),
	TOKEN CHAR(36),
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT,
	constraint USER_PK
		primary key (ID)
);

```

## flyway maven无法下载plugin
使用mvn compile flyway:migrate命令
