package com.gemalto.tsmRnD_Validation;



public class Kshitij {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		ProcessBuilder pb = new ProcessBuilder("/usr/bin/ssh", "tsm@itsm2", "touch /product/yoyo.txt"); 
		//ProcessBuilder pb = new ProcessBuilder("/usr/bin/ssh", "user@host", "command and args"); 
	}

}
