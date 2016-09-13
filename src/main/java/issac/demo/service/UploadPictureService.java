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

}
