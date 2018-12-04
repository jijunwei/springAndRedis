package com.example.utils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 序号生成器
 * */

/**
 * 序号生成器
 */
@Service
@Transactional
public class GeneratorUtil {
	private static final long ONE_STEP = 10;
    private static final Lock LOCK = new ReentrantLock();
    private static long lastTime = System.currentTimeMillis();
    private static short lastCount = 0;
    private static int count = 0;
    
	public synchronized String generateAppNo() {
        java.text.DateFormat format = new java.text.SimpleDateFormat("yyyyMMddHH");
        String appNo = format.format(new Date());
        String zeroAppno =  "0";
        
        /*TmAppnoSeq tmAppnoSeq = new TmAppnoSeq();
        tmAppnoSeq.setOrg(org);
        rTmAppnoSeq.save(tmAppnoSeq);*/
		
		String seq = String.valueOf(nextId());
		if (10 == seq.length()) 
			return appNo+seq;
		
		for (int i = 1; i < (10 - seq.length()); i++) {
			zeroAppno = "0" + zeroAppno;
		}
        return appNo+zeroAppno+seq;
	}
	
	public static synchronized String generateTranceNo() {
        java.text.DateFormat format = new java.text.SimpleDateFormat("yyyyMMddHH");
        String appNo = format.format(new Date());
		String seq = String.valueOf(nextId());
//		if (10 == seq.length()) 
//			return appNo+seq;
//		
//		for (int i = 1; i < (10 - seq.length()); i++) {
//			zeroAppno = "0" + zeroAppno;
//		}
        return appNo+seq;
	}
	
    @SuppressWarnings("finally")
    public static String nextId() 
    {
        LOCK.lock();
        try {
            if (lastCount == ONE_STEP) {
                boolean done = false;
                while (!done) {
                    long now = System.currentTimeMillis();
                    if (now == lastTime) {
                        try {
                            Thread.currentThread();
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                        }
                        continue;
                    } else {
                        lastTime = now;
                        lastCount = 0;
                        done = true;
                    }
                }
            }
            count = lastCount++;
        }
        finally 
        {
            LOCK.unlock();
//            System.out.println(String.format("%03d",count));
            return lastTime+""+String.format("%03d",count); 
        }
      
    }

	
	
	
	
	
	public static void main(String[] args) {
//		String s="201707042301499181957070002";
		System.out.println(GeneratorUtil.generateTranceNo());
		System.out.println(GeneratorUtil.nextId().length());
	}
	
	
	
	
	
	
	
	
	
}