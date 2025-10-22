package com.weikbest.pro.saas.sys.common.ext;

/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/9/22
 * <p>
 * 生成头像，上传到阿里云后返回链接
 */

import cn.hutool.core.util.StrUtil;
import com.weikbest.pro.saas.common.constant.WeikbestConstant;
import com.weikbest.pro.saas.sys.param.service.ThirdConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author 西瓜瓜
 * @project saas
 * @jdk 1.8
 * @since :2022/9/20
 * <p>
 * 生成头像
 */
@Component
@Slf4j
public class CreateAvatarService {

    /**
     * 头像后缀
     */
    private static final String SUFFIX = "png";
    private static final String DOT_SUFFIX = "." + SUFFIX;

    @Autowired
    private ThirdConfigService thirdConfigService;

    // 获取关键字，也就是对传过来的的名称进行切割
    public static String getKeyName(String name) {
        if (name.length() >= WeikbestConstant.THREE_INT) {
            return StrUtil.subSuf(name, name.length() - 2);
        }
        if (name.length() == WeikbestConstant.TWO_INT) {
            return StrUtil.subSuf(name, name.length() - 1);
        }
        return name;
    }

    /**
     * 绘制字体头像 输入到本地
     *
     * @param name
     * @param outputPath 文件输出到本地的路径
     * @param outputName 文件输出到本地的名称
     * @throws IOException
     */
    public static void generateImg(String name, String outputPath, String outputName)
            throws IOException {
        String jsonStr = getKeyName(name);

        String filename = outputPath + File.separator + outputName + DOT_SUFFIX;
        File file = new File(filename);
        BufferedImage rounded = commonGenerateImg(jsonStr, 180, 180);
        ImageIO.write(rounded, SUFFIX, file);
    }

    /**
     * 绘制字体头像 生成二进制数组
     * 如果是英文名，只显示首字母大写
     * 如果是中文名，只显示最后两个字
     *
     * @param name   放到图片中的名称
     * @param width  图片宽度
     * @param height 图片高度
     * @throws IOException
     */
    public static byte[] generateImg(String name, int width, int height)
            throws IOException {
        BufferedImage rounded = commonGenerateImg(name, width, height);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(rounded, SUFFIX, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * 绘制字体头像
     * 如果是英文名，只显示首字母大写
     * 如果是中文名，只显示最后两个字
     *
     * @param name   放到图片中的名称
     * @param width  图片宽度
     * @param height 图片高度
     * @return
     */
    public static BufferedImage commonGenerateImg(String name, int width, int height) {
//        Font font = new Font("Microsoft YaHei", Font.PLAIN, 80);
        Font font = new Font("微软雅黑", Font.PLAIN, 80);
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) bi.getGraphics();
        g2.setColor(getRandomColor());
        g2.fillRect(0, 0, width, height);
        g2.setFont(font);
        g2.setColor(Color.WHITE);
        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(name, context);
        double x = ((double) width - bounds.getWidth()) / 2.0D;
        double y = ((double) height - bounds.getHeight()) / 2.0D;
        double ascent = -bounds.getY();
        double baseY = y + ascent;
        g2.drawString(name, (int) x, (int) baseY);
        return bi;
    }

    /**
     * 判断字符串是否为中文
     *
     * @param str
     * @return
     */
    public static boolean isChinese(String str) {
        String regEx = "[\\u4e00-\\u9fa5]+";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * 获得随机颜色
     *
     * @return
     */
    private static Color getRandomColor() {
        return new Color(getColorRange(), getColorRange(), getColorRange());
    }

    private static int getColorRange() {
        int max = 250, min = 1;
        return (int) (Math.random() * (double) (max - min) + (double) min);
    }

    /**
     * 图片做圆角处理
     *
     * @param image
     * @param cornerRadius
     * @return
     */
    public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return output;
    }

    public String generateImageAndUploadOss(String name) {
        return generateImageAndUploadOss(name, 180, 180);
    }

    private String generateImageAndUploadOss(String name, int width, int height) {
        if (StrUtil.isBlank(name)) {
            return "";
        }
        // 获取关键字
        String jsonStr = getKeyName(name);
        int abs = Math.abs(name.hashCode());
        try {
            log.info("生成头像开始》》》》》》");
            byte[] bytes = generateImg(jsonStr, width, height);
            // 下面这一步是吧文件传到oss上面去，这个工具类是自己写的，你需要替换，传到你们自己的服务器上去。返回的是图片的访问路径
            String headImage = thirdConfigService.aliyunOssService().uploadFileAvatar(new ByteArrayInputStream(bytes), "user", abs + DOT_SUFFIX);
            log.info("生成头像结束》》》》》》");
            log.info("生成头像的路径为：" + headImage);
            return headImage;
        } catch (IOException e) {
            log.error("生成头像失败》》》》》》");
            log.error(e.getMessage());
        }
        return "";
    }

}
