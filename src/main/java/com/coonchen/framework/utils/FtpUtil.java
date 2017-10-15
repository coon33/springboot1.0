package com.coonchen.framework.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;

import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
public class FtpUtil {
	private FTPClient fc;
	private String ftphost;// 地址
	private String ftpuser;// 用户
	private String ftppass;// 密码
	private String ftppath;// 路径
	private int ftpport;// 端口
	private int silent;// 连接超时时间
	private boolean pasv;// 被动 模式
	private boolean ftpssl;// 是否启用安全连接
	private String charset="utf-8";//字符集
	private DecimalFormat df = new DecimalFormat("0.00");

	/**
	 * 
	 * @param ftphost
	 *            //地址
	 * @param ftpuser
	 *            //用户
	 * @param ftppass
	 *            //密码
	 * @param ftppath
	 *            //路径
	 * @param ftpport
	 *            //端口
	 * @param ftpssl
	 *            //是否启用安全连接
	 * @param silent
	 *            //连接超时时间
	 * @param pasv
	 *            //被动 模式
	 */
	public void setFtpValues(String ftphost, String ftpuser, String ftppass,
			String ftppath, int ftpport, boolean ftpssl, int silent,
			boolean pasv) {
		this.ftphost = ftphost;
		this.ftppass = ftppass;
		this.ftppath = ftppath;
		this.pasv = pasv;
		this.ftpport = ftpport;
		this.ftpuser = ftpuser;
		this.silent = silent;
		this.ftpssl = ftpssl;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	/**
	 * 是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		if (ftphost == null || ftphost.equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * 获取连接状态
	 * 
	 * @return
	 */
	public String connectToFtpServer() {
		if ((ftphost == null) || (ftphost.equals("")))
			return "ftp_service_nameerror";
		if (fc != null) {
			try {
				fc.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		fc = new FTPClient();
		if (ftpssl) {
			SSLServerSocketFactory sslserverfactory = (SSLServerSocketFactory) SSLServerSocketFactory
					.getDefault();
			SSLSocketFactory sslfactory = (SSLSocketFactory) SSLSocketFactory
					.getDefault();
			fc.setServerSocketFactory(sslserverfactory);
			fc.setSocketFactory(sslfactory);
		}
		fc.setControlEncoding(charset);
		return connectToServer();
	}

	private String connectToServer() {
		try {
			fc.connect(this.ftphost, this.ftpport);
			if (!fc.login(ftpuser, ftppass)) {
				return "ftp_service_connect";
			}
			if (pasv) {
				fc.enterLocalPassiveMode();
			}
			if (silent != 0) {
				fc.setDataTimeout(silent);
			}
			if (!fc.changeWorkingDirectory(ftppath)) {
				return "ftp_directory";
			} 
		} catch (IOException e) {
			e.printStackTrace();
			return "ftp_connect_fail";
		} catch (SecurityException e) {
			e.printStackTrace();
			return "ftp_noaccess";
		}
		return "";
	}

	/**
	 * 是否已经连接
	 * 
	 * @return
	 */
	public boolean isConnect() {
		if (fc == null || !fc.isConnected()) {
			return false;
		} else {
			try {
				String path = fc.printWorkingDirectory();
				if (path == null) {
					return false;
				}
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
	}

	/**
	 * 关闭释放连接
	 */
	public void closeFtpConnect() {
		if (fc != null) {
			try {
				fc.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				fc = null;
			}
		}
	}

	/**
	 * 创建新目录
	 * 
	 * @param newdir
	 * @return
	 */
	public boolean mkdir(String newdir) {
		boolean makebool;
		try {
			makebool = fc.makeDirectory(newdir);
			return makebool;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean mkdirs(String newdir) {
		boolean makebool = true;
		try {
			String[] paths = newdir.split("/");
			StringBuffer sb = new StringBuffer(30);
			sb.append(ftppath + "/");
			for (String path : paths) {
				sb.append(path + "/");
				System.out.println(sb);
				boolean c = !fc.changeWorkingDirectory(sb.toString());
				if (c) {
					boolean b = fc.makeDirectory(path);
					if (!b)
						return true;
					else {
						// put(sourcename, targetname, test, directory);
						makebool = fc.changeWorkingDirectory(sb.toString());
					}
				}
			}
			// makebool = fc.makeDirectory(makebool);
			return makebool;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 删除目录
	 * 
	 * @param newdir
	 * @return
	 */
	public boolean rmdir(String newdir) {
		try {
			return fc.removeDirectory(newdir);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param newdir
	 * @return
	 */
	public boolean delete(String newdir) {
		try {
			fc.changeWorkingDirectory(ftppath);
			return fc.deleteFile(newdir);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 设置权限
	 * 
	 * @param newdir
	 * @return
	 */
	public boolean site(String newdir) {
		String cmd_mkdir = "chmod 777 " + ftppath + "/" + newdir + "\r\n";
		try {
			return fc.sendSiteCommand(cmd_mkdir);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 改变目录
	 * 
	 * @param dir
	 * @return
	 */
	public boolean chdir(String dir) {
		boolean workboolean;
		try {
			workboolean = fc.changeWorkingDirectory(dir);
			return workboolean;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 是否是目录 传入相对路径就可以了 也就是说当前你所在的工作目录已经被默认组合到这里了
	 * 
	 * @param directory
	 * @return
	 */
	public boolean isdirwork(String directory) {
		try {
			String path = fc.printWorkingDirectory();
			String targetpath = ftppath + "/" + directory;
			if (targetpath.startsWith(".")) {
				targetpath = targetpath.substring(1);
			}
			return targetpath.equals(path);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 进入根目录
	 */
	public void cdrootdir() {
		try {
			if (fc != null) {
				fc.changeWorkingDirectory(ftppath);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean put(InputStream inputstream, String targetname,
			boolean test, String directory) {
		try {
			if (!test) {
				if (!isdirwork(directory)) {
					if (!fc.changeWorkingDirectory(ftppath)) {
						return false;
					}
					if (!fc.changeWorkingDirectory(directory)) {
						return false;
					}
				}
			}
			fc.setBufferSize(1024*4);
			fc.setFileType(FTPClient.BINARY_FILE_TYPE);
			boolean strore = fc.storeFile(targetname, inputstream);
			return strore;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}finally{
			if(inputstream!=null)
				try {
					inputstream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}

	/**
	 * 上传(放入)新文件
	 * 
	 * @param sourcename
	 * @param targetname
	 * @param test
	 * @param directory
	 * @return
	 */
	public boolean put(String sourcename, String targetname, boolean test,
			String directory) {
		try {
			return put(new FileInputStream(sourcename), targetname, test,
					directory);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 读取远程文件到本地
	 * 
	 * @param local_file
	 * @param remote_file
	 * @return
	 */
	public boolean get(String local_file, String remote_file) {
		try {
			fc.setBufferSize(1024*4);
			fc.setFileType(FTPClient.BINARY_FILE_TYPE);
			FileOutputStream os = new FileOutputStream(local_file);
			boolean remark = fc.retrieveFile(remote_file, os);
			os.close();
			return remark;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 根据URL路径读取文件
	 * 
	 * @param url
	 * @param os
	 * @return
	 */
	public boolean readfile(String url, OutputStream os) {
		InputStream in = null;
		URL servletURL = null;
		try {
			servletURL = new URL(url);
			servletURL.openConnection();
			in = servletURL.openStream();
			if (os != null) {
				byte[] bytes = new byte[1024];
				int c;
				while ((c = in.read(bytes)) != -1) {
					os.write(bytes, 0, c);
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			try {
				if (in != null) {
					in.close();
					os.close();
				}
			} catch (Exception e) {
			}
			servletURL = null;
		}
	}

	/**
	 * 读取远程文件到本地
	 * 
	 * @param local_file
	 * @param remote_file
	 * @return
	 */
	public InputStream get(String remote_file) {
		try {
			fc.setBufferSize(1024*4);
			fc.setFileType(FTPClient.BINARY_FILE_TYPE);
			return fc.retrieveFileStream(remote_file);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
	* <p>download</p>
	* <p>Description: </p>
	* <p>Copyright: roiland groups 2013-12-9</p>
	* <p>Company: roiland </p>
	* @author srchen email:jackson.song@roiland.com
	* @date 2013-12-9 下午2:50:06
	* @version 1.0
	* @param remote
	* @param local
	* @throws IOException
	 */
	public OptionStatus download(String remote, String local)
			throws IOException {
		fc.setFileType(FTPClient.BINARY_FILE_TYPE);
		FTPFile[] files = fc.listFiles(remote);
		if (files.length != 1) {
			return OptionStatus.REMOVE_FILE_NOTEXIST;
		}
		long lRemoteSize = files[0].getSize();
		File f = new File(local);
		// 本地存在文件，进行断点下载
		if (f.exists()) {
			System.out.println("继续下载\t"+f.getName());
			long localSize = f.length();
			// 判断本地文件大小是否大于远程文件大小
			if (localSize >= lRemoteSize) {
				return OptionStatus.DIFFERENCE_FILE;
			}

			// 进行断点续传，并记录状态
			FileOutputStream out = new FileOutputStream(f, true);
			fc.setRestartOffset(localSize);
			InputStream in = fc.retrieveFileStream(remote);
			byte[] bytes = new byte[1024*4];
			/*long step = lRemoteSize / 100;
			long process = localSize / step;*/
			BigDecimal step = new  BigDecimal(lRemoteSize).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
			int c;
			double starttime = System.currentTimeMillis();
			double t1 = System.currentTimeMillis();
			double tempcount=0;
			while ((c = in.read(bytes)) != -1) {
				double overtime = System.currentTimeMillis();
				double interval = (overtime-starttime)/1000;
				tempcount+=c;
				BigDecimal bdinterval = new BigDecimal(interval);
				if(bdinterval.doubleValue()>=1){
					BigDecimal btempcount = new BigDecimal(tempcount).divide(new BigDecimal(1024),2,BigDecimal.ROUND_HALF_UP).divide(bdinterval,2,BigDecimal.ROUND_HALF_UP);
					starttime=System.currentTimeMillis();
					double nowProcess = new BigDecimal(localSize).divide(step,2,BigDecimal.ROUND_HALF_UP).doubleValue();
					if(nowProcess==0)nowProcess=0.01;
					System.out.println(df.format(btempcount.doubleValue())+"kb/s"+"\t下载进度：" + df.format(nowProcess)+"% ");
					tempcount=0;
				}
				localSize += c;
				out.write(bytes, 0, c);
				out.flush();
			}
			double t2 = System.currentTimeMillis();
			System.out.println(f.getName()+"\t继续下载完成 !总耗时:"+(t2-t1)+"ms!\t大小"+new BigDecimal(localSize).divide(new BigDecimal(1024),2,BigDecimal.ROUND_HALF_UP)+"kb!");
			in.close();
			out.close();
			boolean isDo = fc.completePendingCommand();
			if (isDo) {
				return OptionStatus.BREAKPOINT_FILE_SUCCESS;
			} else {
				return OptionStatus.BREAKPOINT_FILE_FAIL;
			}
		} else {
			if(!f.getParentFile().isDirectory()){
				f.getParentFile().mkdirs();
			}
			OutputStream out = new FileOutputStream(f);
			InputStream in = fc.retrieveFileStream(remote);
			byte[] bytes = new byte[1024*4];
			BigDecimal step = new  BigDecimal(lRemoteSize).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
			long localSize = 0L;
			int c;
			double starttime = System.currentTimeMillis();
			double t1 = System.currentTimeMillis();
			double tempcount=0;
			while ((c = in.read(bytes)) != -1) {
				double overtime = System.currentTimeMillis();
				double interval = (overtime-starttime)/1000;
				tempcount+=c;
				BigDecimal bdinterval = new BigDecimal(interval);
				if(bdinterval.doubleValue()>=1){
					BigDecimal btempcount = new BigDecimal(tempcount).divide(new BigDecimal(1024),2,BigDecimal.ROUND_HALF_UP).divide(bdinterval,2,BigDecimal.ROUND_HALF_UP);
					starttime=System.currentTimeMillis();
					double nowProcess = new BigDecimal(localSize).divide(step,2,BigDecimal.ROUND_HALF_UP).doubleValue();
					if(nowProcess==0)nowProcess=0.01;
					System.out.println(df.format(btempcount.doubleValue())+"kb/s"+"\t下载进度：" + df.format(nowProcess)+"% ");
					tempcount=0;
				}
				localSize += c;
				out.write(bytes, 0, c);
				out.flush();
			}
			double t2 = System.currentTimeMillis();
			System.out.println(f.getName()+"\t下载完成 !总耗时:"+(t2-t1)+"ms!\t大小"+new BigDecimal(localSize).divide(new BigDecimal(1024),2,BigDecimal.ROUND_HALF_UP)+"kb!");
			in.close();
			out.close();
			boolean upNewStatus = fc.completePendingCommand();
			if (upNewStatus) {
				return OptionStatus.FULL_FILE_SUCCESS;
			} else {
				return OptionStatus.FULL_FILE_FAIL;
			}
		}
	}
	
	public String getWorkingPath() {
		if (fc != null)
			try {
				return fc.printWorkingDirectory();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		return null;
	}
	
	public enum OptionStatus{
		REMOVE_FILE_NOTEXIST,//远程文件不存在
		DIFFERENCE_FILE,//文件不同
		BREAKPOINT_FILE_SUCCESS,//断点续传文件成功
		BREAKPOINT_FILE_FAIL,//断点续传文件失败
		FULL_FILE_SUCCESS,//完整文件下载成功
		FULL_FILE_FAIL//完整文件下载失败
	}
	public static void main(String[] args) throws IOException {
//		FtpUtil ft = new FtpUtil();
////		ft.setFtpValues("127.0.0.1", "ftp", "ftp", "/", 21, false, 0,true);
//		ft.setFtpValues("47.93.42.80","huiyou", "jacksong@1", "/",21, false,0,true);
//		String s  = ft.connectToFtpServer();
//		System.out.println(s);
////		boolean b = ft.put("D:\\a.txt","b.txt",false,"");
//		File file = new File("C:\\Users\\Administrator\\Desktop\\微信截图_20170428103827.png");
//		FileInputStream fin = new FileInputStream(file);
//		boolean b = ft.put(fin, "s.png", true, "");
//		System.out.println(b);
////		ft.download("副本.zip", "C://test//副本.zip");
//		ft.closeFtpConnect(); 
		
		FtpUtil ft = new FtpUtil();
		try {
			//ft.setFtpValues("47.94.18.14", "ftpuser", "ftpuser", "/", 50021, false, 0,true);
			ft.setFtpValues("218.61.208.7", "Adley", "anywide", "/", 21, false, 0,true);
//			ft.setFtpValues("139.129.207.129", "jackson", "jackson.song@0", "/", 21, false, 0,true);
			String s  = ft.connectToFtpServer();
			System.out.println("=========="+s);
			boolean mk = ft.mkdirs("image/");
//			File file = new File("F:\\11.png");
			//File file = new File("D:\\a.png");
			//FileInputStream fin = new FileInputStream(file);
			//boolean upload = ft.put(fin, "1602.png", false, "image");
			
			
			boolean download = ft.get("D:\\d.png", "/cw.png");
			
			//System.out.println(b);
			System.out.println(download);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ft.closeFtpConnect(); 
		}

		
	}

}
