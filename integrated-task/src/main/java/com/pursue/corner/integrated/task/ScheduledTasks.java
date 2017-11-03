package com.pursue.corner.integrated.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
/**cron
 * 
 * Seconds Minutes Hours DayofMonth Month DayofWeek Year或 
 * Seconds Minutes Hours DayofMonth Month DayofWeek
 * 
 * Seconds:可出现", - * /"四个字符，有效范围为0-59的整数 
 * Minutes:可出现", - * /"四个字符，有效范围为0-59的整数 
 * Hours:可出现", - * /"四个字符，有效范围为0-23的整数 
 * DayofMonth:可出现", - * / ? L W C"八个字符，有效范围为0-31的整数 
 * Month:可出现", - * /"四个字符，有效范围为1-12的整数或JAN-DEc 
 * DayofWeek:可出现", - * / ? L C #"四个字符，有效范围为1-7的整数或SUN-SAT两个范围。1表示星期天，2表示星期一， 依次类推
 * Year:可出现", - * /"四个字符，有效范围为1970-2099年
 * 
 * (1)*：表示匹配该域的任意值，假如在Minutes域使用*, 即表示每分钟都会触发事件。
 * (2)?:只能用在DayofMonth和DayofWeek两个域。	DayofMonth和DayofWeek会相互影响。
 * (3)-:表示范围，例如在Minutes域使用5-20，表示从5分到20分钟每分钟触发一次 
 * (4)/：表示起始时间开始触发，然后每隔固定时间触发一次，例如在Minutes域使用5/20,则意味着5分钟触发一次，而25，45等分别触发一次. 
 * (5),:表示列出枚举值值。例如：在Minutes域使用5,20，则意味着在5和20分每分钟触发一次。
 * (6)L:表示最后，只能出现在DayofWeek和DayofMonth域，如果在DayofWeek域使用5L,意味着在最后的一个星期四触发。 
 * (7)W:表示有效工作日(周一到周五),只能出现在DayofMonth域，系统将在离指定日期的最近的有效工作日触发事件。例如：在 DayofMonth使用5W，如果5日是星期六，则将在最近的工作日：星期五，即4日触发。如果5日是星期天，则在6日(周一)触发；如果5日在星期一到星期五中的一天，则就在5日触发。另外一点，W的最近寻找不会跨过月份 
 * (8)LW:这两个字符可以连用，表示在某个月最后一个工作日，即最后一个星期五。
 * (9)#:用于确定每个月第几个星期几，只能出现在DayofMonth域。例如在4#2，表示某月的第二个星期三。
 * 
 * "0 0 10,14,16 * * ?" 每天上午10点，下午2点，4点 
 * "0 0/30 9-17 * * ?" 朝九晚五工作时间内每半小时 
 * "0 0 12 ? * WED" 表示每个星期三中午12点 
 * "0 0 12 * * ?" 每天中午12点触发 
 * "0 15 10 ? * *" 每天上午10:15触发 
 * "0 15 10 * * ?" 每天上午10:15触发 
 * "0 15 10 * * ? *" 每天上午10:15触发 
 * "0 15 10 * * ? 2005" 2005年的每天上午10:15触发 
 * "0 * 14 * * ?" 在每天下午2点到下午2:59期间的每1分钟触发 
 * "0 0/5 14 * * ?" 在每天下午2点到下午2:55期间的每5分钟触发 
 * "0 0/5 14,18 * * ?" 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发 
 * "0 0-5 14 * * ?" 在每天下午2点到下午2:05期间的每1分钟触发 
 * "0 10,44 14 ? 3 WED" 每年三月的星期三的下午2:10和2:44触发 
 * "0 15 10 ? * MON-FRI" 周一至周五的上午10:15触发 
 * "0 15 10 15 * ?" 每月15日上午10:15触发 
 * "0 15 10 L * ?" 每月最后一日的上午10:15触发 
 * "0 15 10 ? * 6L" 每月的最后一个星期五上午10:15触发 
 * "0 15 10 ? * 6L 2002-2005" 2002年至2005年的每月的最后一个星期五上午10:15触发 
 * "0 15 10 ? * 6#3" 每月的第三个星期五上午10:15触发
 * 
 * */

/**
 * 一个组件下面的定时任务由同一个线程运行
 * */
@Component
public class ScheduledTasks {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
	private Task task;
    
    /**
     * A cron-like expression.
     * @throws Exception 
     * */
    @Scheduled(cron = "0/5 * * * * ?")
    @Async
    public void currentTimeByCron() throws Exception {
    	logger.debug("debug");
    	logger.info("info");
    	logger.error("error");
        System.out.println("currentTimeByCron当前时间：" + dateFormat.format(new Date()));
        task.doTaskOne();
        task.doTaskTwo();
        task.doTaskThree();
    }
    
    /**
     * Execute the annotated method with a fixed period between invocations.
     * */
    @Scheduled(initialDelay=2000, fixedRate = 5000)
    @Async
    public void currentTimeByDelay() {
        System.out.println("currentTimeByDelay当前时间：" + dateFormat.format(new Date()));
    }
    
    /**
     * Execute the annotated method with a fixed period between the end of the last invocation and the start of the next.
     * @throws InterruptedException 
     * */
    @Scheduled(fixedDelay = 5000)
    @Async
    public void currentTimeByRate() throws InterruptedException {
        System.out.println("currentTimeByRate当前时间：" + dateFormat.format(new Date()));
    }
}
