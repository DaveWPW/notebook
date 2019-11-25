package common.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

public class FileUtils {
	
	/**
	 * 图片上传工具类 
	 * 
	 * @param file
	 * @param path
	 * @return
	 */
    public static String uploadImageFile(String username, MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//文件存放的路径
		String path = request.getSession().getServletContext().getRealPath("notebook/upload/"+username+"/images");
		//获取文件名
		String fileName = file.getOriginalFilename();
		//文件重命名
		fileName = UUID.randomUUID() + fileName.substring(fileName.indexOf("."), fileName.length());
		File targetFile = new File(path, fileName);
		//是否存在
		if(!targetFile.exists()){
			//创建目录
			targetFile.mkdirs();
		}
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String url = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/notebook/upload/"+username+"/images/" + fileName;
		return url;
	}

    /**
     * 上传MarkDown文件
     * 
     * @param fileName
     * @param data
     * @return
     */
    public static boolean uploadMarkDownFile(String username, String fileName, String data) {
    	File file = new File("D:/notebook/upload/"+username+"/markdown/"+fileName+".md");
		try {
			//是否存在
			if(!file.exists()){
				//创建文件
				file.createNewFile();
			}
			FileOutputStream fileOutputStream = null;
			fileOutputStream = new FileOutputStream(file);
			return uploadOutputStream(fileOutputStream, data);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
    
    /**
     * 上传MarkDown文件输出流处理方法
     * 
     * @param outputStream
     * @param data
     * @return
     */
    public static boolean uploadOutputStream(OutputStream outputStream, String data) {
		boolean isSucess = false;
		OutputStreamWriter outputStreamWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			outputStreamWriter = new OutputStreamWriter(outputStream);
			bufferedWriter = new BufferedWriter(outputStreamWriter);
			
			bufferedWriter.append(data);
			isSucess = true;
		} catch (Exception e) {
			e.printStackTrace();
			isSucess = false;
		} finally {
			if (bufferedWriter != null) {
				try {
					bufferedWriter.close();
					bufferedWriter = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStreamWriter != null) {
				try {
					outputStreamWriter.close();
					outputStreamWriter = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
					outputStream = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return isSucess;
	}
	
	/**
	 * 获取文件数据
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getMarkdownFile(String username, String fileName) {
		File file = new File("D:/notebook/upload/"+username+"/markdown/"+fileName+".md");
        if(!file.isFile()) {
        	return null;
        }
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
        	inputStreamReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
        	bufferedReader = new BufferedReader(inputStreamReader);
        	String text = "";
        	String line;
			//包含该行内容的字符串，不包含任何行终止符，如果已到达流末尾，则返回 null
        	while((line = bufferedReader.readLine()) != null) {
                text = text + line + "\r\n";
            }
        	return text;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
					bufferedReader = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
					inputStreamReader = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static boolean deleteMarkdownFile(String username, String fileName) {
		File file = new File("D:/notebook/upload/"+username+"/markdown/"+fileName+".md");
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}



}