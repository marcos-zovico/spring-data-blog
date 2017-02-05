package com.msouza.blog.doc;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import io.github.swagger2markup.Swagger2MarkupConverter;

public class Swagger2Markup {
	
	@Test
	public void convertMemberSwaggerToMarkdown() throws IOException {
		
		URL remoteSwaggerFile = new URL("http://petstore.swagger.io/v2/swagger.json");
		Path outputDirectory = Paths.get("/home/marcos/Documents/dev/git/projetos pessoais/spring-data-blog/src/doc");

		Swagger2MarkupConverter.from(remoteSwaggerFile) 
		        .build() 
		        .toFolder(outputDirectory);
	}

}
