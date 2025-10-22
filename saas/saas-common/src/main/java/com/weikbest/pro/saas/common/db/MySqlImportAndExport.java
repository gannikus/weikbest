package com.weikbest.pro.saas.common.db;

/**
 * @author wisdomelon
 * @date 2020/6/27
 * @project saas
 * @jdk 1.8
 */

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * 在进行导出的时候，需要注意命令语句的运行环境，如果已经将mysql安装路径下的bin加入到
 * 系统的path变量中，那么在导出的时候可以直接使用命令语句，否则，就需要在执行命令语句的
 * 时候加上命令所在位置的路径，即mysql安装路径想的bin下的mysqldump命令
 *
 * @author andy
 */
@Slf4j
public class MySqlImportAndExport {

    /**
     * 根据属性文件的配置导出指定位置的指定数据库到指定位置
     *
     * @param entity
     * @throws IOException
     */
    public static void export(MysqlEntity entity) throws IOException {
        long start = System.currentTimeMillis();
        Runtime runtime = Runtime.getRuntime();
        String command = getExportCommand(entity);
        Process process = runtime.exec(command);
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("@@@@@@@@@@@@@@@@@@导出命令执行完成，耗时{}ms.@@@@@@@@@@@@@@@@@", (System.currentTimeMillis() - start));
    }

    /**
     * 根据属性文件的配置把指定位置的指定文件内容导入到指定的数据库中
     * 在命令窗口进行mysql的数据库导入一般分三步走：
     * 第一步是登到到mysql； mysql -uusername -ppassword -hhost -Pport -DdatabaseName;如果在登录的时候指定了数据库名则会
     * 直接转向该数据库，这样就可以跳过第二步，直接第三步；
     * 第二步是切换到导入的目标数据库；use importDatabaseName；
     * 第三步是开始从目标文件导入数据到目标数据库；source importPath；
     *
     * @param entity
     * @throws IOException
     */
    public static void importSql(MysqlEntity entity) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        //因为在命令窗口进行mysql数据库的导入一般分三步走，所以所执行的命令将以字符串数组的形式出现
        //根据属性文件的配置获取数据库导入所需的命令，组成一个数组
        String cmdarray[] = getImportCommand(entity);
        //runtime.exec(cmdarray);//这里也是简单的直接抛出异常
        Process process = runtime.exec(cmdarray[0]);
        //执行了第一条命令以后已经登录到mysql了，所以之后就是利用mysql的命令窗口
        //进程执行后面的代码
        OutputStream os = process.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(os);
        //命令1和命令2要放在一起执行
        writer.write(cmdarray[1] + "\r\n" + cmdarray[2]);
        writer.flush();
        writer.close();
        os.close();
    }

    /**
     * 利用属性文件提供的配置来拼装命令语句
     * 在拼装命令语句的时候有一点是需要注意的：一般我们在命令窗口直接使用命令来
     * 进行导出的时候可以简单使用“>”来表示导出到什么地方，即mysqldump -uusername -ppassword databaseName > exportPath，
     * 但在Java中这样写是不行的，它需要你用-r明确的指出导出到什么地方，如：
     * mysqldump -uusername -ppassword databaseName -r exportPath。
     *
     * @param entity
     * @return
     */
    private static String getExportCommand(MysqlEntity entity) {
        StringBuffer command = new StringBuffer();
        //用户名
        String username = entity.getUsername();
        //用户密码
        String password = entity.getPassword();
        //需要导出的数据库名
        String exportDatabaseName = entity.getDbName();
        //从哪个主机导出数据库，如果没有指定这个值，则默认取localhost
        String host = entity.getHost();
        //使用的端口号
        String port = Integer.toString(entity.getPort());
        //导出路径
        String exportPath = entity.getPath();

        //注意哪些地方要空格，哪些不要空格
        /*command.append("/usr/local/mysql/bin/mysqldump --default-character-set utf8")
                .append(" -u").append(username)
                //密码是用的小p，而端口是用的大P。
                .append(" -p").append(password)
                .append(" -h").append(host)
                .append(" -P").append(port)
                .append(" -database ").append(exportDatabaseName)
                .append(" > ").append(exportPath);*/

        // 此处对数据库进行了设置
        /** 数据库执行了命令：
         * use mysql;
         * set password for 'root'@'localhost'=password('');
         * flush privileges
         * 因此这里是执行简单的导出语句
         */
        command.append("/usr/local/mysql/bin/mysqldump --socket=/var/lib/mysql/mysql.sock --default-character-set utf8 ")
                .append(exportDatabaseName)
                .append(" --result-file=").append(exportPath);

        log.info("dbak sql export command is: {}", command);

        return command.toString();
    }

    /**
     * 根据属性文件的配置，分三步走获取从目标文件导入数据到目标数据库所需的命令
     * 如果在登录的时候指定了数据库名则会
     * 直接转向该数据库，这样就可以跳过第二步，直接第三步；
     *
     * @param entity
     * @return
     */
    private static String[] getImportCommand(MysqlEntity entity) {
        //用户名
        String username = entity.getUsername();
        //用户密码
        String password = entity.getPassword();
        //需要导出的数据库名
        String importDatabaseName = entity.getDbName();
        //从哪个主机导出数据库，如果没有指定这个值，则默认取localhost
        String host = entity.getHost();
        //使用的端口号
        String port = Integer.toString(entity.getPort());
        //导出路径
        String importPath = entity.getPath();

        //第一步，获取登录命令语句
        String loginCommand = new StringBuffer().append("mysql -u").append(username)
                .append(" -p").append(password)
                .append(" -h").append(host)
                .append(" -P").append(port).toString();

        //第二步，获取切换数据库到目标数据库的命令语句
        String switchCommand = new StringBuffer("use ").append(importDatabaseName).toString();

        //第三步，获取导入的命令语句
        String importCommand = new StringBuffer("source ").append(importPath).toString();

        //需要返回的命令语句数组
        String[] commands = new String[]{loginCommand, switchCommand, importCommand};

        log.info("dbak sql impoty command is: [{}]", Arrays.toString(commands));

        return commands;
    }
}