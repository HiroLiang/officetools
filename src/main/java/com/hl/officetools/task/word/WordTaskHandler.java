package com.hl.officetools.task.word;

import java.io.File;
import java.util.UUID;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class WordTaskHandler {
	
	public String outputPath = "/" + UUID.randomUUID() + ".docx" ;
	
	private XWPFDocument document;
	
	private File file;
	
	public WordTaskHandler(XWPFDocument document, String outputPath) {
		super();
		this.outputPath = outputPath;
		this.document = document;
	}

	public WordTaskHandler(String path, String outputPath) {
		super();
		this.outputPath = outputPath;
		this.document = document;
	}

	public WordTaskHandler render() {
		
		return this;
	}

}
