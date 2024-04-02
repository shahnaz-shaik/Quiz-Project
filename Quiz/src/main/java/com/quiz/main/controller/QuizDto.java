package com.quiz.main.controller;

import lombok.Data;

@Data
public class QuizDto {
 String subtopic;
 int numQueH;
 int numQueM;
 int numQueE;
 String title;
public String getSubtopic() {
	return subtopic;
}
public void setSubtopic(String subtopic) {
	this.subtopic = subtopic;
}
public int getNumQueH() {
	return numQueH;
}
public void setNumQueH(int numQueH) {
	this.numQueH = numQueH;
}
public int getNumQueM() {
	return numQueM;
}
public void setNumQueM(int numQueM) {
	this.numQueM = numQueM;
}
public int getNumQueE() {
	return numQueE;
}
public void setNumQueE(int numQueE) {
	this.numQueE = numQueE;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}


}
