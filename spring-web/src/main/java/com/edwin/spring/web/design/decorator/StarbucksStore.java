package com.edwin.spring.web.design.decorator;

import com.edwin.spring.web.design.decorator.condiment.CaramelCondiment;
import com.edwin.spring.web.design.decorator.condiment.MilkCondiment;
import com.edwin.spring.web.design.decorator.condiment.VanillaCondiment;
import com.edwin.spring.web.utils.DoraemonKit;
import lombok.extern.slf4j.Slf4j;

/**
 * 星巴克咖啡店
 *
 * @author junming.cao
 * @date 2022/3/1 11:28 下午
 */
@Slf4j
public class StarbucksStore {

    public static void main(String[] args) {

        narrator("新的一天开始了...");
        narrator("————————————————————————————————————————");
        narrator("顾客1：您好，来杯浓缩咖啡，谢谢！");
        Beverage espresso = new Espresso();
        narrator("服务员：您好，您的『{}』已经做好，一共『{}』元", espresso.getDescription(), espresso.cost());
        narrator("......");

        narrator("顾客2：您好，来杯黑咖啡，外加一份牛奶，谢谢！");
        Beverage houseBlend = new MilkCondiment(new HouseBlend());
        narrator("服务员：您好，您的『{}』已经做好，一共『{}』元", houseBlend.getDescription(), houseBlend.cost());
        narrator("......");

        narrator("顾客3：您好，来杯焦糖香草深度烘焙，谢谢！");
        Beverage darkRoast = new CaramelCondiment(new VanillaCondiment(new DarkRoast()));
        narrator("服务员：您好，您的『{}』已经做好，一共『{}』元", darkRoast.getDescription(), darkRoast.cost());
        narrator("......");
        narrator("————————————————————————————————————————");
        narrator("忙碌的一天结束了，see you tomorrow！");
    }

    private static void narrator(String format, Object... arguments) {
        log.info(format, arguments);
        DoraemonKit.sleep(1);
    }
}
