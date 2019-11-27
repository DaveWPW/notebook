package common.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

public class FileUtils {

	/**
	 * 上传图片文件
	 *
	 * @param username
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
    public static String uploadImageFile(String username, MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//获取文件名
		String fileName = file.getOriginalFilename();
		//使用UUID重命名
		fileName = UUID.randomUUID() + fileName.substring(fileName.indexOf("."), fileName.length());
		//文件存放的路径
		File targetFile = new File("D:/notebook/upload/"+username+"/images/"+fileName);
		//是否存在
		if(!targetFile.exists()){
			//创建目录
			targetFile.mkdirs();
		}
		try {
			//保存
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//全路径（协议类型://域名/项目名/命名空间/文件名）
		String url = request.getScheme()+"://"+request.getServerName()+request.getContextPath()+"/markdown/upload/"+username+"/"+fileName;
		return url;
	}

	/**
	 * 获取图片文件
	 *
	 * @param username
	 * @param fileName
	 * @param response
	 */
	public static void getImageFile(String username, String fileName, HttpServletResponse response) {
		String filePath = "D:/notebook/upload/"+username+"/images/"+fileName;
		File file = new File(filePath);
		if(file.exists()){
			FileInputStream fileInputStream = null;
			OutputStream outputStream = null;
			try {
				fileInputStream = new FileInputStream(file);
				outputStream = response.getOutputStream();
				int count = 0;
				byte[] buffer = new byte[1024 * 8];
				while ((count = fileInputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, count);
					outputStream.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					fileInputStream.close();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	/**
	 * 上传 MarkDown 文件
	 *
	 * @param username
	 * @param fileName
	 * @param data
	 * @return
	 */
    public static boolean uploadMarkDownFile(String username, String fileName, String data) {
		File mkdir = new File("D:/notebook/upload/"+username+"/markdown/");
		//是否存在
		if(!mkdir.exists()){
			mkdir.mkdirs();
		}
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
	 * 上传 MarkDown 文件输出流处理方法
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
	 * 获取 Markdown 文件内容
	 *
	 * @param username
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

	/**
	 * 删除 Markdown 文件
	 *
	 * @param username
	 * @param fileName
	 * @return
	 */
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