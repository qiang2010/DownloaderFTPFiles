package jingjinji.getData;

import java.net.ConnectException;

public class AutoDownloadThread implements Runnable{

	 FTPFilesDownloader downloader  = new FTPFilesDownloader();

	 private long timeInterval = 30*1000; //每隔两分钟执行一次 
	 
	 static boolean connectionState = true;
	 static String dest = "/home/jq/software/jingjinji/rtic_data/";
	 static String source = "/rtic/";
	// downloader.downloadFiles("/rtic/", );
	@Override
	public void run() {
		System.out.println("downloading...");
		try {
			boolean flag = true;
			do{
				flag = downloader.downloadFiles(source, dest);
				Thread.sleep(timeInterval);
			}while(flag);
		} catch (ConnectException e) {
			System.err.println("连接错误，将重新启动线程。" );
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
