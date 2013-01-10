package net.sinoace.ui.photostore;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.widget.ImageView;

public class Constant {
	
	private static LinkedList<String> extens=null;
	
	/**
	 * �Ƿ��Ѿ�ɨ��
	 */
	public static boolean isScaned;
	
	/**
	 * ȫ��ͼƬ�ļ���
	 */
	public static ArrayList<ImageFolderInfo> imageFolders = new ArrayList<ImageFolderInfo>();
	
	/**
	 * ͼƬ�ļ���ʵ��
	 * @author wanghb
	 *
	 */
	public static class ImageFolderInfo{
		public String path;
		public int pisNum = 0;
		public ArrayList<String> filePathes = new ArrayList<String>();
		public Drawable image;
	}
	
	public static LinkedList<String> getExtens(){
		if(extens == null){
			extens = new LinkedList<String>();
			extens.add("JPEG");
			extens.add("JPG");
			extens.add("PNG");
			extens.add("GIF");
			extens.add("BMP");
		}
		return extens;
	}
	
	public static void scan(final UIinterface ui){
		if(!isScaned){
			isScaned = !isScaned;
			imageFolders.clear();
			final String mCardPath = Environment.getExternalStorageDirectory().getPath();
			new Thread(){
				public void run() {
					getFiles(mCardPath,ui);
				}
			}.start();
		}
	}
	
	
	private static void getFiles(String path,final UIinterface ui) {
		File f = new File(path);
		File[] files = f.listFiles();
		ImageFolderInfo ifi = new ImageFolderInfo();
		ifi.path = path;
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				final File ff = files[i];
				if (ff.isDirectory()) {
					getFiles(ff.getPath(),ui);
				} else {
					String fName = ff.getName();
					if(fName.indexOf(".")>-1){
						String end = fName.substring(fName.lastIndexOf(".") + 1, fName.length()).toUpperCase();
						if(getExtens().contains(end)){
							ifi.filePathes.add(ff.getPath());
						}
					}
				}
			}
		}
		if(!ifi.filePathes.isEmpty()){
			ifi.pisNum = ifi.filePathes.size();
			synchronized (imageFolders) {
				imageFolders.add(ifi);
				ui.updateUI();
			}
		}
	}
	
	//��ʾʵ��
	public static class gridItemEntity{
		public Drawable image;
		public String path;
		public int index;
		public ImageView imageView;
	}
}
