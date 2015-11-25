package com.msouza.blog.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.msouza.blog.entity.Avatar;
import com.msouza.blog.repository.AvatarRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class AvatarService {
	
	@Autowired
	private AvatarRepository repository;
	
	@Transactional
	public void saveOrUpdate(Avatar avatar){
		repository.save(avatar);
	}
	
	public Avatar getAvatarByUpload(MultipartFile file){
		Avatar avatar = new Avatar();
		
		if (file != null && file.getSize()> 0) {
			try {
				avatar.setTitulo(file.getOriginalFilename());
				avatar.setTipo(file.getContentType());
				avatar.setAvatar(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return avatar;
	}

}
