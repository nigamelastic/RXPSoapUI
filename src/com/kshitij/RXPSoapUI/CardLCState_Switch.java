package com.gemalto.tsmRnD_Validation;


public class CardLCState_Switch {
	
	
	
	
	public static String switchingFunc(String state){
		String x=null;
		switch(state){
		case "0x03": x="INSTALLED";
		break;
		case "0x80": x="LOCKED";
		break;
		case "0x07": x="SELECTABLE";
		break;
		case "0x7F": x="CARD_LOCKED";
		break;
		case "0x01": x="OP_READY";
		break;
		case "0x0F": x="SECURED";
		break;
		case "0xFF": x="TERMINATED";
		break;
		
		}
				
				
		return x;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub
		
		
		System.out.println(switchingFunc("0x03"));

	}

}
