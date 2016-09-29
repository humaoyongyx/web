package issac.demo.service;

import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import issac.demo.utils.CommonUtils;

@Service
public class UploadPictureService {

	@Value("#{propertiesReader[uploadDir]}")
	private String uploadDir;

	@Value("#{propertiesReader[picBaseURL]}")
	private String picBaseURL;

	@PostConstruct
	public void init() {
		File _uploadDir = new File(uploadDir);
		if (!_uploadDir.exists()) {
			_uploadDir.mkdirs();
		}
	}

	public String upload(MultipartFile file) {
		return upload(file, file.getOriginalFilename());
	}

	public String upload(MultipartFile file, String newFileName) {
		File piciture = new File(CommonUtils.normalizePath(uploadDir) + newFileName);
		/*	if (piciture.exists()) {
				return CommonUtils.normalizePath(picBaseURL) + newFileName;
			}*/
		try {
			file.transferTo(piciture);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CommonUtils.normalizePath(picBaseURL) + newFileName;
	}

	public boolean checkFileSize(MultipartFile file) {
		long size = file.getSize();
		long sizeK = (long) Math.ceil(size / 1024.0);
		System.out.println(sizeK);
		if (sizeK > 1536) {
			return false;
		}
		return true;
	}

	public void removeFile(String fileName) {
		if (CommonUtils.isNotEmpty(fileName)) {
			fileName = fileName.replace(CommonUtils.normalizePath(picBaseURL), "");
			String removeFileName = CommonUtils.normalizePath(uploadDir) + fileName;
			System.out.println(removeFileName);
			File file = new File(removeFileName);
			if (file.exists()) {
				file.delete();
			}
		}


	}

}
