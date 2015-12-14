import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Calendar;
import java.util.Date;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JFrame;

/**
 * 
 */

/**
 * @author Huang.Jun Hua
 * @time 2015年8月30日  下午7:18:59
 * 
 */
public class MainClass implements Runnable{
	private JFrame window;
	private Button button = new Button("BEGIN");
	
	private static final long SECONDS = 3000;
	
	public MainClass(){
		window = new JFrame();
	    window.setSize(300, 200);
	    window.setLocationRelativeTo(null); // center on screen
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.getContentPane().setLayout(new BorderLayout(0, 0));
	    
	    button.setBackground(new Color(1, 255, 255, 0));
	    //button.setBounds(10, 10, 100, 50);
	    
	    button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
	            System.out.println("你好世界！");
	            Thread thread = new Thread(new MainClass());
	    		thread.start();
	    		button.setEnabled(false);
			}
		});
	    
	    window.getContentPane().add(button);
	}
	
	@Override
	public void run() {
		// 设置日期2012-12-21
		Calendar c = Calendar.getInstance();
		c.set(2015, 8, 30, 19, 55, 0);
		// 单独设置年、月、日、小时、分钟、秒
		c.set(Calendar.YEAR, 2015);
		c.set(Calendar.MONTH, Calendar.DECEMBER); // 0 表示1月，11 表示12月
		c.set(Calendar.DAY_OF_MONTH, 21);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		System.out.println(button);
		// 获取2012-12-21 0:0:0时间点对应的毫秒数
		long endTime = c.getTimeInMillis();
		// 获取系统当前时间
		Date now = new Date();
		// 获取当前时间点对应的毫秒数
		long currentTime = now.getTime();

		// 计算两个时间点相差的秒数
		long seconds = (endTime - currentTime) / 1000;
		
		seconds = SECONDS;
		System.out.println("计时开始" + SECONDS);
		while (true)
		{
		//System.out.println("还剩： " + seconds + " 秒");
		seconds--;
		if(seconds % 100 == 0) {
			System.out.println(seconds);
		}
		
		if(seconds == 0) {
			runSound();
			System.out.println("计时结束");
			break;
		}
		
		try
		{
		Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{
		e.printStackTrace();
		}
		}//end while
	}
	public static void main(String[] args) {
		MainClass application = new MainClass();
		application.window.setVisible(true);
		
	}
	
	
	
	// 读取声音文件，并且播放出来
    public void runSound() {
        File soundFile = new File("src/开门.wav");
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (Exception e1) {
            e1.printStackTrace();
            return;
        }
        AudioFormat format = audioInputStream.getFormat();
        SourceDataLine auline = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        try {
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        auline.start();
        int nBytesRead = 0;
        byte[] abData = new byte[512];
        try {
            while (nBytesRead != -1) {
                nBytesRead = audioInputStream
                        .read(abData, 0, abData.length);
                if (nBytesRead >= 0)
                    auline.write(abData, 0, nBytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        } finally {
            auline.drain();
            auline.close();
        }

    }
}
