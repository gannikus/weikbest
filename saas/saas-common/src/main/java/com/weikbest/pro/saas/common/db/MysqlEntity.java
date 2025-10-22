package com.weikbest.pro.saas.common.db;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.File;
import java.io.Serializable;

/**
 * @author wisdomelon
 * @date 2020/6/27
 * @project saas
 * @jdk 1.8
 */
@Data
@Accessors(chain = true)
public class MysqlEntity implements Serializable {

    /**
     * 统一前缀
     */
    private static String SCHEMA = "jdbc:mysql://";

    /**
     * 根路径
     */
    private static String SRC_PATH = StrBuilder.create()
            .append(File.separator).append("app")
            .append(File.separator).append("dbak").toString();

    /**
     * 文件名称
     */
    private static String FILE_NAME = "dbak.sql";

    /**
     * 数据库登录用户名
     */
    private String username;

    /**
     * 数据库登录用户名
     */
    private String password;

    /**
     * 数据库地址
     */
    private String host;

    /**
     * 数据库端口
     */
    private Integer port;

    /**
     * 数据库名称
     */
    private String dbName;

    /**
     * 数据库文件路径
     */
    private String path;

    /**
     * 根据熟悉信息拿到一个实例对象
     *
     * @param dbUsername
     * @param dbPassword
     * @param dbUrl：     jdbc:mysql://localhost:3306/saas?serverTimezone=GMT%2B8
     * @param dbBakTime: yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static MysqlEntity buildEntity(String dbUsername, String dbPassword, String dbUrl, String dbBakTime) {
        MysqlEntity mysqlEntity = new MysqlEntity();

        // 解析dbUrl，拿到host，port，dbName
        // localhost:3306/saas
        dbUrl = StrUtil.sub(dbUrl, SCHEMA.length(), dbUrl.contains("?") ? dbUrl.lastIndexOf("?") : dbUrl.length());

        String host = dbUrl.substring(0, dbUrl.indexOf(":"));
        Integer port = Integer.parseInt(dbUrl.substring(dbUrl.indexOf(":") + 1, dbUrl.lastIndexOf("/")));
        String dbName = dbUrl.substring(dbUrl.lastIndexOf("/") + 1);

        // 路径 /dbak/yyyyMMddHHmmss/dbak.sql
        String timePath = DateUtil.format(DateUtil.parse(dbBakTime), DatePattern.PURE_DATETIME_PATTERN);
        StrBuilder pathBuild = StrBuilder.create()
                .append(SRC_PATH).append(File.separator)
                .append(timePath).append(File.separator);

        // 创建文件路径文件夹
        File pathFile = new File(pathBuild.toString());
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }

        String exportPath = pathBuild.append(FILE_NAME).toString();
        mysqlEntity.setUsername(dbUsername)
                .setPassword(dbPassword)
                .setHost(host)
                .setPort(port)
                .setDbName(dbName)
                .setPath(exportPath);

        return mysqlEntity;
    }
}
