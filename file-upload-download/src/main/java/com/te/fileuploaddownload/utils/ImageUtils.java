package com.te.fileuploaddownload.utils;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtils {

	//to compress image
	public static byte[] compressImage(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setLevel(Deflater.BEST_COMPRESSION);
		deflater.setInput(data);
		deflater.finish();
		
		ByteArrayOutputStream outputStream  = new ByteArrayOutputStream(data.length);
		byte[]  temp= new byte[4 *1024];
		while(!deflater.finished()) {
			int size  = deflater.deflate(temp);
			outputStream.write(temp,0,size);
		}
		
		try {
			outputStream.close();
		}catch(Exception ignored) {
			
		}
		
		return outputStream.toByteArray();
	}
	
	//to compress image
	public static byte[] decomposeImage(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		
		ByteArrayOutputStream outputStream  = new ByteArrayOutputStream(data.length);
		byte[]  temp= new byte[4 *1024];
		try {
			while(!inflater.finished()) {
				int count  = inflater.inflate(temp);
				outputStream.write(temp,0,count);
			}
			outputStream.close();
		}catch(Exception ignored) {
			
		}
		
		return outputStream.toByteArray();
	}
}

