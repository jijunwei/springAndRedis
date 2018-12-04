package com.example.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class OverdueUtil {
    private static Logger logger = LoggerFactory.getLogger(OverdueUtil.class);


    public static BigDecimal getOverdueAmount(BigDecimal overduePrincipal,int overdueDay,BigDecimal overdueRate,int delayDay){
        logger.info("逾期本金="+overduePrincipal+"逾期天数="+overdueDay+"逾期罚息率="+overdueRate+"宽限期="+delayDay);
        BigDecimal overdue = new BigDecimal(0);
        if (overdueDay>delayDay){
            overdue = overduePrincipal.multiply(overdueRate).multiply(new BigDecimal(overdueDay)).setScale(2,BigDecimal.ROUND_HALF_UP);
        }
        return overdue;
    }
}
