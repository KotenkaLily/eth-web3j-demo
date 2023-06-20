package org.example.web3.common.utils;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class ImgVerify {
    //测试用url
    private static final String URL = "G:\\Project\\kotenka-lily\\server-demos\\eth-web3j-demo\\demo\\src\\main\\resources\\hanzi\\";
    private static final String fileName = "a.png";

    /*基本中文字体*/
    private static final List<String> FONTS_CN = Arrays.asList("等线", "方正舒体", "方正姚体", "仿宋", "黑体", "华文彩云", "华文仿宋", "华文琥珀", "华文楷体", "华文隶书", "华文宋体", "华文细黑", "华文新魏", "华文行楷", "华文中宋", "楷体", "隶书", "宋体", "微软雅黑", "新宋体", "幼圆");
    /*面板长宽固定*/
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;
    /*修正值，8px*/
    private static final int FIX_PX = 8;
    /*height/2为font最大值，但如果字太大就容易发生重叠，height/3比较保险*/
    private static final int MAX_FONT_SIZE = HEIGHT / 3;
    private static final int MIN_FONT_SIZE = HEIGHT / 6;
    private static final Random random = new Random();

    public Map<String, Object> validationGenerator() {
        //使用成语作为验证
        String[] chars = {"一", "成", "不", "变"};

        //创建画布
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        //背景配色
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);

        //记录汉字的位置，格式：key=汉字，value=左上角坐标x_y_字体大小
        Map<String, String> usedLocations = new HashMap<>();
        //循环每个字，放在画布上。
        drawChars(g2d, chars, usedLocations);
        drawShadow(g2d);
        drawShadow(g2d);
        g2d.dispose();

        /*输出——开始*/
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("img", image);
        output(image);
        usedLocations.forEach((k, v) -> {
            /*格式拆分*/
            String[] s = v.split("_");
            int xTmp = Integer.parseInt(s[0]);
            //mark:这是左下角的y点，不是左上角
            int yTmp = Integer.parseInt(s[1]);
            int size = Integer.parseInt(s[2]);

            /*获取四个顶点的位置，如果超出画布边界则设置为边界*/
            String xLeftLimit = String.valueOf(Math.max(xTmp - 10, 0));
            String xRightLimit = String.valueOf(Math.min(xTmp + size + 10, WIDTH));
            String yTopLimit = String.valueOf(Math.max(yTmp - size - 10, 0));
            String yBottomLimit = String.valueOf(Math.min(yTmp + 10, HEIGHT));

            /*存入结果中间Map*/
            Map<String, String> charPosition = new HashMap<>();
            charPosition.put("xLeftLimit", xLeftLimit);
            charPosition.put("xRightLimit", xRightLimit);
            charPosition.put("yTopLimit", yTopLimit);
            charPosition.put("yBottomLimit", yBottomLimit);

            /*存给该汉字*/
            resultMap.put(k, charPosition);
        });
        return resultMap;
    }

    private void output(BufferedImage image) {
        try {
            /*输出查看*/
            ImageIO.write(image, "png", new File(URL + fileName));
            log.info("{}", "Image saved.");
        } catch (Exception e) {
            log.info("{}", "Error: " + e.getMessage());
        }
    }

    private boolean ifOverlapping(int x, int y, int fontSize, Map<String, String> usedLocations) {
        AtomicBoolean overlapping = new AtomicBoolean(false);
        usedLocations.forEach((character, point) -> {
            if (overlapping.get()) {
                //如果有重叠
                return;
            }
            String[] s = point.split("_");
            int thisFontSize = Integer.parseInt(s[2]);
            int thisX = Integer.parseInt(s[0]) + thisFontSize / 2;
            int thisY = Integer.parseInt(s[1]) - thisFontSize / 2;
            int thatX = x + fontSize / 2;
            int thatY = y - fontSize / 2;
            //中心点的坐标之差绝对值>二者的fontsize的一半之和,则无重叠
            //横坐标重叠
            boolean xx = Math.abs(thisX - thatX) < thisFontSize / 2 + fontSize / 2;
            //纵坐标重叠
            boolean yy = Math.abs(thisY - thatY) < thisFontSize / 2 + fontSize / 2;
            overlapping.set(xx && yy);
        });
        return !overlapping.get();
    }

    private void drawShadow(Graphics2D g2d) {
        /*画阴影线——开始*/
        //头部找个位置
        int x1 = random.nextInt(WIDTH);
        //底部找一个位置
        int x2 = random.nextInt(WIDTH);
        int y1 = 0;
        // 阴影线配色
        g2d.setColor(getRandomColor());
        /*
         * 方法drawLine(x1,y1,x2,y2)逻辑：(x1,y1)画到(x2,y2)
         * 从左上角到右下角: g2d.drawLine(0, 0, width, height);
         * 从右上角到左下角: g2d.drawLine(width,0, 0, height);
         * */
        //根据x1的位置循环绘制阴影线
        for (int x = 0; x < x1 + WIDTH; x += 6) {
            //向右一直画满
            //g2d.setStroke(new BasicStroke(1));
            g2d.drawLine(x1 - x, y1, x2 - x, HEIGHT);
            //向左一直画满
            g2d.drawLine(x1 + x, y1, x2 + x, HEIGHT);
        }
        /*画阴影线——结束*/
    }

    private void drawChars(Graphics2D g2d, String[] chars, Map<String, String> usedLocations) {
        int count = 0;
        for (String s : chars) {
            count++;
            while (true) {
                //设置字体配色
                g2d.setColor(getRandomColor());
                //随机使用字体
                String fontTypeX = FONTS_CN.get(random.nextInt(FONTS_CN.size()));
                //随机fontsize
                int fontSize = random.nextInt(MAX_FONT_SIZE - MIN_FONT_SIZE) + MIN_FONT_SIZE;
                g2d.setFont(new Font(fontTypeX, Font.PLAIN, fontSize));
                /*
                 * Description:
                 * x=0时，字体靠左，x=width-fontSize时，靠右；y=fontSize时贴顶，y=height时贴地
                 * 上下预留8px+fontSize的空间，左右预留8px。
                 * 随机生成xy值：最大值2n抵消n，最右为width-fix；最小值0，最左为width+fix
                 * */
                int x = random.nextInt(WIDTH - (FIX_PX + fontSize)) + FIX_PX;
                int y = random.nextInt(HEIGHT - 2 * (FIX_PX + fontSize)) + FIX_PX + fontSize;
                //对生成的xy判重叠，无重叠则记录下本次位置，有重叠则继续换一批xy判断
                if (usedLocations.size() == 0 || ifOverlapping(x, y, fontSize, usedLocations)) {
                    usedLocations.putIfAbsent(String.valueOf(count), x + "_" + y + "_" + fontSize);
                    log.info("{}", "char is " + s + ",position=(" + x + "," + y + ")，size=" + fontSize);
                    g2d.drawString(String.valueOf(s), x, y);
                    break;
                }
            }
        }
    }

    private Color getRandomColor() {
        Random random = new Random();
        float hue = random.nextFloat();
        float saturation = 0.5f;
        float brightness = 0.9f;
        return Color.getHSBColor(hue, saturation, brightness);
    }
}
